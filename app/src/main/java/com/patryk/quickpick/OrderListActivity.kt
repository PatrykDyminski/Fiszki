package com.patryk.quickpick

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.patryk.quickpick.data.DemoDataContent
import com.patryk.quickpick.data.Order
import com.patryk.quickpick.data.Parser
import com.patryk.quickpick.ui.orderdetail.OrderDetailFragment
import java.io.File


/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [OrderDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class OrderListActivity : AppCompatActivity() {

    private var twoPane: Boolean = false
    private lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.home
        setBottomNav()

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(findViewById(R.id.item_list))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_main_menu, menu)
        return true
    }

    var sortOrder: Boolean = true;

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                R.id.sort -> {

                    if (sortOrder){
                        DemoDataContent.ORDERS.sortBy { it.placedDate }
                        sortOrder = !sortOrder

                        showToast("Old orders on top")

                    }else{
                        DemoDataContent.ORDERS.sortBy { it.placedDate }
                        DemoDataContent.ORDERS.reverse()
                        sortOrder = !sortOrder

                        showToast("New orders on top")
                    }

                    setupRecyclerView(findViewById(R.id.item_list))

                    true
                }
                R.id.parse -> {

                    val intent = Intent(Intent.ACTION_GET_CONTENT)
                    intent.type = "*/*"
                    startActivityForResult(intent, 1)

                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.data?.let {
                val file = getFileFromUri(contentResolver, it, cacheDir)

                Parser.parseFile(file)

                showToast("imported "+DemoDataContent.ORDERS.size.toString()+" orders")

                setupRecyclerView(findViewById(R.id.item_list))
            }
        }
    }

    private fun showToast(text: String){
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun getFileFromUri(contentResolver: ContentResolver, uri: Uri, directory: File): File {
        val file = File.createTempFile("yyyyyyyy", "eeeeeeeee", directory)
        file.outputStream().use {
            contentResolver.openInputStream(uri)?.copyTo(it)
        }

        return file
    }

    private fun setBottomNav() {
        bottomNav.setOnNavigationItemSelectedListener(object :
                BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.home -> {
                        return true
                    }
                    R.id.completed -> {
                        val intent = Intent(this@OrderListActivity, PastOrdersActivity::class.java)
                        this@OrderListActivity.startActivity(intent)
                        return true
                    }
                    R.id.items -> {
                        val intent = Intent(this@OrderListActivity, ItemListActivity::class.java)
                        this@OrderListActivity.startActivity(intent)
                        return true
                    }
                }
                return false
            }
        })
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, DemoDataContent.ORDERS, twoPane)
    }

    @OptIn(ExperimentalStdlibApi::class)
    class SimpleItemRecyclerViewAdapter(
            private val parentActivity: OrderListActivity,
            private val values: List<Order>,
            private val twoPane: Boolean
    ) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val order = v.tag as Order
                if (twoPane) {
                    val fragment = OrderDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(OrderDetailFragment.ARG_ORDER_ID, order.id)
                        }
                    }
                    parentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, OrderDetailActivity::class.java).apply {
                        putExtra(OrderDetailFragment.ARG_ORDER_ID, order.id)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.content_order_list, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val order = values[position]
            holder.idView.text = "order: " + order.id
            holder.contentView.text = order.items.count().toString() + " items"

            with(holder.itemView) {
                tag = order
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.findViewById(R.id.order_name)
            val contentView: TextView = view.findViewById(R.id.item_count)
        }
    }
}