package com.ankiinsight.app.presentation.decks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ankiinsight.app.R
import com.ankiinsight.app.databinding.ItemDeckBinding

class DeckAdapter(private val onDeckClick: (DeckItem) -> Unit) :
    ListAdapter<DeckItem, DeckAdapter.DeckViewHolder>(DeckDiffCallback()) {

    inner class DeckViewHolder(private val binding: ItemDeckBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DeckItem) {
            binding.tvDeckName.text = item.deckName
            val failedText = if (item.failedCards > 0) "${item.failedCards} failed today"
            else "No failures"
            binding.tvDeckSubtitle.text = "${item.totalCards} cards  •  $failedText"

            // Avatar letter
            binding.tvAvatarLetter.text = item.deckName.firstOrNull()?.uppercase() ?: "?"

            // Failed badge
            if (item.failedCards > 0) {
                binding.tvFailedBadge.visibility = android.view.View.VISIBLE
                binding.tvFailedBadge.text = item.failedCards.toString()
            } else {
                binding.tvFailedBadge.visibility = android.view.View.GONE
            }

            // Different avatar background colors based on first letter
            val colors = listOf(
                R.color.avatar_teal, R.color.avatar_amber, R.color.avatar_purple,
                R.color.avatar_red, R.color.avatar_green
            )
            val colorRes = colors[(item.deckName.firstOrNull()?.code ?: 0) % colors.size]
            binding.ivAvatar.setBackgroundResource(R.drawable.bg_avatar)
            binding.ivAvatar.backgroundTintList =
                android.content.res.ColorStateList.valueOf(
                    androidx.core.content.ContextCompat.getColor(binding.root.context, colorRes)
                )

            binding.root.setOnClickListener { onDeckClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckViewHolder {
        val binding = ItemDeckBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeckViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeckViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DeckDiffCallback : DiffUtil.ItemCallback<DeckItem>() {
        override fun areItemsTheSame(oldItem: DeckItem, newItem: DeckItem) =
            oldItem.deckId == newItem.deckId
        override fun areContentsTheSame(oldItem: DeckItem, newItem: DeckItem) =
            oldItem == newItem
    }
}
