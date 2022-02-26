package com.app.punkapp.utils

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.animation.Animation
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.app.domain.BeerItem
import com.app.domain.Malt
import com.app.punkapp.view.fragments.BeersFragmentDirections
import com.google.android.material.card.MaterialCardView

class BindingAdapters {

    companion object {

        @BindingAdapter("android:logoSplash")
        @JvmStatic
        fun logoAnimatedSplash(view: View, animation: Animation) {
            view.startAnimation(animation)
        }

        @BindingAdapter("android:abvText")
        @JvmStatic
        fun abvBeer(view: TextView, beerItem: BeerItem) {
            view.text = beerItem.abv.toString()
        }

        @BindingAdapter("android:ibuProgressBar")
        @JvmStatic
        fun ibuProgressBar(view: ProgressBar, beerItem: BeerItem) {
            view.progress = beerItem.ibu.toInt()
        }

        @BindingAdapter("android:ibuText")
        @JvmStatic
        fun ibuBeer(view: TextView, beerItem: BeerItem) {
            view.text = beerItem.ibu.toString()
        }


        @BindingAdapter("android:phProgressBar")
        @JvmStatic
        fun phProgressBar(view: ProgressBar, beerItem: BeerItem) {
            view.progress = beerItem.ph.toInt()
        }

        @BindingAdapter("android:phText")
        @JvmStatic
        fun phBeer(view: TextView, beerItem: BeerItem) {
            view.text = beerItem.ph.toString()
        }

        @BindingAdapter("android:ebcProgressBar")
        @JvmStatic
        fun ebcProgressBar(view: ProgressBar, beerItem: BeerItem) {
            view.progress = beerItem.ebc.toInt()
        }

        @BindingAdapter("android:ebcText")
        @JvmStatic
        fun ebcBeer(view: TextView, beerItem: BeerItem) {
            view.text = beerItem.ebc.toString()
        }

        @BindingAdapter("android:abvProgressBar")
        @JvmStatic
        fun abvProgressBar(view: ProgressBar, beerItem: BeerItem) {
            view.progress = beerItem.abv.toInt()
        }

        @BindingAdapter("android:fermentationText")
        @JvmStatic
        fun fermentationBeer(view: TextView, beerItem: BeerItem) {
            view.text = beerItem.method.fermentation.temp.value.toString()
        }

        @BindingAdapter("android:fermentationProgressBar")
        @JvmStatic
        fun fermentationProgressBar(view: ProgressBar, beerItem: BeerItem) {
            view.progress = beerItem.method.fermentation.temp.value.toInt()
        }


        @BindingAdapter("android:maltText")
        @JvmStatic
        fun maltBeer(view: TextView, malt: Malt) {
            view.text = malt.amount.value.toString()
        }

        @BindingAdapter("android:maltProgressBar")
        @JvmStatic
        fun maltProgressBar(view: ProgressBar, malt: Malt) {
            view.progress = malt.amount.value.toInt()
        }




        @BindingAdapter("android:sendDataDetailsFragment")
        @JvmStatic
        fun sendDataDetailsFragment(view: MaterialCardView, beerItem: BeerItem) {
            view.setOnClickListener {
                val action = BeersFragmentDirections.actionBeersFragmentToDetailsFragment(beerItem)
                view.findNavController().navigate(action)
            }
        }

        @BindingAdapter("android:return")
        @JvmStatic
        fun returnFragment(view: ImageButton, beerItem: BeerItem) {
            view.setOnClickListener {
                view.findNavController().popBackStack()
            }
        }

    }

}