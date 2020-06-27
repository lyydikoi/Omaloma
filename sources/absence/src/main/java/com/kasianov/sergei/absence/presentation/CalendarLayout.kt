package com.kasianov.sergei.absence.presentation

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import com.google.android.material.snackbar.Snackbar
import com.kasianov.sergei.absence.R
import com.kasianov.sergei.core_api.extentions.getDrawableCompat
import com.kasianov.sergei.core_api.extentions.makeInVisible
import com.kasianov.sergei.core_api.extentions.setCornerRadiusExt
import com.kasianov.sergei.core_api.extentions.setTextColorRes
import com.kasianov.sergei.core_api.extentions.makeVisible
import com.kasianov.sergei.core_api.utils.CalcDateUtils
import com.kizitonwose.calendarview.CalendarView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.layout_calendar.view.calendarAbsence
import kotlinx.android.synthetic.main.layout_calendar.view.btnSaveDates
import kotlinx.android.synthetic.main.layout_calendar_day.view.tvCalendarDayText
import kotlinx.android.synthetic.main.layout_calendar_day.view.viewCalendarDayRoundBg
import kotlinx.android.synthetic.main.tv_calendar_header.view.tvCalendarHeaderText
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.TextStyle
import java.util.Locale


private const val DAYS_IN_ROW_COUNT = 7

class CalendarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val normalDateFormatter = DateTimeFormatter.ofPattern("EEE dd-MMM-yyyy", Locale.getDefault())

    private val todayDate = LocalDate.now()
    private var startDate: LocalDate? = null
    private var endDate: LocalDate? = null
    private var calendarView: CalendarView
    private lateinit var datesSelectedInteraction: (String, String?) -> Unit

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_calendar, this)
        calendarView = this.findViewById(R.id.calendarAbsence)
    }

    fun setDates(startDateString: String, endDateString: String?) {
        startDate = LocalDate.parse(startDateString, normalDateFormatter)
        if (!endDateString.isNullOrBlank() && endDateString != startDateString) {
            endDate = LocalDate.parse(endDateString, normalDateFormatter)
        }
        datesSelectedInteraction(
            startDateString,
            endDateString
        )
        calendarView.notifyCalendarChanged()
    }

    fun setUpCalendar(
        calcDateUtils: CalcDateUtils,
        saveDatesInteraction: (String, String?) -> Unit,
        datesSelectedInteraction: (String, String?) -> Unit
    ) {
        this.datesSelectedInteraction = datesSelectedInteraction

        val calendarSelectedBgStart: GradientDrawable by lazy {
            this.context.getDrawableCompat(R.drawable.shape_calendar_selected_bg_start) as GradientDrawable
        }
        val calendarSelectedBgEnd: GradientDrawable by lazy {
            this.context.getDrawableCompat(R.drawable.shape_calendar_selected_bg_end) as GradientDrawable
        }

        calendarView.post {
            val radius = ((calendarAbsence.width / DAYS_IN_ROW_COUNT) / 2).toFloat()
            calendarSelectedBgStart.setCornerRadiusExt(topLeft = radius, bottomLeft = radius)
            calendarSelectedBgEnd.setCornerRadiusExt(topRight = radius, bottomRight = radius)
        }

        // Set the First day of week depending on Locale
        val daysOfWeek = calcDateUtils.daysOfWeekFromLocale()
        val legendLayout = this.findViewById<LinearLayout>(R.id.layout_calendar_legend)
        legendLayout?.children?.forEachIndexed { index, view ->
            (view as TextView).apply {
                text = daysOfWeek[index].getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
            }
        }

        val currentMonth = YearMonth.now()
        calendarView.setup(currentMonth, currentMonth.plusMonths(12), daysOfWeek.first())
        calendarView.scrollToMonth(currentMonth)

        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val textView = view.tvCalendarDayText
            val roundBgView = view.viewCalendarDayRoundBg

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH &&
                        (day.date == todayDate || day.date.isAfter(todayDate))
                    ) {
                        val date = day.date
                        if (startDate != null) {
                            if (date < startDate || endDate != null) {
                                startDate = date
                                endDate = null
                            } else if (date != startDate) {
                                endDate = date
                            }
                        } else {
                            startDate = date
                        }
                        calendarView.notifyCalendarChanged()

                        startDate?.let { sd ->
                            datesSelectedInteraction(
                                normalDateFormatter.format(sd),
                                endDate?.let { ed -> normalDateFormatter.format(ed) }
                            )
                        }
                    }
                }
            }
        }

        calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.textView
                val roundBgView = container.roundBgView

                textView.text = null
                textView.background = null
                roundBgView.makeInVisible()

                if (day.owner == DayOwner.THIS_MONTH) {
                    textView.text = day.day.toString()

                    if (day.date.isBefore(todayDate)) {
                        textView.setTextColorRes(R.color.calendarDayPastColor)
                    } else {
                        when {
                            startDate == day.date && endDate == null -> {
                                textView.setTextColorRes(R.color.calendarDayColor)
                                roundBgView.makeVisible()
                                roundBgView.setBackgroundResource(R.drawable.shape_calendar_selected_bg_single)
                            }
                            day.date == startDate -> {
                                textView.setTextColorRes(R.color.calendarDayColor)
                                textView.background = calendarSelectedBgStart
                            }
                            startDate != null && endDate != null
                                && (day.date > startDate && day.date < endDate) -> {
                                textView.setTextColorRes(R.color.calendarDayColor)
                                textView.setBackgroundResource(R.drawable.shape_calendar_selected_bg_continuous_midle)
                            }
                            day.date == endDate -> {
                                textView.setTextColorRes(R.color.calendarDayColor)
                                textView.background = calendarSelectedBgEnd
                            }
                            day.date == todayDate -> {
                                textView.setTextColorRes(R.color.calendarDayTodayColor)
                                roundBgView.makeVisible()
                                roundBgView.setBackgroundResource(R.drawable.shape_calendar_bg_today)
                            }
                            else -> textView.setTextColorRes(R.color.calendarDayDarkGreyColor)
                        }
                    }
                } else {
                    // This part is to make the coloured selection background continuous
                    // on the blank in and out dates across various months and also on dates(months)
                    // between the start and end dates if the selection spans across multiple months.

                    val startDate = startDate
                    val endDate = endDate
                    if (startDate != null && endDate != null) {
                        // Mimic selection of inDates that are less than the startDate.
                        // Example: When 26 Feb 2019 is startDate and 5 Mar 2019 is endDate,
                        // this makes the inDates in Mar 2019 for 24 & 25 Feb 2019 look selected.
                        if ((
                            day.owner == DayOwner.PREVIOUS_MONTH &&
                                startDate.monthValue == day.date.monthValue &&
                                endDate.monthValue != day.date.monthValue
                            ) ||
                            // Mimic selection of outDates that are greater than the endDate.
                            // Example: When 25 Apr 2019 is startDate and 2 May 2019 is endDate,
                            // this makes the outDates in Apr 2019 for 3 & 4 May 2019 look selected.
                            (
                                day.owner == DayOwner.NEXT_MONTH &&
                                    startDate.monthValue != day.date.monthValue &&
                                    endDate.monthValue == day.date.monthValue
                                ) ||

                            // Mimic selection of in and out dates of intermediate
                            // months if the selection spans across multiple months.
                            (
                                startDate < day.date && endDate > day.date &&
                                    startDate.monthValue != day.date.monthValue &&
                                    endDate.monthValue != day.date.monthValue
                                )
                        ) {
                            textView.setBackgroundResource(R.drawable.shape_calendar_selected_bg_continuous_midle)
                        }
                    }
                }
            }
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val textView = view.tvCalendarHeaderText
        }

        calendarView.monthHeaderBinder = object :
            MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                val monthTitle = "${month.yearMonth.month.name.toLowerCase().capitalize()} ${month.year}"
                container.textView.text = monthTitle
            }
        }

        btnSaveDates.setOnClickListener click@{
            startDate?.let { sd ->
                saveDatesInteraction(
                    normalDateFormatter.format(sd),
                    endDate?.let { ed -> normalDateFormatter.format(ed) }
                )

                val infoText = endDate?.let {
                    "Selected: ${normalDateFormatter.format(startDate)} - ${normalDateFormatter.format(it)}"
                } ?: "Selected: ${normalDateFormatter.format(startDate)}"

                Snackbar.make(this, infoText, Snackbar.LENGTH_LONG).show()
            } ?: Snackbar.make(this, resources.getString(R.string.no_date_selected), Snackbar.LENGTH_LONG).show()
        }

        startDate?.let { sd ->
            datesSelectedInteraction(
                normalDateFormatter.format(sd),
                endDate?.let { ed -> normalDateFormatter.format(ed) }
            )
        }
    }
}
