package com.example.retrofit2_sandbox

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MarsPropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val thumbnail = itemView.findViewById<ImageView>(R.id.thumbnail_image)
    val estateId = itemView.findViewById<TextView>(R.id.estate_id)
    val price = itemView.findViewById<TextView>(R.id.price_text)
    val type = itemView.findViewById<TextView>(R.id.type_text)
}