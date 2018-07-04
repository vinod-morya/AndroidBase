package com.company.product.Support

import android.os.Environment

object C {
    val App_NAME = "myappName"
    val PERMISSIONS_MULTIPLE_REQUEST = 123

    private val MEDIA = ".Media/"
    val EXTRA_ANIM = "anim"
    val LOCAL_PATH = Environment.getExternalStorageDirectory().absolutePath + "/" + App_NAME + "/"
    val LOCAL_HIDDEN_PATH = Environment.getExternalStorageDirectory().absolutePath + "/." + App_NAME + "/"
    val APP_IMAGES = "$MEDIA" + App_NAME + "Images/"

    val BASE_URL = "http://www.vinodmorya.com"

    // SERVICE NAMES
    val SERVICE_LOGOUT = "logout.json?id=1"
    val SERVICE_SIGNUP = "signup.json"
    val SERVICE_LOGIN = "login.json?id=1"
    val SERVICE_USER_DATA = "login.json"

    // Request Codes
    val GALLERY_REQ_CODE = 101
    val CAMERA_REQ_CODE = 102
    val IMAGE_CROP_CODE = 100
    val TAG_PROFILE = "profile"
    val TAG_SHARE = "share"

    val PAYMENT_ACCOUNT_TYPE_DEMO = 0
    val PAYMENT_ACCOUNT_TYPE_BASIC = 1
    val PAYMENT_ACCOUNT_TYPE_PREMIUM = 2
    val PAYMENT_ACCOUNT_TYPE_DUMMY = 3
    val ZERO_INT_VALUE = 0


    enum class Media {
        NONE, TEXT, AUDIO, VIDEO, IMAGE, TEST
    }

    enum class FRIENDREQUEST {
        ACCEPT, REJECT, RESEND, DELETE
    }


    enum class CHATMESSAGETYPE {
        TEXT, AUDIO, VIDEO, LOCATION, PICTURE
    }
}