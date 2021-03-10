package ru.gressor.skyengdictionary.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gressor.skyengdictionary.R
import ru.gressor.skyengdictionary.databinding.FragmentSearchRvItemBinding
import ru.gressor.core.entities.DictWord

class SearchListAdapter(
    private var wordList: List<DictWord>,
    private var onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<SearchListAdapter.WordHolder>() {

    fun setData(wordList: List<DictWord>) {
        this.wordList = wordList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder =
        WordHolder(
            FragmentSearchRvItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = if (wordList.isNullOrEmpty()) 1 else wordList.size

    inner class WordHolder(
        private val binding: FragmentSearchRvItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) = with(binding) {
            if (wordList.isNullOrEmpty()) {
                tvWord.text = ""
                tvWordId.text = ""
                tvTranslation.text = binding.root.context.getString(R.string.no_translations)
                return
            }

            wordList[position].let { word ->
                itemView.setOnClickListener {
                    onItemClickListener.onItemClick(word)
                }

                tvWord.text = word.text
                tvWordId.text = word.id.toString()

                tvTranslation.text =
                    when {
                        word.meanings.isNullOrEmpty() -> {
                            binding.root.context.getString(R.string.no_translations)
                        }
                        word.meanings.size == 1 -> {
                            word.meanings[0].translation?.text
                        }
                        else -> {
                            word.meanings[0].translation?.text + " " +
                                    binding.root.context.resources.getQuantityString(
                                        R.plurals.translation_variants,
                                        word.meanings.lastIndex,
                                        word.meanings.lastIndex
                                    )
                        }
                    }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(word: DictWord)
    }
}