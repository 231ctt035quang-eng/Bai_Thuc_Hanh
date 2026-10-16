package com.example.bai_thuc_hanh // Giữ nguyên package name của bạn

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Course(
    val id: String,
    val title: String,
    val code: String,
    val credit: Int
)

sealed interface CourseUiState {
    data object Loading : CourseUiState
    data class Success(val list: List<Course>) : CourseUiState
    data class Error(val message: String) : CourseUiState
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lab5CourseScreen() {
    val scope = rememberCoroutineScope()
    var uiState by remember { mutableStateOf<CourseUiState>(CourseUiState.Loading) }

    fun loadCourses(shouldFail: Boolean = false) {
        scope.launch {
            uiState = CourseUiState.Loading
            delay(1500)
            if (shouldFail) {
                uiState = CourseUiState.Error("Không thể kết nối đến máy chủ. Vui lòng kiểm tra lại mạng!")
            } else {
                uiState = CourseUiState.Success(
                    listOf(
                        Course("1", "Lập trình Android với Kotlin", "AND201", 3),
//                        Course("2", "Cơ sở dữ liệu & SQL Server", "DB101", 4),
//                        Course("3", "Kiến trúc & Thiết kế phần mềm", "SE302", 3),
//                        Course("4", "Điện toán đám mây & DevOps", "CC401", 3)
                    )
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        loadCourses(shouldFail = false)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Thực hành Lab 5", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { loadCourses(shouldFail = false) }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Reload Success")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF00695C),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val state = uiState) {
                is CourseUiState.Loading -> LoadingContent()
                is CourseUiState.Success -> SuccessContent(
                    courses = state.list,
                    onSimulateError = { loadCourses(shouldFail = true) }
                )
                is CourseUiState.Error -> ErrorContent(
                    message = state.message,
                    onRetry = { loadCourses(shouldFail = false) }
                )
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = Color(0xFF00695C),
            strokeWidth = 4.dp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Đang tải danh sách môn học...",
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

@Composable
fun SuccessContent(
    courses: List<Course>,
    onSimulateError: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = onSimulateError,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Giả lập lỗi kết nối (Test Error State)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(courses) { course ->
                CourseCard(course)
            }
        }
    }
}

@Composable
fun CourseCard(course: Course) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Book,
                contentDescription = null,
                tint = Color(0xFF00695C),
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = course.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Mã HP: ${course.code} • Số tín chỉ: ${course.credit}",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ErrorContent(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.ErrorOutline,
            contentDescription = null,
            tint = Color(0xFFD32F2F),
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Có lỗi xảy ra!",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color(0xFFD32F2F)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = message,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00695C)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Thử lại")
        }
    }
}