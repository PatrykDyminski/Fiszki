package com.patryk.quickpick

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.patryk.quickpick.data.DemoDataContent
import com.patryk.quickpick.ui.orderdetail.OrderDetailFragment

class MainPageActivity : AppCompatActivity() {

    @ExperimentalStdlibApi
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
    }
}