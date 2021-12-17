package com.patryk.quickpick.data

import java.util.ArrayList

object DemoDataContent {

    var ListaFiszekFull: MutableList<ListaFiszek> = ArrayList()
    var ListaFiszek: MutableList<ListaFiszek> = ArrayList()
    var PAST_ORDERS: MutableList<PastOrder> = ArrayList()
    var Fiszkas: MutableList<Fiszka> = ArrayList()

    var LastCollectionNames: MutableList<String> = ArrayList()

    private const val COUNT = 15

    init {

        addKolekcja("Pojazdy", mutableListOf(
            Fiszka("car", "samochód", LearnStatus.NOT_LEARNED),
            Fiszka("plane", "samolot", LearnStatus.NOT_LEARNED),
            Fiszka("train", "pociąg", LearnStatus.NOT_LEARNED),
            Fiszka("bike", "rower", LearnStatus.NOT_LEARNED),
            Fiszka("helicopter", "helikopter", LearnStatus.NOT_LEARNED),
            Fiszka("boat", "łódź", LearnStatus.NOT_LEARNED)
        ))

        addKolekcja("Zwierzęta", mutableListOf(
            Fiszka("dog", "pies", LearnStatus.NOT_LEARNED),
            Fiszka("owl", "sowa", LearnStatus.NOT_LEARNED),
            Fiszka("lion", "lew", LearnStatus.NOT_LEARNED),
            Fiszka("tiger", "tygrys", LearnStatus.NOT_LEARNED),
            Fiszka("cow", "krowa", LearnStatus.NOT_LEARNED),
            Fiszka("cat", "kangur", LearnStatus.NOT_LEARNED)
        ))

        addKolekcja("Zawody", mutableListOf(
            Fiszka("carpenter", "stolarz", LearnStatus.NOT_LEARNED),
            Fiszka("actor", "aktor", LearnStatus.NOT_LEARNED),
            Fiszka("accountant", "księgowy", LearnStatus.NOT_LEARNED)
        ))

        addKolekcja("Dom", mutableListOf(
            Fiszka("room", "pokój", LearnStatus.NOT_LEARNED),
            Fiszka("bathroom", "łazienka", LearnStatus.NOT_LEARNED)
        ))

        addKolekcja("Zdrowie", mutableListOf(
            Fiszka("doctor", "lekarz", LearnStatus.NOT_LEARNED),
            Fiszka("vaccine", "szczepionka", LearnStatus.NOT_LEARNED)
        ))

        addKolekcja("Sztuka", mutableListOf(
            Fiszka("painting", "obraz", LearnStatus.NOT_LEARNED),
            Fiszka("sculpture", "rzeźba", LearnStatus.NOT_LEARNED)
        ))

        addKolekcja("Kino", mutableListOf(
            Fiszka("movie", "film", LearnStatus.NOT_LEARNED),
            Fiszka("rating", "ocena", LearnStatus.NOT_LEARNED)
        ))
        addKolekcja("Studia", mutableListOf(
            Fiszka("exam", "egzamin", LearnStatus.NOT_LEARNED)
        ))

        for (i in 10..COUNT) {
            addOrder(createOrder(i))
        }

        addKolekcja("Media", mutableListOf(
            Fiszka("newspaper", "gazeta", LearnStatus.NOT_LEARNED),
            Fiszka("radio", "radio", LearnStatus.NOT_LEARNED)
        ))

        addKolekcja("Ciało", mutableListOf(
            Fiszka("shoulder blade", "łopatka", LearnStatus.NOT_LEARNED),
            Fiszka("hip", "biodro", LearnStatus.NOT_LEARNED)
        ))

        addKolekcja("Kuchnia", mutableListOf(
            Fiszka("oven", "piekarnik", LearnStatus.NOT_LEARNED)
        ))

        addKolekcja("łazienka", mutableListOf(
            Fiszka("shower", "prysznic", LearnStatus.NOT_LEARNED)
        ))

        addKolekcja("Owoce", mutableListOf(
            Fiszka("banana", "banan", LearnStatus.NOT_LEARNED)
        ))

        addKolekcja("Warzywa", mutableListOf(
            Fiszka("cucumber", "ogórek", LearnStatus.NOT_LEARNED)
        ))


    }

    private fun addKolekcja(name: String, fiszki: MutableList<Fiszka>){
        val zwierzLista = ListaFiszek(name, fiszki)
        ListaFiszek.add(zwierzLista)
        ListaFiszekFull.add(zwierzLista)
        LastCollectionNames.add(zwierzLista.name)
    }

    private fun addOrder(listaFiszek: ListaFiszek) {
        ListaFiszek.add(listaFiszek)
        ListaFiszekFull.add(listaFiszek)
        LastCollectionNames.add(listaFiszek.name)
    }

    fun createOrder(position: Int): ListaFiszek {
        return ListaFiszek("Lista$position", makeFiszki(position))
    }

    private fun makeFiszki(position: Int): List<Fiszka> {
        val fiszki = mutableListOf<Fiszka>()

        for(i in 1..position){
            val fiszka = Fiszka("word:$i", "translation:$i", LearnStatus.NOT_LEARNED)
            fiszki.add(fiszka)
            Fiszkas.add(fiszka)
        }

        return fiszki
    }
}