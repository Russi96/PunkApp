package com.app.punkapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.domain.Malt
import com.app.punkapp.databinding.ItemIngredientsMaltBinding

class IngredientsAdapter : ListAdapter<Malt, IngredientsAdapter.MyViewHolder>(DiffUtilCallback) {

    class MyViewHolder(private val binding: ItemIngredientsMaltBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(malt: Malt) {
            binding.malt = malt
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemIngredientsMaltBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<Malt>() {
        override fun areItemsTheSame(
            oldItem: Malt,
            newItem: Malt
        ): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: Malt,
            newItem: Malt
        ): Boolean = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val maltItem = getItem(position)
        if (maltItem != null) {
            holder.binding(maltItem)
        }
    }
}