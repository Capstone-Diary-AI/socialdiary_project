//메인 로그인기능 포함

package com.example.socialdiary_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.socialdiary_project.ui.theme.DiaryListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 기존 로그인 버튼 설정
        val loginButton = findViewById<Button>(R.id.login_button)
        val usernameInput = findViewById<EditText>(R.id.username_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val intent = Intent(this, DiaryListActivity::class.java)
                startActivity(intent)
            }
        }

        // '메인' 버튼 클릭 시 DiaryListActivity로 이동
        val mainButton = findViewById<Button>(R.id.button_main)
        mainButton.setOnClickListener {
            val intent = Intent(this, DiaryListActivity::class.java)
            startActivity(intent)
        }
    }
}
