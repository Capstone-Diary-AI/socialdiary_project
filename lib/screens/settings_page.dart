//설정 페이지

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'theme_provider.dart';  // 경로가 맞는지 확인하세요

class SettingsPage extends StatelessWidget {
  final List<String> fonts = ['Roboto', 'Lobster', 'Pacifico', 'DancingScript'];
  final List<double> fontSizes = [12.0, 14.0, 16.0, 18.0, 20.0, 22.0, 24.0, 26.0, 28.0, 30.0];

  @override
  Widget build(BuildContext context) {
    final themeProvider = Provider.of<ThemeProvider>(context);

    return Scaffold(
      appBar: AppBar(
        title: Text("설정"),
        leading: IconButton(
          icon: Icon(Icons.arrow_back),
          onPressed: () => Navigator.of(context).pop(),
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            SwitchListTile(
              title: Text('다크 모드', style: Theme.of(context).textTheme.bodyLarge),  // bodyText1 -> bodyLarge
              value: themeProvider.themeData.brightness == Brightness.dark,
              onChanged: (value) {
                themeProvider.toggleTheme(value);
              },
            ),
            SizedBox(height: 20),
            Text("글꼴 선택", style: Theme.of(context).textTheme.bodyLarge),  // bodyText1 -> bodyLarge
            DropdownButton<String>(
              value: themeProvider.fontFamily,
              items: fonts.map((String font) {
                return DropdownMenuItem<String>(
                  value: font,
                  child: Text(font, style: TextStyle(color: Theme.of(context).textTheme.bodyLarge?.color)),  // bodyText1 -> bodyLarge
                );
              }).toList(),
              onChanged: (String? newFont) {
                if (newFont != null) {
                  themeProvider.updateFontFamily(newFont);
                }
              },
              style: TextStyle(
                color: Theme.of(context).textTheme.bodyLarge?.color,  // bodyText1 -> bodyLarge
                fontSize: Theme.of(context).textTheme.bodyLarge?.fontSize,  // bodyText1 -> bodyLarge
              ),
            ),

            SizedBox(height: 20),
            Text("글자 크기 선택", style: Theme.of(context).textTheme.bodyLarge),  // bodyText1 -> bodyLarge
            DropdownButton<double>(
              value: themeProvider.fontSize,
              items: fontSizes.map((double size) {
                return DropdownMenuItem<double>(
                  value: size,
                  child: Text(size.toString(), style: Theme.of(context).textTheme.bodyLarge),  // bodyText1 -> bodyLarge
                );
              }).toList(),
              onChanged: (double? newSize) {
                if (newSize != null) {
                  themeProvider.updateFontSize(newSize);
                }
              },
            ),
          ],
        ),
      ),
    );
  }
}
