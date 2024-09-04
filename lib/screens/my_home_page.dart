// 메인 페이지
// 친구, 메인, 켈린더 페이지 포함

import 'package:flutter/material.dart';
import 'settings_page.dart';
import 'dialogs.dart';
import 'package:table_calendar/table_calendar.dart';
import 'shared_diary_page.dart';

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> with SingleTickerProviderStateMixin {
  late TabController _tabController;
  int state = 0;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 3, vsync: this);
    _tabController.addListener(_setStateOnTabChange);
  }

  void _setStateOnTabChange() {
    setState(() {
      state = _tabController.index;
    });
  }

  Future<bool> _onWillPop() async {
    if (_tabController.index != 1) {
      setState(() {
        _tabController.index = 1;
      });
      return false;
    }
    return true;
  }

  @override
  void dispose() {
    _tabController.removeListener(_setStateOnTabChange);
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: _onWillPop,
      child: DefaultTabController(
        length: 3,
        child: Scaffold(
          appBar: AppBar(
            actions: <Widget>[
              IconButton(
                icon: Icon(Icons.settings),
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => SettingsPage()),
                  );
                },
              ),
            ],
          ),
          body: TabBarView(
            controller: _tabController,
            children: [
              FriendView(),
              MainView(),
              CalendarView(),
            ],
          ),
          bottomNavigationBar: TabBar(
            controller: _tabController,
            tabs: const [
              Tab(icon: Icon(Icons.people), text: '친구'),
              Tab(icon: Icon(Icons.home), text: '메인'),
              Tab(icon: Icon(Icons.calendar_today), text: '캘린더'),
            ],
          ),
          floatingActionButton: FloatingActionButton(
            onPressed: () => showCreateGroupDialog(context, state),
            child: Icon(Icons.add),
            tooltip: '그룹 생성',
          ),
        ),
      ),
    );
  }
}

class MainView extends StatefulWidget {
  @override
  _MainViewState createState() => _MainViewState();
}

class _MainViewState extends State<MainView> {
  final List<String> personalDiaries = List.generate(7, (index) => '개인 일기 ${index + 1}');
  final List<String> sharedDiaries = List.generate(7, (index) => '공유 일기 ${index + 1}');
  String selectedCategory = '모두';

