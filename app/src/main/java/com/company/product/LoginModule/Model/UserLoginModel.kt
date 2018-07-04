package com.company.product.LoginModule.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserLoginModel : Parcelable {

    @SerializedName("response_string")
    @Expose
    var responseString: String? = null
    @SerializedName("error_code")
    @Expose
    var errorCode: String? = null
    @SerializedName("result")
    @Expose
    var resultBean: ResultBean? = null
    @SerializedName("response_error_key")
    @Expose
    var responseErrorKey: String? = null

    @Entity(tableName = "MasterUser")
    class ResultBean : Parcelable {
        @PrimaryKey
        @SerializedName("userId")
        @Expose
        var userId: String = ""
        @SerializedName("userNickName")
        @Expose
        var userNickName: String? = null
        @SerializedName("userFullName")
        @Expose
        var userFullName: String? = null
        @SerializedName("userEmail")
        @Expose
        var userEmail: String? = null
        @SerializedName("userDob")
        @Expose
        var userDob: String? = null
        @SerializedName("userThemeColor")
        @Expose
        var userThemeColor: String? = null
        @SerializedName("userImage")
        @Expose
        var userImage: String? = null
        @SerializedName("userCoverImage")
        @Expose
        var coverImage: String? = null
        @SerializedName("userCountryCode")
        @Expose
        var userCountryCode: String? = null
        @SerializedName("userPhone")
        @Expose
        var userPhone: String? = null
        @SerializedName("profileStatus")
        @Expose
        var profileStatus: String? = null
        @SerializedName("userLocation")
        @Expose
        var userLocation: String? = null
        @SerializedName("accessToken")
        @Expose
        var accessToken: String? = null
        @SerializedName("userPasscode")
        @Expose
        var userPasscode: String? = null


        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(this.userId)
            dest.writeString(this.userNickName)
            dest.writeString(this.userFullName)
            dest.writeString(this.userEmail)
            dest.writeString(this.userDob)
            dest.writeString(this.userThemeColor)
            dest.writeString(this.userImage)
            dest.writeString(this.coverImage)
            dest.writeString(this.userCountryCode)
            dest.writeString(this.userPhone)
            dest.writeString(this.profileStatus)
            dest.writeString(this.userLocation)
            dest.writeString(this.accessToken)
            dest.writeString(this.userPasscode)
        }

        constructor() {}

        internal constructor(`in`: Parcel) {
            this.userId = `in`.readString()
            this.userNickName = `in`.readString()
            this.userFullName = `in`.readString()
            this.userEmail = `in`.readString()
            this.userDob = `in`.readString()
            this.userThemeColor = `in`.readString()
            this.userImage = `in`.readString()
            this.coverImage = `in`.readString()
            this.userCountryCode = `in`.readString()
            this.userPhone = `in`.readString()
            this.profileStatus = `in`.readString()
            this.userLocation = `in`.readString()
            this.accessToken = `in`.readString()
            this.userPasscode = `in`.readString()
        }

        companion object {

            val CREATOR: Parcelable.Creator<ResultBean> = object : Parcelable.Creator<ResultBean> {
                override fun createFromParcel(source: Parcel): ResultBean {
                    return ResultBean(source)
                }

                override fun newArray(size: Int): Array<ResultBean?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.responseString)
        dest.writeString(this.errorCode)
        dest.writeParcelable(this.resultBean, flags)
        dest.writeString(this.responseErrorKey)
    }

    constructor() {}

    internal constructor(`in`: Parcel) {
        this.responseString = `in`.readString()
        this.errorCode = `in`.readString()
        this.resultBean = `in`.readParcelable(ResultBean::class.java.classLoader)
        this.responseErrorKey = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<UserLoginModel> = object : Parcelable.Creator<UserLoginModel> {
            override fun createFromParcel(source: Parcel): UserLoginModel {
                return UserLoginModel(source)
            }

            override fun newArray(size: Int): Array<UserLoginModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}