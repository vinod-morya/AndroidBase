package com.company.product.LoginModule.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProfileDetailModel {
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
    var result: ProfileResult? = null
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

    inner class ProfileResult {
        /**
         * @return The userId
         */
        /**
         * @param userId The userId
         */
        @SerializedName("userId")
        @Expose
        var userId: String? = null
        /**
         * @return The userNickName
         */
        /**
         * @param userNickName The userNickName
         */
        @SerializedName("userNickName")
        @Expose
        var userNickName: String? = null
        /**
         * @return The userFullName
         */
        /**
         * @param userFullName The userFullName
         */
        @SerializedName("userFullName")
        @Expose
        var userFullName: String? = null
        /**
         * @return The userCountryCode
         */
        /**
         * @param userCountryCode The userCountryCode
         */
        @SerializedName("userCountryCode")
        @Expose
        var userCountryCode: String? = null
        /**
         * @return The userPhone
         */
        /**
         * @param userPhone The userPhone
         */
        @SerializedName("userPhone")
        @Expose
        var userPhone: String? = null
        /**
         * @return The userEmail
         */
        /**
         * @param userEmail The userEmail
         */
        @SerializedName("userEmail")
        @Expose
        var userEmail: String? = null
        /**
         * @return The userImage
         */
        /**
         * @param userImage The userImage
         */
        @SerializedName("userImage")
        @Expose
        var userImage: String? = null
        /**
         * @return The userCoverImage
         */
        /**
         * @param userCoverImage The userCoverImage
         */
        @SerializedName("userCoverImage")
        @Expose
        var userCoverImage: String? = null
        /**
         * @return The isFriend
         */
        /**
         * @param isFriend The isFriend
         */
        @SerializedName("isFriend")
        @Expose
        var isFriend: String? = null
        /**
         * @return The blockByUser
         */
        /**
         * @param blockByUser The blockByUser
         */
        @SerializedName("blockByUser")
        @Expose
        var blockByUser: Int? = null
        /**
         * @return The lastRequestTime
         */
        /**
         * @param lastRequestTime The lastRequestTime
         */
        @SerializedName("lastRequestTime")
        @Expose
        var lastRequestTime: String? = null
        @SerializedName("profileStatus")
        @Expose
        var profileStatus: String? = null
    }
}