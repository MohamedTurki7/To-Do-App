package com.example.todo.base

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerAdapter<AppViewHolder : ViewHolder, T>() :
    RecyclerView.Adapter<AppViewHolder>() {
    var items: MutableList<T>? = null
    override fun getItemCount(): Int = items?.size ?: 0
    fun submitNewList(newItems: MutableList<T>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun addItem(newItem: T) {
        items!!.add(newItem)
        notifyItemInserted(items!!.size)
    }

    open class AppViewHolder(val viewBinding: ViewBinding) : ViewHolder(viewBinding.root) {
        fun <VB : ViewBinding> getBinding(): VB {
            return viewBinding as VB
        }

    }
}