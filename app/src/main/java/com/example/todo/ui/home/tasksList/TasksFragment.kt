//package com.example.todo.ui.home.tasksList
//
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import com.example.todo.R
//import com.example.todo.base.BaseFragment
//import com.example.todo.database.AppDatabase
//import com.example.todo.database.model.Task
//import com.example.todo.databinding.FragmentTasksBinding
//import com.example.todo.ignoreTime
//import com.example.todo.setCurrentDate
//import com.example.todo.ui.home.Constants
//import com.example.todo.ui.home.taskDetails.EditTaskActivity
//import com.prolificinteractive.materialcalendarview.CalendarDay
//import java.util.Calendar
//
//class TasksFragment : BaseFragment<FragmentTasksBinding>() {
////    val tasks: List<Task>? = null
//    override fun getLayoutId(): Int = R.layout.fragment_tasks
//    private val adapter = TasksListAdapter()
//     private val selectedDate = Calendar.getInstance().apply {
//         ignoreTime()
//     }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.rvTasks.adapter = adapter
////
//        adapter.onEditClickListener=TasksListAdapter.OnItemClickListener { position, task ->
//            val intent=Intent(requireContext(),EditTaskActivity::class.java)
//            intent.putExtra(Constants.TASK_KEY,task)
//            startActivity(intent)
//        }
//        adapter.onDeleteListener=TasksListAdapter.OnItemClickListener { position, item ->
//            deleteTask(item)
//        }
//
////
//        binding.calendarView.setDateSelected(CalendarDay.today(),true)
//        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
//            selectedDate.setCurrentDate(date.year,date.month-1,date.day)
//            getTasksFromDatabase()
//
//        }
//    }
//
//    private fun deleteTask(item: Task,) {
//        AppDatabase.getInstance().tasksDao().deleteTask(item )
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        getTasksFromDatabase()
//    }
//
//     fun getTasksFromDatabase() {
//         if (isDetached)return
//       val tasks= AppDatabase.getInstance().tasksDao().getAllTasks()
//        adapter.submitNewList(tasks.toMutableList())
//
//
//    }
//
//
//}
package com.example.todo.ui.home.tasksList

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.todo.R
import com.example.todo.base.BaseFragment
import com.example.todo.database.AppDatabase
import com.example.todo.database.model.Task
import com.example.todo.databinding.FragmentTasksBinding
import com.example.todo.ignoreTime
import com.example.todo.setCurrentDate
import com.example.todo.ui.home.Constants
import com.example.todo.ui.home.taskDetails.EditTaskActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar

class TasksFragment : BaseFragment<FragmentTasksBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_tasks
    private val adapter = TasksListAdapter()
    private val selectedDate = Calendar.getInstance().apply {
        ignoreTime()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTasks.adapter = adapter

        adapter.onEditClickListener = TasksListAdapter.OnItemClickListener { position, task ->
            val intent = Intent(requireContext(), EditTaskActivity::class.java)
            intent.putExtra(Constants.TASK_KEY, task)
            startActivity(intent)
        }

        adapter.onDeleteListener = TasksListAdapter.OnItemClickListener { position, item ->
            deleteTask(item)
//            adapter.remove(removeItem = item)

        }
        adapter.onDoneBtnClick = TasksListAdapter.OnItemClickListener { position, item ->
            item.status = !item.status
            AppDatabase.getInstance().tasksDao().updateTask(item)
//            adapter.
        }

        binding.calendarView.setDateSelected(CalendarDay.today(), true)
        binding.calendarView.setOnDateChangedListener { widget, date, selected ->
            selectedDate.setCurrentDate(date.year, date.month - 1, date.day)
            getTasksFromDatabase()
        }
    }

    private fun deleteTask(item: Task) {
        Thread {
            AppDatabase.getInstance().tasksDao().deleteTask(item)
            requireActivity().runOnUiThread {
                getTasksFromDatabase() // Refresh UI after deletion
            }
        }.start()
    }

    private fun updateTask(item: Task) {
        Thread {
            AppDatabase.getInstance().tasksDao().updateTask(item)
            requireActivity().runOnUiThread {
                getTasksFromDatabase() // Refresh UI after deletion
            }
        }.start()
    }

    override fun onResume() {
        super.onResume()
        getTasksFromDatabase()
    }

    fun getTasksFromDatabase() {
        if (isDetached) return

        Thread {
            val tasks = AppDatabase.getInstance().tasksDao().getAllTasks()
            requireActivity().runOnUiThread {
                adapter.submitNewList(tasks.toMutableList())
            }
        }.start()
    }
}

//binding.calendarView.selectedDate=CalendarDay.from(
//            selectedDate.get(Calendar.YEAR),
//            selectedDate.get(Calendar.MONTH),
//            selectedDate.get(Calendar.DAY_OF_MONTH),
//        )
//        binding.calendarView.currentDate=CalendarDay.from(
//            selectedDate.get(Calendar.YEAR),
//            selectedDate.get(Calendar.MONTH),
//            selectedDate.get(Calendar.DAY_OF_MONTH),
//        )
//