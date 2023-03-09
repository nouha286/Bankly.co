
import 'package:bankly_project/Services/OperarionService.dart';
import 'package:bankly_project/Widgets/appBar.dart';
import 'package:bankly_project/Widgets/carteOperation.dart';
import 'package:bankly_project/Widgets/cartePortefeuil.dart';
import 'package:flutter/material.dart';


class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
     OperationService operationService = OperationService();
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: Size.fromHeight(100.0),
        child: SafeArea(
            child: appBar(
                Icon(
                  Icons.notes,
                  color: Colors.blueGrey,
                ),
                'Bankly',
                Icon(
                  Icons.account_balance_wallet,
                  color: Colors.blueGrey,
                ))),
      ),
      body: Container(
        child: Column(
          children: [
            SizedBox(
              height: 25,
            ),
            SingleChildScrollView(
              scrollDirection: Axis.horizontal,
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  cartePortefeuil(),
                ],
              ),
            ),
            SizedBox(
              height: 15,
            ),
            Padding(
              padding: EdgeInsets.symmetric(horizontal: 25),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text('Operations sorted by :'),
                  Row(
                    children: [
                      Text(
                        'day',
                        style: TextStyle(color: Colors.black45),
                      ),
                      Icon(Icons.keyboard_arrow_down, color: Colors.black45)
                    ],
                  )
                ],
              ),
            ),
            SizedBox(
              height: 15,
            ),
            Padding(
              padding: EdgeInsets.all(20),
              child: FutureBuilder<List>(
                  future: operationService.getOperation(),
                  builder: (BuildContext ctx, AsyncSnapshot<List> snapshot) {
                    return snapshot.hasData
                        ? ListView.builder(
                            shrinkWrap: true,
                            scrollDirection: Axis.vertical,
                            itemCount: snapshot.data!.length,
                            itemBuilder: (BuildContext context, index) {
                              return carteOperation(snapshot.data![index]);
                            },
                          )
                        : Center(
                            child: CircularProgressIndicator(),
                          );
                  }),
            ),
          ],
        ),
      ),
    );
  }
}
