package com.patryk.quickpick.ui.testprocess

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.patryk.quickpick.R
import com.patryk.quickpick.TestSummaryActivity
import com.patryk.quickpick.data.*
import com.patryk.quickpick.ui.orderdetail.OrderDetailFragment

@ExperimentalStdlibApi
class TestProcessFragment : Fragment() {

    private val viewModel: TestProcessViewModel by viewModels()
    private lateinit var fragmentView : View
    private lateinit var listaFiszek: ListaFiszek
    private lateinit var currentFiszka: Fiszka

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(OrderDetailFragment.ARG_ORDER_ID)) {
                listaFiszek = DemoDataContent.ListaFiszek.find { oo -> oo.name ==  it.getString(OrderDetailFragment.ARG_ORDER_ID) }!!
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        val view =  inflater.inflate(R.layout.fragment_test_process, container, false)
        fragmentView = view

        viewModel.setupProcess(listaFiszek)
        val item = viewModel.getCurrentlyProcessedFiszka()

        val nameLabel: TextView = fragmentView.findViewById(R.id.collectionName)
        nameLabel.text = listaFiszek.name

        setItem(item)
        assignButtons()

        return view
    }

    private fun assignButtons(){
        val nextButton: Button = fragmentView.findViewById(R.id.nextButton)
        nextButton.setOnClickListener {
            handleNextWord()
        }
    }

    private fun handleNextWord(){
        val input: EditText = fragmentView.findViewById(R.id.translation_input)
        val answer = input.text.toString().trim()
        val correct = currentFiszka.translation.equals(answer, ignoreCase = true)
        val testFiszka = TestFiszka(currentFiszka, answer, WordType.ORIGINAL, correct)

        viewModel.nextWord(testFiszka)

        input.text.clear()

        if(viewModel.isProcessFinished()){
            goToSummary()
        }else{
            setItem(viewModel.getCurrentlyProcessedFiszka())
        }
    }

    private fun goToSummary() {
        DemoDataContent.LastCollectionNames.remove(listaFiszek.name)
        DemoDataContent.LastCollectionNames.add(listaFiszek.name)

        val summary = viewModel.getSummary()
        val intent = Intent(context, TestSummaryActivity::class.java).apply {
            putExtra(TEST_SUMMARY, summary)
        }
        context?.startActivity(intent)
    }

    private fun setItem(fiszka: Fiszka){
        currentFiszka = fiszka;
        fragmentView.findViewById<TextView>(R.id.wordLabel).text = fiszka.word

        updateStatusLabel()
    }

    private fun updateStatusLabel() {
        fragmentView.findViewById<TextView>(R.id.learnedLabel).text = "Sprawdzono: " + viewModel.getAnsweredFiszkiNumber() + "/" + viewModel.getTotalFiszkiNumber()
    }

    companion object {
        fun newInstance() = TestProcessFragment()

        const val TEST_SUMMARY = "completed"
    }
}