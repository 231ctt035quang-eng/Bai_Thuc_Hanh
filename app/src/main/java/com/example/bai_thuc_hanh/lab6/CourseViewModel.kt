package com.example.bai_thuc_hanh.lab6

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bai_thuc_hanh.Course
import com.example.bai_thuc_hanh.CourseUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CourseUiState>(CourseUiState.Loading)
    val uiState: StateFlow<CourseUiState> = _uiState.asStateFlow()

    private var loadCount = 0

    init {
        load()
    }

    fun load() {
        val attempt = loadCount
        loadCount++

        viewModelScope.launch {
            _uiState.value = CourseUiState.Loading
            delay(1200)

            _uiState.value = if (attempt % 2 == 0) {
                CourseUiState.Success(
                    listOf(
                        Course("1", "Android Kotlin cơ bản", "GV", 3),
                        Course("2", "Jetpack Compose", "GV", 3),
                        Course("3", "Material Design 3", "GV", 3)
                    )
                )
            } else {
                CourseUiState.Error("Không tải được dữ liệu. Thử lại!")
            }
        }
    }
}
