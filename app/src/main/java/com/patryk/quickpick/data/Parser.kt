package com.patryk.quickpick.data

import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object Parser {

    fun parseFile(file: File): ArrayList<Order> {
        val content = file.readLines()

        val itemsStrings = ArrayList<String>()
        val ordersStrings = ArrayList<String>()
        var areOrders = false

        for(line in content){
            if (line.split(";")[0].toUpperCase() == "ORDERS"){
                areOrders = true
                continue
            }
            if(areOrders){
                ordersStrings.add(line)
            }else{
                itemsStrings.add(line)
            }
        }

        val items = populateItems(itemsStrings)
        val orders = populateOrders(ordersStrings, items)

        DemoDataContent.ITEMS = items
        DemoDataContent.ORDERS = orders
        DemoDataContent.PAST_ORDERS = ArrayList()

        return orders
    }

    private fun populateItems(itemStrings: ArrayList<String>): ArrayList<Item> {

        val items = ArrayList<Item>()

        for(itemString in itemStrings){
            val item = createItem(itemString)
            items.add(item)
        }

        return items
    }

    private fun populateOrders(orderStrings: ArrayList<String>, items: List<Item>): ArrayList<Order> {

        val orders = ArrayList<Order>()

        for(orderString in orderStrings){
            val order = createOrder(orderString, items)
            orders.add(order)
        }

        return orders
    }

    private fun createOrder(orderString: String, items: List<Item>): Order {

        val data = orderString.split(";")

        val oname = data[0]

        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
        val dateInString = data[1]
        val odate: Date? = formatter.parse(dateInString)

        val last = data.indexOf("")

        val itemSS = data.subList(2, if(last == -1) data.size else last )

        val oitems = ArrayList<Item>()

        for(itemstr in itemSS){
            oitems.add(items.find { item -> item.barcode == itemstr }!!)
        }

        val order = Order(oname, odate!!, oitems)

        return order
    }

    fun createItem(itemString: String): Item {
        val data = itemString.split(";")

        return Item(data[0], data[1], data[2], data[3].toDouble(), Dimensions(data[4].toFloat(), data[5].toFloat(), data[6].toFloat()))
    }
}