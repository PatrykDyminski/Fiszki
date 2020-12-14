package com.patryk.quickpick.data

import java.util.ArrayList
import java.util.HashMap

object DemoDataContent {

    var ORDERS: MutableList<Order> = ArrayList()
    var PAST_ORDERS: MutableList<PastOrder> = ArrayList()
    var ITEMS: MutableList<Item> = ArrayList()

    private const val COUNT = 15

    init {
        for (i in 1..COUNT) {
            addOrder(createOrder(i))
        }

        PAST_ORDERS.add(PastOrder(createOrder(16), OrderStatus.FAIL))
        PAST_ORDERS.add(PastOrder(createOrder(17),OrderStatus.SUCCESS))
        PAST_ORDERS.add(PastOrder(createOrder(18),OrderStatus.MIXED))
        PAST_ORDERS.add(PastOrder(createOrder(19),OrderStatus.FAIL))
        PAST_ORDERS.add(PastOrder(createOrder(20),OrderStatus.SUCCESS))
    }

    private fun addOrder(order: Order) {
        ORDERS.add(order)
    }

    fun createOrder(position: Int): Order {
        return Order(position.toString(), java.util.Date(), makeItems(position))
    }

    private fun makeItems(position: Int): List<Item> {
        var items = mutableListOf<Item>()

        for(i in 1..position){
            val item = Item("name:$i", "SampleCategory", "brcd:$i", 20.2 * i, Dimensions(1f,1f,1f))
            items.add(item)
            ITEMS.add(item)
        }

        return items
    }
}