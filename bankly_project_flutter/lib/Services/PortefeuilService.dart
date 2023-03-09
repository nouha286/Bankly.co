import 'dart:convert';
import 'package:bankly_project/Services/UserService.dart';
import 'package:http/http.dart' as http;

class PortefeuilService {
  Future<List> getPortef() async {
    String url = "http://10.0.2.2:8084/Management/portefeuil/AllPortefeuil";
    var response =
        await http.get(Uri.parse(url), headers: {"Accept": "application/json"});
    final data = jsonDecode(response.body);

    print("hi");
    if (response.statusCode == 200) {
      print(data.toString());
      return data;
    } else {
      print("error!!!!!");
    }
    return [];
  }

  Future getOnePortefeuilleByUser() async {
    UserService userService = UserService();
      var user = await userService.getEmailOfUserConnected();
      
      


   

    String url =
        "http://10.0.2.2:8084/Management/portefeuil/findReferenceOfPortefeuilByProprietaire/$user";
    var response =
        await http.get(Uri.parse(url), headers: {"Accept": "application/json"});
    final data = jsonDecode(response.body);

    print('hi i m the portefeuil $user ');

    if (response.statusCode == 200) {
      print(data.toString());
      return data;
    } else {
      print("error!!!!!");
    }
    return null;
  }
}
