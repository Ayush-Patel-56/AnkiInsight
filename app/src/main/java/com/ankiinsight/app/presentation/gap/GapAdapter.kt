package com.ankiinsight.app.presentation.gap

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ankiinsight.app.databinding.ItemGapBinding
import com.ankiinsight.app.domain.model.GapResult
import com.google.android.material.chip.Chip

class GapAdapter(private val onGenerateClick: (GapResult) -> Unit) :
    ListAdapter<GapResult, GapAdapter.GapViewHolder>(GapDiffCallback()) {

    inner class GapViewHolder(private val binding: ItemGapBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gap: GapResult) {
            binding.tvConcept.text = gap.concept
            binding.tvExplanation.text = gap.explanation

            // Build "NEEDED FOR" chips
            binding.chipGroupNeededFor.removeAllViews()
            gap.neededFor.forEach { cardFront ->
                val chip = Chip(binding.root.context).apply {
                    text = cardFront.take(20)
                    isClickable = false
                    setTextAppearance(com.google.android.material.R.style.TextAppearance_Material3_LabelSmall)
                }
                binding.chipGroupNeededFor.addView(chip)
            }

            binding.btnGenerate.setOnClickListener { onGenerateClick(gap) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GapViewHolder {
        val binding = ItemGapBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GapViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GapViewHolder, position: Int) =
        holder.bind(getItem(position))

    class GapDiffCallback : DiffUtil.ItemCallback<GapResult>() {
        override fun areItemsTheSame(o: GapResult, n: GapResult) = o.id == n.id && o.concept == n.concept
        override fun areContentsTheSame(o: GapResult, n: GapResult) = o == n
    }
}
