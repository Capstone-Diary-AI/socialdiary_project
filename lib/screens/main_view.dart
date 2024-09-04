import 'package:flutter/material.dart';

class MainView extends StatelessWidget {
  const MainView({super.key});

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Text('존재하는 그룹', style: Theme.of(context).textTheme.headlineSmall), // 수정된 부분
          ),
          Column(
            children: List.generate(40, (index) => const Text('테스트', style: TextStyle(fontSize: 20))),
          ),
        ],
      ),
    );
  }
}
