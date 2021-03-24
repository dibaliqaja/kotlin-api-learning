package com.example.kotlinapi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_main.view.*

class MainAdapter(val results : ArrayList<MainModel.Result>)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder (val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.view.tv_home.text = result.title
        Log.d("MainAdapter", "resultImage: ${result.image}")
        Glide.with(holder.view)
                .load(result.image)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .centerCrop()
                .into(holder.view.imageView)
    }

    override fun getItemCount() = results.size

    fun setData(data: List<MainModel.Result>) {
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }

}