package com.patryk.quickpick

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.patryk.quickpick.data.DemoDataContent
import com.patryk.quickpick.data.OrderStatus
import com.patryk.quickpick.data.PastOrder

class PastOrdersActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_orders)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.completed

        viewManager = LinearLayoutManager(this)
        viewAdapter = PastOrdersAdapter(DemoDataContent.PAST_ORDERS)
        recyclerView = this.findViewById<RecyclerView>(R.id.pastOrders).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    class PastOrdersAdapter(private var orders: List<PastOrder>)
        : RecyclerView.Adapter<PastOrdersAdapter.ViewHolder>() {

        init {
            orders = orders.sortedBy { it.status }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.order_name)
            val count: TextView = view.findViewById(R.id.item_count)
            val statusImg: ImageView = view.findViewById(R.id.order_status_img)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.content_order_list, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val order = orders[position]
            holder.name.text = "order: " + order.listaFiszek.name
            holder.count.text = order.listaFiszek.fiszkas.count().toString() + " items"
            if(order.status == OrderStatus.FAIL){
                holder.statusImg.setImageResource(R.drawable.ic_failed)
                holder.statusImg.setColorFilter(Color.RED)
            }else if(order.status == OrderStatus.SUCCESS){
                holder.statusImg.setColorFilter(Color.GREEN)
            }else{
                holder.statusImg.setImageResource(R.drawable.ic_failed)
                holder.statusImg.setColorFilter(Color.YELLOW)
            }
            holder.statusImg.visibility = View.VISIBLE

            Log.i("aaaa",order.listaFiszek.name)
        }

        override fun getItemCount() = orders.size
    }
}