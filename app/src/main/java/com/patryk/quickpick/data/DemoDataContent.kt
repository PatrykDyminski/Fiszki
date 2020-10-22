package com.patryk.quickpick.data

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DemoDataContent {

    val ORDERS: MutableList<Order> = ArrayList()

    val ORDERS_MAP: MutableMap<String, Order> = HashMap()

    private val COUNT = 20

    init {
        for (i in 1..COUNT) {
            addOrder(createOrder(i))
        }
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
            items.add(Item(i.toString(), "SampleCategory", 1f, Dimensions(1f,1f,1f)))
        }

        return items
    }

}