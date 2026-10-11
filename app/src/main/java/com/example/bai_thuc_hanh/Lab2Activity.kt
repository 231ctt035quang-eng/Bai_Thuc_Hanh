package com.example.bai_thuc_hanh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Lab2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lab_2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnDetail1 = findViewById<Button>(R.id.button1)
        val btnDetail2 = findViewById<Button>(R.id.button2)
        val btnDetail3 = findViewById<Button>(R.id.button3)
        val fabAdd = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val imgSearch = findViewById<ImageView>(R.id.imageViewSearch)
        val imgSort = findViewById<ImageView>(R.id.imageViewSort)

        btnDetail1?.setOnClickListener {
            openDetailScreen("1")
        }

        btnDetail2?.setOnClickListener {
            openDetailScreen("2")
        }

        btnDetail3?.setOnClickListener {
            openDetailScreen("3")
        }

        fabAdd?.setOnClickListener {
            Toast.makeText(this, "Thêm sinh viên mới", Toast.LENGTH_SHORT).show()
        }
        imgSearch?.setOnClickListener {
            Toast.makeText(this, "Tính năng tìm kiếm", Toast.LENGTH_SHORT).show()
        }
        imgSort?.setOnClickListener {
            Toast.makeText(this, "Tính năng sắp xếp", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openDetailScreen(studentId: String) {
        val intent = Intent(this, Lab3Activity::class.java).apply {
            putExtra(Lab3Activity.EXTRA_STUDENT_ID, studentId)
        }
        startActivity(intent)
    }
}