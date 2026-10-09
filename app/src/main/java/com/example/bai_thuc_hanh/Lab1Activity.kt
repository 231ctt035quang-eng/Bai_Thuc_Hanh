package com.example.bai_thuc_hanh

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bai_thuc_hanh.databinding.ActivityLab1Binding

class Lab1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLab1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLab1Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.imageView.setOnClickListener {
            finish()
        }

        binding.button.setOnClickListener {
            val name = binding.editTextText.text?.toString()?.trim().orEmpty()
            val email = binding.editTextTextEmailAddress.text?.toString()?.trim().orEmpty()
            val password = binding.editTextTextPassword.text?.toString()?.trim().orEmpty()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}