package com.patryk.quickpick.ui.pickprocess

import androidx.lifecycle.ViewModel
import com.patryk.quickpick.data.Fiszka
import com.patryk.quickpick.data.ListaFiszek
import com.patryk.quickpick.data.PickProcessSummary
import com.patryk.quickpick.logic.PickProcess

@ExperimentalStdlibApi
class PickProcessViewModel : ViewModel() {

    private lateinit var processedListaFiszek: ListaFiszek
    private lateinit var pickProcess: PickProcess

    fun setupProcess(listaFiszek: ListaFiszek){
        processedListaFiszek = listaFiszek
        pickProcess = PickProcess(processedListaFiszek)
    }

    fun getCurrentlyProcessedItem(): Fiszka {
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