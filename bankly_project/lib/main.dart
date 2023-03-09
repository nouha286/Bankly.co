import 'dart:convert';


import 'package:bankly_project/Pages/Home.dart';
import 'package:bankly_project/Services/OperarionService.dart';
import 'package:bankly_project/Services/PortefeuilService.dart';
import 'package:bankly_project/Services/UserService.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;


void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
    init();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Bankly",
      home: HomePage(),
      theme: ThemeData.fallback(),
    );
    ;
  }

  void init() async {
    UserService userService = UserService();
    OperationService operationService = OperationService();
    PortefeuilService portefeuilService = PortefeuilService();
    await userService.login();


    

    
  }
}
