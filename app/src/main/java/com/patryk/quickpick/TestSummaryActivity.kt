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
import com.patryk.quickpick.ui.testprocess.TestProcessFragment

@ExperimentalStdlibApi
class TestSummaryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_summary)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val summary = intent.getParcelableExtra<TestProcessSummary>(TestProcessFragment.TEST_SUMMARY)
        val items = ArrayList<TestFiszka>()

        summary?.fiszki?.forEach {
            items.add(it)
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

    class CompletedItemsAdapter(private var fiszkas: List<TestFiszka>)
        : RecyclerView.Adapter<CompletedItemsAdapter.ViewHolder>() {

        init {
            fiszkas = fiszkas.sortedBy { !it.isCorrect }
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.item_name)
            val translation: TextView = view.findViewById(R.id.barcode)
            val answer: TextView = view.findViewById(R.id.answeredLabel)
            val statusImg: ImageView = view.findViewById(R.id.item_status_img)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.content_completed_test_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = fiszkas[position]

            holder.name.text = item.fiszka.word
            holder.translation.text = item.fiszka.translation
            holder.answer.text = item.answer

            if(!item.isCorrect){
                holder.statusImg.setImageResource(R.drawable.ic_failed)
                holder.statusImg.setColorFilter(Color.RED)
            }else{
                holder.statusImg.setColorFilter(Color.GREEN)
            }
        }

        override fun getItemCount() = fiszkas.size
    }
}