
import 'package:flutter/material.dart';

import 'carte.dart';

class carteOperation extends StatelessWidget {
  Map<String, dynamic> o;

  carteOperation(this.o);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.only(bottom: 20),
      child: carte(
          child: Row(mainAxisAlignment: MainAxisAlignment.start, children: [
        Icon(
          Icons.money_off_csred_rounded,
          size: 40,
          color: Colors.pink,
        ),
        SizedBox(
          width: 20,
        ),
        Expanded(
            child: Column(
          children: [Text(this.o['montant']), Text('data')],
        ))
      ])),
    );
  }
}
