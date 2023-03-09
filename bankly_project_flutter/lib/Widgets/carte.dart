import 'package:flutter/material.dart';

Widget carte(
    {double width = double.infinity,
    double padding = 20,
    required Widget child}) {
  return Container(
    width: width,
    padding: EdgeInsets.all(padding),
    decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.all(Radius.circular(15)),
        boxShadow: [
          BoxShadow(offset: Offset(7, 3), blurRadius: 25, color: Colors.black12)
        ]),
    child: child,
  );
}
