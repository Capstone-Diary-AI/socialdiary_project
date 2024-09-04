import 'package:flutter/material.dart';

class FriendView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Padding(
            padding: EdgeInsets.all(16.0),
            child: Text('친구 목록', style: Theme.of(context).textTheme.headlineSmall), // 수정된 부분
          ),
          Column(
            children: List.generate(40, (index) => Text('친구 $index', style: TextStyle(fontSize: 20))),
          ),
        ],
      ),
    );
  }
}
