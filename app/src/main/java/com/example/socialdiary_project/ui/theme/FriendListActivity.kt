// FriendListActivity.kt
// 친구 목록을 보여주며 팝업 대화창 기능 제공

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import android.widget.ArrayAdapter
import com.example.socialdiary_project.R

class FriendListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_list)

        val friendListView = findViewById<ListView>(R.id.listview_friends)

        val friends = arrayOf("친구 0", "친구 1", "친구 2", "친구 3", "친구 4", "친구 5")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, friends)
        friendListView.adapter = adapter

        // 팝업 버튼
        val addButton = findViewById<Button>(R.id.button_add_friend)
        addButton.setOnClickListener {
            showFriendActionDialog()
        }
    }

    private fun showFriendActionDialog() {
        val options = arrayOf("그룹 만들기", "친구 추가하기")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("무엇을 하시겠습니까?")
            .setItems(options) { _, _ ->
                // 선택한 작업 처리
            }
        builder.create().show()
    }
}
