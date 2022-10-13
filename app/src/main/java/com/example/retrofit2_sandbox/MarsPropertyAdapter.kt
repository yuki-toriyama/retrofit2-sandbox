package com.example.retrofit2_sandbox

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit2_sandbox.data.MarsProperty

class MarsPropertyAdapter : RecyclerView.Adapter<MarsPropertyViewHolder>() {
    var data = listOf<MarsProperty>()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_property, parent, false)
        return MarsPropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        val item = data[position]
        holder.estateId.text = item.id.toString()
        holder.price.text = item.price.toString()
        holder.type.text = item.type
        bindImage(holder.thumbnail, item.imgSrcUrl)
    }

    fun bindImage(imgView: ImageView, imgUrl: String) {
        imgUrl.let {
            val uri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context).load(uri).into(imgView)
        }
    }
}