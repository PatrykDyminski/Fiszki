package com.patryk.quickpick

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.patryk.quickpick.data.DemoDataContent
import com.patryk.quickpick.data.ListaFiszek
import com.patryk.quickpick.data.Parser
import com.patryk.quickpick.ui.orderdetail.OrderDetailFragment
import java.io.File
import java.util.*

class ListaListFiszekActivity : AppCompatActivity() {

    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_list)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                DemoDataContent.ListaFiszek = DemoDataContent.ListaFiszekFull
                    .filter { it.name.toLowerCase(Locale.ROOT).contains(query?.toLowerCase(Locale.ROOT)!!) }
                    .toMutableList()
                setupRecyclerView(findViewById(R.id.item_list))
                return true
            }
        })

        searchView.setOnCloseListener {
            DemoDataContent.ListaFiszek = DemoDataContent.ListaFiszekFull
            setupRecyclerView(findViewById(R.id.item_list))
            false
        }

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            twoPane = true
        }

        val fab = findViewById<ExtendedFloatingActionButton>(R.id.newListFab)
        fab.setOnClickListener {
            val intent = Intent(this, NewListActivity::class.java)
            startActivity(intent)
        }


        setupRecyclerView(findViewById(R.id.item_list))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_main_menu, menu)
        return true
    }

    private var sortOrder: Boolean = true

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                R.id.sort -> {

                    if (sortOrder){
                        DemoDataContent.ListaFiszek.sortBy { it.name }
                        sortOrder = !sortOrder

                        showToast("Sorted by name")

                    }else{
                        DemoDataContent.ListaFiszek.sortBy { it.name }
                        DemoDataContent.ListaFiszek.reverse()
                        sortOrder = !sortOrder

                        showToast("Sorted by name")
                    }

                    setupRecyclerView(findViewById(R.id.item_list))

                    true
                }
                android.R.id.home -> {
                    val intent = Intent(this, MainPageActivity::class.java)
                    startActivity(intent)
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

                showToast("imported "+DemoDataContent.ListaFiszek.size.toString()+" orders")

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

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, DemoDataContent.ListaFiszek, twoPane)
    }

    @OptIn(ExperimentalStdlibApi::class)
    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: ListaListFiszekActivity,
        private val values: List<ListaFiszek>,
        private val twoPane: Boolean
    ) : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener = View.OnClickListener { v ->
            val order = v.tag as ListaFiszek
            if (twoPane) {
                val fragment = OrderDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(OrderDetailFragment.ARG_ORDER_ID, order.name)
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(v.context, OrderDetailActivity::class.java).apply {
                    putExtra(OrderDetailFragment.ARG_ORDER_ID, order.name)
                }
                v.context.startActivity(intent)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.content_order_list, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val order = values[position]
            holder.idView.text = order.name
            holder.contentView.text = order.fiszkas.count().toString() + " słówek"

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