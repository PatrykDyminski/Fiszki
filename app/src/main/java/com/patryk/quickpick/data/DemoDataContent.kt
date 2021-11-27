package com.patryk.quickpick.data

import java.util.ArrayList

object DemoDataContent {

    var ORDERS: MutableList<Order> = ArrayList()
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

    private fun addOrder(order: Order) {
        ORDERS.add(order)
    }

    fun createOrder(position: Int): Order {
        return Order(position.toString(), java.util.Date(), makeFiszki(position))
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