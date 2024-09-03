import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'screens/theme_provider.dart';
import 'screens/my_home_page.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  SharedPreferences prefs = await SharedPreferences.getInstance();
  bool isDarkMode = prefs.getBool('isDarkMode') ?? false;
  String fontFamily = prefs.getString('fontFamily') ?? 'Roboto';
  double fontSize = prefs.getDouble('fontSize') ?? 14.0;

  ThemeData initialTheme = isDarkMode ? ThemeData.dark() : ThemeData.light();
  ThemeProvider themeProvider = ThemeProvider(initialTheme.copyWith(
    textTheme: TextTheme(
      bodyLarge: TextStyle(fontFamily: fontFamily, fontSize: fontSize),
      bodyMedium: TextStyle(fontFamily: fontFamily, fontSize: fontSize),
    ),
  ), fontFamily, fontSize);
  await themeProvider.loadTheme();

  runApp(
    ChangeNotifierProvider(
      create: (context) => themeProvider,
      child: MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final themeProvider = Provider.of<ThemeProvider>(context);

    return MaterialApp(
      title: 'Flutter Demo',
      theme: themeProvider.themeData,
      home: MyHomePage(),
    );
  }
}
