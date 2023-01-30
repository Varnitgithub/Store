package com.example.store

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class adapter(private var context: Context, private var listener:myinterface): RecyclerView.Adapter<adapter.myviewholder>() {
    private var item = dataitem()

    inner class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id : TextView = itemView.findViewById(R.id.id)
        val category : TextView = itemView.findViewById(R.id.category)
        val title : TextView = itemView.findViewById(R.id.title)
        val price : TextView = itemView.findViewById(R.id.price)
        val description : TextView = itemView.findViewById(R.id.description)
        val image: ImageView = itemView.findViewById(androidx.appcompat.R.id.image)
        var layout: LinearLayout = itemView.findViewById(R.id.linearlayout)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val viewholder =  myviewholder(
            LayoutInflater.from(context).
        inflate(R.layout.items,parent,false))
        viewholder.layout.setOnClickListener {
            listener.onclick(item[viewholder.adapterPosition])
        }
        return viewholder
    }
    override fun getItemCount(): Int {
        return item.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updatelist(newlist:dataitem){
        item.clear()
        item.addAll(newlist)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val currentitem = item[position]
        holder.id.text = currentitem.id.toString()
        holder.category.text = currentitem.category
        holder.title.text = currentitem.title
        holder.price.text = currentitem.price.toString()
        holder.description.text = currentitem.description
        Glide.with(holder.itemView).load(currentitem.image).into(holder.image)
    }
    interface  myinterface{
        fun onclick(dataItem: dataitemItem)
    }
}