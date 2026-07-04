package com.shrikant.digitalbanking.model

import java.io.Serializable

data class Customer(

    var name: String,
    var account: String,
    var balance: Int,
    var verified: Boolean,

    var dob: String,
    var nationality: String,
    var phone: String,
    var email: String,

    var ifsc: String,
    var bank: String,
    var branch: String,
    var accountType: String

) : Serializable