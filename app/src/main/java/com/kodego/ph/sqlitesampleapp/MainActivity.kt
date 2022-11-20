package com.kodego.ph.sqlitesampleapp

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodego.ph.sqlitesampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding //(1)Binding Kotlin File from XML File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)//(2)Binding Kotlin File from XML File
        setContentView(binding.root)//(3)Binding Kotlin File from XML File


        var databaseHelper = DatabaseHelper(applicationContext)
        var everyEmployee: MutableList<EmployeeModel> = databaseHelper.getAllData()
        var adapter = EmployeeAdapter(everyEmployee)
        adapter.onEmployeeDelete = { item: EmployeeModel, position: Int ->

        AlertDialog.Builder(this).setMessage("Do you want to delete this data? \n'${item.name} = ${item.salary}'")
            .setPositiveButton("Delete"){ dialog, item2 ->
                //delete from table database
                var databaseHelper = DatabaseHelper(applicationContext)
                databaseHelper.deleteOne(item)

                //delete from recyclerview
                adapter.employeeModel.removeAt(position)
                adapter.notifyDataSetChanged()

                Toast.makeText(applicationContext, "Successful Deleted!", Toast.LENGTH_SHORT).show()
            }

            .setNegativeButton("Cancel"){dialog, item ->

            }.show()
        }

        binding.recyclerViewEmployees.adapter = adapter
        binding.recyclerViewEmployees.layoutManager = LinearLayoutManager(this)

        binding.btnSave.setOnClickListener(){
            lateinit var employeeModel: EmployeeModel
            try {
                var name = binding.etName.text.toString()
                var salary = binding.etSalary.text.toString().toInt()

                //creates new employee object
                employeeModel = EmployeeModel(-1, name, salary)
                //add new employee to recyclerview
                adapter.employeeModel.add(employeeModel)
                adapter.notifyDataSetChanged()

                //add new employee to database
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