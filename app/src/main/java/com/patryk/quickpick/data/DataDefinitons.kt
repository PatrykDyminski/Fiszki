package com.patryk.quickpick.data

import java.util.*

data class Dimensions(val x: Float, val y: Float, val z: Float)

data class Item(val name: String, val category: String, val barcode: String, val mass: Float, val dimensions: Dimensions)

data class Order(val id: String, val placedDate: Date, val items: List<Item>)