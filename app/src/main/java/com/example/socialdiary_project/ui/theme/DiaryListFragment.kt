package com.example.socialdiary_project.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import com.example.socialdiary_project.R

class DiaryListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_diary_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 카테고리 필터 버튼
        val filterButton = view.findViewById<Button>(R.id.button_filter)
        filterButton.setOnClickListener {
            showCategoryDialog()
        }
    }

    private fun showCategoryDialog() {
        val categories = arrayOf("모두", "개인", "공유")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("카테고리 선택")
            .setItems(categories) { _, _ ->
                // 선택한 카테고리 필터 처리
            }
        builder.create().show()
    }
}
