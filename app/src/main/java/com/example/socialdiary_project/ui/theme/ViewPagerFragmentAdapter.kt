// ViewPagerFragmentAdapter.kt
package com.example.socialdiary_project.ui.theme

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerFragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3 // 친구 목록, 일기 목록, 캘린더
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FriendListFragment() // 친구 목록
            1 -> DiaryListFragment()  // 일기 목록
            2 -> CalendarFragment()   // 캘린더
            else -> DiaryListFragment()
        }
    }
}
