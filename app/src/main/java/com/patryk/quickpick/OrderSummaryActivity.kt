package com.patryk.quickpick

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.patryk.quickpick.data.CompletedItem
import com.patryk.quickpick.data.PickProcessSummary
import com.patryk.quickpick.ui.pickprocess.PickProcessFragment

@ExperimentalStdlibApi
class OrderSummaryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val summary = intent.getParcelableExtra<PickProcessSummary>(PickProcessFragment.ORDER_SUMMARY)
        Log.i("eee",summary?.failedItems?.size.toString())

        val items = ArrayList<CompletedItem>()

        summary?.completedItems?.forEach {
            items.add(CompletedItem(it, true))
        }

        summary?.failedItems?.forEach {
            items.add(CompletedItem(it, false))
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = CompletedItemsAdapter(items)
        recyclerView = this.findViewById<RecyclerView>(R.id.completedItems).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    class CompletedItemsAdapter(private var items: List<CompletedItem>)
        : RecyclerView.Adapter<CompletedItemsAdapter.ViewHolder>() {

        init {
            items = items.sortedBy { !it.isSuccess }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.item_name)
            val barcode: TextView = view.findViewById(R.id.barcode)
            val statusImg: ImageView = view.findViewById(R.id.item_status_img)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.content_completed_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]

            holder.name.text = item.item.name
            holder.barcode.text = item.item.barcode

            if(!item.isSuccess){
                holder.statusImg.setImageResource(R.drawable.ic_failed)
                holder.statusImg.setColorFilter(Color.RED)
            }else{
                holder.statusImg.setColorFilter(Color.GREEN)
            }
        }

        override fun getItemCount() = items.size
    }
}