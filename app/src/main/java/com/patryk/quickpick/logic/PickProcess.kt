package com.patryk.quickpick.logic

import com.patryk.quickpick.data.Fiszka
import com.patryk.quickpick.data.ListaFiszek
import com.patryk.quickpick.data.PickProcessSummary

@ExperimentalStdlibApi
class PickProcess(listaFiszek: ListaFiszek) {

    private var _listaFiszek : ListaFiszek = listaFiszek

    private var pendingFiszkas: MutableList<Fiszka> = mutableListOf<Fiszka>()
    private var completedFiszkas: MutableList<Fiszka> = mutableListOf<Fiszka>()
    private var failedFiszkas: MutableList<Fiszka> = mutableListOf<Fiszka>()
    private var currentFiszka: Fiszka

    private var isProcessFinished: Boolean = false

    init{
        pendingFiszkas.addAll(listaFiszek.fiszkas)
        currentFiszka = pendingFiszkas.removeFirst()
    }

    fun getCurrentlyProcessedItem() : Fiszka{
        return currentFiszka
    }

    fun getPickingSummary(): PickProcessSummary {
        return PickProcessSummary(completedFiszkas, failedFiszkas, _listaFiszek)
    }

    fun getIsProcessFinished() : Boolean{
        return isProcessFinished
    }

    fun pickItem(){
        completedFiszkas.add(currentFiszka)
        if(pendingFiszkas.size > 0){
            currentFiszka = pendingFiszkas.removeFirst()
        }else{
            endProcess()
        }
    }

    fun failItem(){
        failedFiszkas.add(currentFiszka)
        if(pendingFiszkas.size > 0){
            currentFiszka = pendingFiszkas.removeFirst()
        }else{
            endProcess()
        }
    }

    private fun endProcess(){
        isProcessFinished = true
    }
}