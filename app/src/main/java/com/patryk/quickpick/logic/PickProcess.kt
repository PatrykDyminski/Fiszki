package com.patryk.quickpick.logic

import com.patryk.quickpick.data.Item
import com.patryk.quickpick.data.Order
import com.patryk.quickpick.data.PickProcessSummary

@ExperimentalStdlibApi
class PickProcess(order: Order) {

    private var pendingItems: MutableList<Item> = mutableListOf<Item>()
    private var completedItems: MutableList<Item> = mutableListOf<Item>()
    private var failedItems: MutableList<Item> = mutableListOf<Item>()
    private var currentItem: Item

    private var isProcessFinished: Boolean = false

    init{
        pendingItems.addAll(order.items)
        currentItem = pendingItems.removeFirst()
    }

    fun getCurrentlyProcessedItem() : Item{
        return currentItem
    }

    fun getPickingSummary(): PickProcessSummary {
        return PickProcessSummary(completedItems, failedItems)
    }

    fun getIsProcessFinished() : Boolean{
        return isProcessFinished
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

    private fun endProcess(){
        isProcessFinished = true
    }
}