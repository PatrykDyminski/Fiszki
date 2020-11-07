package com.patryk.quickpick.ui.ordersummary

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.patryk.quickpick.R

class OrderSummaryFragment : Fragment() {

    companion object {
        fun newInstance() = OrderSummaryFragment()
    }

    private lateinit var viewModel: OrderSummaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_order_summary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderSummaryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}