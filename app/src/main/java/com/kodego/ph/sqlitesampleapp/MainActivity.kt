package com.kodego.ph.sqlitesampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kodego.ph.sqlitesampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding //(1)Binding Kotlin File from XML File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)//(2)Binding Kotlin File from XML File
        setContentView(binding.root)//(3)Binding Kotlin File from XML File

        binding.btnSave.setOnClickListener(){
            lateinit var employeeModel: EmployeeModel
            try {
                var name = binding.etName.text.toString()
                var salary = binding.etSalary.text.toString().toInt()

                employeeModel = EmployeeModel(-1, name, salary)

                var databaseHelper = DatabaseHelper(applicationContext)
                databaseHelper.addOne(employeeModel)

                Toast.makeText(applicationContext, "Success!", Toast.LENGTH_LONG).show()

            }catch (e: Exception){
                Toast.makeText(applicationContext, "Error Occurred!", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnView.setOnClickListener(){
            var databaseHelper = DatabaseHelper(applicationContext)
            var everyEmployee: List<EmployeeModel> = databaseHelper.getAllData()
            Toast.makeText(applicationContext, everyEmployee.toString(), Toast.LENGTH_LONG).show()
        }
    }
}