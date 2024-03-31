package com.example.classproject

import android.app.AlertDialog
import android.os.Bundle
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.classproject.Helper.DataHelper
import com.example.classproject.Models.Employee
import com.example.classproject.databinding.ActivityInsertBinding

class InsertActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsertBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataHelper = DataHelper(this)

        binding.bInsert.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Confirm")
                .setMessage("Are you sure to insert it?")
                .setCancelable(true)
                .setPositiveButton("No"){ dialog, which ->
                    // Your code
                }
                .setNegativeButton("Yes"){ dialog, which ->
                    val studentId = Integer.parseInt(binding.etNim.text.toString())
                    val name = binding.etName.text.toString()
                    val faculty = binding.etFaculty.text.toString()
                    val gender = findViewById<RadioButton>(binding.rgGender.checkedRadioButtonId)
                    dataHelper.addStudent(Employee(studentId, name, gender.text.toString(), faculty))
                    binding.etName.setText("")
                    binding.etNim.setText("")
                    binding.etFaculty.setText("")
                    finish()
                }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}
