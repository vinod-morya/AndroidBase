package com.company.product.Support.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.text.TextUtils
import com.company.product.LoginModule.Model.ContactsDetailModel
import com.company.product.LoginModule.Model.ProfileDetailModel
import com.company.product.LoginModule.Model.UserLoginModel
import com.company.product.Support.C
import com.company.product.Support.Database.Tables.ContactsTable
import com.company.product.Support.Database.Tables.MasterUserTable
import com.company.product.Support.Database.Tables.ServicesTable
import java.util.*

class DatabaseManager {

    companion object {
        fun saveMasterUser(context: Context, userLoginModel: UserLoginModel) {
            val cvUser = ContentValues()
            cvUser.put(MasterUserTable.Cols.ID, userLoginModel.resultBean?.userId)
            cvUser.put(MasterUserTable.Cols.USERNAME, userLoginModel.resultBean?.userImage)
            cvUser.put(MasterUserTable.Cols.FULLNAME, userLoginModel.resultBean?.userFullName)
            cvUser.put(MasterUserTable.Cols.EMAIL, userLoginModel.resultBean?.userEmail)
            cvUser.put(MasterUserTable.Cols.COUNTRYCODE, userLoginModel.resultBean?.userCountryCode)
            cvUser.put(MasterUserTable.Cols.PHONE, userLoginModel.resultBean?.userPhone)
            cvUser.put(MasterUserTable.Cols.PROFILE_PIC, userLoginModel.resultBean?.userImage)
            cvUser.put(MasterUserTable.Cols.COVER_PIC, userLoginModel.resultBean?.coverImage)
            cvUser.put(MasterUserTable.Cols.THEME, userLoginModel.resultBean?.userThemeColor)
            cvUser.put(MasterUserTable.Cols.ACCESSTOKEN, userLoginModel.resultBean?.accessToken)
            //		  context.getContentResolver().insert(MasterUserTable.CONTENT_URI, cvUser);
            var cursor: Cursor? = null
            val condition = MasterUserTable.Cols.ID + "= ?"
            val contentResolver = context.contentResolver
            try {
                cursor = contentResolver.query(MasterUserTable.CONTENT_URI, null, condition, arrayOf(userLoginModel.resultBean?.userId.toString()), null)
                if (cursor != null && cursor.moveToNext()) {
                    contentResolver.update(MasterUserTable.CONTENT_URI, cvUser, condition, arrayOf(userLoginModel.resultBean?.userId.toString()))
                } else {
                    context.contentResolver.insert(MasterUserTable.CONTENT_URI, cvUser)
                }
            } catch (e: Exception) {
            } finally {
                if (cursor != null) {
                    cursor.close()
                }
            }
        }

        fun saveMasterUser(context: Context, userLoginModel: ProfileDetailModel) {
            val cvUser = ContentValues()
            cvUser.put(MasterUserTable.Cols.ID, userLoginModel.result?.userId)
            cvUser.put(MasterUserTable.Cols.USERNAME, userLoginModel.result?.userImage)
            cvUser.put(MasterUserTable.Cols.FULLNAME, userLoginModel.result?.userFullName)
            cvUser.put(MasterUserTable.Cols.EMAIL, userLoginModel.result?.userEmail)
            cvUser.put(MasterUserTable.Cols.COUNTRYCODE, "+" + userLoginModel.result?.userCountryCode)
            cvUser.put(MasterUserTable.Cols.PHONE, userLoginModel.result?.userPhone)
            cvUser.put(MasterUserTable.Cols.PROFILE_PIC, userLoginModel.result?.userImage)
            cvUser.put(MasterUserTable.Cols.COVER_PIC, userLoginModel.result?.userCoverImage)
            var cursor: Cursor? = null
            val condition = MasterUserTable.Cols.ID + "= ?"
            val contentResolver = context.contentResolver
            try {
                cursor = contentResolver.query(MasterUserTable.CONTENT_URI, null, condition, arrayOf(userLoginModel.result?.userId.toString()), null)
                if (cursor != null && cursor.moveToNext()) {
                    contentResolver.update(MasterUserTable.CONTENT_URI, cvUser, condition, arrayOf(userLoginModel.result?.userId.toString()))
                } else {
                    context.contentResolver.insert(MasterUserTable.CONTENT_URI, cvUser)
                }
            } catch (e: Exception) {
            } finally {
                if (cursor != null) {
                    cursor.close()
                }
            }
        }

        fun saveContact(context: Context, contact: ContactsDetailModel.Result) {
            var tempCV = ContentValues()
            if (checkAndUpdateContactIfInDatabase(context, contact)) {
                tempCV = getContentValuesFromContactDetailModel(contact)
                context.contentResolver.insert(ContactsTable.CONTENT_URI, tempCV)
            }
        }

        private fun checkAndUpdateContactIfInDatabase(context: Context, contact: ContactsDetailModel.Result): Boolean {
            var cursor: Cursor? = null
            val condition = ContactsTable.Cols.FRIENDROWID + "= ?"
            val contentResolver = context.contentResolver
            try {
                cursor = contentResolver.query(ContactsTable.CONTENT_URI, null, condition, arrayOf(contact.friendRowId.toString()), null)
                if (cursor != null && cursor.count > C.ZERO_INT_VALUE) {
                    val tempCV = getContentValuesFromContactDetailModel(contact)
                    val i = contentResolver.update(ContactsTable.CONTENT_URI, tempCV, condition, arrayOf(contact.friendRowId.toString()))

                    return false
                } else {
                    return true
                }
            } finally {
                if (cursor != null) {
                    cursor.close()
                }
            }
        }

        private fun getContentValuesFromContactDetailModel(contactsDetailModelResult: ContactsDetailModel.Result): ContentValues {
            val cvUser = ContentValues()
            cvUser.put(ContactsTable.Cols.FRIENDROWID, contactsDetailModelResult.friendRowId)
            cvUser.put(ContactsTable.Cols.FRIENDID, contactsDetailModelResult.friendId)
            cvUser.put(ContactsTable.Cols.FRIENDNAME, contactsDetailModelResult.friendName)
            cvUser.put(ContactsTable.Cols.FRIENDFULLNAME, contactsDetailModelResult.friendFullName)
            cvUser.put(ContactsTable.Cols.FRIENDEMAIL, contactsDetailModelResult.friendEmail)
            cvUser.put(ContactsTable.Cols.FRIENDCOUNTRYCODE, contactsDetailModelResult.friendCountryCode)
            cvUser.put(ContactsTable.Cols.FRIENDPHONE, contactsDetailModelResult.friendPhone)
            cvUser.put(ContactsTable.Cols.FRIENDIMAGE, contactsDetailModelResult.friendImage)
            cvUser.put(ContactsTable.Cols.FRIENDCOVERIMAGE, contactsDetailModelResult.friendCoverImage)
            cvUser.put(ContactsTable.Cols.PROFILESTATUS, contactsDetailModelResult.profileStatus)
            cvUser.put(ContactsTable.Cols.USERLOCATION, contactsDetailModelResult.userLocation)
            cvUser.put(ContactsTable.Cols.CREATIONDATE, contactsDetailModelResult.creationDate)
            cvUser.put(ContactsTable.Cols.STATUS, contactsDetailModelResult.status)
            cvUser.put(ContactsTable.Cols.ISSENDREQUEST, contactsDetailModelResult.isSendRequest)
            cvUser.put(ContactsTable.Cols.BLOCKBYUSER, contactsDetailModelResult.blockByUser)
            cvUser.put(ContactsTable.Cols.HIDEPROFILE, contactsDetailModelResult.hideProfile)
            if (contactsDetailModelResult.lastMessage != null) {
                cvUser.put(ContactsTable.Cols.LAST_MESSAGE, contactsDetailModelResult.lastMessage)
            }
            if (contactsDetailModelResult.lastMessageTime > 0) {
                cvUser.put(ContactsTable.Cols.LAST_MESSAGE_TIME, contactsDetailModelResult.lastMessageTime)
            }
            //		  if(contactsDetailModelResult.getUnreadMsgCount() != null)
            //		  {
            //			 cvUser.put(ContactsTable.Cols.UNREAD_MESSAGE_COUNT, contactsDetailModelResult.getUnreadMsgCount());
            //		  }
            //		  if(contactsDetailModelResult.isIsOnline() != null)
            //		  {
            //			 cvUser.put(ContactsTable.Cols.IS_ONLINE, contactsDetailModelResult.isIsOnline());
            //		  }
            return cvUser
        }

        fun saveContacts(context: Context, contactsDetailModel: ContactsDetailModel) {
            val ContactsList = contactsDetailModel.result
            val cvList = ArrayList<ContentValues>()
            for (result in ContactsList) {
                if (checkAndUpdateContactIfInDatabase(context, result)) {
                    val tempCV = getContentValuesFromContactDetailModel(result)
                    cvList.add(tempCV)
                }
            }
            context.contentResolver.bulkInsert(ContactsTable.CONTENT_URI, cvList.toTypedArray())
        }

        fun deleteContact(context: Context, contact: ContactsDetailModel.Result) {
            context.contentResolver.delete(ContactsTable.CONTENT_URI, ContactsTable.Cols.FRIENDROWID + " = ?", arrayOf(contact.friendRowId))
        }

        fun saveServiceLastAccessTime(context: Context, servicename: String, lastRequestTime: String) {
            val cv = getContentValuesForSavingServiesDetails(servicename, lastRequestTime)

            if (checkAndUpdateServiceIfInDatabase(context, cv, servicename, lastRequestTime)) {
                context.contentResolver.insert(ServicesTable.CONTENT_URI, cv)
            }
        }

        fun getServiceLastAccessTime(context: Context, service: String, extraParam: String?): String {
            var lastTime = ""
            var cursor: Cursor? = null
            val condition: String
            if (extraParam == null) {
                condition = ServicesTable.Cols.NAME + "= ?"
            } else {
                condition = ServicesTable.Cols.NAME + "= ? AND " + ServicesTable.Cols.EXTRA_PARAM + " = ?"
            }
            val contentResolver = context.contentResolver
            try {
                if (extraParam == null) {
                    cursor = contentResolver.query(ServicesTable.CONTENT_URI, null, condition, arrayOf(service), null)
                } else {
                    cursor = contentResolver.query(ServicesTable.CONTENT_URI, null, condition, arrayOf(service, extraParam), null)
                }
                if (cursor != null && cursor.count > C.ZERO_INT_VALUE && cursor.moveToNext()) {
                    lastTime = cursor.getString(cursor.getColumnIndex(ServicesTable.Cols.LAST_ACCESS_TIME))
                }
                return lastTime
            } finally {
                if (cursor != null) {
                    cursor.close()
                }
            }
        }

        private fun getContentValuesForSavingServiesDetails(service: String, lasttime: String): ContentValues {
            val cvUser = ContentValues()
            cvUser.put(ServicesTable.Cols.NAME, service)
            cvUser.put(ServicesTable.Cols.LAST_ACCESS_TIME, lasttime)
            return cvUser
        }

        private fun checkAndUpdateServiceIfInDatabase(context: Context, cv: ContentValues, service: String, lastTime: String): Boolean {
            var cursor: Cursor? = null
            val condition = ServicesTable.Cols.NAME + "= ?"
            val contentResolver = context.contentResolver
            return try {
                cursor = contentResolver.query(ServicesTable.CONTENT_URI, null, condition, arrayOf(service), null)
                if (cursor != null && cursor.count > C.ZERO_INT_VALUE) {
                    val i = contentResolver.update(ServicesTable.CONTENT_URI, cv, condition, arrayOf(service))
                    false
                } else {
                    true
                }
            } finally {
                if (cursor != null) {
                    cursor.close()
                }
            }
        }



        fun getContactsByFullname(context: Context, fullname: String): Cursor? {
            val cursor: Cursor?
            val sorting = MasterUserTable.Cols.ID + " ORDER BY " + MasterUserTable.Cols.FULLNAME + " COLLATE NOCASE ASC"
            if (TextUtils.isEmpty(fullname)) {
                cursor = context.contentResolver.query(MasterUserTable.CONTENT_URI, null, null, null, sorting)
            } else {
                cursor = context.contentResolver.query(MasterUserTable.CONTENT_URI, null, MasterUserTable.Cols.FULLNAME + " like '%" + fullname + "%'", null, sorting)
            }
            cursor?.moveToFirst()
            return cursor
        }

        fun getAllContactsCursor(context: Context): Cursor? {
            return context.contentResolver.query(ContactsTable.CONTENT_URI, null, null, null, ContactsTable.Cols.ID + " ORDER BY " + ContactsTable.Cols.FRIENDFULLNAME + " COLLATE NOCASE ASC")
        }

        fun getAllContactsList(context: Context): List<ContactsDetailModel.Result> {
            val condition = ContactsTable.Cols.STATUS + " = ? OR " + ContactsTable.Cols.STATUS + " = ?"
            val list = ArrayList<ContactsDetailModel.Result>()
            var cursor: Cursor? = null
            try {
                cursor = context.contentResolver.query(ContactsTable.CONTENT_URI, null, condition, arrayOf("1", "4"), ContactsTable.Cols.ID + " ORDER BY " + ContactsTable.Cols.FRIENDFULLNAME +
                        " COLLATE NOCASE ASC")
                if (cursor != null && cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        list.add(getContactFromCursor(cursor))
                    }
                }
            } finally {
                if (cursor != null && !cursor.isClosed) {
                    cursor.close()
                }
            }

            return list
        }

