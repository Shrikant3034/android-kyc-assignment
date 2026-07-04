package com.shrikant.digitalbanking.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shrikant.digitalbanking.R
import com.shrikant.digitalbanking.model.Customer
class CustomerAdapter(
    private var customerList: MutableList<Customer>,
    private val onClick: (Customer) -> Unit
) : RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    inner class CustomerViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtAccount: TextView = itemView.findViewById(R.id.txtAccount)
        val txtBalance: TextView = itemView.findViewById(R.id.txtBalance)
        val txtStatus: TextView = itemView.findViewById(R.id.txtStatus)
        val btnAction: Button = itemView.findViewById(R.id.btnAction)
        val imgCustomer: ImageView = itemView.findViewById(R.id.imgCustomer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_customer, parent, false)

        return CustomerViewHolder(view)
    }

    override fun getItemCount(): Int = customerList.size

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {

        val customer = customerList[position]

        holder.txtName.text = customer.name
        holder.txtAccount.text = customer.account
        holder.txtBalance.text = "₹${customer.balance}"

        if (customer.verified) {

            holder.txtStatus.text = "VERIFIED"
            holder.txtStatus.setBackgroundColor(Color.parseColor("#2E7D32"))

            holder.btnAction.text = "View Details"

        } else {

            holder.txtStatus.text = "PENDING"
            holder.txtStatus.setBackgroundColor(Color.parseColor("#F57C00"))

            holder.btnAction.text = "Complete KYC"

        }

        holder.btnAction.setOnClickListener {

            onClick(customer)

        }

    }

    fun updateList(list: List<Customer>) {
        customerList = list.toMutableList()
        notifyDataSetChanged()
    }
}