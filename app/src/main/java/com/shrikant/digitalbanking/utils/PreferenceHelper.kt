package com.shrikant.digitalbanking.utils

import android.content.Context

object PreferenceHelper {

    private const val PREF_NAME = "DigitalBanking"

    fun saveKycStatus(
        context: Context,
        account: String,
        verified: Boolean
    ) {

        val pref = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

        pref.edit()
            .putBoolean(account, verified)
            .apply()
    }

    fun getKycStatus(
        context: Context,
        account: String
    ): Boolean {

        val pref = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

        return pref.getBoolean(account, false)
    }

}