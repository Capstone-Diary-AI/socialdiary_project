import 'package:flutter/material.dart';

class SharedDiaryPage extends StatelessWidget {
  final String diaryTitle;

  SharedDiaryPage({required this.diaryTitle});

  @override
  Widget build(BuildContext context) {
    final diaryContent = '''
오늘 아침에는 일찍 일어나 커피를 한 잔 마시며 하루를 시작했어요. 창문을 열어보니 햇살이 눈부시게 내리쬐고 있었고, 기분 좋은 바람이 방 안으로 들어오면서 상쾌한 기운이 온몸에 퍼졌어요. 아침 식사로는 토스트와 계란후라이, 그리고 과일 몇 조각을 먹었어요. 간단하지만 영양가 있는 식사 덕분에 에너지가 넘쳤죠. 

_민지가 아침 식사를 준비했는데, 정말 맛있었어요._

식사를 마친 후, 집 근처 공원으로 산책을 나갔어요. 공원은 푸르름이 가득하고, 산책로를 따라 걸으면서 자연의 아름다움을 만끽했어요. 새들의 지저귐이 평화로움을 더해주었죠. 

_산책 도중에 우리는 강아지를 산책시키는 이웃을 만나 이야기를 나누기도 했어요._

산책을 마치고 집으로 돌아와 독서를 했는데, 요즘 읽고 있는 책이 너무 흥미진진해서 시간 가는 줄 모르고 빠져들었어요. 

_지훈은 독서를 하다가 잠깐 낮잠을 잤고, 민지는 계속 책을 읽었어요._

점심으로는 간단하게 샐러드를 만들어 먹었어요. 신선한 채소들과 닭가슴살을 넣은 샐러드는 맛도 좋고 건강에도 좋아서 만족스러웠어요. 점심을 먹고 나서는 잠시 낮잠을 자며 휴식을 취했어요. 짧은 시간이었지만, 잠깐의 휴식이 큰 도움이 되었죠. 

_수현은 낮잠 대신 요가를 하며 시간을 보냈어요._

오후에는 친구와 함께 카페에서 만나기로 했어요. 오랜만에 만난 친구와 이런저런 이야기를 나누며 즐거운 시간을 보냈어요. 서로의 근황을 나누고, 앞으로의 계획에 대해 이야기하며 시간 가는 줄 몰랐죠. 카페에서 마신 아이스 라떼는 더운 날씨에 딱 맞는 선택이었어요. 

_지훈은 친구에게 요즘 배우고 있는 기타 연주에 대해 이야기했어요._

저녁으로는 파스타를 만들어 먹었어요. 오랜만에 요리를 했는데, 결과물이 생각보다 맛있어서 뿌듯했어요. 저녁 식사 후에는 좋아하는 드라마를 보며 여유로운 시간을 보냈어요. 드라마 속 인물들의 이야기에 빠져들어 웃기도 하고 울기도 하며 감정이입을 했죠. 

_드라마를 본 후, 우리는 보드게임을 하며 시간을 보냈어요._

이제 하루를 마무리하며 일기를 쓰고 있어요. 오늘은 평범하지만 행복한 하루였어요. 내일은 또 어떤 일들이 우리를 기다리고 있을지 기대가 되네요.

굿나잇!
''';

    return Scaffold(
      appBar: AppBar(
        title: Text(diaryTitle),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: SingleChildScrollView(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: diaryContent.split('\n').map((line) {
              if (line.startsWith('_') && line.endsWith('_')) {
                return Padding(
                  padding: const EdgeInsets.symmetric(vertical: 4.0),
                  child: Text(
                    line.replaceAll('_', ''),
                    style: TextStyle(
                      fontSize: 16.0,
                      fontStyle: FontStyle.italic,
                      color: Colors.blueGrey,
                    ),
                  ),
                );
              } else {
                return Padding(
                  padding: const EdgeInsets.symmetric(vertical: 4.0),
                  child: Text(
                    line,
                    style: TextStyle(
                      fontSize: 16.0,
                    ),
                  ),
                );
              }
            }).toList(),
          ),
        ),
      ),
    );
  }
}
