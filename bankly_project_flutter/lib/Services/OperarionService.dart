import 'dart:convert';
import 'package:bankly_project/Services/PortefeuilService.dart';
import 'package:http/http.dart' as http;


class OperationService {
  Future<List> getOperation() async {
    PortefeuilService portefeuilService = PortefeuilService();
    dynamic portefeuil = portefeuilService.getOnePortefeuilleByUser();
    String pr = portefeuil['reference'];
    print("$pr");
    String url = "http://10.0.2.2:8080/MyOperations/$pr";
    var response =
        await http.get(Uri.parse(url), headers: {"Accept": "application/json"});
    final data = jsonDecode(response.body);

    print("hi i'm operation");
    if (response.statusCode == 200) {
      print(data.toString());
      return data;
    } else {
      print("error!!!!!");
    }
    return [];
  }
}
