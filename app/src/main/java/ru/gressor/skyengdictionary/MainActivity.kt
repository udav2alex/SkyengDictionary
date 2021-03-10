package ru.gressor.skyengdictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.gressor.core.BaseContract
import ru.gressor.historyscreen.HistoryFragment
import ru.gressor.skyengdictionary.views.SearchFragment
import ru.gressor.skyengdictionary.views.SettingsFragment
import java.lang.RuntimeException

class MainActivity : AppCompatActivity(), BaseContract.SearchRunner {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    R.id.navigation_item_history -> showFragment(FRAGMENT_TAG_HISTORY)
                    R.id.navigation_item_settings -> showFragment(FRAGMENT_TAG_SETTINGS)
                    else -> throw IllegalArgumentException("Unknown menu item!")
                }

                return@setOnNavigationItemSelectedListener true
            }
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
                FRAGMENT_TAG_HISTORY -> HistoryFragment()
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
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {
        const val FRAGMENT_TAG_SEARCH = "FRAGMENT_TAG_SEARCH"
        const val FRAGMENT_TAG_HISTORY = "FRAGMENT_TAG_HISTORY"
        const val FRAGMENT_TAG_SETTINGS = "FRAGMENT_TAG_SETTINGS"
    }
}