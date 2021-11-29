package com.patryk.quickpick.logic

import com.patryk.quickpick.data.Fiszka
import com.patryk.quickpick.data.ListaFiszek
import com.patryk.quickpick.data.PickProcessSummary

@ExperimentalStdlibApi
class PickProcess(listaFiszek: ListaFiszek) {

    private var _listaFiszek : ListaFiszek = listaFiszek

    private var pendingFiszki: MutableList<Fiszka> = mutableListOf()
    private var learnedFiszki: MutableList<Fiszka> = mutableListOf()
    private var failedFiszkas: MutableList<Fiszka> = mutableListOf()
    private var currentFiszka: Fiszka

    private var isProcessFinished: Boolean = false

    init{
        pendingFiszki.addAll(listaFiszek.fiszkas)
        currentFiszka = pendingFiszki.removeFirst()
    }

    fun getTotalFiszkiNumber() : Int{
        return _listaFiszek.fiszkas.size
    }

    fun getLearnedFiszkiNumber() : Int{
        return learnedFiszki.size
    }

    fun getCurrentlyProcessedFiszka() : Fiszka {
        return currentFiszka
    }

    fun getPickingSummary(): PickProcessSummary {
        return PickProcessSummary(learnedFiszki, failedFiszkas, _listaFiszek)
    }

    fun getIsProcessFinished() : Boolean{
        return isProcessFinished
    }

    fun wordLearned(){
        learnedFiszki.add(currentFiszka)
        nextFiszka()
    }

    fun wordNotLearned() {
        pendingFiszki.add(currentFiszka)
        nextFiszka()
    }

    fun nextFiszka(){
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