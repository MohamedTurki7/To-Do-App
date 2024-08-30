package com.example.todo.ui.home

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todo.R
import com.example.todo.database.AppDatabase
import com.example.todo.database.model.Task
import com.example.todo.databinding.FragmentAddTaskBinding
import com.example.todo.dateFormatOnly
import com.example.todo.ignoreDate
import com.example.todo.ignoreTime
import com.example.todo.setCurrentDate
import com.example.todo.setCurrentTime
import com.example.todo.timeFormatOnly
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.selectDateTv.setOnClickListener {
            showDatePicker()
        }
        binding.selectTimeTv.setOnClickListener {
            showTimePicker()
        }
        binding.addTaskBtn.setOnClickListener {
            createTask()
        }
    }


    private fun createTask() {
        if (!isValidForm()) return
        AppDatabase.getInstance().tasksDao().createTask(
            Task(
                title = binding.title.text.toString(),
                description = binding.description.text.toString(),
                date = date.timeInMillis,
                time = time.timeInMillis

            )
        )
        showSuccessDialog()
    }

    private fun showSuccessDialog() {
        val builder = AlertDialog.Builder(context).setMessage("Task added successfully")
            .setPositiveButton(R.string.ok, { dialog, i ->
                dialog.dismiss()
                dismiss()
            })
            .setCancelable(false)
        builder.show()
    }

    private fun isValidForm(): Boolean {
        var isValid = true;
        if (binding.title.text.toString().isBlank()) {
            isValid = false
            binding.titleTil.error = "please enter title"
        } else {
            binding.titleTil.error = "null"


        }
        if (binding.description.text.toString().isBlank()) {
            isValid = false
            binding.descriptionTil.error = "please enter description"
        } else {
            binding.descriptionTil.error = "null"
        }
        if (binding.selectDateTv.text.toString().isBlank()) {
            isValid = false
            binding.selectDateTil.error = "please select date "
        } else {
            binding.selectDateTil.error = "null"

        }
        if (binding.selectTimeTv.text.toString().isBlank()) {
            isValid = false
            binding.selectTimeTil.error = "please select time"
        } else {
            binding.selectDateTil.error = "null"
        }
        return isValid
    }

    fun showTimePicker() {
        val dialog = TimePickerDialog(
            requireContext(), { timePicker, hours, mins ->
                time.setCurrentTime(hours, mins)
                binding.selectTimeTv.text = time.timeFormatOnly()

            },

            time.get(Calendar.HOUR_OF_DAY),
            time.get(Calendar.MINUTE),
            false
        )
        dialog.show()
    }

    val date = java.util.Calendar.getInstance()
        .apply {
            ignoreTime()
        }
    val time = java.util.Calendar.getInstance()
        .apply {
            ignoreDate()
        }

    private fun showDatePicker() {
        val dialog = DatePickerDialog(
            requireContext(),
            { datePicker, year, month, day ->
                date.setCurrentDate(year, month, day)
                binding.selectDateTv.text = date.dateFormatOnly()
            },

            date.get(Calendar.YEAR),
            date.get(Calendar.MONTH),
            date.get(Calendar.DAY_OF_MONTH),
        )
        dialog.show()


    }

}




