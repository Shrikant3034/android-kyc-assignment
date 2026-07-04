package com.shrikant.digitalbanking.ui.details

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shrikant.digitalbanking.R
import com.shrikant.digitalbanking.model.Customer
import com.shrikant.digitalbanking.utils.BankUtils
import com.shrikant.digitalbanking.utils.PreferenceHelper

class DetailsActivity : AppCompatActivity() {

    private lateinit var customer: Customer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        customer = intent.getSerializableExtra("customer") as Customer

        val imgCustomer = findViewById<ImageView>(R.id.imgCustomer)
        val txtName = findViewById<TextView>(R.id.txtName)
        val txtAccount = findViewById<TextView>(R.id.txtAccount)
        val txtBalance = findViewById<TextView>(R.id.txtBalance)
        val txtDob = findViewById<TextView>(R.id.txtDob)
        val txtNationality = findViewById<TextView>(R.id.txtNationality)
        val txtPhone = findViewById<TextView>(R.id.txtPhone)
        val txtEmail = findViewById<TextView>(R.id.txtEmail)
        val txtIfsc = findViewById<TextView>(R.id.txtIfsc)
        val txtBank = findViewById<TextView>(R.id.txtBank)
        val txtBranch = findViewById<TextView>(R.id.txtBranch)
        val btnKyc = findViewById<Button>(R.id.btnKyc)

        txtName.text = customer.name
        txtAccount.text = "Account : ${customer.account}"
        txtBalance.text = "Balance : ₹${customer.balance}"
        txtDob.text = "DOB : ${customer.dob}"
        txtNationality.text = "Nationality : ${customer.nationality}"
        txtPhone.text = "Phone : ${customer.phone}"
        txtEmail.text = "Email : ${customer.email}"
        txtIfsc.text = "IFSC : ${customer.ifsc}"
        txtBank.text = "Bank : ${BankUtils.getBank(customer.ifsc)}"
        txtBranch.text = "Branch : ${BankUtils.getBranch(customer.ifsc)}"

        if (customer.verified) {
            btnKyc.text = "Verified"
            btnKyc.isEnabled = false
        } else {
            btnKyc.setOnClickListener {

                androidx.appcompat.app.AlertDialog.Builder(this)

                    .setTitle("Complete KYC")

                    .setMessage("Are you sure you want to verify this customer?")

                    .setPositiveButton("Yes") { _, _ ->

                        customer.verified = true

                        PreferenceHelper.saveKycStatus(
                            this,
                            customer.account,
                            true
                        )

                        btnKyc.text = "Verified"

                        btnKyc.isEnabled = false

                        Toast.makeText(
                            this,
                            "Customer Verified Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                    .setNegativeButton("Cancel", null)

                    .show()

            }
        }
    }
}