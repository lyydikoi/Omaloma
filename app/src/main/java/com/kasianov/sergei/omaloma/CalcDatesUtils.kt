package com.kasianov.sergei.omaloma

import com.kasianov.sergei.omaloma.data.HolidayMonth
import org.threeten.bp.LocalDate
import kotlin.collections.ArrayList

object CalcDatesUtils {

    /*fun getHolidayYears(workingStartDate: LocalDate) : ArrayList<HolidayYear> {
        val firstHolidayYear = getHolidayYear(workingStartDate, true)
        val currentDate = LocalDate.now()
        val result = ArrayList<HolidayYear>()
        result.add(firstHolidayYear)
        var nextYear = LocalDate(firstHolidayYear.startDate.withYear(firstHolidayYear.year + 1))
        while (nextYear.isBefore(currentDate)) {
            result.add(getHolidayYear(nextYear))
            nextYear = nextYear.withYear(nextYear.year + 1)
        }
        return result
    }

    fun getHolidayYear(date: LocalDate, isTrial: Boolean = false): HolidayYear {
        // Holiday year starts on the 1st of April, and ends on the 31st of March next year
        val holidayYearStart = getHolidayYearStart(date)
        val holidayYearEnd = getHolidayYearEnd(holidayYearStart)
        return  HolidayYear(
                    holidayYearStart.year,
                    holidayYearStart,
                    holidayYearEnd,
                    isTrial = isTrial,
                    isClosed = isHolidayYearClosed(holidayYearEnd),
                    holidayMonths = getHolidayMonths(date)
                )
    }

    fun getHolidayYearStart(date: LocalDate): LocalDate {
        if (date.isAfter(LocalDate(date.year, 3, 31))) {
            return LocalDate(date.year, 4, 1)
        } else {
            return LocalDate(date.year - 1, 4, 1)
        }
    }

    fun getHolidayYearEnd(date: LocalDate): LocalDate {
        return LocalDate(date.year + 1, 3, 31)
    }*/

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
            // TODO: should I check here is Month full or not???
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