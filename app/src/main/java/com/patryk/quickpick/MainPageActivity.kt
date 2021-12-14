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

        val name1 = DemoDataContent.LastCollectionNames.last()
        val name2 = DemoDataContent.LastCollectionNames[DemoDataContent.LastCollectionNames.size - 2]
        val name3 = DemoDataContent.LastCollectionNames[DemoDataContent.LastCollectionNames.size - 3]
        val name4 = DemoDataContent.LastCollectionNames[DemoDataContent.LastCollectionNames.size - 4]

        val testBtn: Button = findViewById(R.id.testBtn)
        testBtn.setOnClickListener {
            val intent = Intent(this, TestProcessActivity::class.java).apply {
                putExtra(OrderDetailFragment.ARG_ORDER_ID, name1)
            }
            startActivity(intent)
        }

        val studyBtn: Button = findViewById(R.id.studyBtn)
        studyBtn.setOnClickListener {
            val intent = Intent(this, PickProcessActivity::class.java).apply {
                putExtra(OrderDetailFragment.ARG_ORDER_ID, name1)
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
        col1.text = name1
        col1.setOnClickListener {
            val intent = Intent(this, OrderDetailActivity::class.java).apply {
                putExtra(OrderDetailFragment.ARG_ORDER_ID, name1)
            }
            startActivity(intent)
        }

        val col2: TextView = findViewById(R.id.col2)
        col2.text = name2
        col2.setOnClickListener {
            val intent = Intent(this, OrderDetailActivity::class.java).apply {
                putExtra(OrderDetailFragment.ARG_ORDER_ID, name2)
            }
            startActivity(intent)
        }

        val col3: TextView = findViewById(R.id.col3)
        col3.text = name3
        col3.setOnClickListener {
            val intent = Intent(this, OrderDetailActivity::class.java).apply {
                putExtra(OrderDetailFragment.ARG_ORDER_ID, name3)
            }
            startActivity(intent)
        }

        val col4: TextView = findViewById(R.id.col4)
        col4.text = name4
        col4.setOnClickListener {
            val intent = Intent(this, OrderDetailActivity::class.java).apply {
                putExtra(OrderDetailFragment.ARG_ORDER_ID, name4)
            }
            startActivity(intent)
        }
    }
}