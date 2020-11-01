package com.patryk.quickpick.ui.pickprocess

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.patryk.quickpick.OrderDetailFragment
import com.patryk.quickpick.R
import com.patryk.quickpick.data.DemoDataContent
import com.patryk.quickpick.data.Item
import com.patryk.quickpick.data.Order


@ExperimentalStdlibApi
class PickProcessFragment : Fragment() {

    private val viewModel: PickProcessViewModel by viewModels()

    private lateinit var fragmentView : View

    private lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(OrderDetailFragment.ARG_ORDER_ID)) {
                order = DemoDataContent.ORDERS_MAP[it.getString(OrderDetailFragment.ARG_ORDER_ID)]!!
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        val view =  inflater.inflate(R.layout.fragment_pick_process, container, false)
        fragmentView = view

        viewModel.setupProcess(order)
        val item = viewModel.getCurrentlyProcessedItem()

        setItem(item)

        assignButtons(view)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


//        viewModel.GetFirstProcessItem().let {
//            //UI
//        }
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

        }else{
            val nextItem = viewModel.getCurrentlyProcessedItem()
            setItem(nextItem)
        }
    }

    private fun setItem(item: Item){
        fragmentView.findViewById<TextView>(R.id.itemNameLabel).text = item.name
        fragmentView.findViewById<TextView>(R.id.barcodeLabel).text = item.barcode
        fragmentView.findViewById<TextView>(R.id.categoryLabel).text = item.category
        fragmentView.findViewById<TextView>(R.id.weightLabel).text = item.mass.toString()
        fragmentView.findViewById<TextView>(R.id.dimensionsLabel).text = item.dimensions.toString()
    }

    companion object {
        fun newInstance() = PickProcessFragment()
    }
}