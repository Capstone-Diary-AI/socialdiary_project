// FriendListActivity.kt
// 친구 목록을 보여주며 팝업 대화창 기능 제공

package com.example.socialdiary_project.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.ImageButton
import androidx.core.content.ContextCompat.startActivity
import com.example.socialdiary_project.R

class FriendListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_list)

        val friendListView = findViewById<ListView>(R.id.listview_friends)

        val friends = arrayOf("친구 0", "친구 1", "친구 2", "친구 3", "친구 4", "친구 5")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, friends)
        friendListView.adapter = adapter

    }
}
