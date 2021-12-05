package com.patryk.quickpick

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import com.patryk.quickpick.data.DemoDataContent
import com.patryk.quickpick.data.Fiszka
import com.patryk.quickpick.data.LearnStatus
import com.patryk.quickpick.data.ListaFiszek


class NewListActivity : AppCompatActivity() {

    private lateinit var ROWS: MutableList<Pair<EditText, EditText>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ROWS = ArrayList()

        //First row
        addRow()

        val finishButton: ExtendedFloatingActionButton = findViewById(R.id.saveListFab)
        finishButton.setOnClickListener {

            val fiszki = ArrayList<Fiszka>()

            ROWS.forEach { pair ->
                val wordString = pair.first.text.toString()
                val transString = pair.second.text.toString()
                fiszki.add(Fiszka(wordString, transString, LearnStatus.NOT_LEARNED))
            }

            val colName: EditText = findViewById(R.id.collectionNameInput)
            val listaFiszek = ListaFiszek(colName.text.toString(), fiszki)

            DemoDataContent.ListaFiszek.add(listaFiszek)
            DemoDataContent.ListaFiszekFull.add(listaFiszek)

            val intent = Intent(this, ListaListFiszekActivity::class.java)
            startActivity(intent)
        }

        val nextRow: Button = findViewById(R.id.addNextRowFab)
        nextRow.setOnClickListener {
            addRow()
        }

        val input: EditText = findViewById(R.id.collectionNameInput)
        input.setOnFocusChangeListener { v, hasFocus -> if (hasFocus){
            input.text.clear()
        } }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            navigateUpTo(Intent(this, ListaListFiszekActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun addRow(){
        val rowsLayout = findViewById<LinearLayout>(R.id.rows)
        val view: View = LayoutInflater.from(this).inflate(R.layout.new_list_row, null)

        val word: EditText = view.findViewById(R.id.wordInput)
        val translation: EditText = view.findViewById(R.id.translationInput)

        ROWS.add(Pair(word,translation))

        rowsLayout.addView(view)
    }
}