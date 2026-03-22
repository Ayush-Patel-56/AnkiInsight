package com.ankiinsight.app.presentation.insights

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ankiinsight.app.R
import com.ankiinsight.app.databinding.FragmentInsightsBinding
import com.ankiinsight.app.domain.usecase.InsightsData
import com.ankiinsight.app.presentation.common.UiState
import com.google.android.material.progressindicator.LinearProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InsightsFragment : Fragment() {

    private var _binding: FragmentInsightsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: InsightsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsightsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.insightsState.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.scrollContent.visibility = View.GONE
                    }
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.scrollContent.visibility = View.VISIBLE
                        bindInsights(state.data)
                    }
                    is UiState.Empty -> {
                        binding.progressBar.visibility = View.GONE
                        binding.scrollContent.visibility = View.GONE
                        binding.tvEmptyState.visibility = View.VISIBLE
                    }
                    else -> {}
                }
            }
        }
    }

    private fun bindInsights(data: InsightsData) {
        // Health Score
        val score = data.healthScore
        binding.tvHealthScore.text = score.toString()
        binding.tvHealthScoreLabel.text = "Learning Health Score"
        binding.indicatorBar.progress = score

        val scoreColor = when {
            score < 50 -> ContextCompat.getColor(requireContext(), R.color.color_error)
            score < 70 -> ContextCompat.getColor(requireContext(), R.color.color_warning)
            else -> ContextCompat.getColor(requireContext(), R.color.color_primary)
        }
        binding.tvHealthScore.setTextColor(scoreColor)

        // Forgetting patterns
        data.patterns.getOrNull(0)?.let { p ->
            binding.tvPattern1Label.text = p.label
            binding.tvPattern1Desc.text = p.description
        }
        data.patterns.getOrNull(1)?.let { p ->
            binding.tvPattern2Label.text = p.label
            binding.tvPattern2Desc.text = p.description
        }

        // Weakest topics
        data.weakestTopics.getOrNull(0)?.let { (name, rate) ->
            binding.tvTopic1Name.text = name
            val pct = (rate * 100).toInt()
            binding.tvTopic1Pct.text = "$pct% weak"
            binding.progressTopic1.progress = pct
            binding.progressTopic1.setIndicatorColor(topicColor(pct))
        }
        data.weakestTopics.getOrNull(1)?.let { (name, rate) ->
            binding.tvTopic2Name.text = name
            val pct = (rate * 100).toInt()
            binding.tvTopic2Pct.text = "$pct% weak"
            binding.progressTopic2.progress = pct
            binding.progressTopic2.setIndicatorColor(topicColor(pct))
        }
        data.weakestTopics.getOrNull(2)?.let { (name, rate) ->
            binding.tvTopic3Name.text = name
            val pct = (rate * 100).toInt()
            binding.tvTopic3Pct.text = "$pct% weak"
            binding.progressTopic3.progress = pct
            binding.progressTopic3.setIndicatorColor(topicColor(pct))
        }

        // AI recommendation
        binding.tvAiRecommendation.text = data.aiRecommendation
        binding.btnStartAnalysis.setOnClickListener {
            findNavController().navigate(R.id.action_insights_to_deckSelector)
        }
    }

    private fun topicColor(pct: Int): Int = when {
        pct > 60 -> ContextCompat.getColor(requireContext(), R.color.color_error)
        pct > 40 -> ContextCompat.getColor(requireContext(), R.color.color_warning)
        else -> ContextCompat.getColor(requireContext(), R.color.color_warning_light)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
