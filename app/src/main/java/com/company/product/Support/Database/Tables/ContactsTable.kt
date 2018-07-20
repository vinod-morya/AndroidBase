package com.company.product.Support.Database.Tables

import android.net.Uri

import com.company.product.Support.Database.ContentDescriptor


object ContactsTable {
    val TABLE_NAME = "contacts"
    val PATH = "contacts"
    val PATH_TOKEN = 20
    val CONTENT_URI = ContentDescriptor.BASE_URI.buildUpon().appendPath(PATH).build()

    object Cols {
        val ID = "_id"
        val FRIENDROWID = "friendRowId"
        val FRIENDID = "friendId"
        val FRIENDNAME = "friendName"
        val FRIENDFULLNAME = "friendFullName"
        val FRIENDEMAIL = "friendEmail"
        val FRIENDCOUNTRYCODE = "friendCountryCode"
        val FRIENDPHONE = "friendPhone"
        val FRIENDIMAGE = "friendImage"
        val FRIENDCOVERIMAGE = "friendCoverImage"
        val PROFILESTATUS = "profileStatus"
        val USERLOCATION = "userLocation"
        val CREATIONDATE = "creationDate"
        val STATUS = "status"
        val ISSENDREQUEST = "isSendRequest"
        val BLOCKBYUSER = "blockByUser"
        val HIDEPROFILE = "hideProfile"
        val LAST_MESSAGE = "last_message"
        val LAST_MESSAGE_TIME = "last_message_time"
        val UNREAD_MESSAGE_COUNT = "unread_msg_count"
        val IS_ONLINE = "online"
    }
}