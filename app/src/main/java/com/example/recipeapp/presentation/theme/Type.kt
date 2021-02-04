package com.example.recipeapp.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import com.example.recipeapp.R

val quickSand = fontFamily(
        font(R.font.quicksand_light, FontWeight.W300),
        font(R.font.quicksand_regular, FontWeight.W400),
        font(R.font.quicksand_medium, FontWeight.W500),
        font(R.font.quicksand_bold, FontWeight.W600)
)

val QuickSandTypography = Typography(
        h1 = TextStyle(
                fontFamily = quickSand,
                fontWeight = FontWeight.W500,
                fontSize = 30.sp
        ),
        h2 = TextStyle(
                fontFamily = quickSand,
                fontWeight = FontWeight.W500,
                fontSize = 24.sp
        ),
        h3 = TextStyle(
                fontFamily = quickSand,
                fontWeight = FontWeight.W400,
                fontSize = 20.sp
        ),
        h4 = TextStyle(
                fontFamily = quickSand,
                fontWeight = FontWeight.W400,
                fontSize = 18.sp
        ),
        h5 = TextStyle(
                fontFamily = quickSand,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp
        ),
        h6 = TextStyle(
                fontFamily = quickSand,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
        ),
        subtitle1 = TextStyle(
                fontFamily = quickSand,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp
        ),
        subtitle2 = TextStyle(
                fontFamily = quickSand,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
        ),
        body1 = TextStyle(
                fontFamily = quickSand,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        ),
        body2 = TextStyle(
                fontFamily = quickSand,
                fontSize = 14.sp
        ),
        button = TextStyle(
                fontFamily = quickSand,
                fontWeight = FontWeight.W400,
                fontSize = 15.sp,
                color = Color.White
        ),
        caption = TextStyle(
                fontFamily = quickSand,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
        ),
)