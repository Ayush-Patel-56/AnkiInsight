package com.ankiinsight.app.presentation.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ankiinsight.app.R
import com.ankiinsight.app.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is HomeUiState.Loading -> showLoading()
                    is HomeUiState.NotInstalled -> showNotInstalled()
                    is HomeUiState.PermissionDenied -> showPermissionDenied()
                    is HomeUiState.Connected -> showConnected(state)
                }
            }
        }
    }

    private fun showLoading() {
        binding.loadingGroup.visibility = View.VISIBLE
        binding.connectedGroup.visibility = View.GONE
        binding.errorGroup.visibility = View.GONE
    }

    private fun showNotInstalled() {
        binding.loadingGroup.visibility = View.GONE
        binding.connectedGroup.visibility = View.GONE
        binding.errorGroup.visibility = View.VISIBLE
        binding.tvErrorTitle.text = getString(R.string.ankidroid_not_installed_title)
        binding.tvErrorMessage.text = getString(R.string.ankidroid_not_installed_message)
        binding.btnErrorAction.text = getString(R.string.install_ankidroid)
        binding.btnErrorAction.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=com.ichi2.anki")))
        }
    }

    private fun showPermissionDenied() {
        binding.loadingGroup.visibility = View.GONE
        binding.connectedGroup.visibility = View.GONE
        binding.errorGroup.visibility = View.VISIBLE
        binding.tvErrorTitle.text = getString(R.string.permission_denied_title)
        binding.tvErrorMessage.text = getString(R.string.permission_denied_message)
        binding.btnErrorAction.text = getString(R.string.grant_permission)
        binding.btnErrorAction.setOnClickListener {
            activity?.let { act ->
                act.requestPermissions(
                    arrayOf("com.ichi2.anki.permission.READ_WRITE_DATABASE"), 1001
                )
            }
        }
    }

    private fun showConnected(state: HomeUiState.Connected) {
        binding.loadingGroup.visibility = View.GONE
        binding.errorGroup.visibility = View.GONE
        binding.connectedGroup.visibility = View.VISIBLE

        binding.tvCardCount.text = "${state.totalCards} cards • ${state.deckCount} decks"
        binding.tvStatCardsDue.text = state.cardsDue.toString()
        binding.tvStatFailed.text = state.failedToday.toString()
        binding.tvStatConflicts.text = state.conflictsFound.toString()
    }

    private fun setupClickListeners() {
        binding.cardFailureAnalyser.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_deckSelector)
        }
        binding.cardGapDetector.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_deckSelector)
        }
        binding.cardConflictDetector.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_conflictDetector)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
