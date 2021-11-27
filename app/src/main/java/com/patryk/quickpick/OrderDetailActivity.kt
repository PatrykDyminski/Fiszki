package com.patryk.quickpick

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.patryk.quickpick.ui.orderdetail.OrderDetailFragment

@ExperimentalStdlibApi
class OrderDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val fragment = OrderDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(OrderDetailFragment.ARG_ORDER_ID,
                            intent.getStringExtra(OrderDetailFragment.ARG_ORDER_ID))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit()
        }
    }

    fun fBack(){
        navigateUpTo(Intent(this, ListaListFiszekActivity::class.java))
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            navigateUpTo(Intent(this, ListaListFiszekActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}