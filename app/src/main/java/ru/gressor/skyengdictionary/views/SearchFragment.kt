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
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import ru.gressor.core.di.NAME_SEARCH
import ru.gressor.core.entities.SearchData
import ru.gressor.core.entities.DictWord
import ru.gressor.skyengdictionary.MainActivity
import ru.gressor.skyengdictionary.databinding.FragmentSearchBinding
import ru.gressor.skyengdictionary.di.injectDependencies
import ru.gressor.skyengdictionary.viewmodels.SearchViewModel

class SearchFragment : Fragment(),
    SearchListAdapter.OnItemClickListener, TextView.OnEditorActionListener {
    private lateinit var binding: FragmentSearchBinding
    private var adapter: SearchListAdapter? = null

    init { injectDependencies() }
    private val viewModel: SearchViewModel by fragmentScope().inject()

    private var searchString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            searchString = arguments?.getString(BUNDLE_TAG_SEARCH_STRING)
            searchString?.let {
                viewModel.getData(it, true)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSearchBinding.inflate(inflater, container, false)
        .also {
            binding = it
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvWords.layoutManager = LinearLayoutManager(context)

        viewModel.liveData.observe(viewLifecycleOwner) { renderData(it) }

        binding.etWord.setOnEditorActionListener(this)
        binding.ilWordContainer.setStartIconOnClickListener {
            onEditorAction(null, EditorInfo.IME_ACTION_SEARCH, null)
        }

        if (savedInstanceState == null && searchString != null) {
            binding.etWord.setText(searchString)
        }
    }

    fun searchIt(search: String) {
        binding.etWord.setText(search)
        searchString = search
        viewModel.getData(search, true)
    }

    override fun onItemClick(word: DictWord) {
        val activity = requireActivity()
        if (activity is MainActivity) {
            activity.showFragment(WordFragment.getInstance(word))
        }
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
        return true
    }

    private fun renderData(data: SearchData) {
        when (data) {
            is SearchData.Empty -> showEmpty()
            is SearchData.Success -> showWords(data.wordList)
            is SearchData.Loading -> showLoading(data.progress)
            is SearchData.Error -> showError(data.error)
        }
    }

    private fun showWords(wordList: List<DictWord>) {
        makeVisible(recycler = true)

        if (adapter == null) {
            adapter = SearchListAdapter(wordList, this)
            binding.rvWords.adapter = adapter
        } else {
            if (binding.rvWords.adapter == null) {
                binding.rvWords.adapter = adapter
            }
            adapter!!.setData(wordList)
        }
    }

    private fun showEmpty() {
        makeVisible(empty = true)
    }

    private fun showLoading(progress: Int?) {
        makeVisible(loading = true)

        if (progress == null) {
            binding.stateContainers.pbInfiniteProgress.visibility = View.VISIBLE
            binding.stateContainers.pbMeasuredProgress.visibility = View.GONE
        } else {
            binding.stateContainers.pbInfiniteProgress.visibility = View.GONE
            binding.stateContainers.pbMeasuredProgress.visibility = View.VISIBLE
            binding.stateContainers.pbMeasuredProgress.progress = progress
        }
    }

    private fun showError(error: Throwable) {
        makeVisible(error = true)

        binding.stateContainers.tvErrorMessage.text = "$error"
    }

    private fun makeVisible(
        empty: Boolean = false,
        recycler: Boolean = false,
        loading: Boolean = false,
        error: Boolean = false
    ) {
        binding.rvWords.visibility = if (recycler) View.VISIBLE else View.GONE
        binding.stateContainers.containerEmpty.visibility = if (empty) View.VISIBLE else View.GONE
        binding.stateContainers.containerLoading.visibility =
            if (loading) View.VISIBLE else View.GONE
        binding.stateContainers.containerError.visibility = if (error) View.VISIBLE else View.GONE
    }

    companion object {
        private const val BUNDLE_TAG_SEARCH_STRING =
            "ru.gressor.skyengdictionary.views.SearchFragment.search"

        fun getSearchingInstance(search: String): SearchFragment {
            val fragment = SearchFragment()
            val bundle = Bundle()

            bundle.putString(BUNDLE_TAG_SEARCH_STRING, search)
            fragment.arguments = bundle

            return fragment
        }
    }
}