  @override
  Widget build(BuildContext context) {
    List<String> filteredDiaries = [];
    if (selectedCategory == '모두') {
      filteredDiaries = [...personalDiaries, ...sharedDiaries];
    } else if (selectedCategory == '개인') {
      filteredDiaries = personalDiaries;
    } else if (selectedCategory == '공유') {
      filteredDiaries = sharedDiaries;
    }

    return Column(
      children: [
        Padding(
          padding: const EdgeInsets.all(8.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                '카테고리: ',
                style: TextStyle(
                  color: Theme.of(context).textTheme.bodyLarge?.color, // bodyText1 -> bodyLarge
                ),
              ),
              DropdownButton<String>(
                value: selectedCategory,
                onChanged: (String? newValue) {
                  setState(() {
                    selectedCategory = newValue!;
                  });
                },
                items: <String>['모두', '개인', '공유']
                    .map<DropdownMenuItem<String>>((String value) {
                  return DropdownMenuItem<String>(
                    value: value,
                    child: Text(value),
                  );
                }).toList(),
                style: TextStyle(
                  color: Theme.of(context).textTheme.bodyLarge?.color, // bodyText1 -> bodyLarge
                  fontSize: Theme.of(context).textTheme.bodyLarge?.fontSize, // bodyText1 -> bodyLarge
                ),
              ),
            ],
          ),
        ),
        Expanded(
          child: CustomScrollView(
            slivers: [
              SliverAppBar(
                pinned: true,
                expandedHeight: 50.0,
                flexibleSpace: FlexibleSpaceBar(
                  title: Text(
                    '일기 목록',
                    style: Theme.of(context).textTheme.headlineSmall, // headline6 -> headlineSmall
                  ),
                  centerTitle: false,
                  titlePadding: EdgeInsets.all(16.0),
                ),
              ),
              SliverList(
                delegate: SliverChildBuilderDelegate(
                      (context, index) {
                    final diary = filteredDiaries[index];
                    return ListTile(
                      title: Text(
                        (personalDiaries.contains(diary) ? '[개인] ' : '[공유] ') + diary,
                        style: TextStyle(
                          color: Theme.of(context).textTheme.bodyLarge?.color, // bodyText1 -> bodyLarge
                        ),
                      ),
                      onTap: () {
                        if (sharedDiaries.contains(diary)) {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => SharedDiaryPage(diaryTitle: diary),
                            ),
                          );
                        } else {
                          showDialog(
                            context: context,
                            builder: (BuildContext context) {
                              return AlertDialog(
                                title: Text('일기 선택'),
                                content: Text('$diary 선택됨'),
                                actions: <Widget>[
                                  TextButton(
                                    child: Text('확인'),
                                    onPressed: () {
                                      Navigator.of(context).pop();
                                    },
                                  ),
                                ],
                              );
                            },
                          );
                        }
                      },
                    );
                  },
                  childCount: filteredDiaries.length,
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}

class FriendView extends StatelessWidget {
  final List<String> friends = List.generate(40, (index) => '친구 $index');

  @override
  Widget build(BuildContext context) {
    return CustomScrollView(
      slivers: [
        SliverAppBar(
          pinned: true,
          expandedHeight: 50.0,
          flexibleSpace: FlexibleSpaceBar(
            title: Text('친구 목록', style: Theme.of(context).textTheme.headlineSmall), // headline6 -> headlineSmall
            centerTitle: false,
            titlePadding: EdgeInsets.all(16.0),
          ),
        ),
        SliverList(
          delegate: SliverChildBuilderDelegate(
                (context, index) {
              return ListTile(
                title: Text(
                  friends[index],
                  style: TextStyle(
                    color: Theme.of(context).textTheme.bodyLarge?.color, // bodyText1 -> bodyLarge
                  ),
                ),
                onTap: () {
                  showDialog(
                    context: context,
                    builder: (BuildContext context) {
                      return AlertDialog(
                        title: Text('친구 선택'),
                        content: Text('${friends[index]} 선택됨'),
                        actions: <Widget>[
                          TextButton(
                            child: Text('확인'),
                            onPressed: () {
                              Navigator.of(context).pop();
                            },
                          ),
                        ],
                      );
                    },
                  );
                },
              );
            },
            childCount: friends.length,
          ),
        ),
      ],
    );
  }
}

class FriendSelector extends StatelessWidget {
  final List<String> friends = List.generate(40, (index) => '친구 $index');

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 150.0,
      width: double.maxFinite,
      child: ListView.builder(
        itemCount: friends.length,
        itemBuilder: (context, index) {
          return ListTile(
            title: Text(friends[index]),
            onTap: () {
              Navigator.of(context).pop();
            },
          );
        },
      ),
    );
  }
}

class CalendarView extends StatefulWidget {
  @override
  _CalendarViewState createState() => _CalendarViewState();
}

class _CalendarViewState extends State<CalendarView> {
  DateTime _focusedDay = DateTime.now();
  DateTime? _selectedDay;
  Map<DateTime, List<String>> _events = {
    DateTime.utc(2023, 6, 1): ['Event 1', 'Event 2'],
    DateTime.utc(2023, 6, 2): ['Event 3'],
  };
  Map<DateTime, String> _notes = {};

  final TextEditingController _noteController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        TableCalendar(
          firstDay: DateTime.utc(2010, 1, 1),
          lastDay: DateTime.utc(2030, 12, 31),
          focusedDay: _focusedDay,
          selectedDayPredicate: (day) {
            return isSameDay(_selectedDay, day);
          },
          onDaySelected: (selectedDay, focusedDay) {
            setState(() {
              _selectedDay = selectedDay;
              _focusedDay = focusedDay;
              _noteController.text = _notes[selectedDay] ?? '';
            });
          },
          eventLoader: (day) {
            return _events[day] ?? [];
          },
        ),
        const SizedBox(height: 8.0),
        _selectedDay != null
            ? Padding(
          padding: const EdgeInsets.all(8.0),
          child: Column(
            children: [
              Text(
                'Selected Day: ${_selectedDay!.toLocal()}',
                style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 8.0),
              TextField(
                controller: _noteController,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: '메모',
                ),
                maxLines: 2,
              ),
              const SizedBox(height: 8.0),
              ElevatedButton(
                onPressed: () {
                  setState(() {
                    _notes[_selectedDay!] = _noteController.text;
                  });
                },
                child: Text('저장'),
              ),
              const SizedBox(height: 8.0),
              Expanded(child: _buildEventList()),
            ],
          ),
        )
            : Expanded(child: Center(child: Text('날짜를 선택하세요'))),
      ],
    );
  }

  Widget _buildEventList() {
    List<String> selectedEvents = _selectedDay != null && _events[_selectedDay!] != null
        ? _events[_selectedDay!]!
        : [];
    return ListView.builder(
      itemCount: selectedEvents.length,
      itemBuilder: (context, index) {
        return ListTile(
          title: Text(selectedEvents[index]),
        );
      },
    );
  }
}
