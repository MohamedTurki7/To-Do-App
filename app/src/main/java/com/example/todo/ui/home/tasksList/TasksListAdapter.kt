package com.example.todo.ui.home.tasksList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.todo.R
import com.example.todo.base.BaseRecyclerAdapter
import com.example.todo.database.model.Task
import com.example.todo.databinding.ItemTaskBinding
import com.example.todo.timeFormatOnly


class TasksListAdapter : BaseRecyclerAdapter<TasksListAdapter.ViewHolder, Task>() {

    class ViewHolder(val binding: ItemTaskBinding) : AppViewHolder(binding) {
        fun changeTaskStatus(isDone: Boolean) {
            if (isDone) {
                binding.draggingBar.setImageResource(R.drawable.dragging_bar_done)
                binding.title.setTextColor(Color.GREEN)
                binding.btnTaskIsDone.setBackgroundResource(R.drawable.done)
            } else {
                val blue = ContextCompat.getColor(itemView.context, R.color.blue)
                binding.title.setTextColor(blue)
                binding.draggingBar.setImageResource(R.drawable.dragging_bar)
                binding.btnTaskIsDone.setBackgroundResource(R.drawable.check_mark)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.getBinding<ItemTaskBinding>()
        val task = items?.get(position)
        binding.title.text = task?.title
        holder.changeTaskStatus(isDone = true)
        binding.time.text = task?.time?.timeFormatOnly()
        binding.icDelete.setOnClickListener {
            onDeleteListener?.itemClickL(position, task!!)
        }
        onEditClickListener.let {
            binding.dragItem.setOnClickListener {
                onEditClickListener?.itemClickL(position, task!!)
            }
        }
        onDoneBtnClick.let { onDoneBtnClick ->
            binding.btnTaskIsDone.setOnClickListener {
                onDoneBtnClick?.itemClickL(position, task!!)
            }
        }



    }

    var onEditClickListener: OnItemClickListener? = null
    var onDoneBtnClick: OnItemClickListener? = null
    var onDeleteListener: OnItemClickListener? = null

    fun interface OnItemClickListener {
        fun itemClickL(position: Int, item: Task)
    }
}
//class TasksListAdapter(var tasks: MutableList<Task>? = null) :
//    RecyclerView.Adapter<TasksListAdapter.ViewHolder>() {
//    class ViewHolder(val itemTaskBinding: ItemTaskBinding) :
//        RecyclerView.ViewHolder(itemTaskBinding.root) {
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int = tasks?.size ?: 0
//
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val binding = holder.itemTaskBinding
//        val task = tasks?.get(position)
//        binding.title.text = task?.title
//        binding.time.text = "${task?.time}"
//    }
//    fun submitNewList(newItems: MutableList<Task>) {
//        tasks = newItems
//        notifyDataSetChanged()
//    }
//
//    fun addItem(newItem: Task) {
//        tasks!!.add(newItem)
//        notifyItemInserted(tasks!!.size)
//    }
//
//}