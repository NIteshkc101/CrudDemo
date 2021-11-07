package com.example.crudusingroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudusingroom.modal.Items

class ItemsAdapter(
    private val listItems: ArrayList<Items>,
    private val listener: ItemsListener
): RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsAdapter.ItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ItemsViewHolder, position: Int) {
        val item = listItems[position]
        holder.textViewItem.text = item.item
        holder.textViewQuantity.text = item.qty
        holder.textViewBorrowed.text = item.get
        holder.itemView.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return listItems.size

    }
    class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewItem = itemView.findViewById<TextView>(R.id.tv_name)
        var textViewQuantity = itemView.findViewById<TextView>(R.id.tv_qty)
        var textViewBorrowed = itemView.findViewById<TextView>(R.id.tv_borrowed)
    }

    interface ItemsListener{
        fun OnItemClicked(items : Items)
    }

}