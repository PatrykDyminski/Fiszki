package com.patryk.quickpick.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Dimensions(val x: Float, val y: Float, val z: Float) : Parcelable

@Parcelize
data class Item(val name: String, val category: String, val barcode: String, val mass: Double, val dimensions: Dimensions) : Parcelable

@Parcelize
data class CompletedItem(val item: Item, val isSuccess: Boolean) : Parcelable

@Parcelize
data class Order(val id: String, val placedDate: Date, val items: List<Item>) : Parcelable

@Parcelize
data class PastOrder(val order: Order, val isSuccess: Boolean) : Parcelable

@Parcelize
data class PickProcessSummary(val completedItems: List<Item>, val failedItems: List<Item>, val order: Order) : Parcelable