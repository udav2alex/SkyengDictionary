package ru.gressor.historyscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import ru.gressor.core.BaseContract
import ru.gressor.core.di.NAME_HISTORY
import ru.gressor.core.entities.HistoryItem
import ru.gressor.core.entities.HistoryData
import ru.gressor.historyscreen.databinding.FragmentHistoryBinding
import java.lang.RuntimeException

class HistoryFragment : Fragment(), HistoryListAdapter.ClickListener {

    private lateinit var binding: FragmentHistoryBinding
    private var adapter: HistoryListAdapter? = null

    private val viewModel: HistoryViewModel by viewModel(named(NAME_HISTORY))

    private lateinit var searchRunner: BaseContract.SearchRunner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHistoryBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.liveData.observe(viewLifecycleOwner) { renderData(it) }
        binding.rvHistory.layoutManager = LinearLayoutManager(context)

        if (requireActivity() is BaseContract.SearchRunner) {
            searchRunner = requireActivity() as BaseContract.SearchRunner
        } else {
            throw RuntimeException("Activity can't run search!")
        }
    }

    override fun onClick(historyItem: HistoryItem) {
        searchRunner.runSearch(historyItem.word)
    }

    private fun renderData(data: HistoryData) {
        when (data) {
            is HistoryData.Empty -> showEmpty()
            is HistoryData.Success -> showWords(data.itemsList)
            is HistoryData.Loading -> showLoading(data.progress)
            is HistoryData.Error -> showError(data.error)
        }
    }

    private fun showWords(wordList: List<HistoryItem>) {
        makeVisible(recycler = true)

        if (adapter == null) {
            adapter = HistoryListAdapter(wordList, this)
            binding.rvHistory.adapter = adapter
        } else {
            if (binding.rvHistory.adapter == null) {
                binding.rvHistory.adapter = adapter
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
        binding.rvHistory.visibility = if (recycler) View.VISIBLE else View.GONE
        binding.stateContainers.containerEmpty.visibility = if (empty) View.VISIBLE else View.GONE
        binding.stateContainers.containerLoading.visibility =
            if (loading) View.VISIBLE else View.GONE
        binding.stateContainers.containerError.visibility = if (error) View.VISIBLE else View.GONE
    }
}