package com.patryk.quickpick.ui.pickprocess

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.patryk.quickpick.ui.orderdetail.OrderDetailFragment
import com.patryk.quickpick.OrderSummaryActivity
import com.patryk.quickpick.R
import com.patryk.quickpick.data.DemoDataContent
import com.patryk.quickpick.data.Fiszka
import com.patryk.quickpick.data.ListaFiszek

@ExperimentalStdlibApi
class PickProcessFragment : Fragment() {

    private val viewModel: PickProcessViewModel by viewModels()

    private lateinit var fragmentView : View

    private lateinit var listaFiszek: ListaFiszek

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
        val view =  inflater.inflate(R.layout.fragment_pick_process, container, false)
        fragmentView = view

        viewModel.setupProcess(listaFiszek)
        val item = viewModel.getCurrentlyProcessedItem()

        setItem(item)
        assignButtons(view)

        return view
    }

    private fun assignButtons(view: View){

        val pickSuccessButton: Button = view.findViewById(R.id.pickSuccessButton)
        pickSuccessButton.setOnClickListener {
            viewModel.itemPicked()
            handleNextItem()
        }

        val pickProblemButton: Button = view.findViewById(R.id.pickProblemButton)
        pickProblemButton.setOnClickListener {
            viewModel.problemWithItem()
            handleNextItem()
        }
    }

    private fun handleNextItem(){
        if(viewModel.isProcessFinished()){
            goToSummary()
        }else{
            val nextItem = viewModel.getCurrentlyProcessedItem()
            setItem(nextItem)
        }
    }

    private fun goToSummary() {
        val summary = viewModel.getSummary()
        val intent = Intent(context, OrderSummaryActivity::class.java).apply {
            putExtra(ORDER_SUMMARY, summary)
        }
        context?.startActivity(intent)
    }

    private fun setItem(fiszka: Fiszka){
        fragmentView.findViewById<TextView>(R.id.itemNameLabel).text = fiszka.word
        fragmentView.findViewById<TextView>(R.id.categoryLabel).text = fiszka.translation
    }

    companion object {
        fun newInstance() = PickProcessFragment()

        const val ORDER_SUMMARY = "completed"
    }
}