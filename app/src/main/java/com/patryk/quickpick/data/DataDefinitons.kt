package com.patryk.quickpick.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Fiszka(val word: String, val translation: String, val status: LearnStatus) : Parcelable

@Parcelize
data class CompletedItem(val fiszka: Fiszka, val isSuccess: Boolean) : Parcelable

@Parcelize
data class ListaFiszek(val name: String, val fiszkas: List<Fiszka>) : Parcelable

@Parcelize
data class PastOrder(val listaFiszek: ListaFiszek, val status: OrderStatus) : Parcelable

@Parcelize
data class PickProcessSummary(val completedFiszkas: List<Fiszka>, val failedFiszkas: List<Fiszka>, val listaFiszek: ListaFiszek) : Parcelable

enum class OrderStatus {
    SUCCESS, MIXED, FAIL
}

enum class LearnStatus {
    LEARNED, MIXED, NOT_LEARNED
}