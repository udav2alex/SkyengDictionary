package ru.gressor.skyengdictionary.views

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gressor.skyengdictionary.R
import ru.gressor.skyengdictionary.databinding.FragmentWordRvItemBinding
import ru.gressor.core.entities.Meaning2

class WordListAdapter(
    private val meanings: List<Meaning2>
) : RecyclerView.Adapter<WordListAdapter.TranslationHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationHolder =
        TranslationHolder(
            FragmentWordRvItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: TranslationHolder, position: Int) {
        holder.bind(meanings[position])
    }

    override fun getItemCount() = meanings.size

    inner class TranslationHolder(
        private val binding: FragmentWordRvItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(meaning: Meaning2) {
            with(binding) {
                meaning.let {
                    @SuppressLint("SetTextI18n")
                    tvTranscription.text = "[ ${it.transcription} ]"
                    tvTranslation.text = it.translation?.text

                    Glide.with(root)
                        .load("https:${it.imageUrl}")
                        .placeholder(R.drawable.ic_no_photo_vector)
                        .error(R.drawable.ic_load_error_vector)
                        .centerCrop()
                        .into(ivImage)
                }
            }
        }
    }
}