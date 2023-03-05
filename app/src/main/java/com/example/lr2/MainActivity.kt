package com.example.lr2

import android.app.DownloadManager.Query
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val d = DBHelper.getInstance(this).writableDatabase



        val valuesBook = ContentValues()
        valuesBook.put(DBContract.Books.COLUMN_NAME, "Война и Мир")
        valuesBook.put(DBContract.Books.COLUMN_AUTHOR, "Л.Н. Толстой")
       val newBook =  d.insert(DBContract.Books.TABLE_NAME, null, valuesBook)

        val valuesPerson = ContentValues()
        valuesPerson.put(DBContract.Persons.COLUMN_NAME, "Олег Олегович")
        val newPerson = d.insert(DBContract.Persons.TABLE_NAME, null, valuesPerson)

        val valuesLibraries = ContentValues()
        valuesLibraries.put(DBContract.Libraries.COLUMN_NAME, "Библиотека имени Пушкина")
        valuesLibraries.put(DBContract.Libraries.COLUMN_ADRESS, "Мира 18")
        val newLibraries = d.insert(DBContract.Libraries.TABLE_NAME, null, valuesLibraries)

        val projection = arrayOf(
            BaseColumns._ID,
            DBContract.Books.COLUMN_NAME,
            DBContract.Books.COLUMN_AUTHOR
        )

        val selection = DBContract.Books.COLUMN_NAME + " = ?"
        val selectionArgs = arrayOf("user")

        val sortOrder = DBContract.Books.COLUMN_NAME + " DESC"

        val cursor = d.query(
            DBContract.Books.TABLE_NAME,  // The table to query
            projection,  // The array of columns to return (pass null to get all)
            selection,  // The columns for the WHERE clause
            selectionArgs,  // The values for the WHERE clause
            null,  // don't group the rows
            null,  // don't filter by row groups
            sortOrder // The sort order
        )

        Log.d( "DBContract",DBContract.Books.TABLE_NAME.toString())
        Log.d( "projection",projection.toString())
        Log.d( "selection",selection.toString())
        Log.d( "selectionArgs",selectionArgs.toString())
        Log.d( "sortOrder",sortOrder.toString())

        Log.d("Books", newBook.toString())
        Log.d("Persons", newPerson.toString())
        Log.d("Libraries", newLibraries.toString())

        Log.d("Quary", cursor.toString())

        with(cursor) {
            while (moveToNext()) {
                Log.d("AUTHOR", "${cursor.getLong(getColumnIndexOrThrow(BaseColumns._ID))} -|||- ${cursor.getString(getColumnIndexOrThrow(DBContract.Books.COLUMN_NAME))} -|||- ${cursor.getString(getColumnIndexOrThrow(DBContract.Books.COLUMN_AUTHOR))} ")
            }
        }
        cursor.close()



    }
}