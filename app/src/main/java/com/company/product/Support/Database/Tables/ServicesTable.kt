package com.company.product.Support.Database.Tables

import com.company.product.Support.Database.ContentDescriptor


object ServicesTable {
    val TABLE_NAME = "services"
    val PATH = "services"
    val PATH_TOKEN = 30
    val CONTENT_URI = ContentDescriptor.BASE_URI.buildUpon().appendPath(PATH).build()

    object Cols {
        val ID = "_id"
        val NAME = "name"
        val EXTRA_PARAM = "extraParam"
        val LAST_ACCESS_TIME = "lastAccessTime"
    }
}