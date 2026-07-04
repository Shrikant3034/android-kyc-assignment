package com.shrikant.digitalbanking.ui.accounts

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayout
import com.shrikant.digitalbanking.R
import com.shrikant.digitalbanking.ui.adapter.CustomerAdapter
import com.shrikant.digitalbanking.ui.details.DetailsActivity
import com.shrikant.digitalbanking.utils.DummyData
import com.shrikant.digitalbanking.utils.PreferenceHelper

class AccountsFragment : Fragment() {

    private lateinit var adapter: CustomerAdapter

    private var allCustomers = DummyData.customers()

    private var currentVerified = false
    private var selectedChip = "All"

    private lateinit var txtEmpty: TextView
    private lateinit var txtTotal: TextView
    private lateinit var txtPending: TextView
    private lateinit var txtVerified: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(
            R.layout.fragment_accounts,
            container,
            false
        )

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)
        val search = view.findViewById<EditText>(R.id.search)
        val tabs = view.findViewById<TabLayout>(R.id.tabLayout)
        val chipGroup = view.findViewById<ChipGroup>(R.id.chipGroup)

        txtEmpty = view.findViewById(R.id.txtEmpty)
        txtTotal = view.findViewById(R.id.txtTotal)
        txtPending = view.findViewById(R.id.txtPending)
        txtVerified = view.findViewById(R.id.txtVerified)

        initCustomers()

        updateDashboard()

        tabs.removeAllTabs()
        tabs.addTab(tabs.newTab().setText("Pending"))
        tabs.addTab(tabs.newTab().setText("Verified"))

        adapter = CustomerAdapter(mutableListOf()) { customer ->

            val intent = Intent(requireContext(), DetailsActivity::class.java)
            intent.putExtra("customer", customer)
            startActivity(intent)

        }

        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler.adapter = adapter

        loadCustomers()

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {

                currentVerified = tab.position == 1

                loadCustomers()

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}

        })

        chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->

            selectedChip = when (checkedIds.firstOrNull()) {

                R.id.chipSavings -> "Savings"
                R.id.chipCurrent -> "Current"
                R.id.chipNri -> "NRI"
                else -> "All"

            }

            loadCustomers()

        }

        search.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {

                filter(s.toString())

            }

            override fun afterTextChanged(s: Editable?) {}

        })

        return view

    }

    override fun onResume() {

        super.onResume()

        initCustomers()

        updateDashboard()

        loadCustomers()

    }

    private fun initCustomers() {

        allCustomers.forEach {

            it.verified = PreferenceHelper.getKycStatus(
                requireContext(),
                it.account
            )

        }

    }

    private fun updateDashboard() {

        txtTotal.text = allCustomers.size.toString()

        txtPending.text =
            allCustomers.count { !it.verified }.toString()

        txtVerified.text =
            allCustomers.count { it.verified }.toString()

    }

    private fun loadCustomers() {

        var list = allCustomers.filter {

            it.verified == currentVerified

        }

        if (selectedChip != "All") {

            list = list.filter {

                it.accountType == selectedChip

            }

        }

        adapter.updateList(list)

        txtEmpty.visibility =
            if (list.isEmpty()) View.VISIBLE
            else View.GONE

        updateDashboard()

    }

    private fun filter(text: String) {

        var list = allCustomers.filter {

            it.verified == currentVerified &&
                    (
                            it.name.contains(text, true) ||
                                    it.account.contains(text, true)
                            )

        }

        if (selectedChip != "All") {

            list = list.filter {

                it.accountType == selectedChip

            }

        }

        adapter.updateList(list)

        txtEmpty.visibility =
            if (list.isEmpty()) View.VISIBLE
            else View.GONE

    }

}