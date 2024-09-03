// lib/diary_model.dart
class Diary {
  final String id;
  final DateTime date;
  final String title;
  final String content;
  final double latitude;
  final double longitude;

  Diary({
    required this.id,
    required this.date,
    required this.title,
    required this.content,
    required this.latitude,
    required this.longitude,
  });
}
