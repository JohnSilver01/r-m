package com.john.portfolio.data.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.john.portfolio.models.DataComments

class MyDbHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME,
    factory, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL(DELETE_TABLE)
        onCreate(db)
    }

    fun addComment(comment : String, rating : String ){
        val values = ContentValues()

        values.put(COLUMN_NAME_COMMENT, comment)
        values.put(COLUMN_NAME_RATING, rating)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        db.close()
    }

    fun readData(): List<DataComments>{
        val dataList = ArrayList<DataComments>()
        val db = this.readableDatabase
        val cursor: Cursor?

        try{
            cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        }catch(e: SQLiteException) {
            db.execSQL("SELECT * FROM $TABLE_NAME")
            return ArrayList()
        }
        var comment: String
        var rating: String
        if (cursor.moveToFirst()){
            do {
                comment = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_COMMENT))
                rating = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_RATING))
                dataList.add(DataComments(rating, comment))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return dataList
    }

    fun delete(id: Int) {
        val db = this.writableDatabase

        db.delete(TABLE_NAME, "id = ?",
            arrayOf(id.toString()))

        db.close()
    }

    companion object{
        const val TABLE_NAME = "comment_entity"
        const val COLUMN_NAME_COMMENT = "comment"
        const val COLUMN_NAME_RATING = "rating"

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MyCommentDb"

        const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY, $COLUMN_NAME_COMMENT TEXT, $COLUMN_NAME_RATING TEXT)"
        const val DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}