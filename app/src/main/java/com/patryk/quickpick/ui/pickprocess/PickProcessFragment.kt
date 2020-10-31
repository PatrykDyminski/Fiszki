package com.patryk.quickpick.ui.pickprocess

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.patryk.quickpick.R

class PickProcessFragment : Fragment() {

    companion object {
        fun newInstance() = PickProcessFragment()
    }

    private val viewModel: PickProcessViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_pick_process, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


//        viewModel.GetFirstProcessItem().let {
//            //UI
//        }
    }

}