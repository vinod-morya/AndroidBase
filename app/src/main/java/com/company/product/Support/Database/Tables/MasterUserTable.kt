package com.company.product.Support.Database.Tables

import android.net.Uri

import com.company.product.Support.Database.ContentDescriptor


object MasterUserTable {
    val TABLE_NAME = "masteruser"
    val PATH = "masteruser"
    val PATH_TOKEN = 10
    val CONTENT_URI = ContentDescriptor.BASE_URI.buildUpon().appendPath(PATH).build()

    object Cols {
        val ID = "_id"
        val QBID = "quickbloxid"
        val USERNAME = "username"
        val FULLNAME = "fullname"
        val PROFILE_PIC = "profilepic"
        val COVER_PIC = "coverPic"
        val EMAIL = "email"
        val COUNTRYCODE = "countrycode"
        val PHONE = "phone"
        val THEME = "theme"
        val SUBSCRIPTION_START_DATE = "subscriptionStartDate"
        val SUBSCRIPTION_TYPE = "accountType"
        val ACCESSTOKEN = "accesstoken"
        val PC_ID = "pc_id"
        val PC_CODE_TYPE = "pc_code_type"
        val PC_CODE = "pc_code"
        val PC_DISCOUNT_TYPE = "pc_discount_type"
        val PC_DISCOUNT = "pc_discount"
        val PC_MAX_LIMIT = "pc_max_limit"
        val PC_USED_COUNT = "pc_used_count"
    }
}