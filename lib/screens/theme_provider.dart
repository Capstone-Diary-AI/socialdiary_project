//설정에서 필요한 테마 관련 코드
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class ThemeProvider extends ChangeNotifier {
  ThemeData _themeData;
  String _fontFamily;
  double _fontSize;
  SharedPreferences? _prefs;

  ThemeProvider(this._themeData, this._fontFamily, this._fontSize);

  ThemeData get themeData => _themeData;
  String get fontFamily => _fontFamily;
  double get fontSize => _fontSize;

  void toggleTheme(bool isDark) async {
    _themeData = isDark ? _buildDarkTheme() : _buildLightTheme();
    _prefs ??= await SharedPreferences.getInstance();
    _prefs!.setBool('isDarkMode', isDark);
    notifyListeners();
  }

  Future<void> loadTheme() async {
    _prefs = await SharedPreferences.getInstance();
    bool isDarkMode = _prefs!.getBool('isDarkMode') ?? false;
    _fontFamily = _prefs!.getString('fontFamily') ?? 'Roboto';
    _fontSize = _prefs!.getDouble('fontSize') ?? 14.0;
    _themeData = isDarkMode ? _buildDarkTheme() : _buildLightTheme();
    _updateTextTheme();
    notifyListeners();
  }

  void updateFontFamily(String fontFamily) async {
    _fontFamily = fontFamily;
    _updateTextTheme();
    _prefs ??= await SharedPreferences.getInstance();
    _prefs!.setString('fontFamily', fontFamily);
    notifyListeners();
  }

  void updateFontSize(double fontSize) async {
    _fontSize = fontSize;
    _updateTextTheme();
    _prefs ??= await SharedPreferences.getInstance();
    _prefs!.setDouble('fontSize', fontSize);
    notifyListeners();
  }

  void _updateTextTheme() {
    _themeData = _themeData.copyWith(
      textTheme: TextTheme(
        bodyLarge: TextStyle(  // bodyText1 대신
          fontFamily: _fontFamily,
          fontSize: _fontSize,
          color: _themeData.brightness == Brightness.dark ? Colors.white : Colors.black,
        ),
        bodyMedium: TextStyle(  // bodyText2 대신
          fontFamily: _fontFamily,
          fontSize: _fontSize,
          color: _themeData.brightness == Brightness.dark ? Colors.white : Colors.black,
        ),
      ),
    );
  }

  ThemeData _buildLightTheme() {
    return ThemeData.light().copyWith(
      primaryColor: Colors.blue,
      hintColor: Colors.blueAccent,
      textTheme: TextTheme(
        headlineSmall: TextStyle(color: Colors.black),  // headline6 대신 메인 타이틀용 스타일
        bodyLarge: TextStyle(color: Colors.black, fontFamily: _fontFamily, fontSize: _fontSize),  // bodyText1 대신
        bodyMedium: TextStyle(color: Colors.black, fontFamily: _fontFamily, fontSize: _fontSize),  // bodyText2 대신
      ),
    );
  }

  ThemeData _buildDarkTheme() {
    return ThemeData.dark().copyWith(
      primaryColor: Colors.blueGrey,
      hintColor: Colors.cyan,
      textTheme: TextTheme(
        headlineSmall: TextStyle(color: Colors.white),  // headline6 대신
        bodyLarge: TextStyle(color: Colors.white, fontFamily: _fontFamily, fontSize: _fontSize),  // bodyText1 대신
        bodyMedium: TextStyle(color: Colors.white, fontFamily: _fontFamily, fontSize: _fontSize),  // bodyText2 대신
      ),
    );
  }
}
