package com.ankiinsight.app.presentation.conflict

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ankiinsight.app.R
import com.ankiinsight.app.databinding.ItemConflictBinding
import com.ankiinsight.app.domain.model.ConflictResult

class ConflictAdapter(
    private val onTagClick: (ConflictResult) -> Unit,
    private val onKeepBothClick: (ConflictResult) -> Unit
) : ListAdapter<ConflictResult, ConflictAdapter.ConflictViewHolder>(ConflictDiffCallback()) {

    inner class ConflictViewHolder(private val binding: ItemConflictBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(conflict: ConflictResult) {
            // Type badge
            binding.tvConflictType.text = conflict.type.uppercase()
            binding.tvConflictType.setBackgroundResource(
                if (conflict.type == "contradiction") R.drawable.bg_badge_contradiction
                else R.drawable.bg_badge_duplicate
            )

            binding.tvDeckInfo.text = "Found in 2 different decks"
            binding.tvConflictExplanation.text = conflict.explanation

            // Card A
            binding.tvCardADeck.text = conflict.cardA.deckName.uppercase()
            binding.tvCardAQuestion.text = conflict.cardA.front
            binding.tvCardAAnswer.text = conflict.cardA.back

            // Card B
            binding.tvCardBDeck.text = conflict.cardB.deckName.uppercase()
            binding.tvCardBQuestion.text = conflict.cardB.front
            binding.tvCardBAnswer.text = conflict.cardB.back

            binding.btnTagConflicted.setOnClickListener { onTagClick(conflict) }
            binding.btnKeepBoth.setOnClickListener { onKeepBothClick(conflict) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConflictViewHolder {
        val binding = ItemConflictBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConflictViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConflictViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ConflictDiffCallback : DiffUtil.ItemCallback<ConflictResult>() {
        override fun areItemsTheSame(o: ConflictResult, n: ConflictResult) = o.id == n.id
        override fun areContentsTheSame(o: ConflictResult, n: ConflictResult) = o == n
    }
}
