package com.kasianov.sergei.omaloma.presentation.utils

import com.kasianov.sergei.omaloma.data.HolidayMonth
import com.kasianov.sergei.omaloma.data.HolidayYear
import org.threeten.bp.LocalDate
import kotlin.collections.ArrayList

object CalcDatesUtils {

    /**
     * Calculates all holiday years from given working start date. Assumes, that user is
     * trial period in the first year.
     *
     * return ArrayList<HolidayYear>
     */
    fun getHolidayYears(workingStartDate: LocalDate) : ArrayList<HolidayYear> {
        val firstHolidayYear =
            getHolidayYear(workingStartDate, true)
        val currentDate = LocalDate.now()
        val result = ArrayList<HolidayYear>()
        result.add(firstHolidayYear)
        var nextYear = firstHolidayYear.startDate.plusYears(1)
        while (nextYear.isBefore(currentDate)) {
            result.add(getHolidayYear(nextYear))
            nextYear = nextYear.plusYears(1)
        }
        return result
    }

    /**
     * Returns HolidayYear instance by provided date. It contains start ans end values.
     * Holiday year does not match calendar year. It starts on the 1st of April,
     * and ends on the 31st of March next year.
     * If user is in trial period, this should be defined, because it affects amount
     * of earned holidays per month.
     *
     * return HolidayYear
     */
    fun getHolidayYear(date: LocalDate, isTrial: Boolean = false): HolidayYear {
        val holidayYearStart =
            getHolidayYearStart(date)
        val holidayYearEnd =
            getHolidayYearEndByStart(
                holidayYearStart
            )
        return HolidayYear(
            holidayYearStart.year,
            holidayYearStart,
            holidayYearEnd,
            isTrial = isTrial,
            isClosed = isHolidayYearClosed(
                holidayYearEnd
            ),
            holidayMonths = getHolidayMonths(
                date
            )
        )
    }

    /**
     * Calculates start of the Holiday Year from given date.
     *
     * return LocalDate
     */
    fun getHolidayYearStart(date: LocalDate): LocalDate {
        return if (date.isAfter(LocalDate.of(date.year, 3, 31))) {
            LocalDate.of(date.year, 4, 1)
        } else {
            LocalDate.of(date.year - 1, 4, 1)
        }
    }

    /**
     * Calculates the end of Holiday Year from its start date.
     *
     * return LocalDate
     */
    fun getHolidayYearEndByStart(holidayYearStart: LocalDate): LocalDate {
        return LocalDate.of(holidayYearStart.year.plus(1), 3, 31)
    }

    /**
     * Holiday Year is closed, when current date is after Holyday Year end.
     *
     * return Boolean
     */
    fun isHolidayYearClosed(holidayYearEnd: LocalDate): Boolean {
        return LocalDate.now().isAfter(holidayYearEnd)
    }

    /**
     * Calculates amount of holiday month starting from given date.
     * Holiday year (1.4 -- 31.3) does not match calendar year. If user starts job in January,
     * his first holiday year is 3 moths (till 31.3). Next holiday year starts on the 1st of April.
     *
     * return ArrayList<HolidayMonth>
     */
    fun getHolidayMonths(date: LocalDate, isTrial: Boolean = false): ArrayList<HolidayMonth> {
        val result = ArrayList<HolidayMonth>()
        val startMonth = date.month.value
        for(i in 1..getHolidayMonthCount(startMonth)) {
            result.add(HolidayMonth(i))
        }
        return result
    }

    /**
     * Calculates amount of Holiday Months starting from given date till the end of Holiday Year.
     *
     * return Int
     */
    fun getHolidayMonthCount(startMonth: Int) = if (startMonth <= 3) 4 - startMonth else 16 - startMonth

}