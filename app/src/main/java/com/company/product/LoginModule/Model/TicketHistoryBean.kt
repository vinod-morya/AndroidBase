package com.company.product.LoginModule.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TicketHistoryBean {

    @SerializedName("tID")
    @Expose
    private var tID: Int = 0
    @SerializedName("ticket")
    @Expose
    var ticket: String? = null
    @SerializedName("datetime_format")
    @Expose
    var datetime_format: String? = null
    @SerializedName("ticket_status")
    @Expose
    var ticket_status: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("total_farmers")
    @Expose
    var total_farmers: String? = null

    @SerializedName("total_valid_farmers")
    @Expose
    var total_valid_farmers: String? = null

    fun gettID(): Int {
        return tID
    }

    fun settID(tID: Int) {
        this.tID = tID
    }
}