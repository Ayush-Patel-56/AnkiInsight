package com.ankiinsight.app.presentation.failure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankiinsight.app.R
import com.ankiinsight.app.databinding.FragmentFailureAnalyserBinding
import com.ankiinsight.app.domain.model.CardData
import com.ankiinsight.app.presentation.common.UiState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FailureAnalyserFragment : Fragment() {

    private var _binding: FragmentFailureAnalyserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FailureAnalyserViewModel by viewModels()
    private val args: FailureAnalyserFragmentArgs by navArgs()
    private lateinit var adapter: FailedCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFailureAnalyserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvToolbarSubtitle.text = "${args.deckName} • loading…"
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        adapter = FailedCardAdapter(
            onRegenerateClick = { card -> viewModel.regenerateCard(card) },
            onSkipClick = { /* remove from list or ignore */ }
        )
        binding.rvFailedCards.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFailedCards.adapter = adapter

        binding.btnRegenerateAll.setOnClickListener {
            adapter.currentList.firstOrNull()?.let { viewModel.regenerateCard(it) }
        }

        observeState()
        viewModel.loadFailedCards(args.deckId)
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cardsState.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvProgressLabel.visibility = View.VISIBLE
                        binding.tvProgressLabel.text = getString(R.string.analysing_failed_cards)
                        binding.rvFailedCards.visibility = View.GONE
                        binding.tvEmptyState.visibility = View.GONE
                    }
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvProgressLabel.visibility = View.GONE
                        binding.rvFailedCards.visibility = View.VISIBLE
                        binding.tvEmptyState.visibility = View.GONE
                        binding.tvToolbarSubtitle.text = "${args.deckName} • ${state.data.size} failed"
                        binding.tvFailedCount.text = state.data.size.toString()
                        adapter.submitList(state.data)
                    }
                    is UiState.Empty -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvProgressLabel.visibility = View.GONE
                        binding.rvFailedCards.visibility = View.GONE
                        binding.tvEmptyState.visibility = View.VISIBLE
                        binding.tvEmptyState.text = getString(R.string.no_failed_cards)
                    }
                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvProgressLabel.visibility = View.GONE
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.regenerateState.collect { state ->
                when (state) {
                    is UiState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val (original, regenCard) = state.data
                        val action = FailureAnalyserFragmentDirections
                            .actionFailureAnalyserToCardRegenerated(
                                originalFront = original.front,
                                originalBack = original.back,
                                regenFront = regenCard.front,
                                regenBack = regenCard.back,
                                cardId = original.id,
                                deckId = original.deckId,
                                easeFactor = original.easeFactor,
                                modelId = original.modelId
                            )
                        findNavController().navigate(action)
                        viewModel.clearRegenerateState()
                    }
                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                        viewModel.clearRegenerateState()
                    }
                    null, is UiState.Empty -> binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
