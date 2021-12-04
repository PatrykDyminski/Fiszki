package com.patryk.quickpick.ui.testprocess

import androidx.lifecycle.ViewModel
import com.patryk.quickpick.data.*
import com.patryk.quickpick.logic.PickProcess
import com.patryk.quickpick.logic.TestProcess

@ExperimentalStdlibApi
class TestProcessViewModel : ViewModel() {

    private lateinit var processedListaFiszek: ListaFiszek
    private lateinit var testProcess: TestProcess

    fun setupProcess(listaFiszek: ListaFiszek){
        processedListaFiszek = listaFiszek
        testProcess = TestProcess(processedListaFiszek)
    }

    fun getTotalFiszkiNumber() : Int{
        return testProcess.getTotalFiszkiNumber()
    }

    fun getAnsweredFiszkiNumber() : Int{
        return testProcess.getAnsweredFiszkiNumber()
    }

    fun getCurrentlyProcessedFiszka(): Fiszka {
        return testProcess.getCurrentlyProcessedFiszka()
    }

    fun isProcessFinished() : Boolean {
        return testProcess.getIsProcessFinished()
    }

    fun nextWord(answeredFiszka: TestFiszka){
        testProcess.nextFiszka(answeredFiszka)
    }

    fun getSummary(): TestProcessSummary {
        return testProcess.getTestSummary()
    }
}