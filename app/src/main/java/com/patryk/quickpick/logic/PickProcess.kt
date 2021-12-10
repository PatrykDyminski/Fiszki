package com.patryk.quickpick.logic

import com.patryk.quickpick.data.Fiszka
import com.patryk.quickpick.data.LearnStatus
import com.patryk.quickpick.data.ListaFiszek
import com.patryk.quickpick.data.PickProcessSummary

@ExperimentalStdlibApi
class PickProcess(listaFiszek: ListaFiszek) {

    private var _listaFiszek : ListaFiszek = listaFiszek

    private var pendingFiszki: MutableList<Fiszka> = mutableListOf()
    private var learnedFiszki: MutableList<Fiszka> = mutableListOf()
    private var currentFiszka: Fiszka

    private var isProcessFinished: Boolean = false

    init{
        listaFiszek.fiszkas.forEach { fiszka ->
            fiszka.status = LearnStatus.NOT_LEARNED
        }
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
        pendingFiszki.add(currentFiszka)
        return PickProcessSummary(learnedFiszki, pendingFiszki, _listaFiszek)
    }

    fun getIsProcessFinished() : Boolean{
        return isProcessFinished
    }

    fun wordLearned(){
        currentFiszka.status = LearnStatus.LEARNED
        learnedFiszki.add(currentFiszka)
        nextFiszka()
    }

    fun wordMixed() {
        currentFiszka.status = LearnStatus.MIXED
        pendingFiszki.add(currentFiszka)
        nextFiszka()
    }

    fun wordNotLearned() {
        currentFiszka.status = LearnStatus.NOT_LEARNED
        pendingFiszki.add(currentFiszka)
        nextFiszka()
    }

    private fun nextFiszka(){
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