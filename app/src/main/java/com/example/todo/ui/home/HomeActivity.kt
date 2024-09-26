package com.example.todo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.base.BaseActivity
import com.example.todo.databinding.ActivityHomeBinding
import com.example.todo.ui.home.settings.SettingsFragment
import com.example.todo.ui.home.tasksList.TasksFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    var tasksFragment: TasksFragment? = null
    override fun getLayoutId(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.bottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.tasks_navigation -> {
                    tasksFragment = TasksFragment()
                    showFragment(tasksFragment!!)
                }

                R.id.settings_navigation -> {
                    showFragment(SettingsFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomNavigation.selectedItemId = R.id.tasks_navigation
        binding.fabAddTask.setOnClickListener {
            showAddTaskBottomSheet()

        }


    }

    private fun showAddTaskBottomSheet() {
        val addTaskSheet = AddTaskBottomSheet()
        addTaskSheet.tasKAddedListener = AddTaskBottomSheet.OnTasKAddedListener {
            tasksFragment?.getTasksFromDatabase()

        }
        addTaskSheet.show(supportFragmentManager, "")
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()


    }


}