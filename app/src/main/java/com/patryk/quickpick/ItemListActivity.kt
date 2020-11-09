package com.patryk.quickpick

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.patryk.quickpick.data.DemoDataContent

class ItemListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var bottomNav : BottomNavigationView

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        //toolbar.title = title

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.items
        setBottomNav()

        viewManager = LinearLayoutManager(this)
        viewAdapter = OrderDetailFragment.MyAdapter(DemoDataContent.ITEMS)
        recyclerView = this.findViewById<RecyclerView>(R.id.allItems).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun setBottomNav() {
        bottomNav.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.home -> {
                        val intent = Intent(this@ItemListActivity, OrderListActivity::class.java)
                        this@ItemListActivity.startActivity(intent)
                        return true
                    }
                    R.id.completed -> {
                        return true
                    }
                    R.id.items -> {
                        return true
                    }
                }
                return false
            }
        })
    }
}