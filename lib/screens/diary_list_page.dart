import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:geolocator/geolocator.dart';
import 'diary_model.dart';
import 'diary_detail_page.dart';
import 'location_service.dart';

class DiaryListPage extends StatefulWidget {
  final LocationService locationService;

  DiaryListPage({required this.locationService});

  @override
  _DiaryListPageState createState() => _DiaryListPageState();
}

class _DiaryListPageState extends State<DiaryListPage> {
  final List<Diary> diaryEntries = [];
  GoogleMapController? _mapController;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Diary List')),
      body: Column(
        children: [
          Expanded(
            flex: 1,
            child: GoogleMap(
              initialCameraPosition: CameraPosition(
                target: LatLng(37.7749, -122.4194),
                zoom: 10,
              ),
              onMapCreated: (controller) {
                _mapController = controller;
              },
              markers: _getMarkers(),
            ),
          ),
          Expanded(
            flex: 1,
            child: ListView.builder(
              itemCount: diaryEntries.length,
              itemBuilder: (context, index) {
                final diary = diaryEntries[index];
                return ListTile(
                  title: Text(diary.title),
                  subtitle: Text(diary.date.toLocal().toString().split(' ')[0]),
                  onTap: () {
                    _mapController?.animateCamera(
                      CameraUpdate.newLatLng(
                        LatLng(diary.latitude, diary.longitude),
                      ),
                    );
                    Navigator.of(context).push(
                      MaterialPageRoute(builder: (context) => DiaryDetailPage(diary: diary)),
                    );
                  },
                );
              },
            ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _addDiaryEntry,
        child: Icon(Icons.add),
      ),
    );
  }

  Set<Marker> _getMarkers() {
    return diaryEntries.map((diary) {
      return Marker(
        markerId: MarkerId(diary.id),
        position: LatLng(diary.latitude, diary.longitude),
        infoWindow: InfoWindow(
          title: diary.title,
          snippet: diary.date.toLocal().toString().split(' ')[0],
        ),
      );
    }).toSet();
  }

  void _addDiaryEntry() {
    showDialog(
      context: context,
      builder: (context) {
        final titleController = TextEditingController();
        final contentController = TextEditingController();
        return AlertDialog(
          title: Text('New Diary Entry'),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              TextField(
                controller: titleController,
                decoration: InputDecoration(labelText: 'Title'),
              ),
              TextField(
                controller: contentController,
                decoration: InputDecoration(labelText: 'Content'),
              ),
              DropdownButton<Position>(
                hint: Text('Select a location'),
                items: widget.locationService.positions.map((position) {
                  return DropdownMenuItem<Position>(
                    value: position,
                    child: Text('${position.latitude}, ${position.longitude}'),
                  );
                }).toList(),
                onChanged: (position) {
                  setState(() {
                    final newEntry = Diary(
                      id: DateTime.now().millisecondsSinceEpoch.toString(),
                      date: DateTime.now(),
                      title: titleController.text,
                      content: contentController.text,
                      latitude: position!.latitude,
                      longitude: position.longitude,
                    );
                    diaryEntries.add(newEntry);
                  });
                  Navigator.of(context).pop();
                },
              ),
            ],
          ),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: Text('Cancel'),
            ),
          ],
        );
      },
    );
  }
}
