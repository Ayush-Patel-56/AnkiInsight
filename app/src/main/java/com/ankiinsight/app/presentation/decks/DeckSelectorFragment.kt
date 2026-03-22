package com.ankiinsight.app.presentation.decks

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ankiinsight.app.databinding.FragmentDeckSelectorBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeckSelectorFragment : Fragment() {

    private var _binding: FragmentDeckSelectorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DeckSelectorViewModel by viewModels()
    private lateinit var adapter: DeckAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeckSelectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DeckAdapter { deck ->
            val action = DeckSelectorFragmentDirections
                .actionDeckSelectorToFailureAnalyser(deck.deckId, deck.deckName)
            findNavController().navigate(action)
        }

        binding.rvDecks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDecks.adapter = adapter
        
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.filter(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.decks.collect { decks ->
                adapter.submitList(decks)
                binding.tvEmptyState.visibility = if (decks.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isLoading.collect { loading ->
                binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
            }
        }

        viewModel.loadDecks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
