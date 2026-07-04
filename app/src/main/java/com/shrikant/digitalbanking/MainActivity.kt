package com.shrikant.digitalbanking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shrikant.digitalbanking.ui.accounts.AccountsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){

            supportFragmentManager.beginTransaction()

                .replace(R.id.container, AccountsFragment())

                .commit()

        }

    }

}