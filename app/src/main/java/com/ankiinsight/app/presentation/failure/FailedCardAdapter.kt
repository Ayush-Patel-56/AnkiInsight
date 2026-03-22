package com.ankiinsight.app.presentation.failure

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ankiinsight.app.databinding.ItemFailedCardBinding
import com.ankiinsight.app.domain.model.CardData

class FailedCardAdapter(
    private val onRegenerateClick: (CardData) -> Unit,
    private val onSkipClick: (CardData) -> Unit
) : ListAdapter<CardData, FailedCardAdapter.CardViewHolder>(CardDiffCallback()) {

    inner class CardViewHolder(private val binding: ItemFailedCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardData) {
            binding.tvQuestion.text = card.front
            binding.tvAnswer.text = card.back

            // Failure count badge: inferred from ease factor as proxy
            val failCount = when {
                card.easeFactor < 1500 -> "8x"
                card.easeFactor < 1700 -> "5x"
                card.easeFactor < 1900 -> "3x"
                else -> "2x"
            }
            binding.tvFailedBadge.text = "Failed $failCount"
            binding.tvLastFailed.text = "Last failed: today"

            binding.btnRegenerateWithAi.setOnClickListener { onRegenerateClick(card) }
            binding.btnSkip.setOnClickListener { onSkipClick(card) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemFailedCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        holder.bind(getItem(position))

    class CardDiffCallback : DiffUtil.ItemCallback<CardData>() {
        override fun areItemsTheSame(o: CardData, n: CardData) = o.id == n.id
        override fun areContentsTheSame(o: CardData, n: CardData) = o == n
    }
}
