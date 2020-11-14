package com.patryk.quickpick.data

import java.util.ArrayList
import java.util.HashMap

object DemoDataContent {

    val ORDERS: MutableList<Order> = ArrayList()
    val PAST_ORDERS: MutableList<PastOrder> = ArrayList()
    val ITEMS: MutableList<Item> = ArrayList()

    val ORDERS_MAP: MutableMap<String, Order> = HashMap()

    private const val COUNT = 15

    init {
        for (i in 1..COUNT) {
            addOrder(createOrder(i))
        }

        PAST_ORDERS.add(PastOrder(createOrder(16),true))
        PAST_ORDERS.add(PastOrder(createOrder(17),true))
        PAST_ORDERS.add(PastOrder(createOrder(18),false))
        PAST_ORDERS.add(PastOrder(createOrder(19),true))
        PAST_ORDERS.add(PastOrder(createOrder(20),false))
    }

    private fun addOrder(order: Order) {
        ORDERS.add(order)
        ORDERS_MAP[order.id] = order
    }

    private fun createOrder(position: Int): Order {
        return Order(position.toString(), java.util.Date(), makeItems(position))
    }

    private fun makeItems(position: Int): List<Item> {
        var items = mutableListOf<Item>()

        for(i in 1..position){
            val item = Item("name:"+i.toString(), "SampleCategory", "brcd:"+i.toString(), 1f, Dimensions(1f,1f,1f))
            items.add(item)
            ITEMS.add(item)
        }

        return items
    }

}