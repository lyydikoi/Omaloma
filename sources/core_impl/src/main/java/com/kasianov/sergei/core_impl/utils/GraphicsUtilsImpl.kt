package com.kasianov.sergei.core_impl.utils

import android.content.Context
import com.kasianov.sergei.core_api.utils.GraphicsUtils
import javax.inject.Inject

class GraphicsUtilsImpl @Inject constructor() : GraphicsUtils {
    override fun pxToDp(pxValue: Float, context: Context): Float {
        val density: Float = context.resources.displayMetrics.density
        return pxValue / density
    }
}
