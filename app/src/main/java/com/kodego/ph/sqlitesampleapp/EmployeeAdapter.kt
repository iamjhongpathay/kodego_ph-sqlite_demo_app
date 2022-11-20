package com.kodego.ph.sqlitesampleapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kodego.ph.sqlitesampleapp.databinding.RowEmployeeBinding

class EmployeeAdapter(var employeeModel: MutableList<EmployeeModel>): RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    var onEmployeeDelete : ((EmployeeModel, Int) -> Unit) ? = null

    inner class EmployeeViewHolder(var binding: RowEmployeeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowEmployeeBinding.inflate(layoutInflater, parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.binding.apply {
            tvEmployeeName.text = employeeModel[position].name
            tvSalary.text = employeeModel[position].salary.toString()
            btnDeleteIcon.setOnClickListener(){
                onEmployeeDelete?.invoke(employeeModel[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return employeeModel.size
    }
}