// ViewPagerFragmentAdapter.kt
// ViewPager2에서 프래그먼트를 관리하는 어댑터로, 친구 목록, 일기 목록, 캘린더 화면을 스와이프 전환하도록 설정

package com.example.socialdiary_project.ui.theme

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerFragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    // 페이지 수 반환 (친구 목록, 일기 목록, 캘린더 총 3개 페이지)
    override fun getItemCount(): Int {
        return 3
    }

    // 각 포지션에 해당하는 프래그먼트를 생성하여 반환
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FriendListFragment() // 첫 번째 프래그먼트: 친구 목록
            1 -> DiaryListFragment()  // 두 번째 프래그먼트: 일기 목록
            2 -> CalendarFragment()   // 세 번째 프래그먼트: 캘린더
            else -> DiaryListFragment() // 기본값으로 일기 목록 반환
        }
    }
}
