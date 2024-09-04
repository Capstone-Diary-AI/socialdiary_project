//버튼 작동 페이지

import 'package:flutter/material.dart';
import 'my_home_page.dart';
import 'write_page.dart';  // write_page.dart를 임포트합니다.

void showCreateGroupDialog(BuildContext context, int state) {
  if (state == 0) {
    Navigator.of(context).pop();
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => const WritePage(title: '일기 작성하기')),
    );
  } else if (state == 1) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text(
            "무엇을 하시겠습니까?" "$state",
            style: TextStyle(color: Theme.of(context).textTheme.bodyLarge?.color),  // Updated
          ),
          actions: <Widget>[
            TextButton(
              child: Text(
                "그룹 만들기",
                style: TextStyle(color: Theme.of(context).textTheme.bodyLarge?.color),  // Updated
              ),
              onPressed: () {
                Navigator.of(context).pop();
                showSelectFriendsDialog(context);
              },
            ),
            TextButton(
              child: Text(
                "친구 추가하기",
                style: TextStyle(color: Theme.of(context).textTheme.bodyLarge?.color),  // Updated
              ),
              onPressed: () {
                Navigator.of(context).pop();
                showAddFriendDialog(context);
              },
            ),
          ],
        );
      },
    );
  } else if (state == 2) {
    //캘린더 페이지에서 버튼 기능
  }
}

void showSelectFriendsDialog(BuildContext context) {
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: Text(
          "친구를 선택하세요",
          style: TextStyle(color: Theme.of(context).textTheme.bodyLarge?.color),  // Updated
        ),
        content: FriendSelector(),
        actions: <Widget>[
          TextButton(
            child: Text(
              "완료",
              style: TextStyle(color: Theme.of(context).textTheme.bodyLarge?.color),  // Updated
            ),
            onPressed: () {
              Navigator.of(context).pop();
              // 선택된 친구를 기반으로 그룹을 생성하는 로직을 여기에 추가하세요.
            },
          ),
        ],
      );
    },
  );
}

void showAddFriendDialog(BuildContext context) {
  showDialog(
    context: context,
    builder: (BuildContext context) {
      return AlertDialog(
        title: Text(
          "추가할 친구의 ID를 입력하세요",
          style: TextStyle(color: Theme.of(context).textTheme.bodyLarge?.color),  // Updated
        ),
        content: const TextField(
          decoration: InputDecoration(
            border: OutlineInputBorder(),
            labelText: 'ID',
          ),
        ),
        actions: <Widget>[
          TextButton(
            child: Text(
              "완료",
              style: TextStyle(color: Theme.of(context).textTheme.bodyLarge?.color),  // Updated
            ),
            onPressed: () {
              Navigator.of(context).pop();
              // 입력한 ID로 친구를 추가하는 로직을 추가하세요.
            },
          ),
        ],
      );
    },
  );
}
