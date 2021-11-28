package com.patryk.quickpick.data

import java.util.ArrayList

object DemoDataContent {

    var ListaFiszekFull: MutableList<ListaFiszek> = ArrayList()
    var ListaFiszeks: MutableList<ListaFiszek> = ArrayList()
    var PAST_ORDERS: MutableList<PastOrder> = ArrayList()
    var Fiszkas: MutableList<Fiszka> = ArrayList()

    private const val COUNT = 15

    init {
        for (i in 1..COUNT) {
            addOrder(createOrder(i))
        }

        PAST_ORDERS.add(PastOrder(createOrder(16),OrderStatus.FAIL))
        PAST_ORDERS.add(PastOrder(createOrder(17),OrderStatus.SUCCESS))
        PAST_ORDERS.add(PastOrder(createOrder(18),OrderStatus.MIXED))
        PAST_ORDERS.add(PastOrder(createOrder(19),OrderStatus.FAIL))
        PAST_ORDERS.add(PastOrder(createOrder(20),OrderStatus.SUCCESS))
    }

    private fun addOrder(listaFiszek: ListaFiszek) {
        ListaFiszeks.add(listaFiszek)
        ListaFiszekFull.add(listaFiszek)
    }

    fun createOrder(position: Int): ListaFiszek {
        return ListaFiszek("Lista$position", makeFiszki(position))
    }

    private fun makeFiszki(position: Int): List<Fiszka> {
        var fiszki = mutableListOf<Fiszka>()

        for(i in 1..position){
            val fiszka = Fiszka("word:$i", "translation:$i", LearnStatus.LEARNED)
            fiszki.add(fiszka)
            Fiszkas.add(fiszka)
        }

        return fiszki
    }
}