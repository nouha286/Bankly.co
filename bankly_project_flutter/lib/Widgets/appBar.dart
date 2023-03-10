import 'package:flutter/material.dart';

Widget appBar(Widget left, String title, Widget right) {
  return Container(
    color: Colors.white,
    child: Padding(
      padding: EdgeInsets.symmetric(horizontal: 15, vertical: 20),
      child: Row(mainAxisAlignment: MainAxisAlignment.spaceBetween, children: [
        left,
        Text(
          '$title',
          style: TextStyle(
              color: Colors.black54, fontWeight: FontWeight.bold, fontSize: 25),
        ),
        right
      ]),
    ),
  );
}
