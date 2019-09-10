package com.kasianov.sergei.omaloma

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
    /*fun isHolidyYearClosed_correct() {
        // Input test dates.
        val a = LocalDate.parse("2017-12-05")
        val b = LocalDate.now()
        val c = LocalDate.parse("2999-12-05")
        // Right values for test dates.
        val isClosedA = false
        val isClosedB = false
        val isClosedC = true
    }*/
}