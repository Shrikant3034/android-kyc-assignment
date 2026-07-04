package com.shrikant.digitalbanking.utils

import com.shrikant.digitalbanking.model.Customer

object DummyData {

    fun customers() = mutableListOf(

        Customer(
            name = "Shrikant Pawar",
            account = "****3424",
            balance = 84200,
            verified = false,
            dob = "12 Apr 1996",
            nationality = "Indian",
            phone = "9876543210",
            email = "rahul@gmail.com",
            ifsc = "HDFC0CAGSBK",
            bank = "HDFC Bank",
            branch = "Andheri",
            accountType = "Savings"
        ),

        Customer(
            name = "Neha Gupta",
            account = "****1147",
            balance = 9870,
            verified = false,
            dob = "14 Jan 1998",
            nationality = "Indian",
            phone = "9876543211",
            email = "neha@gmail.com",
            ifsc = "SBIN0000001",
            bank = "State Bank of India",
            branch = "Mumbai",
            accountType = "Current"
        ),

        Customer(
            name = "Aman Iyer",
            account = "****8860",
            balance = 12050,
            verified = true,
            dob = "25 Jul 1995",
            nationality = "Indian",
            phone = "9876543212",
            email = "aman@gmail.com",
            ifsc = "ICIC0000001",
            bank = "ICICI Bank",
            branch = "Pune",
            accountType = "NRI"
        ),

        Customer(
            name = "Riya Sharma",
            account = "****5567",
            balance = 65400,
            verified = false,
            dob = "08 Feb 1997",
            nationality = "Indian",
            phone = "9876543213",
            email = "riya@gmail.com",
            ifsc = "HDFC0CAGSBK",
            bank = "HDFC Bank",
            branch = "Delhi",
            accountType = "Savings"
        ),

        Customer(
            name = "Arjun Mehta",
            account = "****9988",
            balance = 150000,
            verified = true,
            dob = "30 Nov 1994",
            nationality = "Indian",
            phone = "9876543214",
            email = "arjun@gmail.com",
            ifsc = "SBIN0000001",
            bank = "State Bank of India",
            branch = "Nagpur",
            accountType = "Current"
        )

    )
}