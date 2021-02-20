package ru.gressor.skyengdictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gressor.skyengdictionary.views.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, MainFragment())
            .commitNow()
    }
}