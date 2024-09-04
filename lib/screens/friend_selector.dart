import 'package:flutter/material.dart';

class FriendSelector extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 150.0, // 조정 가능
      width: double.maxFinite,
      child: ListView.builder(
        itemCount: 40,
        itemBuilder: (context, index) {
          return ListTile(
            title: Text('친구 $index'),
            onTap: () {
              // 친구 선택 로직 추가 가능
              Navigator.of(context).pop(); // 선택 후 다이얼로그 닫기
            },
          );
        },
      ),
    );
  }
}
