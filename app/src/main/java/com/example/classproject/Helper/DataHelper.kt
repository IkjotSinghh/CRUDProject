package com.example.classproject.Helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.classproject.Models.Employee


class DataHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,
    DATABASE_VERSION
){
    companion object{
        private val DATABASE_NAME = "database.db"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "STUDENT"
        val KEY_NIM = "nim"
        val KEY_NAME = "name"
        val KEY_GENDER = "gender"
        val KEY_FAC = "faculty"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + "NIM" + " INTEGER PRIMARY KEY," +
                "NAME" + " TEXT," +
                "GENDER" + " TEXT,"+
                "FACULTY" + " TEXT)")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME)
        onCreate(db)
    }

    fun addStudent(student: Employee):Boolean{
        var db = this.writableDatabase
        var values = ContentValues()
        values.put(KEY_NIM,student.nim)
        values.put(KEY_NAME,student.name)
        values.put(KEY_GENDER,student.gender)
        values.put(KEY_FAC,student.faculty)
        val success = db.insert(TABLE_NAME,null,values)
        db.close()

        if (success.toInt() == -1){
            Toast.makeText(context,"An error occurred during the insert, please try again",Toast.LENGTH_SHORT).show()
            return false
        }
        else{
            Toast.makeText(context,"Success Insert, Try Refresh",Toast.LENGTH_SHORT).show()
            return true
        }
    }

    fun deleteStudent(student: Employee){
        var db = this.writableDatabase
        val selectionArgs = arrayOf(student.nim.toString())
        db.delete(TABLE_NAME, KEY_NIM +" = ? ",selectionArgs)
    }

    fun getAllStudent():ArrayList<Employee>{
        var db = this.writableDatabase
        var studentList : ArrayList<Employee>
            studentList= ArrayList<Employee>()

        val selectAll = "SELECT * FROM "+ TABLE_NAME
        val cursor = db.rawQuery(selectAll,null)
        if (cursor.moveToFirst()){
            do{
                val student = Employee()
                student.nim = cursor.getInt(0)
                student.name = cursor.getString(1)
                student.gender = cursor.getString(2)
                student.faculty = cursor.getString(3)
                studentList.add(student)
            } while (cursor.moveToNext())
        }
        return studentList
    }

}
