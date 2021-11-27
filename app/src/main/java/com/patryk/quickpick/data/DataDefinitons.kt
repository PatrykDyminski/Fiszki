package com.patryk.quickpick.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Fiszka(val word: String, val translation: String, val status: LearnStatus) : Parcelable

@Parcelize
data class CompletedItem(val fiszka: Fiszka, val isSuccess: Boolean) : Parcelable

@Parcelize
data class Order(val id: String, val placedDate: Date, val fiszkas: List<Fiszka>) : Parcelable

@Parcelize
data class PastOrder(val order: Order, val status: OrderStatus) : Parcelable

@Parcelize
data class PickProcessSummary(val completedFiszkas: List<Fiszka>, val failedFiszkas: List<Fiszka>, val order: Order) : Parcelable

enum class OrderStatus {
    SUCCESS, MIXED, FAIL
}

enum class LearnStatus {
    LEARNED, MIXED, NOT_LEARNED
}