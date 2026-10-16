package com.example.bai_thuc_hanh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class Lab5Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab5CourseScreen()
        }
    }
}