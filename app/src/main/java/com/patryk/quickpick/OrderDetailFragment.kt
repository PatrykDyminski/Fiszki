package com.patryk.quickpick

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patryk.quickpick.data.DemoDataContent
import com.patryk.quickpick.data.Item
import com.patryk.quickpick.data.Order

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [OrderListActivity]
 * in two-pane mode (on tablets) or a [OrderDetailActivity]
 * on handsets.
 */
class OrderDetailFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ORDER_ID)) {
                order = DemoDataContent.ORDERS_MAP[it.getString(ARG_ORDER_ID)]!!
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_order_detail, container, false)

        rootView.findViewById<TextView>(R.id.pageTitle).text = "Order " + order.id + " - " + order.items.size.toString() + " to pick"

        viewManager = LinearLayoutManager(context)
        viewAdapter = MyAdapter(order.items)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.itemsInOrderRecycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val startPickingButton: Button = rootView.findViewById(R.id.start_picking)
        startPickingButton.setOnClickListener {
            val intent = Intent(context, PickProcessActivity::class.java).apply {
                putExtra(ARG_ORDER_ID, order.id)
            }
            context?.startActivity(intent)
        }

        val backToListButton: Button = rootView.findViewById(R.id.back_to_list)
        backToListButton.setOnClickListener {
            (activity as OrderDetailActivity?)?.fBack()
        }

        return rootView
    }

    companion object {
        const val ARG_ORDER_ID = "order_id"
    }

    class MyAdapter(private val items: List<Item>)
        : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.item_name)
            val barcode: TextView = view.findViewById(R.id.barcode)
            val category: TextView = view.findViewById(R.id.category)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {

            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.content_item_in_order, parent, false) as LinearLayout

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.name.text = item.name
            holder.barcode.text = "placeholder"
            holder.category.text = item.category
        }

        override fun getItemCount() = items.size
    }
}