package com.company.product.Support.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.company.product.BuildConfig
import com.company.product.Support.AppPrefrences
import com.company.product.Support.Database.Tables.ContactsTable
import com.company.product.Support.Database.Tables.MasterUserTable
import com.company.product.Support.Database.Tables.ServicesTable
import com.orhanobut.logger.Logger
import java.text.MessageFormat

internal class DatabaseHelperChat(context: Context) : SQLiteOpenHelper(context, DB_NAME + AppPrefrences(context, AppPrefrences.FILE_USER).getPref(AppPrefrences.PREFS_USER_ID, ""), null,
        CURRENT_DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        createMasterUserTable(db)
        createContactsTable(db)
        createServicesTable(db)
        Logger.d("DatabaseHelper", "database created")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        dropTable(db, MasterUserTable.TABLE_NAME)
        dropTable(db, ContactsTable.TABLE_NAME)
        dropTable(db, ServicesTable.TABLE_NAME)
        onCreate(db)
    }

    private fun dropTable(db: SQLiteDatabase, name: String) {
        val query = MessageFormat.format(DatabaseHelperChat.KEY_DROP_TABLE, name)
        db.execSQL(query)
    }

    private fun createTable(db: SQLiteDatabase, name: String, fields: String) {
        val query = MessageFormat.format(DatabaseHelperChat.KEY_CREATE_TABLE, name, fields)
        db.execSQL(query)
    }

    //@formatter:off
    private fun createMasterUserTable(db: SQLiteDatabase) {
        val masterTableFields = MasterUserTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MasterUserTable.Cols.QBID + " TEXT, " +
                MasterUserTable.Cols.USERNAME + " TEXT, " +
                MasterUserTable.Cols.FULLNAME + " TEXT, " +
                MasterUserTable.Cols.EMAIL + " TEXT," + " " +
                MasterUserTable.Cols.COUNTRYCODE + " TEXT, " +
                MasterUserTable.Cols.PHONE + " TEXT," + " " +
                MasterUserTable.Cols.THEME + " TEXT, " +
                MasterUserTable.Cols.PROFILE_PIC + " TEXT, " +
                MasterUserTable.Cols.COVER_PIC + " TEXT, " +
                MasterUserTable.Cols.SUBSCRIPTION_START_DATE + " TEXT, " +
                MasterUserTable.Cols.SUBSCRIPTION_TYPE + " INTEGER, " +
                MasterUserTable.Cols.ACCESSTOKEN + " TEXT, " +
                MasterUserTable.Cols.PC_ID + " TEXT, " +
                MasterUserTable.Cols.PC_CODE + " TEXT, " +
                MasterUserTable.Cols.PC_CODE_TYPE + " TEXT, " +
                MasterUserTable.Cols.PC_DISCOUNT + " TEXT, " +
                MasterUserTable.Cols.PC_DISCOUNT_TYPE + " TEXT, " +
                MasterUserTable.Cols.PC_MAX_LIMIT + " TEXT, " +
                MasterUserTable.Cols.PC_USED_COUNT + " TEXT"
        createTable(db, MasterUserTable.TABLE_NAME, masterTableFields)
    }

    private fun createContactsTable(db: SQLiteDatabase) {
        val contactsTableFields = ContactsTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContactsTable.Cols.FRIENDROWID + " TEXT, " +
                ContactsTable.Cols.FRIENDID + " TEXT, " +
                ContactsTable.Cols.FRIENDNAME + " TEXT," + " " +
                ContactsTable.Cols.FRIENDFULLNAME + " TEXT, " +
                ContactsTable.Cols.FRIENDEMAIL + " TEXT, " +
                ContactsTable.Cols.FRIENDCOUNTRYCODE + " TEXT, " +
                ContactsTable.Cols.FRIENDPHONE + " TEXT, " +
                ContactsTable.Cols.STATUS + " TEXT, " +
                ContactsTable.Cols.CREATIONDATE + " TEXT, " +
                ContactsTable.Cols.PROFILESTATUS + " TEXT, " +
                ContactsTable.Cols.FRIENDIMAGE + " TEXT, " +
                ContactsTable.Cols.FRIENDCOVERIMAGE + " TEXT, " +
                ContactsTable.Cols.USERLOCATION + " TEXT, " +
                ContactsTable.Cols.ISSENDREQUEST + " TEXT, " +
                ContactsTable.Cols.BLOCKBYUSER + " INTEGER, " +
                ContactsTable.Cols.HIDEPROFILE + " TEXT, " +
                ContactsTable.Cols.LAST_MESSAGE + " TEXT, " +
                ContactsTable.Cols.LAST_MESSAGE_TIME + " LONG, " +
                ContactsTable.Cols.UNREAD_MESSAGE_COUNT + " INTEGER, " +
                ContactsTable.Cols.IS_ONLINE + " INTEGER"
        createTable(db, ContactsTable.TABLE_NAME, contactsTableFields)
    }

    private fun createServicesTable(db: SQLiteDatabase) {
        val servicesTableField = ServicesTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ServicesTable.Cols.NAME + " TEXT, " +
                ServicesTable.Cols.EXTRA_PARAM + " TEXT, " +
                ServicesTable.Cols.LAST_ACCESS_TIME + " TEXT"
        createTable(db, ServicesTable.TABLE_NAME, servicesTableField)
    }


    companion object {
        private val KEY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS {0} ({1})"
        private val KEY_DROP_TABLE = "DROP TABLE IF EXISTS {0}"
        private val CURRENT_DB_VERSION = BuildConfig.VERSION_CODE
        private val DB_NAME = BuildConfig.APPLICATION_ID + ".db"
    }
}