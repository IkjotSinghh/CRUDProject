package com.example.classproject.Adapters
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.classproject.Helper.DataHelper
import com.example.classproject.Models.Employee
import com.example.classproject.R
import com.example.classproject.databinding.ItemEmployeeBinding

class EmployeeAdapter(private val context: Context, private var EmployeeList: ArrayList<Employee>) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = EmployeeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(EmployeeList[position])
    }

    inner class ViewHolder(private val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(student: Employee) {
            binding.tvNim.text = student.nim.toString()
            binding.tvName.text = student.name
            binding.tvFaculty.text = student.faculty
            if (student.gender == "Male") {
                binding.ivStudent.setImageResource(R.drawable.baseline_boy_24)
            } else {
                binding.ivStudent.setImageResource(R.drawable.baseline_girl_24)
            }
            binding.root.setOnLongClickListener {
                val alertDialogBuilder = AlertDialog.Builder(itemView.context)
                alertDialogBuilder.setTitle("Confirm")
                    .setMessage("Are you sure to delete ${student.name}?")
                    .setCancelable(true)
                    .setPositiveButton("No") { dialog, which ->
                        Toast.makeText(itemView.context, "Cancel Delete", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Yes") { dialog, which ->
                        val db = DataHelper(itemView.context)
                        db.deleteStudent(student)
                        EmployeeList.remove(student)
                        notifyItemRemoved(adapterPosition) // More efficient than notifyDataSetChanged()
                        Toast.makeText(itemView.context, "Delete success", Toast.LENGTH_SHORT).show()
                    }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
                true
            }
        }
    }

    fun getUpdate() {
        val db = DataHelper(context)
        EmployeeList = db.getAllStudent()
        notifyDataSetChanged()
    }
}


