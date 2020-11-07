package com.patryk.quickpick.ui.pickprocess

import androidx.lifecycle.ViewModel
import com.patryk.quickpick.data.Item
import com.patryk.quickpick.data.Order
import com.patryk.quickpick.data.PickProcessSummary
import com.patryk.quickpick.logic.PickProcess

@ExperimentalStdlibApi
class PickProcessViewModel : ViewModel() {

    private lateinit var processedOrder: Order
    private lateinit var pickProcess: PickProcess

    fun setupProcess(order: Order){
        processedOrder = order
        pickProcess = PickProcess(processedOrder)
    }

    fun getCurrentlyProcessedItem(): Item {
        return pickProcess.getCurrentlyProcessedItem()
    }

    fun isProcessFinished() : Boolean {
        return pickProcess.getIsProcessFinished()
    }

    fun itemPicked(){
        pickProcess.pickItem()
    }

    fun problemWithItem(){
        pickProcess.failItem()
    }

    fun getSummary(): PickProcessSummary {
        return pickProcess.getPickingSummary()
    }
}