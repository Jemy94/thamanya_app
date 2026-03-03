package com.jemy.thamanya.ui.ui.common

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class BottomCurvedShape(
    private val curveHeight: Float = 40f
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(0f, size.height - curveHeight)

            quadraticBezierTo(
                size.width / 2,
                size.height + curveHeight,
                size.width,
                size.height - curveHeight
            )

            lineTo(size.width, 0f)
            close()
        }
        return Outline.Generic(path)
    }
}