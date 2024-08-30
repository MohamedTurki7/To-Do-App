package com.example.todo.ui.home.tasksList

import android.os.Bundle
import android.view.View
import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.database.AppDatabase
import com.example.todo.database.model.Task
import com.example.todo.databinding.FragmentTasksBinding

class TasksFragment : BaseFragment<FragmentTasksBinding>() {
    val tasks: List<Task>? = null
    override fun getLayoutId(): Int = R.layout.fragment_tasks
    val adapter = TasksListAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTasks.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        getTasksFromDatabase()
    }

    private fun getTasksFromDatabase() {
        AppDatabase.getInstance().tasksDao().getAllTasks()
        adapter.submitNewList(tasks!!.toMutableList())


    }


}