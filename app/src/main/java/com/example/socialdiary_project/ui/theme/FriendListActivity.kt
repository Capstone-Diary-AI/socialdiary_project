// FriendListActivity.kt
// 친구 목록을 보여주며 팝업 대화창 기능 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import com.example.socialdiary_project.R

class FriendListActivity : AppCompatActivity() {
    private lateinit var friendListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_list)

        // 친구 목록 리스트 설정
        friendListView = findViewById(R.id.listview_friends)
        val friends = arrayOf("친구 0", "친구 1", "친구 2", "친구 3", "친구 4")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, friends)
        friendListView.adapter = adapter

        // 플러스 버튼 클릭 이벤트
        val addButton = findViewById<ImageButton>(R.id.add_button)
        addButton.setOnClickListener {
            addNewFriend() // 친구 추가 기능
        }
    }

    private fun addNewFriend() {
        // 친구 추가 로직
    }
}
