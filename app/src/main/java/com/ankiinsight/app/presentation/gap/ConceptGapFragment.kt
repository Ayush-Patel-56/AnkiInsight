package com.ankiinsight.app.presentation.gap

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankiinsight.app.R
import com.ankiinsight.app.databinding.FragmentConceptGapBinding
import com.ankiinsight.app.domain.model.GapResult
import com.ankiinsight.app.presentation.common.UiState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConceptGapFragment : Fragment() {

    private var _binding: FragmentConceptGapBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConceptGapViewModel by viewModels()
    private val args: ConceptGapFragmentArgs by navArgs()
    private lateinit var adapter: GapAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConceptGapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvToolbarSubtitle.text = "${args.deckName} • ${args.failedCount} failed"

        adapter = GapAdapter { gap ->
            viewModel.generateCardForGap(gap, args.deckId)
        }
        binding.rvGaps.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGaps.adapter = adapter

        binding.btnGenerateAll.setOnClickListener {
            val gaps = (viewModel.gapsState.value as? UiState.Success)?.data ?: return@setOnClickListener
            gaps.forEach { gap -> viewModel.generateCardForGap(gap, args.deckId) }
        }

        observeState()
        viewModel.loadGaps(args.deckId)
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.gapsState.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.rvGaps.visibility = View.GONE
                        binding.tvEmptyState.visibility = View.GONE
                    }
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvGaps.visibility = View.VISIBLE
                        binding.tvEmptyState.visibility = View.GONE
                        binding.tvGapsFoundBadge.text = state.data.size.toString()
                        adapter.submitList(state.data)
                    }
                    is UiState.Empty -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvGaps.visibility = View.GONE
                        binding.tvEmptyState.visibility = View.VISIBLE
                        binding.tvEmptyState.text = getString(R.string.no_gaps_found)
                    }
                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pendingGap.collect { pending ->
                if (pending != null) {
                    showApprovalDialog(pending.first, pending.second.front, pending.second.back)
                }
            }
        }
    }

    private fun showApprovalDialog(gap: GapResult, front: String, back: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Add Missing Card?")
            .setMessage("Front: $front\n\nBack: $back")
            .setPositiveButton("Add to AnkiDroid") { _, _ ->
                viewModel.confirmSaveGapCard(args.deckId)
            }
            .setNegativeButton("Cancel") { _, _ ->
                viewModel.clearPendingGap()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
