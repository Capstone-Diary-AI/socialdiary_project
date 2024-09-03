//일기 작성 페이지

import 'package:flutter/material.dart';
import 'dart:io';
import 'package:image_picker/image_picker.dart';
import 'package:geolocator/geolocator.dart';
import 'package:geocoding/geocoding.dart';
import 'package:permission_handler/permission_handler.dart';
import 'my_home_page.dart'; // 메인 페이지를 임포트합니다.

class WritePage extends StatefulWidget {
  const WritePage({super.key, required this.title});
  final String title;

  @override
  State<WritePage> createState() => _WritePageState();
}

final picker = ImagePicker();
List<XFile?> multiImage = []; // 갤러리에서 여러 장의 사진을 선택해서 저장할 변수
List<XFile?> images = []; // 가져온 사진들을 보여주기 위한 변수

class _WritePageState extends State<WritePage> {
  DateTime? _selectedDate;
  Position? _currentPosition;
  String? _currentAddress;

  @override
  void initState() {
    super.initState();
    _checkPermissionsAndGetLocation();
  }

  Future<void> _checkPermissionsAndGetLocation() async {
    if (await _handlePermission(Permission.location)) {
      _getCurrentLocation();
    } else {
      // 권한을 허용하지 않은 경우
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('위치 권한이 필요합니다.')),
      );
    }
  }

  Future<bool> _handlePermission(Permission permission) async {
    final status = await permission.request();
    return status == PermissionStatus.granted;
  }

  Future<void> _getCurrentLocation() async {
    try {
      Position position = await Geolocator.getCurrentPosition(desiredAccuracy: LocationAccuracy.high);
      setState(() {
        _currentPosition = position;
      });

      await _getAddressFromLatLng();
    } catch (e) {
      print(e);
    }
  }

  Future<void> _getAddressFromLatLng() async {
    try {
      List<Placemark> placemarks = await placemarkFromCoordinates(
        _currentPosition!.latitude,
        _currentPosition!.longitude,
      );

      Placemark place = placemarks[0];

      setState(() {
        _currentAddress = "${place.locality}, ${place.postalCode}, ${place.country}";
      });
    } catch (e) {
      print(e);
    }
  }

  Future<bool> _onWillPop() async {
    Navigator.of(context).pushAndRemoveUntil(
      MaterialPageRoute(builder: (context) => MyHomePage()),
          (Route<dynamic> route) => false,
    );
    return false;
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: _onWillPop,
      child: Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: Text(widget.title),
          leading: IconButton(
            icon: Icon(Icons.arrow_back),
            onPressed: _onWillPop,
          ),
        ),
        body: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              const SizedBox(height: 15,),
              Row(
                mainAxisSize: MainAxisSize.max,
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: <Widget> [
                  const SizedBox(
                    width: 250,
                    child: Flexible(
                      child: TextField(
                        decoration: InputDecoration(
                          border: OutlineInputBorder(),
                          labelText: '제목',
                        ),
                      ),
                    ),
                  ),
                  const SizedBox(width: 20,),
                  ElevatedButton(
                    onPressed: (){
                      // 일기 저장 로직 추가
                    },
                    child: const Text('저장'),
                  ),
                ],
              ),
              const SizedBox(height: 20,),
              Row(
                  mainAxisSize: MainAxisSize.max,
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  children: <Widget> [
                    SizedBox(
                        width: 250,
                        child: Text(
                          _selectedDate != null
                              ? "날짜: ${_selectedDate.toString().split(" ")[0]}"
                              : "날짜를 선택하시오.",
                        )
                    ),
                    ElevatedButton(
                      onPressed: (){
                        showDatePicker(
                          context: context,
                          initialDate: DateTime.now(),
                          firstDate: DateTime(2000),
                          lastDate: DateTime(2100),
                        ).then((selectedDate) {
                          setState(() {
                            _selectedDate = selectedDate;
                          });
                        });
                      },
                      child: const Text('날짜 선택'),
                    ),
                  ]
              ),
              const SizedBox(height: 20,),
              Row(
                mainAxisSize: MainAxisSize.max,
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: <Widget>[
                  SizedBox(
                    width: 250,
                    child: Text(
                      _currentPosition != null
                          ? "위치: ${_currentAddress ?? '주소를 가져오는 중...'}"
                          : "위치를 가져오는 중...",
                      style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                    ),
                  ),
                  ElevatedButton(
                    onPressed: _getCurrentLocation,
                    child: const Text('위치 갱신'),
                  ),
                ],
              ),
              const SizedBox(height: 20,),
              const Row(
                mainAxisSize: MainAxisSize.max,
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: <Widget>[
                  SizedBox(width: 50,),
                  Expanded(
                      child: TextField(
                        keyboardType: TextInputType.multiline,
                        maxLines: 20,
                        decoration: InputDecoration(
                          border: OutlineInputBorder(),
                          labelText: '일기 내용',
                          alignLabelWithHint: true,
                        ),
                      )
                  ),
                  SizedBox(width: 50,),
                ],
              ),
              const SizedBox(height: 20,),
              Row(
                  mainAxisSize: MainAxisSize.max,
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  children: <Widget>[
                    const Text(
                        "사진 선택"
                    ),
                    ElevatedButton(onPressed: () async {
                      multiImage = await picker.pickMultiImage();
                      setState(() {
                        //multiImage를 통해 갤러리에서 가지고 온 사진들은 리스트 변수에 저장되므로 addAll()을 사용해서 images와 multiImage 리스트를 합쳐줍니다.
                        images.addAll(multiImage);
                      });
                    }, child: const Text('사진 선택'))
                  ]
              ),
              const SizedBox(height: 20,),
              Container(
                margin: const EdgeInsets.all(10),
                child: GridView.builder(padding: const EdgeInsets.all(0),
                  shrinkWrap: true,
                  itemCount: images.length, //보여줄 item 개수. images 리스트 변수에 담겨있는 사진 수 만큼.
                  gridDelegate:
                  const SliverGridDelegateWithFixedCrossAxisCount(
                    crossAxisCount: 3, //1 개의 행에 보여줄 사진 개수
                    childAspectRatio:
                    1 / 1, //사진 의 가로 세로의 비율
                    mainAxisSpacing: 10, //수평 Padding
                    crossAxisSpacing: 10, //수직 Padding
                  ),
                  itemBuilder: (BuildContext context, int index) {
                    // 사진 오른 쪽 위 삭제 버튼을 표시하기 위해 Stack을 사용함
                    return Stack(
                      alignment: Alignment.topRight,
                      children: [
                        Container(
                          decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(5),
                              image:
                              DecorationImage(
                                  fit: BoxFit.cover,  //사진 크기를 Container 크기에 맞게 조절
                                  image: FileImage(File(images[index]!.path   // images 리스트 변수 안에 있는 사진들을 순서대로 표시함
                                  ))
                              )
                          ),
                        ),
                        Container(
                            decoration: BoxDecoration(
                              color: Colors.black,
                              borderRadius:
                              BorderRadius.circular(5),
                            ),
                            //삭제 버튼
                            child: IconButton(
                              padding: EdgeInsets.zero,
                              constraints: const BoxConstraints(),
                              icon: const Icon(Icons.close,
                                  color: Colors.white,
                                  size: 15),
                              onPressed: () {
                                //버튼을 누르면 해당 이미지가 삭제됨
                                setState(() {
                                  images.remove(images[index]);
                                });
                              },
                            )
                        )
                      ],
                    );
                  },
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
