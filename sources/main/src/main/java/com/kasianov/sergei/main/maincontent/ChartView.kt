package com.kasianov.sergei.main.maincontent

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.withStyledAttributes
import com.kasianov.sergei.main.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


private enum class ChartLabels(val label: Int) {
    EARNED(R.string.label_earned_days),
    SPENT(R.string.label_spent_days),
    REMAINING(R.string.label_remaining_days);

    fun next() = when (this) {
        EARNED -> SPENT
        SPENT -> REMAINING
        REMAINING -> EARNED
    }
}

private const val RADIUS_OFFSET_INDICATOR = -35
private const val CHART_STROKE_WIDTH = 50f
private const val CHART_TEXT_SIZE = 80.0f
private const val CHART_LABEL_SIZE = 55.0f
private const val CHART_START_ANGLE = -90.0f
private const val TAG = "ChartViewTag"

class ChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private lateinit var chartSelectedInteraction: (String) -> Unit

    private var chartFillRadius = 0.0f
    private val labelPointPosition = PointF(0.0f, 0.0f)
    private var chartArcPlaceholder = RectF(CHART_STROKE_WIDTH , CHART_STROKE_WIDTH , 0.0f, 0.0f)
    private var anglePerValue = 360.0f / 100

    private var currentChart = ChartLabels.EARNED
    private var currentChartValue = 0.0f

    private var earnedDaysColor = 0
    private var spentDaysColor = 0
    private var remainingDaysColor = 0

    private var earnedDaysCount = 0.0f
    private var spentDaysCount = 0.0f
    private var remainingDaysCount = 0.0f

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = CHART_LABEL_SIZE
        typeface = Typeface.create("", Typeface.BOLD)
        color = Color.LTGRAY
    }

    private val arcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = CHART_STROKE_WIDTH
        color = Color.BLUE
        isAntiAlias = true
        style = Paint.Style.STROKE
    }

    init {
        isClickable = true

        context.withStyledAttributes(attrs, R.styleable.ChartView) {
            earnedDaysColor = getColor(R.styleable.ChartView_chartColor1, 0)
            spentDaysColor = getColor(R.styleable.ChartView_chartColor2, 0)
            remainingDaysColor = getColor(R.styleable.ChartView_chartColor3, 0)
        }
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true

        currentChart = currentChart.next()
        val nextChartTitle = resources.getString(currentChart.label)
        contentDescription = nextChartTitle
        chartSelectedInteraction(nextChartTitle)

        invalidate()
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        chartFillRadius = (min(width, height) / 2.0 * 0.8).toFloat()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        when (widthMode) {
            MeasureSpec.UNSPECIFIED -> Log.d(TAG, "onMeasure UNSPECIFIED")
            MeasureSpec.AT_MOST -> {
                Log.d(TAG, "onMeasure AT_MOST")
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            }
            MeasureSpec.EXACTLY -> {
                Log.d(TAG, "onMeasure EXACTLY")
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        currentChartValue = when (currentChart) {
            ChartLabels.EARNED -> earnedDaysCount
            ChartLabels.SPENT -> spentDaysCount
            ChartLabels.REMAINING -> remainingDaysCount
        }

        // Draw closing chart
        arcPaint.color = Color.LTGRAY
        canvas.drawArc(
            chartArcPlaceholder.computeDimensForArc(height),
            currentChartValue * anglePerValue - 90,
            360.0f - currentChartValue * anglePerValue,
            false,
            arcPaint
        )

        arcPaint.color = when (currentChart) {
            ChartLabels.EARNED -> earnedDaysColor
            ChartLabels.SPENT -> spentDaysColor
            ChartLabels.REMAINING -> remainingDaysColor
        } as Int

        // Draw value chart
        canvas.drawArc(
            chartArcPlaceholder.computeDimensForArc(height),
            CHART_START_ANGLE,
            currentChartValue * anglePerValue,
            false,
            arcPaint
        )

        // Draw the value text
        textPaint.textSize = CHART_TEXT_SIZE
        textPaint.color = Color.GRAY
        val valueText = "$currentChartValue%"
        val xPos = (width / 2).toFloat()
        val yPos = (height / 2 - (textPaint.descent() + textPaint.ascent()) / 2)
        canvas.drawText(valueText, xPos, yPos, textPaint)
        textPaint.textSize = CHART_LABEL_SIZE

        // Draw the indicator circle.
        val markerRadius = chartFillRadius + RADIUS_OFFSET_INDICATOR
        for (i in ChartLabels.values()) {
            labelPointPosition.computeXYForMarkers(i, markerRadius)
            textPaint.color = if (i == currentChart) arcPaint.color else Color.LTGRAY
            canvas.drawCircle(labelPointPosition.x, labelPointPosition.y, chartFillRadius/12, textPaint)
        }
    }

    private fun PointF.computeXYForMarkers(pos: ChartLabels, radius: Float) {
        // Angles are in radians.
        val startAngle = Math.PI * (9 / 8.0)
        val angle = startAngle + pos.ordinal * (Math.PI / 6)
        x = (radius * cos(angle)).toFloat() + width / 2
        y = (radius * sin(angle)).toFloat() + height / 2
    }

    private fun RectF.computeDimensForArc(canvasHeight: Int) : RectF {
        left = canvasHeight.toFloat() / 4
        top = canvasHeight.toFloat() / 4
        right = canvasHeight.toFloat() * 3 / 4
        bottom = canvasHeight.toFloat() * 3 / 4
        return this
    }

    fun setValues(
        earnedPercent: Float = 0f,
        spentPercent: Float = 0f,
        remainingPercent: Float = 0f
    ) {
        earnedDaysCount = earnedPercent
        spentDaysCount = spentPercent
        remainingDaysCount = remainingPercent

        requestLayout()
        invalidate()
    }

    fun setChartSelectedInteraction(interaction: (String) -> Unit) {
        chartSelectedInteraction = interaction
    }
}
