package com.patryk.quickpick

import com.patryk.quickpick.data.Parser
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.lang.IndexOutOfBoundsException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalStdlibApi
class ParserUnitTest {

    @Test
    fun ordersAreParsed(){

        val classLoader = this.javaClass.classLoader
        val file = File(classLoader.getResource("items.csv").file)

        val orders = Parser.parseFile(file)

        assertEquals(4, orders.size)
        assertEquals(3, orders[0].fiszkas.size)
        assertEquals(3, orders[1].fiszkas.size)
        assertEquals(5, orders[2].fiszkas.size)
        assertEquals(4, orders[3].fiszkas.size)
    }

    @Test
    fun itemsAreParsed(){

        val classLoader = this.javaClass.classLoader
        val file = File(classLoader.getResource("items.csv").file)

        val orders = Parser.parseFile(file)

        assertEquals(4, orders.size)
        assertEquals("bar1", orders[0].fiszkas[0].barcode)
        assertEquals("bar2", orders[0].fiszkas[1].barcode)
    }

    @Test
    fun delimiterCanBeLowercase(){
        val classLoader = this.javaClass.classLoader
        val file = File(classLoader.getResource("items.csv").file)
        val orders = Parser.parseFile(file)
        val file2 = File(classLoader.getResource("items4.csv").file)
        val orders2 = Parser.parseFile(file)

        assertEquals(4, orders.size)
        assertEquals(false, orders2.isEmpty())
    }

    @Test
    fun createItemItemIsCreated(){
        val item = Parser.createItem("item5;cat5;bar5;20;10;10;20")

        assertEquals("item5", item.word)
        assertEquals("bar5", item.barcode)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun createItemItemIsNotCreated() {
        Parser.createItem("item5cat5;bar5;20;10;10;20")
    }

    @Test(expected = KotlinNullPointerException::class)
    fun parseFileFileIsNotParsedErrorInData(){
        val classLoader = this.javaClass.classLoader
        val file = File(classLoader.getResource("items2.csv").file)
        Parser.parseFile(file)
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun parseFileFileIsNotParsedEmptyFile(){
        val classLoader = this.javaClass.classLoader
        val file = File(classLoader.getResource("items3.csv").file)
        Parser.parseFile(file)
    }
}