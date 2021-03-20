package ru.gressor.skyengdictionary.views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import ru.gressor.skyengdictionary.databinding.FragmentSettingsBinding
import java.lang.RuntimeException
import kotlin.reflect.KProperty

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSettingsBinding.inflate(inflater, container, false)
        .also {
            binding = it
            preferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureThemeChips()
    }

    private fun configureThemeChips() {
        var checkedChipId by Prefs(preferences, 0)
        (binding.cgTheme.getChildAt(checkedChipId) as Chip).isChecked = true

        binding.cgTheme.setOnCheckedChangeListener { _, checkedId ->
            val chip: Chip = binding.cgTheme.findViewById(checkedId)
            checkedChipId = binding.cgTheme.indexOfChild(chip)
        }
    }
}

class Prefs<T>(
    private val preferences: SharedPreferences,
    private val defaultValue: T
) {
    @Suppress("UNCHECKED_CAST")
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val name = property.name

        return when (defaultValue) {
            is Boolean -> preferences.getBoolean(name, defaultValue) as? T ?: defaultValue
            is Int -> preferences.getInt(name, defaultValue) as? T ?: defaultValue
            is Long -> preferences.getLong(name, defaultValue) as? T ?: defaultValue
            is Float -> preferences.getFloat(name, defaultValue) as? T ?: defaultValue
            is String -> preferences.getString(name, defaultValue) as? T ?: defaultValue
            is Set<*> -> preferences.getStringSet(name, defaultValue as Set<String>) as? T
                ?: defaultValue
            else -> throw RuntimeException("$defaultValue can't be stored in SharedPreferences :(")
        }
    }

    @Suppress("UNCHECKED_CAST")
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        val name = property.name

        preferences.edit().run {
            when (value) {
                is Boolean -> putBoolean(name, value)
                is Int -> putInt(name, value)
                is Long -> putLong(name, value)
                is Float -> putFloat(name, value)
                is String -> putString(name, value)
                is Set<*> -> putStringSet(name, value as? Set<String>)
                else -> throw RuntimeException("$value can't be stored in SharedPreferences :(")
            }
            apply()
        }
    }
}