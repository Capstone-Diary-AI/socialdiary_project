// FriendListActivity.kt
// 친구 목록을 보여주는 화면을 위한 액티비티로,
// FriendListFragment를 불러와서 친구 목록을 표시

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FriendListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // FriendListFragment를 호출하여 액티비티의 전체 화면을 채움
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, FriendListFragment())
                .commit()
        }
    }
}
