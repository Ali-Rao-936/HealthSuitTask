package com.health.suit.presentation.homeFragment

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.health.suit.R
import com.health.suit.databinding.HomeItemLayoutBinding
import com.health.suit.domain.home.response.HomeApiRes
import com.health.suit.presentation.details.ProductDetailsActivity
import com.health.suit.utils.loadImageFromPath

class HomeItemsAdapter(val context: Context, private val list: HomeApiRes) : RecyclerView.Adapter<HomeItemsAdapter.viewHolder>() {



   inner class viewHolder(val binding : HomeItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        init {
                binding.root.setOnClickListener {
                    context.startActivity(Intent(context, ProductDetailsActivity::class.java)
                        .putExtra("itemId", list[adapterPosition].id))
                }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding =  HomeItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.imgItem.loadImageFromPath(list[position].image, R.drawable.placeholder)
        holder.binding.txtProductName.text = list[position].title
        holder.binding.txtPrice.text = "AED " + list[position].price
    }
}