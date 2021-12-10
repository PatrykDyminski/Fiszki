package com.patryk.quickpick.ui.orderdetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patryk.quickpick.*
import com.patryk.quickpick.data.DemoDataContent
import com.patryk.quickpick.data.Fiszka
import com.patryk.quickpick.data.ListaFiszek

@ExperimentalStdlibApi
class OrderDetailFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var listaFiszek: ListaFiszek

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ORDER_ID)) {
                listaFiszek = DemoDataContent.ListaFiszek.find { oo -> oo.name ==  it.getString(ARG_ORDER_ID) }!!
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_order_detail, container, false)

        viewManager = LinearLayoutManager(context)
        viewAdapter = MyAdapter(listaFiszek.fiszkas)
        recyclerView = rootView.findViewById<RecyclerView>(R.id.itemsInOrderRecycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        bindFields(rootView)
        bindButtons(rootView)

        return rootView
    }

    private fun bindFields(rootView: View){
        rootView.findViewById<TextView>(R.id.pageTitle).text = listaFiszek.name
    }

    private fun bindButtons(rootView : View){
        val startPickingButton: Button = rootView.findViewById(R.id.start_picking)
        startPickingButton.setOnClickListener {
            val intent = Intent(context, PickProcessActivity::class.java).apply {
                putExtra(ARG_ORDER_ID, listaFiszek.name)
            }
            context?.startActivity(intent)
        }

        val startTestButton: Button = rootView.findViewById(R.id.start_test)
        startTestButton.setOnClickListener {
            val intent = Intent(context, TestProcessActivity::class.java).apply {
                putExtra(ARG_ORDER_ID, listaFiszek.name)
            }
            context?.startActivity(intent)
        }

        val edit: Button = rootView.findViewById(R.id.editBtn)
        edit.setOnClickListener {
            val intent = Intent(context, NewListActivity::class.java).apply {
                putExtra("XDD", listaFiszek.name)
            }
            context?.startActivity(intent)
        }


    }

    companion object {
        const val ARG_ORDER_ID = "order_id"
    }

    class MyAdapter(private val fiszkas: List<Fiszka>)
        : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.item_name)
            val category: TextView = view.findViewById(R.id.category)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.content_item_in_order, parent, false) as LinearLayout

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = fiszkas[position]
            holder.name.text = item.word
            holder.category.text = item.translation
        }

        override fun getItemCount() = fiszkas.size
    }
}