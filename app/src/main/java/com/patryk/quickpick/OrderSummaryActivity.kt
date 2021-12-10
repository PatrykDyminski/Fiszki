package com.patryk.quickpick

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patryk.quickpick.data.*
import com.patryk.quickpick.ui.pickprocess.PickProcessFragment

@ExperimentalStdlibApi
class OrderSummaryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_summary)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val summary = intent.getParcelableExtra<PickProcessSummary>(PickProcessFragment.ORDER_SUMMARY)
        val items = ArrayList<CompletedFiszka>()

        summary?.completedFiszkas?.forEach {
            items.add(CompletedFiszka(it, true))
        }

        summary?.failedFiszkas?.forEach {
            items.add(CompletedFiszka(it, false))
        }

        val finishButton: Button = findViewById(R.id.finishButton)
        finishButton.setOnClickListener {
            val intent = Intent(this, ListaListFiszekActivity::class.java)
            startActivity(intent)
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = CompletedItemsAdapter(items)
        recyclerView = this.findViewById<RecyclerView>(R.id.completedItems).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    class CompletedItemsAdapter(private var fiszkas: List<CompletedFiszka>)
        : RecyclerView.Adapter<CompletedItemsAdapter.ViewHolder>() {

        init {
            fiszkas = fiszkas.sortedBy { it.fiszka.status }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.item_name)
            val translation: TextView = view.findViewById(R.id.barcode)
            val statusImg: ImageView = view.findViewById(R.id.item_status_img)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.content_completed_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = fiszkas[position]

            holder.name.text = item.fiszka.word
            holder.translation.text = item.fiszka.translation

            when (item.fiszka.status) {
                LearnStatus.NOT_LEARNED -> {
                    holder.statusImg.setImageResource(R.drawable.ic_failed)
                    holder.statusImg.setColorFilter(Color.RED)
                }
                LearnStatus.MIXED -> {
                    holder.statusImg.setColorFilter(Color.parseColor("#03fc24"))
                }
                else -> {
                    holder.statusImg.setColorFilter(Color.parseColor("#f4fc03"))
                }
            }
        }

        override fun getItemCount() = fiszkas.size
    }
}