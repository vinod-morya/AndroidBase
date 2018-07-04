package com.company.product.Support.Database

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import com.company.product.Support.Database.Tables.ContactsTable
import com.company.product.Support.Database.Tables.MasterUserTable

class DatabaseProvider : ContentProvider() {
    private var dbHelper: DatabaseHelperChat? = null

    override fun onCreate(): Boolean {
        //		  dbHelper = new DatabaseHelperChat(getContext());
        //		  dbHelper.getWritableDatabase();
        return false
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        if (dbHelper == null) {
            dbHelper = DatabaseHelperChat(context)
        }
        val db = dbHelper!!.readableDatabase
        val token = ContentDescriptor.URI_MATCHER.match(uri)
        var result: Cursor? = null
        when (token) {
            MasterUserTable.PATH_TOKEN -> {
                result = doQuery(db, uri, MasterUserTable.TABLE_NAME, projection, selection, selectionArgs, sortOrder)
            }
            ContactsTable.PATH_TOKEN -> {
                result = doQuery(db, uri, ContactsTable.TABLE_NAME, projection, selection, selectionArgs, sortOrder)
            }
        }
        return result
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        if (dbHelper == null) {
            dbHelper = DatabaseHelperChat(context)
        }
        dbHelper!!.writableDatabase
        val db = dbHelper!!.writableDatabase
        val token = ContentDescriptor.URI_MATCHER.match(uri)
        var result: Uri? = null
        when (token) {
            MasterUserTable.PATH_TOKEN -> {
                result = doInsert(db, MasterUserTable.TABLE_NAME, MasterUserTable.CONTENT_URI, uri, values)
            }
            ContactsTable.PATH_TOKEN -> {
                result = doInsert(db, ContactsTable.TABLE_NAME, ContactsTable.CONTENT_URI, uri, values)
            }
        }
        if (result == null) {
            throw IllegalArgumentException(UNKNOWN_URI + uri)
        }
        return result
    }

    override fun bulkInsert(uri: Uri, values: Array<ContentValues>): Int {
        var table: String? = null
        val token = ContentDescriptor.URI_MATCHER.match(uri)
        when (token) {
            ContactsTable.PATH_TOKEN -> {
                table = ContactsTable.TABLE_NAME
            }
            MasterUserTable.PATH_TOKEN -> {
                table = MasterUserTable.TABLE_NAME
            }
        }
        val db = dbHelper!!.writableDatabase
        db.beginTransaction()
        for (cv in values) {
            db.insert(table, null, cv)
        }
        db.setTransactionSuccessful()
        db.endTransaction()
        context!!.contentResolver.notifyChange(uri, null)
        return values.size
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        if (dbHelper == null) {
            dbHelper = DatabaseHelperChat(context)
        }
        val db = dbHelper!!.writableDatabase
        val token = ContentDescriptor.URI_MATCHER.match(uri)
        var result = 0
        when (token) {
            MasterUserTable.PATH_TOKEN -> {
                result = doDelete(db, uri, MasterUserTable.TABLE_NAME, selection, selectionArgs)
            }
            ContactsTable.PATH_TOKEN -> {
                result = doDelete(db, uri, ContactsTable.TABLE_NAME, selection, selectionArgs)
            }
        }
        return result
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        if (dbHelper == null) {
            dbHelper = DatabaseHelperChat(context)
        }
        val db = dbHelper!!.writableDatabase
        val token = ContentDescriptor.URI_MATCHER.match(uri)
        var result = 0
        when (token) {
            MasterUserTable.PATH_TOKEN -> {
                result = doUpdate(db, uri, MasterUserTable.TABLE_NAME, selection, selectionArgs, values)
            }
            ContactsTable.PATH_TOKEN -> {
                result = doUpdate(db, uri, ContactsTable.TABLE_NAME, selection, selectionArgs, values)
            }
        }
        return result
    }

    fun closeDatabase() {
        val db = dbHelper!!.readableDatabase
        db.close()
    }

    private fun doQuery(db: SQLiteDatabase, uri: Uri, tableName: String, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor {
        val builder = SQLiteQueryBuilder()
        builder.tables = tableName
        val result = builder.query(db, projection, selection, selectionArgs, sortOrder, null, null)
        result.setNotificationUri(context!!.contentResolver, uri)
        return result
    }

    private fun doUpdate(db: SQLiteDatabase, uri: Uri, tableName: String, selection: String?, selectionArgs: Array<String>?, values: ContentValues?): Int {
        val result = db.update(tableName, values, selection, selectionArgs)
        context!!.contentResolver.notifyChange(uri, null)
        return result
    }

    private fun doDelete(db: SQLiteDatabase, uri: Uri, tableName: String, selection: String?, selectionArgs: Array<String>?): Int {
        val result = db.delete(tableName, selection, selectionArgs)
        context!!.contentResolver.notifyChange(uri, null)
        return result
    }

    private fun doInsert(db: SQLiteDatabase, tableName: String, contentUri: Uri, uri: Uri, values: ContentValues?): Uri {
        val id = db.insert(tableName, null, values)
        val result = contentUri.buildUpon().appendPath(id.toString()).build()
        context!!.contentResolver.notifyChange(uri, null)
        return result
    }

    companion object {
        private val UNKNOWN_URI = "Unknown URI "
    }
}