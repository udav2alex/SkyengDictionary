package ru.gressor.skyengdictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.gressor.skyengdictionary.views.HistoryFragment
import ru.gressor.skyengdictionary.views.SearchFragment
import ru.gressor.skyengdictionary.views.SettingsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_container, SearchFragment())
                .commitNow()
        }

        findViewById<BottomNavigationView>(R.id.main_bottom_nav)
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.mi_search -> showFragment(SearchFragment())
                    R.id.mi_history -> showFragment(HistoryFragment())
                    R.id.mi_settings -> showFragment(SettingsFragment())
                    else -> throw IllegalArgumentException("Unknown menu item!")
                }

                return@setOnNavigationItemSelectedListener true
            }
    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}