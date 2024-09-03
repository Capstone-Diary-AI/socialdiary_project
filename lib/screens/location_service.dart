import 'package:geolocator/geolocator.dart';

class LocationService {
  final List<Position> _positions = [];

  Future<void> startTracking() async {
    await Geolocator.requestPermission();
    Geolocator.getPositionStream(
      locationSettings: LocationSettings(
        accuracy: LocationAccuracy.high,
        distanceFilter: 50,
      ),
    ).listen((Position position) {
      _positions.add(position);
    });
  }

  List<Position> get positions => _positions;
}
