package com.kodego.ph.sqlitesampleapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "company.db", null, 1) {
    //this is called when a new database is created
    override fun onCreate(db: SQLiteDatabase?) {
        var createTable: String = "CREATE TABLE EMPLOYEE (id integer primary key autoincrement, name varchar(30), salary int)"
        db?.execSQL(createTable)
    }

    //whenever there are changes to the database
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addOne(employeeModel: EmployeeModel){
        var db = this.writableDatabase
        var cv = ContentValues()

        cv.put("name", employeeModel.name)
        cv.put("salary", employeeModel.salary)

        db.insert("EMPLOYEE", null, cv)
    }

    fun getAllData():List<EmployeeModel>{
        var returnList = ArrayList<EmployeeModel>()
        var queryString: String = "SELECT * FROM EMPLOYEE"
        var db = this.readableDatabase

        var cursor: Cursor = db.rawQuery(queryString, null)
        if(cursor.moveToFirst()){
            do {
                var id = cursor.getInt(0)
                var name = cursor.getString(1)
                var salary = cursor.getInt(2)

                var newEmployeeModel: EmployeeModel = EmployeeModel(id, name, salary)
                returnList.add(newEmployeeModel)

            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return returnList
    }
}