package com.patryk.quickpick.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Dimensions(val x: Float, val y: Float, val z: Float) : Parcelable

@Parcelize
data class Item(val name: String, val category: String, val barcode: String, val mass: Float, val dimensions: Dimensions) : Parcelable

@Parcelize
data class Order(val id: String, val placedDate: Date, val items: List<Item>) : Parcelable

@Parcelize
data class PickProcessSummary(val completedItems: List<Item>, val failedItems: List<Item>) : Parcelable