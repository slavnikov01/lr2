package com.example.lr2

import android.provider.BaseColumns

class DBContract {
    object Books : BaseColumns {
        const val TABLE_NAME = "BOOKS"
        const val COLUMN_NAME = "Name"
        const val COLUMN_AUTHOR = "Author"
    }

    object Persons : BaseColumns {
        const val TABLE_NAME = "PERSONS"
        const val COLUMN_NAME = "Name"
    }

    object Libraries : BaseColumns {
        const val TABLE_NAME = "LIBRARIES"
        const val COLUMN_NAME = "Name"
        const val COLUMN_ADRESS = "Adress"
    }

    object PersonBook : BaseColumns {
        const val TABLE_NAME = "PERSONBOOK"
        const val COLUMN_BOOK = "BOOK_ID"
        const val COLUMN_PERSON = "PERSON_ID"
    }

    object LibraryBook : BaseColumns {
        const val TABLE_NAME = "LIBRARYBOOK"
        const val COLUMN_BOOK = "BOOK_ID"
        const val COLUMN_LIBRARY = "LIBRARY_ID"
    }

    object PersonLibrary : BaseColumns {
        const val TABLE_NAME = "PERSONLIBRARY"
        const val COLUMN_LIBRARY = "LIBRARY_ID"
        const val COLUMN_PERSON = "PERSON_ID"
    }

    companion object {

        const val SQL_DELETE_BOOKS = "DROP TABLE IF EXISTS ${Books.TABLE_NAME}"

        const val SQL_CREATE_BOOKS =
            "CREATE TABLE ${Books.TABLE_NAME} (${BaseColumns._ID} INTEGER PRIMARY KEY,${Books.COLUMN_NAME} TEXT,${Books.COLUMN_AUTHOR} TEXT)"

        const val SQL_DELETE_PERSON = "DROP TABLE IF EXISTS ${Persons.TABLE_NAME}"

        const val SQL_CREATE_PERSON =
            "CREATE TABLE ${Persons.TABLE_NAME} (${BaseColumns._ID} INTEGER PRIMARY KEY,${Persons.COLUMN_NAME} TEXT)"

        const val SQL_DELETE_LIBRARIES = "DROP TABLE IF EXISTS ${Libraries.TABLE_NAME}"

        const val SQL_CREATE_LIBRARIES =
            "CREATE TABLE ${Libraries.TABLE_NAME} (${BaseColumns._ID} INTEGER PRIMARY KEY,${Libraries.COLUMN_NAME} TEXT,${Libraries.COLUMN_ADRESS} TEXT)"

        const val SQL_DELETE_PERSON_LIBRARIES = "DROP TABLE IF EXISTS ${PersonLibrary.TABLE_NAME}"

        const val SQL_CREATE_PERSON_LIBRARIES =
            """CREATE TABLE ${PersonLibrary.TABLE_NAME} (${BaseColumns._ID} INTEGER PRIMARY KEY,${PersonLibrary.COLUMN_LIBRARY} INTEGER NOT NULL,${PersonLibrary.COLUMN_PERSON} INTEGER NOT NULL, CONSTRAINT "PERSON_FKEY" FOREIGN KEY("LIBRARY_ID") REFERENCES "PERSONS.ID",
	CONSTRAINT "LIBRARY_FKEY" FOREIGN KEY("PERSON_ID") REFERENCES "LIBRARIES.ID"
)"""
        const val SQL_DELETE_LIBRARY_BOOK = "DROP TABLE IF EXISTS ${LibraryBook.TABLE_NAME}"

        const val SQL_CREATE_LIBRARY_BOOK =
            """CREATE TABLE ${LibraryBook.TABLE_NAME} (${BaseColumns._ID} INTEGER PRIMARY KEY,${LibraryBook.COLUMN_BOOK} INTEGER NOT NULL,${LibraryBook.COLUMN_LIBRARY} INTEGER NOT NULL, CONSTRAINT "BOOK_FKEY" FOREIGN KEY("LIBRARY_ID") REFERENCES "BOOKS.ID",
	CONSTRAINT "LIBRARY_FKEY" FOREIGN KEY("BOOK_ID") REFERENCES "LIBRARIES.ID"
)"""
        const val SQL_DELETE_PERSON_BOOK = "DROP TABLE IF EXISTS ${PersonBook.TABLE_NAME}"

        const val SQL_CREATE_PERSON_BOOK =
            """CREATE TABLE ${PersonBook.TABLE_NAME} (${BaseColumns._ID} INTEGER PRIMARY KEY,${PersonBook.COLUMN_BOOK} INTEGER NOT NULL,${PersonBook.COLUMN_PERSON} INTEGER NOT NULL, CONSTRAINT "BOOK_FKEY" FOREIGN KEY("PERSON_ID") REFERENCES "BOOKS.ID",
	CONSTRAINT "PERSON_FKEY" FOREIGN KEY("BOOK_ID") REFERENCES "PERSONS.ID"
)"""
    }

}