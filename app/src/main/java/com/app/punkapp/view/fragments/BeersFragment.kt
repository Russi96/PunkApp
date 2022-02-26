package com.app.punkapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.data.database.entities.BeerEntity
import com.app.data.utils.NetworkResult
import com.app.domain.Beer
import com.app.domain.BeerItem
import com.app.punkapp.adapters.BeerAdapter
import com.app.punkapp.databinding.FragmentBeersBinding
import com.app.punkapp.utils.showSnackBar
import com.app.punkapp.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import java.lang.Exception

@AndroidEntryPoint
class BeersFragment : Fragment() {

    private val viewModel by viewModels<ViewModel>()
    private val mAdapterBeer by lazy { BeerAdapter() }
    private var _binding: FragmentBeersBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBeersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBeerRecyclerView()
        collectUiState()
        searchMovies()
        getBeers(view)

    }

    private fun getBeers(view: View) {
        lifecycleScope.launch {
            viewModel.getBeer().observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        try {
                            val beer = response.data
                            if (beer != null) {
                                insertBeerDatabaseRoom(beer)
                            }
                        } catch (e: Exception) {
                            showSnackBar(view, "Recipes not found")
                        }
                    }

                    is NetworkResult.Error -> {
                        response.message?.let { showSnackBar(view, it) }
                    }
                }
            }
        }
    }

    private fun insertBeerDatabaseRoom(beer: Beer) {
        beer.forEach { item ->
            val beerEntity = BeerEntity(item)
            viewModel.insertBeer(beerEntity)
        }
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.beerPagination().collect {
                mAdapterBeer.submitData(it)
            }
        }
    }

    private fun setupBeerRecyclerView() {

        binding.rvListBeerFragmentBeer.adapter = mAdapterBeer
        binding.rvListBeerFragmentBeer.layoutManager =
            GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun searchMovies() {
        binding.svSearchBeerFragmentBeers.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchThroughDatabase(newText)
                }
                return true
            }

        })
    }

    private fun searchThroughDatabase(query: String) {
        var searchQuery = query
        searchQuery = "$searchQuery%"
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.searchBeerData(searchQuery).collect {
                mAdapterBeer.submitData(it)
            }
        }

    }
}