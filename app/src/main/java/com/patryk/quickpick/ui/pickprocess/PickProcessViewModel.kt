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

    fun getTotalFiszkiNumber() : Int{
        return pickProcess.getTotalFiszkiNumber()
    }

    fun getLearnedFiszkiNumber() : Int{
        return pickProcess.getLearnedFiszkiNumber()
    }

    fun getCurrentlyProcessedFiszka(): Fiszka {
        return pickProcess.getCurrentlyProcessedFiszka()
    }

    fun isProcessFinished() : Boolean {
        return pickProcess.getIsProcessFinished()
    }

    fun wordLearned(){
        pickProcess.wordLearned()
    }

    fun wordNotLearned(){
        pickProcess.wordNotLearned()
    }

    fun getSummary(): PickProcessSummary {
        return pickProcess.getPickingSummary()
    }
}