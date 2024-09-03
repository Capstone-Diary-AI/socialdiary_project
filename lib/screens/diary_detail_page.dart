// lib/diary_detail_page.dart
import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'diary_model.dart';

class DiaryDetailPage extends StatelessWidget {
  final Diary diary;

  DiaryDetailPage({required this.diary});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(diary.title)),
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Date: ${diary.date.toLocal().toString().split(' ')[0]}',
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
            SizedBox(height: 16),
            Text(diary.content, style: TextStyle(fontSize: 16)),
            SizedBox(height: 16),
            Expanded(
              child: GoogleMap(
                initialCameraPosition: CameraPosition(
                  target: LatLng(diary.latitude, diary.longitude),
                  zoom: 15,
                ),
                markers: {
                  Marker(
                    markerId: MarkerId(diary.id),
                    position: LatLng(diary.latitude, diary.longitude),
                    infoWindow: InfoWindow(
                      title: diary.title,
                      snippet: diary.date.toLocal().toString().split(' ')[0],
                    ),
                  ),
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
