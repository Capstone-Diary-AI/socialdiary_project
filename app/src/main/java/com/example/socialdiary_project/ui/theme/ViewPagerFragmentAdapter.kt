package com.example.socialdiary_project.ui.theme

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 3  // 3개의 페이지 (친구 목록, 일기 목록, 캘린더)
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FriendListFragment()  // 0번 인덱스: 친구 목록
            1 -> DiaryListFragment()   // 1번 인덱스: 일기 목록
            2 -> CalendarFragment()    // 2번 인덱스: 캘린더
            else -> DiaryListFragment()  // 기본값: 일기 목록
        }
    }
}

