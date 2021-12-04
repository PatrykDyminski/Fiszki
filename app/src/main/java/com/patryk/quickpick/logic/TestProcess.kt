package com.patryk.quickpick.logic

import com.patryk.quickpick.data.*

@ExperimentalStdlibApi
class TestProcess(listaFiszek: ListaFiszek) {

    private var _listaFiszek : ListaFiszek = listaFiszek

    private var pendingFiszki: MutableList<Fiszka> = mutableListOf()
    private var answeredFiszki: MutableList<TestFiszka> = mutableListOf()
    private var currentFiszka: Fiszka

    private var isProcessFinished: Boolean = false

    init{
        pendingFiszki.addAll(listaFiszek.fiszkas)
        currentFiszka = pendingFiszki.removeFirst()
    }

    fun getTotalFiszkiNumber() : Int{
        return _listaFiszek.fiszkas.size
    }

    fun getAnsweredFiszkiNumber() : Int{
        return answeredFiszki.size
    }

    fun getCurrentlyProcessedFiszka() : Fiszka {
        return currentFiszka
    }

    fun getTestSummary(): TestProcessSummary {
        return TestProcessSummary(answeredFiszki)
    }

    fun getIsProcessFinished() : Boolean{
        return isProcessFinished
    }

    fun nextFiszka(answeredFiszka: TestFiszka){
        answeredFiszki.add(answeredFiszka)

        if(pendingFiszki.size > 0){
            currentFiszka = pendingFiszki.removeFirst()
        }else{
            endProcess()
        }
    }

    private fun endProcess(){
        isProcessFinished = true
    }
}