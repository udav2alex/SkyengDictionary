package ru.gressor.skyengdictionary.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gressor.skyengdictionary.databinding.FragmentWordBinding
import ru.gressor.core.entities.DictWord

class WordFragment : Fragment() {
    private lateinit var binding: FragmentWordBinding
    private lateinit var adapter: WordListAdapter
    private var word: DictWord? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentWordBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        word = arguments?.getParcelable(TAG_WORD)

        binding.tvWordCaption.text = word?.text

        word?.meanings?.let {
            adapter = WordListAdapter(it)
            binding.rvTranslations.layoutManager = LinearLayoutManager(context)
            binding.rvTranslations.adapter = adapter
        }
    }

    companion object {
        private const val TAG_WORD = "ru.gressor.skyengdictionary.views.WordFragment.TAG_WORD"

        fun getInstance(word: DictWord): WordFragment {
            val fragment = WordFragment()
            val arguments = Bundle()

            arguments.putParcelable(TAG_WORD, word)
            fragment.arguments = arguments

            return fragment
        }
    }
}