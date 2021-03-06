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
import android.widget.ImageButton
import com.google.android.material.snackbar.Snackbar
import com.patryk.quickpick.data.DemoDataContent
import com.patryk.quickpick.data.Fiszka
import com.patryk.quickpick.data.LearnStatus
import com.patryk.quickpick.data.ListaFiszek

@ExperimentalStdlibApi
class NewListActivity : AppCompatActivity() {

    private lateinit var ROWS: MutableList<Pair<EditText, EditText>>
    private var isEdit: Boolean = false
    private var colEditName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ROWS = ArrayList()

        val colEdit: EditText = findViewById(R.id.collectionNameInput)

        val colName = intent.getStringExtra("XDD")
        if(colName != null){
            isEdit = true
            colEditName = colName

            supportActionBar?.title = "Edytuj Kolekcje"

            colEdit.setText(colName)

            val fiszki = DemoDataContent.ListaFiszekFull.find { it.name == colName }
            if (fiszki != null) {
                for (fiszka in fiszki.fiszkas) {
                    addRow(fiszka)
                }
            }
        }else{
            //First row
            addRow()
            colEdit.requestFocus()
        }

        val finishButton: ExtendedFloatingActionButton = findViewById(R.id.saveListFab)
        finishButton.setOnClickListener {
            saveCollection()
        }

        val nextRow: Button = findViewById(R.id.addNextRowFab)
        nextRow.setOnClickListener {
            addRow()
        }
    }

    private fun saveCollection() {
        val fiszki = ArrayList<Fiszka>()

        ROWS.forEach { pair ->
            val wordString = pair.first.text.toString().trim()
            val transString = pair.second.text.toString().trim()
            fiszki.add(Fiszka(wordString, transString, LearnStatus.NOT_LEARNED))
        }

        val colName: EditText = findViewById(R.id.collectionNameInput)
        val listaFiszek = ListaFiszek(colName.text.toString().trim(), fiszki)

        val contextView = findViewById<View>(R.id.scroll)

        when {
            listaFiszek.name.isBlank() -> {
                Snackbar
                    .make(contextView, "Nie mo??na zapisa?? listy bez nazwy", Snackbar.LENGTH_SHORT)
                    .show()
            }
            listaFiszek.fiszkas.isEmpty() -> {
                Snackbar
                    .make(contextView, "Nie mo??na zapisa?? pustej kolekcji", Snackbar.LENGTH_SHORT)
                    .show()
            }
            listaFiszek.fiszkas.any { it.word.isBlank() || it.translation.isBlank() } -> {
                Snackbar
                    .make(contextView, "Nie mo??na zapisa?? kolekcji zawieraj??cej puste s??owa", Snackbar.LENGTH_SHORT)
                    .show()
            }
            else -> {
                if(isEdit){
                    DemoDataContent.ListaFiszek.removeIf { it.name == colEditName }
                    DemoDataContent.ListaFiszekFull.removeIf { it.name == colEditName }
                }

                DemoDataContent.ListaFiszek.add(listaFiszek)
                DemoDataContent.ListaFiszekFull.add(listaFiszek)

                DemoDataContent.LastCollectionNames.remove(listaFiszek.name)
                DemoDataContent.LastCollectionNames.add(listaFiszek.name)

                val intent = Intent(this, MainPageActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            navigateUpTo(Intent(this, ListaListFiszekActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun addRow(){
        val rowsLayout = findViewById<LinearLayout>(R.id.rows)
        val view: View = LayoutInflater.from(this).inflate(R.layout.new_list_row, null)

        val word: EditText = view.findViewById(R.id.wordInput)
        val translation: EditText = view.findViewById(R.id.translationInput)

        val pair = Pair(word,translation)

        val delBtn: ImageButton = view.findViewById(R.id.delBtn)
        delBtn.setOnClickListener{
            rowsLayout.removeView(view)
            ROWS.remove(pair)
        }

        ROWS.add(pair)
        rowsLayout.addView(view)

        word.requestFocus()
    }

    private fun addRow(fiszka: Fiszka){
        val rowsLayout = findViewById<LinearLayout>(R.id.rows)
        val view: View = LayoutInflater.from(this).inflate(R.layout.new_list_row, null)

        val word: EditText = view.findViewById(R.id.wordInput)
        val translation: EditText = view.findViewById(R.id.translationInput)

        word.setText(fiszka.word)
        translation.setText(fiszka.translation)

        val pair = Pair(word,translation)

        val delBtn: ImageButton = view.findViewById(R.id.delBtn)
        delBtn.setOnClickListener{
            rowsLayout.removeView(view)
            ROWS.remove(pair)
        }

        ROWS.add(pair)

        rowsLayout.addView(view)
    }
}