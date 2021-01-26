package com.example.recipeapp.presentation.components

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.recipeapp.R
import com.example.recipeapp.presentation.components.HeartAnimationDefinition.HeartStates.*
import com.example.recipeapp.presentation.components.HeartAnimationDefinition.heartSize
import com.example.recipeapp.presentation.components.HeartAnimationDefinition.heartTransitionDefinition
import com.example.recipeapp.utils.loadPicture

@Composable
fun AnimatedHeartButton(
        modifier: Modifier,
        buttonState: MutableState<HeartAnimationDefinition.HeartStates>,
        onToggle: () -> Unit
) {
    // set the next state by checking the current state.
    val toState = if (buttonState.value == IDLE) {
        ACTIVE
    } else {
        IDLE
    }

    val state = transition(
            definition = heartTransitionDefinition,
            initState = buttonState.value,
            toState = toState
    )

    HeartButton(
            modifier = modifier,
            buttonState = buttonState,
            state = state,
            onToggle = onToggle
    )



}

@Composable
private fun HeartButton(
        modifier: Modifier,
        buttonState: MutableState<HeartAnimationDefinition.HeartStates>,
        state: TransitionState,
        onToggle: () -> Unit
) {
    if (buttonState.value == ACTIVE) {
        loadPicture(drawable = R.drawable.heart_red).value?.let { image ->
            Image(
                    bitmap = image.asImageBitmap(),
                    modifier = modifier
                            .clickable(
                                    onClick = onToggle,
                                    indication = null
                            )
                            .width(state[heartSize])
                            .height(state[heartSize])
            )
        }
    } else {
        loadPicture(drawable = R.drawable.heart_grey).value?.let { image ->
            Image(
                    bitmap = image.asImageBitmap(),
                    modifier = modifier
                            .clickable(
                                    onClick = onToggle,
                                    indication = null
                            )
                            .width(state[heartSize])
                            .height(state[heartSize])
            )
        }
    }
}

object HeartAnimationDefinition {

    // Define the states of the animation
    enum class HeartStates {
        IDLE,
        ACTIVE
    }

    // Define the prop key for animation
    val heartColor = ColorPropKey(label = "heartColor")
    val heartSize = DpPropKey(label = "heartSize")

    private val idleIconSize = 30.dp
    private val expandedIconSize = 60.dp

    val heartTransitionDefinition = transitionDefinition<HeartStates> {
        state(IDLE) {
            this[heartColor] = Color.LightGray
            this[heartSize] = idleIconSize
        }

        state(ACTIVE) {
            this[heartColor] = Color.Red
            this[heartSize] = idleIconSize
        }

        transition(IDLE to ACTIVE) {
            heartColor using tween(durationMillis = 500)

            // expand the icon size at 100 millisecond and idle icon size at 200 ms
            heartSize using keyframes {
                durationMillis = 500
                expandedIconSize at 100
                idleIconSize at 200
            }
        }

        transition(ACTIVE to IDLE) {
            heartColor using tween(durationMillis = 500)

            // expand the icon size at 100 millisecond and idle icon size at 200 ms
            heartSize using keyframes {
                durationMillis = 500
                expandedIconSize at 100
                idleIconSize at 200
            }
        }
    }
}