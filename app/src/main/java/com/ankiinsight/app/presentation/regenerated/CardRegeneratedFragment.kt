package com.ankiinsight.app.presentation.regenerated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ankiinsight.app.R
import com.ankiinsight.app.databinding.FragmentCardRegeneratedBinding
import com.ankiinsight.app.domain.model.CardData
import com.ankiinsight.app.presentation.common.UiState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardRegeneratedFragment : Fragment() {

    private var _binding: FragmentCardRegeneratedBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CardRegeneratedViewModel by viewModels()
    private val args: CardRegeneratedFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardRegeneratedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Build original CardData from nav args
        val originalCard = CardData(
            id = args.cardId, 
            deckId = args.deckId, 
            deckName = "",
            front = args.originalFront, 
            back = args.originalBack,
            easeFactor = args.easeFactor, 
            due = 0L,
            modelId = args.modelId
        )

        // Bind original card
        binding.tvOriginalQuestion.text = args.originalFront
        binding.tvOriginalAnswer.text = args.originalBack

        // Bind regenerated card
        binding.tvRegenQuestion.text = args.regenFront
        binding.tvRegenAnswer.text = args.regenBack

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnAccept.setOnClickListener {
            viewModel.saveCard(originalCard, args.regenFront, args.regenBack)
        }

        binding.btnReject.setOnClickListener {
            viewModel.retry(originalCard)
        }

        observeState(originalCard)
    }

    private fun observeState(originalCard: CardData) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.saveState.collect { state ->
                when (state) {
                    is UiState.Loading -> binding.btnAccept.isEnabled = false
                    is UiState.Success -> {
                        Snackbar.make(binding.root, getString(R.string.card_saved), Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                    is UiState.Error -> {
                        binding.btnAccept.isEnabled = true
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                    }
                    else -> binding.btnAccept.isEnabled = true
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.retryState.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        binding.btnReject.isEnabled = false
                        binding.btnReject.text = getString(R.string.trying_again)
                    }
                    is UiState.Success -> {
                        binding.btnReject.isEnabled = true
                        binding.btnReject.text = getString(R.string.reject_try_again)
                        val regen = state.data
                        binding.tvRegenQuestion.text = regen.front
                        binding.tvRegenAnswer.text = regen.back
                        viewModel.clearRetryState()
                    }
                    is UiState.Error -> {
                        binding.btnReject.isEnabled = true
                        binding.btnReject.text = getString(R.string.reject_try_again)
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                        viewModel.clearRetryState()
                    }
                    else -> {
                        binding.btnReject.isEnabled = true
                        binding.btnReject.text = getString(R.string.reject_try_again)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
