// DiaryListFragment.kt
// 사용자가 작성한 일기 목록을 보여주는 프래그먼트로, 리스트 형태로 일기를 표시

package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.socialdiary_project.R

class DiaryListFragment : Fragment() {

    private lateinit var listView: ListView
    private lateinit var spinnerFilter: Spinner

    // 예시 일기 목록 데이터
    private var allDiaries = listOf(
        "[개인] 오늘의 일기",
        "[공유] 여행 이야기",
        "[개인] 비밀 노트",
        "[공유] 프로젝트 회의",
        "[공유] 가족 모임",
        "[개인] 새로운 운동 루틴",
        "[공유] 영화 감상 후기",
        "[개인] 독서 노트 - 해리포터",
        "[공유] 친구와의 피크닉",
        "[공유] 생일 파티 계획",
        "[개인] 요리 도전기",
        "[공유] 산책하며 느낀 것들",
        "[공유] 집 꾸미기 아이디어",
        "[개인] 꿈 일기",
        "[공유] 캠핑 준비 목록",
        "[개인] 업무 일정 정리",
        "[공유] 소풍 후기",
        "[개인] 감정일기 - 오늘은 조금 우울했다",
        "[공유] 주말 농장 체험기",
        "[개인] 저녁 식사 레시피",
        "[공유] 가족과의 대화",
        "[공유] 친구들과의 카페 모임",
        "[개인] 새로운 취미 - 그림 그리기",
        "[공유] 축구 경기 관람 후기",
        "[개인] 내일 할 일 목록",
        "[공유] 크리스마스 준비 이야기",
        "[개인] 다이어트 기록",
        "[공유] 독서 토론 모임",
        "[개인] 혼자 떠난 여행",
        "[공유] 음악 페스티벌 후기",
        "[개인] 오늘의 기분 - 행복",
        "[공유] 친구와의 등산",
        "[개인] 영화 보면서 느낀 점",
        "[공유] 저녁 식사 메뉴 결정하기",
        "[개인] 오늘의 감사한 일",
        "[공유] 사진 촬영 여행",
        "[개인] 하루 반성 노트",
        "[공유] 요가 수업 후기",
        "[개인] 비오는 날의 생각들",
        "[공유] 부모님과의 저녁",
        "[공유] 주말 게임 대회 후기",
        "[개인] 나만의 명상 시간",
        "[공유] 친구들과의 추억 이야기",
        "[개인] 새로 배운 요리법",
        "[공유] 겨울 방학 계획",
        "[개인] 이사 준비 기록",
        "[공유] 팀 프로젝트 계획",
        "[공유] 동네 산책 코스 추천",
        "[개인] 오늘의 목표 - 자기 개발",
        "[공유] 친구와 함께한 미술관 나들이",
        "[개인] 좋아하는 노래 목록 정리",
        "[공유] 주말 브런치 후기"
    )

    private var filteredDiaries = allDiaries // 초기에는 전체 목록 표시

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment_diary_list.xml 파일을 inflate하여 일기 목록 레이아웃을 화면에 표시
        val view = inflater.inflate(R.layout.fragment_diary_list, container, false)

        // Spinner와 ListView 초기화
        spinnerFilter = view.findViewById(R.id.spinner_filter)
        listView = view.findViewById(R.id.listview_diary)

        // Spinner 옵션 설정
        val filterOptions = arrayOf("모두", "공유", "개인")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, filterOptions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFilter.adapter = spinnerAdapter

        // ListView 초기화
        updateListView()

        // Spinner 선택 이벤트 리스너 설정
        spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> filteredDiaries = allDiaries // 모두
                    1 -> filteredDiaries = allDiaries.filter { it.startsWith("[공유]") } // 공유
                    2 -> filteredDiaries = allDiaries.filter { it.startsWith("[개인]") } // 개인
                }
                updateListView()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 아무것도 선택되지 않은 경우 기본 설정을 유지
            }
        }

        return view
    }

    // 필터링된 일기 목록을 ListView에 표시하는 메서드
    private fun updateListView() {
        val diaryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, filteredDiaries)
        listView.adapter = diaryAdapter
    }
}
