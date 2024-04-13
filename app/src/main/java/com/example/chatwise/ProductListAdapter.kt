package com.example.chatwise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatwise.data.Product

class ProductListAdapter(private val items: ArrayList<Product?>, private val listener: ProductItemClciked): RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_product,parent,false)
        val viewHolder = ProductViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = items[position]
      holder.titleView2.text = currentItem?.description
        holder.titleView.text = currentItem?.title
        Glide.with(holder.imageView.context).load(currentItem?.thumbnail)
            .into(holder.imageView)
    }
    fun update(updatedData: List<Product?>) {
        items.clear()
        items.addAll(updatedData)
        notifyDataSetChanged()
    }
}
class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val titleView2 : TextView = itemView.findViewById(R.id.tv2)
  val titleView : TextView = itemView.findViewById(R.id.title)
    val imageView : ImageView = itemView.findViewById(R.id.img)
}

interface ProductItemClciked{
    fun onItemClicked(item:Product?)
}
