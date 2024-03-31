package com.example.classproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.classproject.Adapters.EmployeeAdapter
import com.example.classproject.Helper.DataHelper
import com.example.classproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: EmployeeAdapter

    override fun onResume() {
        super.onResume()
        studentAdapter.getUpdate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bInsertEmployees.setOnClickListener {
            val intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
        }
        val dataHelper = DataHelper(this)
        Log.d("read", "read data")
        val studentList = dataHelper.getAllStudent()
        studentAdapter = EmployeeAdapter(this@MainActivity, studentList) // Instantiate studentAdapter

        binding.rvStudent.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = studentAdapter
        }
    }
}
