package com.ankiinsight.app.presentation.conflict

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankiinsight.app.R
import com.ankiinsight.app.databinding.FragmentConflictDetectorBinding
import com.ankiinsight.app.presentation.common.UiState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ConflictDetectorFragment : Fragment() {

    private var _binding: FragmentConflictDetectorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConflictDetectorViewModel by viewModels()
    private lateinit var adapter: ConflictAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConflictDetectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ConflictAdapter(
            onTagClick = { conflict -> viewModel.tagConflict(conflict) },
            onKeepBothClick = { /* dismiss from list */ }
        )
        binding.rvConflicts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvConflicts.adapter = adapter

        binding.btnScan.setOnClickListener {
            binding.tvSubtitle.text = getString(R.string.scanning_all_decks)
            viewModel.scan(forceRefresh = true)
        }

        binding.btnTagAll.setOnClickListener {
            val conflicts = (viewModel.conflictsState.value as? UiState.Success)?.data ?: return@setOnClickListener
            viewModel.tagAllConflicts(conflicts)
        }

        observeState()
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.conflictsState.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvProgress.visibility = View.VISIBLE
                        binding.tvProgress.text = getString(R.string.comparing_cards)
                        binding.rvConflicts.visibility = View.GONE
                        binding.tvEmptyState.visibility = View.GONE
                        binding.btnTagAll.visibility = View.GONE
                    }
                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvProgress.visibility = View.GONE
                        binding.rvConflicts.visibility = View.VISIBLE
                        binding.tvEmptyState.visibility = View.GONE
                        binding.btnTagAll.visibility = View.VISIBLE

                        binding.tvSubtitle.text = getString(R.string.scan_complete)
                        binding.tvConflictCount.text = state.data.size.toString()
                        adapter.submitList(state.data)
                    }
                    is UiState.Empty -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvProgress.visibility = View.GONE
                        binding.rvConflicts.visibility = View.GONE
                        binding.tvEmptyState.visibility = View.VISIBLE
                        binding.btnTagAll.visibility = View.GONE
                        binding.tvEmptyState.text = getString(R.string.no_conflicts_found)
                    }
                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Snackbar.make(binding.root, state.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tagState.collect { message ->
                if (message != null) {
                    Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
                    viewModel.clearTagState()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
