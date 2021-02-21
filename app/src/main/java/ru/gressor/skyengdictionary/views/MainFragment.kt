package ru.gressor.skyengdictionary.views

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.gressor.skyengdictionary.databinding.FragmentMainBinding
import ru.gressor.skyengdictionary.entities.DictData
import ru.gressor.skyengdictionary.entities.DictWord
import ru.gressor.skyengdictionary.viewmodels.MainViewModel

class MainFragment : Fragment(),
    WordListAdapter.OnItemClickListener, TextView.OnEditorActionListener {

    private lateinit var binding: FragmentMainBinding
    private var adapter: WordListAdapter? = null

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(inflater, container, false)
        .also {
            binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        binding.etWord.setOnEditorActionListener(this)
        binding.ilWordContainer.setStartIconOnClickListener {
            onEditorAction(null, EditorInfo.IME_ACTION_SEARCH, null)
        }
    }

    private fun initViewModel() {
        // TODO: как правильно сохранять модель для фрагмента при ConfigChange?
        val model: MainViewModel by requireActivity().viewModel()
        viewModel = model

        viewModel.liveData.observe(viewLifecycleOwner) { renderData(it) }
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (
            (event != null
                    && event.keyCode == KeyEvent.KEYCODE_ENTER
                    && event.action == KeyEvent.ACTION_DOWN)
            || actionId == EditorInfo.IME_ACTION_SEARCH
        ) {
            val text = binding.etWord.text.toString()
            viewModel.getData(text, true)
            return true
        }
        return false
    }

    private fun renderData(data: DictData) {
        when (data) {
            is DictData.Empty -> showEmpty()
            is DictData.Success -> showWords(data.wordList)
            is DictData.Loading -> showLoading(data.progress)
            is DictData.Error -> showError(data.error)
        }
    }

    override fun onItemClick(word: DictWord) {

    }

    private fun showEmpty() {
        makeVisible(empty = true)
    }

    private fun showWords(wordList: List<DictWord>) {
        makeVisible(recycler = true)

        if (adapter == null) {
            adapter = WordListAdapter(wordList, this)
            binding.rvWords.layoutManager = LinearLayoutManager(context)
            binding.rvWords.adapter = adapter
        } else {
            adapter!!.setData(wordList)
        }
    }

    private fun showLoading(progress: Int?) {
        makeVisible(loading = true)

        if (progress == null) {
            binding.pbInfiniteProgress.visibility = View.VISIBLE
            binding.pbMeasuredProgress.visibility = View.GONE
        } else {
            binding.pbInfiniteProgress.visibility = View.GONE
            binding.pbMeasuredProgress.visibility = View.VISIBLE
            binding.pbMeasuredProgress.progress = progress
        }
    }

    private fun showError(error: Throwable) {
        makeVisible(error = true)

        binding.tvErrorMessage.text = error.message
    }

    private fun makeVisible(
        empty: Boolean = false,
        recycler: Boolean = false,
        loading: Boolean = false,
        error: Boolean = false
    ) {
        binding.rvWords.visibility = if (recycler) View.VISIBLE else View.GONE
        binding.containerEmpty.visibility = if (empty) View.VISIBLE else View.GONE
        binding.containerLoading.visibility = if (loading) View.VISIBLE else View.GONE
        binding.containerError.visibility = if (error) View.VISIBLE else View.GONE
    }
}