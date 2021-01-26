package com.example.recipeapp.presentation.components.utils

import androidx.compose.animation.core.*
import com.example.recipeapp.presentation.components.utils.ShimmerAnimationDefinition.AnimationStates.END
import com.example.recipeapp.presentation.components.utils.ShimmerAnimationDefinition.AnimationStates.START

/**
 * Shimmer Animation Definition
 */
class ShimmerAnimationDefinition(
        private val widthPx: Float,
        private val heightPx: Float
) {

    var gradientHeight: Float = 0.2f * heightPx

    /**
     * The Animation State
     */
    enum class AnimationStates {
        START,
        END
    }

    //Define the propkey
    val xShimmerPropKey = FloatPropKey(label = "xShimmerKey")
    val yShimmerPropKey = FloatPropKey(label = "yShimmerKey")

    val shimmerAnimationTransition = transitionDefinition<AnimationStates> {
        state(START) {
            // Animation start from (0,0) origin
            this[xShimmerPropKey] = 0f
            this[yShimmerPropKey] = 0f
        }

        state(END) {
            // Animation end out of the screen
            this[xShimmerPropKey] = widthPx + gradientHeight
            this[yShimmerPropKey] = heightPx + gradientHeight
        }

        transition(START, END) {
            xShimmerPropKey using infiniteRepeatable(
                    animation = tween(
                            durationMillis = 1300,
                            delayMillis = 300,
                            easing = LinearEasing
                    )
            )

            yShimmerPropKey using infiniteRepeatable(
                    animation = tween(
                            durationMillis = 1300,
                            delayMillis = 300,
                            easing = LinearEasing
                    )
            )
        }
    }


}