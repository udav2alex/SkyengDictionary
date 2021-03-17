package ru.gressor.skyengdictionary

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import ru.gressor.core.BaseContract
import ru.gressor.skyengdictionary.views.SearchFragment
import ru.gressor.skyengdictionary.views.SettingsFragment
import ru.gressor.utils.ConnectivityLiveData
import ru.gressor.utils.Prefs
import java.lang.RuntimeException

class MainActivity : AppCompatActivity(), BaseContract.SearchRunner {
    private lateinit var splitInstallManager: SplitInstallManager

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setCurrentTheme()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, SearchFragment(), FRAGMENT_TAG_SEARCH)
                .commitNow()
        }

        bottomNavigationView = findViewById(R.id.main_bottom_nav)
        bottomNavigationView
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_item_search -> showFragment(FRAGMENT_TAG_SEARCH)
                    R.id.navigation_item_history -> showFragment(FRAGMENT_TAG_HISTORY_FEATURE)
                    R.id.navigation_item_settings -> showFragment(FRAGMENT_TAG_SETTINGS)
                    else -> throw IllegalArgumentException("Unknown menu item!")
                }

                return@setOnNavigationItemSelectedListener true
            }
    }

    private fun setCurrentTheme() {
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val checkedChipId by Prefs(sharedPreferences, 0)

        val currentTheme = when(checkedChipId) {
            0 -> R.style.Theme_SkyengDictionary
            1 -> R.style.Theme_SkyengDictionary_Day
            2 -> R.style.Theme_SkyengDictionary_Night
            else -> R.style.Theme_SkyengDictionary // fallback to default
        }

        setTheme(currentTheme)
    }

    override fun runSearch(search: String) {
        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG_SEARCH)
        if (fragment == null) {
            fragment = SearchFragment()
        }

        fragment as SearchFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment, FRAGMENT_TAG_SEARCH)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()

        fragment.searchIt(search)

        bottomNavigationView.menu.findItem(R.id.navigation_item_search)?.isChecked = true
    }

    private fun showFragment(tag: String) {
        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            fragment = when (tag) {
                FRAGMENT_TAG_SEARCH -> SearchFragment()
                FRAGMENT_TAG_HISTORY_FEATURE -> {
                    startInstallHistoryFeatureFragment()
                    return
                }
                FRAGMENT_TAG_SETTINGS -> SettingsFragment()
                else -> throw RuntimeException("Unknown tag selected!")
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        subscribeConnectivityStatus()
    }

    private fun subscribeConnectivityStatus() {
        ConnectivityLiveData(this)
            .observe(this) {
                if (!it) {
                    Snackbar.make(bottomNavigationView, "Offline!", Snackbar.LENGTH_LONG)
                        .apply {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                setAction(R.string.network_setup) {
                                    startActivityForResult(
                                        Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY), 1111
                                    )
                                }
                            }
                        }
                        .show()
                } else {
                    Snackbar.make(bottomNavigationView, "Online!", Snackbar.LENGTH_LONG)
                        .show()
                }
            }
    }

    private fun startInstallHistoryFeatureFragment() {
        splitInstallManager = SplitInstallManagerFactory.create(applicationContext)

        val request = SplitInstallRequest
            .newBuilder()
            .addModule(HISTORY_FEATURE_NAME)
            .build()

        splitInstallManager.startInstall(request)
            .addOnSuccessListener {
                val fragment = Class.forName(HISTORY_FEATURE_PATH).newInstance() as? Fragment
                if (fragment is Fragment) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_container, fragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    this@MainActivity,
                    "Couldn't download History feature: $exception",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {
        const val FRAGMENT_TAG_SEARCH = "FRAGMENT_TAG_SEARCH"
        const val FRAGMENT_TAG_HISTORY_FEATURE = "FRAGMENT_TAG_HISTORY_FEATURE"
        const val FRAGMENT_TAG_SETTINGS = "FRAGMENT_TAG_SETTINGS"

        const val HISTORY_FEATURE_NAME = "historyScreen"
        const val HISTORY_FEATURE_PATH = "ru.gressor.historyscreen.HistoryFragment"
    }
}