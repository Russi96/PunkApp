package com.app.punkapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.domain.BeerItem
import com.app.imagemanager.bindImageUrl
import com.app.punkapp.R
import com.app.punkapp.databinding.ItemBeerBinding

class BeerAdapter:
    PagingDataAdapter<BeerItem, BeerAdapter.MyViewHolder>(DiffUtilCallback) {



    class MyViewHolder(private val binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(beerItem: BeerItem){
            binding.beerItem = beerItem
            binding.ivBeerImage.bindImageUrl(
                beerItem.imageUrl,
                R.drawable.logo,
                R.drawable.logo
            )
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBeerBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }

    }


    private object DiffUtilCallback : DiffUtil.ItemCallback<BeerItem>() {
        override fun areItemsTheSame(
            oldItem: BeerItem,
            newItem: BeerItem
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: BeerItem,
            newItem: BeerItem
        ): Boolean = oldItem == newItem

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val beerItem = getItem(position)


        if (beerItem != null) {
            holder.binding(beerItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }
}