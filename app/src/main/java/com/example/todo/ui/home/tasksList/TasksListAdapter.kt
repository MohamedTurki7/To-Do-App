package com.example.todo.ui.home.tasksList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.todo.base.BaseRecyclerAdapter
import com.example.todo.database.model.Task
import com.example.todo.databinding.ItemTaskBinding

class TasksListAdapter : BaseRecyclerAdapter<TasksListAdapter.ViewHolder, Task>() {
    class ViewHolder(val vb: ViewBinding) : AppViewHolder(vb)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.getBinding<ItemTaskBinding>()
        val task = items?.get(position)
        binding.title.text = task?.title
        binding.time.text = "${task?.time}"


    }
}