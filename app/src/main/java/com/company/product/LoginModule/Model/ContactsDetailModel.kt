package com.company.product.LoginModule.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class ContactsDetailModel {

    /**
     * @return The lastRequestTime
     */
    /**
     * @param lastRequestTime The lastRequestTime
     */
    @SerializedName("lastRequestTime")
    @Expose
    var lastRequestTime: String? = null
    /**
     * @return The result
     */
    /**
     * @param result The result
     */
    @SerializedName("result")
    @Expose
    var result: List<Result> = ArrayList()
    /**
     * @return The responseErrorKey
     */
    /**
     * @param responseErrorKey The response_error_key
     */
    @SerializedName("response_error_key")
    @Expose
    var responseErrorKey: String? = null
    /**
     * @return The errorCode
     */
    /**
     * @param errorCode The error_code
     */
    @SerializedName("error_code")
    @Expose
    var errorCode: String? = null

    class Result : Parcelable {
        @SerializedName("friendRowId")
        @Expose
        var friendRowId: String? = null
        @SerializedName("friendId")
        @Expose
        var friendId: Int = 0
        @SerializedName("hideProfile")
        @Expose
        var hideProfile: String? = null
        @SerializedName("isSendRequest")
        @Expose
        var isSendRequest: String? = null
        @SerializedName("status")
        @Expose
        var status: Int = 0
        @SerializedName("friendName")
        @Expose
        var friendName: String? = null
        @SerializedName("friendFullName")
        @Expose
        var friendFullName: String? = null
        @SerializedName("friendEmail")
        @Expose
        var friendEmail: String? = null
        @SerializedName("friendCountryCode")
        @Expose
        var friendCountryCode: String? = null
        @SerializedName("friendPhone")
        @Expose
        var friendPhone: String? = null
        @SerializedName("friendImage")
        @Expose
        var friendImage: String? = null
        @SerializedName("friendCoverImage")
        @Expose
        var friendCoverImage: String? = null
        @SerializedName("creationDate")
        @Expose
        var creationDate: String? = null
        @SerializedName("profileStatus")
        @Expose
        var profileStatus: String? = null
        @SerializedName("userLocation")
        @Expose
        var userLocation: String? = null
        @SerializedName("blockByUser")
        @Expose
        var blockByUser: Int = 0
        var positionOnList: Int = 0
        var lastMessage: String? = null
        var lastMessageTime: Long = 0
        var unreadMsgCount: Int = 0
        var isOnline: Int = 0
        var isChecked: Int = 0

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(this.friendRowId)
            dest.writeInt(this.friendId)
            dest.writeString(this.hideProfile)
            dest.writeString(this.isSendRequest)
            dest.writeInt(this.status)
            dest.writeString(this.friendName)
            dest.writeString(this.friendFullName)
            dest.writeString(this.friendEmail)
            dest.writeString(this.friendCountryCode)
            dest.writeString(this.friendPhone)
            dest.writeString(this.friendImage)
            dest.writeString(this.friendCoverImage)
            dest.writeString(this.creationDate)
            dest.writeString(this.profileStatus)
            dest.writeString(this.userLocation)
            dest.writeInt(this.blockByUser)
            dest.writeInt(this.positionOnList)
            dest.writeString(this.lastMessage)
            dest.writeLong(this.lastMessageTime)
            dest.writeInt(this.unreadMsgCount)
            dest.writeInt(this.isOnline)
            dest.writeInt(this.isChecked)
        }

        constructor() {}

        internal constructor(`in`: Parcel) {
            this.friendRowId = `in`.readString()
            this.friendId = `in`.readInt()
            this.hideProfile = `in`.readString()
            this.isSendRequest = `in`.readString()
            this.status = `in`.readInt()
            this.friendName = `in`.readString()
            this.friendFullName = `in`.readString()
            this.friendEmail = `in`.readString()
            this.friendCountryCode = `in`.readString()
            this.friendPhone = `in`.readString()
            this.friendImage = `in`.readString()
            this.friendCoverImage = `in`.readString()
            this.creationDate = `in`.readString()
            this.profileStatus = `in`.readString()
            this.userLocation = `in`.readString()
            this.blockByUser = `in`.readInt()
            this.positionOnList = `in`.readInt()
            this.lastMessage = `in`.readString()
            this.lastMessageTime = `in`.readLong()
            this.unreadMsgCount = `in`.readInt()
            this.isOnline = `in`.readInt()
            this.isChecked = `in`.readInt()
        }

        companion object {

            val CREATOR: Parcelable.Creator<Result> = object : Parcelable.Creator<Result> {
                override fun createFromParcel(source: Parcel): Result {
                    return Result(source)
                }

                override fun newArray(size: Int): Array<Result?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}