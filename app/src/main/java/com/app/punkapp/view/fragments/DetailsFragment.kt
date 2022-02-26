package com.app.punkapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.imagemanager.bindImageUrl
import com.app.punkapp.R
import com.app.punkapp.adapters.BeerAdapter
import com.app.punkapp.adapters.IngredientsAdapter
import com.app.punkapp.databinding.FragmentBeersBinding
import com.app.punkapp.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val mAdapterIngredients by lazy { IngredientsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupIngredientsRecyclerView()
        val ingredients = args.beerItems?.ingredients?.malt
        mAdapterIngredients.submitList(ingredients)
        binding.beerItem = args.beerItems

        binding.ivBeerImage.bindImageUrl(
            args.beerItems?.imageUrl,
            R.drawable.logo,
            R.drawable.logo
        )
    }

    private fun setupIngredientsRecyclerView() {

        binding.rvListBeerMaltFragmentDetails.adapter = mAdapterIngredients
        binding.rvListBeerMaltFragmentDetails.layoutManager =
            LinearLayoutManager(requireContext(),  RecyclerView.VERTICAL, false)

    }
}