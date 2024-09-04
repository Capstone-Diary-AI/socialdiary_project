import 'package:flutter/material.dart';
import 'package:table_calendar/table_calendar.dart';

class CalendarView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return TableCalendar(
        firstDay: DateTime.utc(2010, 1, 1),
        lastDay: DateTime.utc (2030, 12, 31),
    focusedDay: DateTime.now(),
    );
  }
}
