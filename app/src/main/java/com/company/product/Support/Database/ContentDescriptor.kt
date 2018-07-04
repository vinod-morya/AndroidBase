package com.company.product.Support.Database

import android.content.UriMatcher
import android.net.Uri
import com.company.product.BuildConfig
import com.company.product.Support.Database.Tables.ContactsTable
import com.company.product.Support.Database.Tables.MasterUserTable


object ContentDescriptor {
    private val AUTHORITY = BuildConfig.APPLICATION_ID
    val BASE_URI = Uri.parse("content://$AUTHORITY")
    val URI_MATCHER = buildUriMatcher()

    private fun buildUriMatcher(): UriMatcher {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(AUTHORITY, MasterUserTable.PATH, MasterUserTable.PATH_TOKEN)
        matcher.addURI(AUTHORITY, ContactsTable.PATH, ContactsTable.PATH_TOKEN)
        return matcher
    }
}