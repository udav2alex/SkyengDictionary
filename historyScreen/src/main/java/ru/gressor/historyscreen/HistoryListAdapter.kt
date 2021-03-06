package ru.gressor.historyscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gressor.core.entities.HistoryItem
import ru.gressor.historyscreen.databinding.FragmentHistoryRvItemBinding

class HistoryListAdapter(
    private var itemsList: List<HistoryItem>,
    private val clickListener: ClickListener
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
            itemView.setOnClickListener {
                clickListener.onClick(item)
            }

            with(binding) {
                tvHistoryItem.text = item.word
            }
        }
    }

    interface ClickListener {
        fun onClick(historyItem: HistoryItem)
    }
}