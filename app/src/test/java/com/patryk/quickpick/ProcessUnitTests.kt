package com.patryk.quickpick

import com.patryk.quickpick.data.DemoDataContent
import com.patryk.quickpick.logic.PickProcess
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalStdlibApi
class ProcessUnitTests {

    @Test
    fun itemsPickedProcessFinished(){
        val sut = prepareSut(2)

        sut.pickItem()
        sut.pickItem()

        assertEquals(true, sut.getIsProcessFinished())
    }

    @Test
    fun itemsNotPickedProcessNotFinished(){
        val sut = prepareSut(2)

        sut.pickItem()

        assertEquals(false, sut.getIsProcessFinished())
    }

    @Test
    fun processFinishedSummaryIsValid(){
        val sut = prepareSut(2)

        sut.pickItem()
        sut.pickItem()

        assertEquals(2, sut.getPickingSummary().completedFiszkas.size)
        assertEquals(0, sut.getPickingSummary().failedFiszkas.size)
    }

    @Test
    fun processFinishedSummaryIsValid2(){
        val sut = prepareSut(4)

        sut.pickItem()
        sut.pickItem()
        sut.pickItem()
        sut.failItem()

        assertEquals(3, sut.getPickingSummary().completedFiszkas.size)
        assertEquals(1, sut.getPickingSummary().failedFiszkas.size)
    }

    @Test
    fun processNotFinishedSummaryIsValid(){
        val sut = prepareSut(2)

        assertEquals(0, sut.getPickingSummary().completedFiszkas.size)
        assertEquals(0, sut.getPickingSummary().failedFiszkas.size)
    }

    @Test
    fun processStartedCurrenlyProcessedItemIsValid(){
        val sut = prepareSut(2)

        val item = sut.getCurrentlyProcessedItem()

        assertEquals("brcd:1", item.barcode)
    }

    @Test
    fun processInProgressItemIsValid(){
        val sut = prepareSut(2)

        sut.pickItem()

        val item = sut.getCurrentlyProcessedItem()

        assertEquals("brcd:2", item.barcode)
    }

    private fun prepareSut(n: Int): PickProcess {
        val order = DemoDataContent.createOrder(n)
        return PickProcess(order)
    }
}