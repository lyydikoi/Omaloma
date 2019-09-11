package com.kasianov.sergei.omaloma

import com.kasianov.sergei.omaloma.data.HolidayYear
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertThat
import org.junit.Test
import org.threeten.bp.LocalDate
import com.kasianov.sergei.omaloma.data.HolidayMonth as HolidayMonth

/**
 * These tests are run locally on development machine's JVM and do not require running on either
 * an emulator or physical device.
 * Unit tests are almost always local tests.
 * Integration tests can be run locally or as instrumented tests.
 */
class CalcDatesUtilsTest {
    /**
     * Unit tests for [CalcDatesUtils.getHolidayMonthCount]
     */
    @Test
    fun getHolidayMonthCountStartYear_isCorrect() {
        val startMonth = arrayListOf<Int>(1, 2, 3)
        startMonth.forEach {
            val result = CalcDatesUtils.getHolidayMonthCount(it)
            assertThat(result, `is` (4 - it))
        }
    }

    @Test
    fun getHolidayMonthCountEndYear_isCorrect() {
        val startMonth = arrayListOf<Int>(4, 5, 6, 7, 8, 9, 10, 11, 12)
        var result: Int
        startMonth.forEach {
            result = CalcDatesUtils.getHolidayMonthCount(it)
            assertThat(result, `is` (16 - it))
        }
    }

    /**
     * Unit tests for [CalcDatesUtils.getHolidayMonths]
     */
    @Test
    fun getHolidayMonthsStartYear_isCorrect() {
        // Given:
        // Input test dates.
        val a = LocalDate.parse("2017-12-05")
        val b = LocalDate.parse("2017-03-03")
        val c = LocalDate.parse("2017-06-05")
        // Right values for test dates.
        val holMonthsCountA = 4
        val holMonthsCountB = 1
        val holMonthsCountC = 10

        val startDates = arrayListOf<LocalDate>(a, b, c)

        // When: get holiday months for all dates.
        for (i in 1..startDates.size) {
            var testDate = startDates[i-1]
            var holidayMonths = CalcDatesUtils.getHolidayMonths(testDate)

            // Then: assert, that result has expected number of holiday months.
            assertThat(holidayMonths, `is`(notNullValue()))
            val holMonthsCount = if (i == 1) holMonthsCountA else (if (i == 2) holMonthsCountB else holMonthsCountC)
            assertThat(holidayMonths.size, `is`(holMonthsCount))
            holidayMonths.forEach {
                assertThat(it, instanceOf(HolidayMonth::class.java))
            }
        }
    }

    /**
     * Unit texts for [CalcDatesUtils.isHolidayYearClosed]
     */
    /*@Test
    fun isHolidyYearClosed_correct() {
        // Input test dates.
        val a = LocalDate.parse("2017-12-05")
        val b = LocalDate.parse("2099-12-05")
        // Right values for test dates.
        val isClosedA = true
        val isClosedB = false

        val testDates = arrayListOf<LocalDate>(a, b)

        for (i in 1..testDates.size) {
            var testDate = testDates[i-1]
            var isClosed = CalcDatesUtils.isHolidayYearClosed(testDate)
            var rightResult = if (i == 1) isClosedA else isClosedB
            assertThat(isClosed, `is`(rightResult))
        }
    }*/

    /**
     * Unit tests for [CalcDatesUtils.getHolidayYearEndByStart]
     */
    @Test
    fun getHolidayYearEnd_correct() {
        // Input test dates.
        val a = LocalDate.parse("2019-04-01")
        val b = LocalDate.parse("2020-04-01")
        // Right values for test dates.
        val endDateA = LocalDate.of(2020, 3, 31)
        val endDateB = LocalDate.of(2021, 3, 31)

        // When..
        val resultA = CalcDatesUtils.getHolidayYearEndByStart(a)
        val resultB = CalcDatesUtils.getHolidayYearEndByStart(b)

        // Then...
        assertThat(resultA, `is`(endDateA))
        assertThat(resultB, `is`(endDateB))
    }

    /**
     * Unit tests for [CalcDatesUtils.getHolidayYearStart]
     */
    @Test
    fun getHolidayYearStart_correct() {
        // Input test dates.
        val a = LocalDate.parse("2019-01-05")
        val b = LocalDate.parse("2020-05-01")
        // Right values for test dates.
        val startDateA = LocalDate.of(2018, 4, 1)
        val startDateB = LocalDate.of(2020, 4, 1)

        // When..
        val resultA = CalcDatesUtils.getHolidayYearStart(a)
        val resultB = CalcDatesUtils.getHolidayYearStart(b)

        // Then...
        assertThat(resultA, `is`(startDateA))
        assertThat(resultB, `is`(startDateB))
    }

    /**
     * Unit tests for [CalcDatesUtils.getHolidayYear]
     */
    /*@Test
    fun getHolidayYear_correct() {
        // Input test date.
        val date = LocalDate.parse("2019-01-05")

        // When..
        val result = CalcDatesUtils.getHolidayYear(date)

        // Then...
        assertThat(result, instanceOf(HolidayYear::class.java))
    }*/



}