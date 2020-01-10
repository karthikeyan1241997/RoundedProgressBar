package com.karthi.roundedprogressbar

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import kotlin.math.min

class RoundedCircularProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var mStartAngle = -90f
    private var currentSweepAngle = 0f
    private var maxProgress = 100
    private var maxSweepAngle = 360f
    private var mBackgroundColor: Int
    private var mProgressColor: Int
    private var mStrokeWidth: Float
    private val mCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { style = Paint.Style.STROKE }
    private val oval = RectF(0f, 0f, 0f, 0f)

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundedCircularProgressBar)
        mBackgroundColor = typedArray.getColor(R.styleable.RoundedCircularProgressBar_rcp_backgroundColor, Color.GRAY)
        mProgressColor = typedArray.getColor(R.styleable.RoundedCircularProgressBar_rcp_progressColor, Color.BLUE)
        mStrokeWidth = typedArray.getDimension(R.styleable.RoundedCircularProgressBar_rcp_strokeWidth, 20f)
        setProgress(typedArray.getInt(R.styleable.RoundedCircularProgressBar_rcp_progress, 0))
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawProgressBar(canvas)
    }

    private fun drawProgressBar(canvas: Canvas?){
        canvas ?: return
        val diameter = min(width, height)
        val pad = mStrokeWidth/2
        oval.set(pad, pad, diameter - pad, diameter - pad)

        mCirclePaint.color = mBackgroundColor
        mCirclePaint.strokeWidth = mStrokeWidth

        // drawing background
        canvas.drawArc(oval, 0f, 360f, false, mCirclePaint)
        mCirclePaint.apply {
            color = mProgressColor
            strokeCap = Paint.Cap.ROUND
        }
        // drawing progress
        canvas.drawArc(oval, mStartAngle, currentSweepAngle, false, mCirclePaint)
    }

    fun setProgress(progress: Int) {
        val animator = ValueAnimator.ofFloat(currentSweepAngle, calculateSweepAngleForProgress(progress))
        animator.interpolator = DecelerateInterpolator()
        animator.duration = 500
        animator.addUpdateListener { valueAnimator ->
            currentSweepAngle = valueAnimator.animatedValue as Float
            invalidate()
        }
        animator.start()
    }

    private fun calculateSweepAngleForProgress(progress: Int) = (progress/maxProgress.toFloat()).times(maxSweepAngle).unaryMinus()
}