        private fun getContactFromCursor(cursor: Cursor): ContactsDetailModel.Result {
            //				public static final String ID = "_id";
            //				public static final String FRIENDROWID = "friendRowId";
            //				public static final String FRIENDID = "friendId";
            //				public static final String FRIENDNAME = "friendName";
            //				public static final String FRIENDFULLNAME = "friendFullName";
            //				public static final String FRIENDEMAIL = "friendEmail";
            //				public static final String FRIENDCOUNTRYCODE = "friendCountryCode";
            //				public static final String FRIENDPHONE = "friendPhone";
            //				public static final String FRIENDIMAGE = "friendImage";
            //				public static final String PROFILESTATUS = "profileStatus";
            //				public static final String USERLOCATION = "userLocation";
            //				public static final String CREATIONDATE = "creationDate";
            //				public static final String STATUS = "status";
            //				public static final String ISSENDREQUEST = "isSendRequest";
            //				public static final String BLOCKBYUSER = "blockByUser";
            //				public static final String HIDEPROFILE = "hideProfile";

            val cdm = ContactsDetailModel.Result()
            val rowId = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.FRIENDROWID))
            val friendId = cursor.getInt(cursor.getColumnIndex(ContactsTable.Cols.FRIENDID))
            val friendName = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.FRIENDNAME))
            val friendFullname = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.FRIENDFULLNAME))
            val countrycode = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.FRIENDCOUNTRYCODE))
            val phone = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.FRIENDPHONE))
            val userImage = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.FRIENDIMAGE))
            val email = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.FRIENDEMAIL))
            val creationDate = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.CREATIONDATE))
            val profileSatus = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.PROFILESTATUS))
            val location = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.USERLOCATION))
            val status = cursor.getInt(cursor.getColumnIndex(ContactsTable.Cols.STATUS))
            val isSendRequest = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.ISSENDREQUEST))
            val blockUser = cursor.getInt(cursor.getColumnIndex(ContactsTable.Cols.BLOCKBYUSER))
            val hideProfile = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.HIDEPROFILE))
            val lastMsg = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.LAST_MESSAGE))
            val lastMsgTime = cursor.getLong(cursor.getColumnIndex(ContactsTable.Cols.LAST_MESSAGE_TIME))
            val unreadCount = cursor.getInt(cursor.getColumnIndex(ContactsTable.Cols.UNREAD_MESSAGE_COUNT))
            val isOnline = cursor.getInt(cursor.getColumnIndex(ContactsTable.Cols.IS_ONLINE))
            cdm.friendRowId = rowId
            cdm.friendId = friendId
            cdm.friendName = friendName
            cdm.friendFullName = friendFullname
            cdm.friendCountryCode = countrycode
            cdm.friendPhone = phone
            cdm.friendImage = userImage
            cdm.friendEmail = email
            cdm.creationDate = creationDate
            cdm.profileStatus = profileSatus
            cdm.userLocation = location
            cdm.status = status
            cdm.isSendRequest = isSendRequest
            cdm.blockByUser = blockUser
            cdm.hideProfile = hideProfile
            cdm.lastMessage = lastMsg
            cdm.lastMessageTime = lastMsgTime
            cdm.unreadMsgCount = unreadCount
            cdm.isOnline = isOnline
            return cdm
        }

        fun getAllContactsListForChat(context: Context): List<ContactsDetailModel.Result> {
            val condition = ContactsTable.Cols.STATUS + " = ? OR " + ContactsTable.Cols.STATUS + " = ?"
            val list = ArrayList<ContactsDetailModel.Result>()
            var cursor: Cursor? = null
            try {
                cursor = context.contentResolver.query(ContactsTable.CONTENT_URI, null, condition, arrayOf("1", "4"), ContactsTable.Cols.ID + " ORDER BY " + ContactsTable.Cols
                        .LAST_MESSAGE_TIME +
                        " COLLATE NOCASE DESC")
                if (cursor != null && cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        list.add(getContactFromCursor(cursor))
                    }
                }
            } finally {
                if (cursor != null && !cursor.isClosed) {
                    cursor.close()
                }
            }

            return list
        }

        fun getAllTrustedContactsList(context: Context): List<ContactsDetailModel.Result> {
            val condition = ContactsTable.Cols.STATUS + " = ?"
            val list = ArrayList<ContactsDetailModel.Result>()
            var cursor: Cursor? = null
            try {
                cursor = context.contentResolver.query(ContactsTable.CONTENT_URI, null, condition, arrayOf("4"), ContactsTable.Cols.ID + " ORDER BY " + ContactsTable.Cols.FRIENDFULLNAME +
                        " COLLATE NOCASE ASC")
                if (cursor != null && cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        list.add(getContactFromCursor(cursor))
                    }
                }
            } finally {
                if (cursor != null && !cursor.isClosed) {
                    cursor.close()
                }
            }

            return list
        }

        fun getAllContactIdsList(context: Context): List<String> {
            val condition = ContactsTable.Cols.STATUS + " = ? OR " + ContactsTable.Cols.STATUS + " = ?"
            val list = ArrayList<String>()
            var cursor: Cursor? = null
            try {
                cursor = context.contentResolver.query(ContactsTable.CONTENT_URI, null, condition, arrayOf("1", "4"), ContactsTable.Cols.ID + " ORDER BY " + ContactsTable.Cols.FRIENDFULLNAME +
                        " COLLATE NOCASE ASC")
                if (cursor != null && cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        list.add(cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.FRIENDID)))
                    }
                }
            } finally {
                if (cursor != null && !cursor.isClosed) {
                    cursor.close()
                }
            }

            return list
        }

        fun getAllContactsEmailOrPhoneList(context: Context): List<String> {
            val condition = ContactsTable.Cols.STATUS + " = ? OR " + ContactsTable.Cols.STATUS + " = ?"
            val list = ArrayList<String>()
            var cursor: Cursor? = null
            try {
                cursor = context.contentResolver.query(ContactsTable.CONTENT_URI, null, condition, arrayOf("1", "4"), ContactsTable.Cols.ID + " ORDER BY " + ContactsTable.Cols.FRIENDFULLNAME +
                        " COLLATE NOCASE ASC")
                if (cursor != null && cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        val _email = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.FRIENDEMAIL))
                        val _phone = cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.FRIENDPHONE))
                        if (_email != null && _email.length > 0) {
                            list.add(cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.FRIENDEMAIL)))
                        } else if (_phone != null && _phone.length > 0) {
                            list.add(cursor.getString(cursor.getColumnIndex(ContactsTable.Cols.FRIENDPHONE)))
                        }
                    }
                }
            } finally {
                if (cursor != null && !cursor.isClosed) {
                    cursor.close()
                }
            }

            return list
        }

        fun getContactByContactId(context: Context, contactId: String): ContactsDetailModel.Result {
            var contact = ContactsDetailModel.Result()
            val condition = ContactsTable.Cols.FRIENDID + " = ?"
            var cursor: Cursor? = null
            try {
                cursor = context.contentResolver.query(ContactsTable.CONTENT_URI, null, condition, arrayOf(contactId), null)
                if (cursor != null && cursor.count > 0 && cursor.moveToNext()) {
                    contact = getContactFromCursor(cursor)
                }
            } finally {
                if (cursor != null && !cursor.isClosed) {
                    cursor.close()
                }
            }
            return contact
        }

        //@formatter:off
        fun getAllIncomingRequestList(context: Context): List<ContactsDetailModel.Result> {
            val condition = ContactsTable.Cols.STATUS + " = ? AND	" + ContactsTable.Cols.ISSENDREQUEST + " = ?"
            val list = ArrayList<ContactsDetailModel.Result>()
            var cursor: Cursor? = null
            try {
                cursor = context.contentResolver.query(ContactsTable.CONTENT_URI, null, condition, arrayOf("0", "2"), ContactsTable.Cols.ID + " ORDER BY " + ContactsTable.Cols.FRIENDFULLNAME +
                        " COLLATE NOCASE ASC")
                if (cursor != null && cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        list.add(getContactFromCursor(cursor))
                    }
                }
            } finally {
                if (cursor != null && !cursor.isClosed) {
                    cursor.close()
                }
            }

            return list
        }

        fun getAllPendingRequestList(context: Context): List<ContactsDetailModel.Result> {
            val condition = ContactsTable.Cols.STATUS + " = ? AND	" + ContactsTable.Cols.ISSENDREQUEST + " = ?"
            val list = ArrayList<ContactsDetailModel.Result>()
            var cursor: Cursor? = null
            try {
                cursor = context.contentResolver.query(ContactsTable.CONTENT_URI, null, condition, arrayOf("0", "1"), ContactsTable.Cols.ID + " ORDER BY " + ContactsTable.Cols.FRIENDFULLNAME +
                        " COLLATE NOCASE ASC")
                if (cursor != null && cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        list.add(getContactFromCursor(cursor))
                    }
                }
            } finally {
                if (cursor != null && !cursor.isClosed) {
                    cursor.close()
                }
            }

            return list
        }

        fun getContactsFilteredByIds(context: Context, friendIdsList: List<Int>): Cursor? {
            val selection = prepareContactsFilter(friendIdsList)
            return context.contentResolver.query(MasterUserTable.CONTENT_URI, null, selection, null, MasterUserTable.Cols.ID
                    + " ORDER BY " + MasterUserTable.Cols.FULLNAME + " COLLATE NOCASE ASC")
        }

        private fun prepareContactsFilter(friendIdsList: List<Int>): String {
            val condition = String.format("('%s')", TextUtils.join("','", friendIdsList))
            return MasterUserTable.Cols.ID + " NOT IN " + condition
        }

        fun clearAllCache(context: Context) {
            DatabaseManager.deleteAllContacts(context)
        }

        private fun deleteAllContacts(context: Context) {
            context.contentResolver.delete(MasterUserTable.CONTENT_URI, null, null)
        }

        fun getMasterUser(context: Context): UserLoginModel.ResultBean? {
            var udb = UserLoginModel.ResultBean()
            val c = context.contentResolver.query(MasterUserTable.CONTENT_URI, null, null, null, null)
            if (c != null && c.moveToNext()) {
                udb = getMasterUserFromCursor(c)
                return udb
            }
            return null
        }


        private fun getMasterUserFromCursor(cursor: Cursor): UserLoginModel.ResultBean {
            val cdm = UserLoginModel.ResultBean()
            val friendId = cursor.getString(cursor.getColumnIndex(MasterUserTable.Cols.ID))
            val friendFullname = cursor.getString(cursor.getColumnIndex(MasterUserTable.Cols.FULLNAME))
            val countrycode = cursor.getString(cursor.getColumnIndex(MasterUserTable.Cols.COUNTRYCODE))
            val phone = cursor.getString(cursor.getColumnIndex(MasterUserTable.Cols.PHONE))
            val userImage = cursor.getString(cursor.getColumnIndex(MasterUserTable.Cols.PROFILE_PIC))
            val email = cursor.getString(cursor.getColumnIndex(MasterUserTable.Cols.EMAIL))
            val coverpic = cursor.getString(cursor.getColumnIndex(MasterUserTable.Cols.COVER_PIC))
            val subscriptionStartDate = cursor.getString(cursor.getColumnIndex(MasterUserTable.Cols.SUBSCRIPTION_START_DATE))
            val subscriptionType = cursor.getInt(cursor.getColumnIndex(MasterUserTable.Cols.SUBSCRIPTION_TYPE))
            cdm.userId = friendId
            cdm.userFullName = friendFullname
            cdm.userCountryCode = countrycode
            cdm.userPhone = phone
            cdm.userImage = userImage
            cdm.coverImage = coverpic
            cdm.userEmail = email
            return cdm
        }

    }
}