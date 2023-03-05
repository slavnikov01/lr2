package com.example.lr2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
        override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
            sqLiteDatabase.execSQL(DBContract.SQL_CREATE_BOOKS)
            sqLiteDatabase.execSQL(DBContract.SQL_CREATE_PERSON)
            sqLiteDatabase.execSQL(DBContract.SQL_CREATE_LIBRARIES)
            sqLiteDatabase.execSQL(DBContract.SQL_CREATE_PERSON_LIBRARIES)
            sqLiteDatabase.execSQL(DBContract.SQL_CREATE_LIBRARY_BOOK)
            sqLiteDatabase.execSQL(DBContract.SQL_CREATE_PERSON_BOOK)
        }


        override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
//            sqLiteDatabase.execSQL(DBContract.SQL_DELETE_BOOKS)
//            sqLiteDatabase.execSQL(DBContract.SQL_DELETE_PERSON)
//            sqLiteDatabase.execSQL(DBContract.SQL_DELETE_LIBRARIES)
//            sqLiteDatabase.execSQL(DBContract.SQL_DELETE_PERSON_LIBRARIES)
//            sqLiteDatabase.execSQL(DBContract.SQL_DELETE_LIBRARY_BOOK)
//            sqLiteDatabase.execSQL(DBContract.SQL_DELETE_PERSON_BOOK)

            onCreate(sqLiteDatabase)
        }

        override fun onDowngrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            onUpgrade(sqLiteDatabase, oldVersion, newVersion)
        }

        companion object {
            const val DB_VERSION = 1
            const val DB_NAME = "SampleStore.db"

            @Volatile
            private var instance: DBHelper? = null
            fun getInstance(context: Context): DBHelper {
                if (instance==null)
                    synchronized(DBHelper::class.java) {
                        if (instance == null) {
                            instance = DBHelper(context)
                        }
                    }
                return instance!!
            }

        }

    }
