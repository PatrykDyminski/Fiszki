package com.patryk.quickpick.logic

import com.patryk.quickpick.data.Item
import com.patryk.quickpick.data.Order

@ExperimentalStdlibApi
class PickProcess(order: Order) {

    private var pendingItems: MutableList<Item> = mutableListOf<Item>()
    private var completedItems: MutableList<Item> = mutableListOf<Item>()
    private var failedItems: MutableList<Item> = mutableListOf<Item>()
    private var currentItem: Item

    init{
        pendingItems.addAll(order.items)
        currentItem = pendingItems.removeFirst()
    }

    fun pickItem(){
        completedItems.add(currentItem)
        if(pendingItems.size > 0){
            currentItem = pendingItems.removeFirst()
        }else{
            endProcess()
        }
    }

    fun failItem(){
        failedItems.add(currentItem)
        if(pendingItems.size > 0){
            currentItem = pendingItems.removeFirst()
        }else{
            endProcess()
        }
    }

    fun endProcess(){

    }
}