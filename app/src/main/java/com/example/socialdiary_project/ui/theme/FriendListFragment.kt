package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.socialdiary_project.R

class FriendListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 프래그먼트 레이아웃 로드
        val view = inflater.inflate(R.layout.fragment_friend_list, container, false)

        // 친구 목록 데이터 설정 (임시 데이터)
        val friendList = listOf(
            "*학교",
            "  - 홍길동",
            "  - 이순신",
            "  - 유관순",
            "*지인",
            "  - 김영희",
            "  - 박철수",
            "  - 나선희",
            "*회사",
            "  - 강민수",
            "  - 오지혜",
            "  - 박진영",
            "*동네 친구",
            "  - 김철수",
            "  - 최준희",
            "  - 서윤재",
            "*가족",
            "  - 어머니",
            "  - 아버지",
            "  - 동생",
            "*운동 동호회",
            "  - 정하늘",
            "  - 김태경",
            "  - 이주희"
        )

        // ArrayAdapter를 사용하여 ListView에 친구 목록 설정
        val listView: ListView = view.findViewById(R.id.listview_friends)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, friendList)
        listView.adapter = adapter

        return view
    }
}
