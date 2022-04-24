package com.sevenshift.stringcalculator

import org.junit.Test

import org.junit.Assert.*
import org.junit.function.ThrowingRunnable

class StringCalculatorUnitTest {
    private val calculator = StringCalculator()

    @Test
    fun `comma delimeted numbers should return sum`() {
        val addition = calculator.add("1,2,4")
        val expectedResult = 7

        assertEquals(expectedResult, addition)
    }

    @Test
    fun `comma delimeted numbers with line breaks should return sum`() {
        val addition = calculator.add("1,\n5,4")
        val expectedResult = 10

        assertEquals(expectedResult, addition)
    }

    @Test
    fun `custom delimeted numbers should return sum`() {
        val addition = calculator.add("//$\n1$2$5")
        val expectedResult = 8

        assertEquals(expectedResult, addition)
    }

    @Test
    fun `numbers larger than 1000 should be ignored`() {
        val addition = calculator.add("//@\n1@2@4@1001")
        val expectedResult = 7

        assertEquals(expectedResult, addition)
    }

    @Test
    fun `delimeters can be arbitrary length`() {
        val addition = calculator.add("//***\n1***2***6")
        val expectedResult = 9

        assertEquals(expectedResult, addition)
    }

    @Test
    fun `allow for multiple delimeters`() {
        val addition = calculator.add("//$,@\n3@1$4")
        val expectedResult = 8

        assertEquals(expectedResult, addition)
    }

    @Test
    fun `allow for multiple delimeters with arbitrary length`() {
        val addition = calculator.add("//$$,@@@\n3@@@1$$4")
        val expectedResult = 8

        assertEquals(expectedResult, addition)
    }

    @Test
    fun `negative numbers should throw exception`() {
        val addition = ThrowingRunnable { calculator.add("//$,@\n3@-1$4") }

        assertThrows(IllegalArgumentException::class.java, addition)
    }
}