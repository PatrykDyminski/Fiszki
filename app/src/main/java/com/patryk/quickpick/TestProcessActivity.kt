package com.patryk.quickpick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.patryk.quickpick.ui.orderdetail.OrderDetailFragment
import com.patryk.quickpick.ui.pickprocess.PickProcessFragment
import com.patryk.quickpick.ui.testprocess.TestProcessFragment

@ExperimentalStdlibApi
class TestProcessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_process)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {

            val fragment = TestProcessFragment.newInstance().apply {
                arguments = Bundle().apply {
                    putString(OrderDetailFragment.ARG_ORDER_ID,
                        intent.getStringExtra(OrderDetailFragment.ARG_ORDER_ID))
                }
            }

            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commitNow()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}