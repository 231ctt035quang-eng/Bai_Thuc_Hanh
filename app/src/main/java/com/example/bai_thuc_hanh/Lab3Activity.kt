package com.example.bai_thuc_hanh

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bai_thuc_hanh.databinding.ActivityLab3Binding

class Lab3Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLab3Binding

    companion object {
        const val EXTRA_STUDENT_ID = "extra_student_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLab3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val studentId = intent.getStringExtra(EXTRA_STUDENT_ID) ?: "1"

        val student = getStudentById(studentId)

        displayStudentData(student)

        setupEvents(student)
    }

    private fun displayStudentData(student: Student) {
        binding.tvName.text = student.name
        binding.tvNghanh.text = student.major
        binding.tvEmail.text = student.email
        binding.tvPhone.text = student.phone

        val avatarChar = student.name.trim().split(" ").lastOrNull()?.firstOrNull()?.uppercase() ?: "A"
        binding.tvAvatar.text = avatarChar
    }

    private fun setupEvents(student: Student) {
        // Nút Back trên thanh TopBar
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnGoiDien.setOnClickListener {
            Toast.makeText(this, "Đang kết nối cuộc gọi tới ${student.phone}...", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${student.phone}")
            }
            startActivity(intent)
        }

        binding.btnGuiEmail.setOnClickListener {
            Toast.makeText(this, "Mở ứng dụng gửi mail tới ${student.email}...", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${student.email}")
            }
            startActivity(intent)
        }
    }

    private fun getStudentById(id: String): Student {
        val list = listOf(
            Student("1", "Nguyễn Văn An", "Công nghệ thông tin", "an.nguyen@example.com", "0123 456 789"),
            Student("2", "Trần Thị B", "Công nghệ thông tin", "b.tran@example.com", "0987 654 321"),
            Student("3", "Lê Minh Hoàng", "Công nghệ thông tin", "hoang.le@example.com", "0912 345 678")
        )
        return list.find { it.id == id } ?: list[0]
    }
}