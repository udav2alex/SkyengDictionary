package ru.gressor.skyengdictionary.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.databinding.FragmentMainBinding
import ru.gressor.skyengdictionary.entities.DictData
import ru.gressor.skyengdictionary.entities.DictWord
import ru.gressor.skyengdictionary.presenters.MainPresenter

class MainFragment: BaseView(), MainContract.View,
    WordListAdapter.OnItemClickListener {

    private lateinit var binding: FragmentMainBinding
    private var adapter: WordListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(inflater, container, false)
        .also {
            binding = it

            binding.etWord.setOnEditorActionListener { _, actionId, event ->
                    if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER))
                        || (actionId == EditorInfo.IME_ACTION_DONE)
                    ) {
                        val text = binding.etWord.text.toString()
                        presenter.getData(text, true)
                        return@setOnEditorActionListener true
                    }
                    return@setOnEditorActionListener false
                }
        }.root

    override fun createPresenter(): MainContract.Presenter {
        return MainPresenter()
    }

    override fun renderData(data: DictData) {
        when (data) {
            is DictData.Success -> showWords(data.wordList)
            is DictData.Loading -> showLoading(data.progress)
            is DictData.Error -> showError(data.error)
        }
    }

    override fun onItemClick(word: DictWord) {

    }

    private fun showWords(wordList: List<DictWord>) {
        if (adapter == null) {
            adapter = WordListAdapter(wordList, this)
            binding.rvWords.layoutManager = LinearLayoutManager(context)
            binding.rvWords.adapter = adapter
        } else {
            adapter!!.setData(wordList)
        }
    }

    private fun showLoading(progress: Int) {

    }

    private fun showError(error: Throwable) {

    }
}