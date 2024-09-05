// lib/services/auth_service.dart

import 'package:http/http.dart' as http;
import 'dart:convert';
import 'package:bcrypt/bcrypt.dart';

class AuthService {
  Future<bool> login(String email, String password) async {
    final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

    final response = await http.post(
      Uri.parse('https://yourserver.com/api/login'), // 서버 URL을 설정
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, String>{
        'email': email,
        'password': hashedPassword,
      }),
    );

    if (response.statusCode == 200) {
      // 로그인 성공 처리
      return true;
    } else {
      // 로그인 실패 처리
      return false;
    }
  }

  Future<bool> signup(String email, String password) async {
    final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

    final response = await http.post(
      Uri.parse('https://yourserver.com/api/signup'), // 서버 URL을 설정
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, String>{
        'email': email,
        'password': hashedPassword,
      }),
    );

    if (response.statusCode == 200) {
      // 회원가입 성공 처리
      return true;
    } else {
      // 회원가입 실패 처리
      return false;
    }
  }
}
