package ru.gressor.skyengdictionary.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gressor.skyengdictionary.data.local.HistoryItem
import ru.gressor.skyengdictionary.databinding.FragmentHistoryRvItemBinding

class HistoryListAdapter(
    private var itemsList: List<HistoryItem>
) : RecyclerView.Adapter<HistoryListAdapter.HistoryItemHolder>() {

    fun setData(itemsList: List<HistoryItem>) {
        this.itemsList = itemsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HistoryItemHolder(
            FragmentHistoryRvItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: HistoryItemHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    override fun getItemCount() = itemsList.size

    inner class HistoryItemHolder(
        private val binding: FragmentHistoryRvItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HistoryItem) {
            with(binding) {
                tvHistoryItem.text = item.word
            }
        }
    }
}