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
import ru.gressor.utils.Prefs
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

            activity?.recreate()
        }
    }
}