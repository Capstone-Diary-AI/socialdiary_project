package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
            showFriendManagementDialog() // 팝업 띄우기
        }
    }

    // "친구 관리" 팝업을 띄우는 함수
    private fun showFriendManagementDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("친구 관리")

        // 팝업에서 보여줄 항목들
        val options = arrayOf("친구 추가", "친구 삭제", "친구 목록 관리")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> {
                    // 친구 추가 로직
                    addNewFriend()
                }
                1 -> {
                    // 친구 삭제 로직
                    deleteFriend()
                }
                2 -> {
                    // 친구 목록 관리 로직
                    manageFriendList()
                }
            }
        }

        // 취소 버튼 추가
        builder.setNegativeButton("취소") { dialog, _ ->
            dialog.dismiss()
        }

        // 팝업 창 표시
        val dialog = builder.create()
        dialog.show()
    }

    private fun addNewFriend() {
        // 친구 추가 로직
    }

    private fun deleteFriend() {
        // 친구 삭제 로직
    }

    private fun manageFriendList() {
        // 친구 목록 관리 로직
    }
}
