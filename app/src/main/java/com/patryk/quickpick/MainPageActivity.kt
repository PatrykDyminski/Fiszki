package com.patryk.quickpick

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.patryk.quickpick.data.DemoDataContent
import com.patryk.quickpick.ui.orderdetail.OrderDetailFragment

@ExperimentalStdlibApi
class MainPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val testBtn: Button = findViewById(R.id.testBtn)
        testBtn.setOnClickListener {
            val intent = Intent(this, TestProcessActivity::class.java).apply {
                putExtra(OrderDetailFragment.ARG_ORDER_ID, DemoDataContent.ListaFiszekFull[0].name)
            }
            startActivity(intent)
        }

        val studyBtn: Button = findViewById(R.id.studyBtn)
        studyBtn.setOnClickListener {
            val intent = Intent(this, PickProcessActivity::class.java).apply {
                putExtra(OrderDetailFragment.ARG_ORDER_ID, DemoDataContent.ListaFiszekFull[0].name)
            }
            startActivity(intent)
        }

        val newColBtn: Button = findViewById(R.id.newListBtn)
        newColBtn.setOnClickListener {
            val intent = Intent(this, NewListActivity::class.java)
            startActivity(intent)
        }

        val listBtn: Button = findViewById(R.id.listBtn)
        listBtn.setOnClickListener {
            val intent = Intent(this, ListaListFiszekActivity::class.java)
            startActivity(intent)
        }

        val col1: TextView = findViewById(R.id.col1)
        col1.text = DemoDataContent.ListaFiszekFull[0].name
        col1.setOnClickListener {
            val intent = Intent(this, OrderDetailActivity::class.java).apply {
                putExtra(OrderDetailFragment.ARG_ORDER_ID, DemoDataContent.ListaFiszekFull[0].name)
            }
            startActivity(intent)
        }

        val col2: TextView = findViewById(R.id.col2)
        col2.text = DemoDataContent.ListaFiszekFull[1].name
        col2.setOnClickListener {
            val intent = Intent(this, OrderDetailActivity::class.java).apply {
                putExtra(OrderDetailFragment.ARG_ORDER_ID, DemoDataContent.ListaFiszekFull[1].name)
            }
            startActivity(intent)
        }

        val col3: TextView = findViewById(R.id.col3)
        col3.text = DemoDataContent.ListaFiszekFull[2].name
        col3.setOnClickListener {
            val intent = Intent(this, OrderDetailActivity::class.java).apply {
                putExtra(OrderDetailFragment.ARG_ORDER_ID, DemoDataContent.ListaFiszekFull[2].name)
            }
            startActivity(intent)
        }

        val col4: TextView = findViewById(R.id.col4)
        col4.text = DemoDataContent.ListaFiszekFull[3].name
        col4.setOnClickListener {
            val intent = Intent(this, OrderDetailActivity::class.java).apply {
                putExtra(OrderDetailFragment.ARG_ORDER_ID, DemoDataContent.ListaFiszekFull[3].name)
            }
            startActivity(intent)
        }
    }
}