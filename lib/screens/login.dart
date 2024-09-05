// lib/screens/login.dart
import 'package:flutter/material.dart';
import 'my_home_page.dart';
import 'reg_account.dart';
import '../services/auth_service.dart'; // AuthService를 import

class LoginPage extends StatefulWidget {
  const LoginPage({super.key, required this.title});
  final String title;

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  late String id;
  late String password;

  final idController = TextEditingController();
  final pswdController = TextEditingController();

  final AuthService _authService = AuthService(); // AuthService 인스턴스 생성

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              '로그인 정보',
              style: Theme.of(context).textTheme.headlineMedium,
            ),
            const SizedBox(height: 40),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                const SizedBox(width: 20),
                const Text('ID : ', style: TextStyle(fontSize: 20, color: Colors.black)),
                const SizedBox(width: 40),
                SizedBox(
                  width: 250,
                  child: TextField(
                    controller: idController,
                    decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: 'ID',
                    ),
                  ),
                ),
              ],
            ),
            const SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                const Text('비밀번호 : ', style: TextStyle(fontSize: 20, color: Colors.black)),
                const SizedBox(width: 20),
                SizedBox(
                  width: 250,
                  child: TextField(
                    controller: pswdController,
                    obscureText: true,
                    decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      labelText: 'PASSWORD',
                    ),
                  ),
                ),
              ],
            ),
            const SizedBox(height: 40),
            ElevatedButton(
              onPressed: () async {
                id = idController.text;
                password = pswdController.text;

                // 로그인 처리
                bool loginSuccess = await _authService.login(id, password);

                if (loginSuccess) {
                  // 로그인 성공 시 MyHomePage로 이동
                  Navigator.pushReplacement(
                    context,
                    MaterialPageRoute(builder: (context) => MyHomePage()),
                  );
                } else {
                  // 로그인 실패 시 처리
                  print('Login Failed');
                }
              },
              child: const Text('로그인'),
            ),
          ],
        ),
      ),
      floatingActionButton: SizedBox(
        height: 70,
        width: 120,
        child: extendButton(),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
    );
  }

  FloatingActionButton extendButton() {
    return FloatingActionButton.extended(
      onPressed: () {
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => const Reg_AccountPage(title: 'Account Registering Page'),
          ),
        );
      },
      label: const Text("회원가입"),
      isExtended: true,
      icon: const Icon(Icons.add, size: 30),
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
    );
  }
}
