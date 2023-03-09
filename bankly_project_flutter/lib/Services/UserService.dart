import 'dart:convert';

import 'package:http/http.dart' as http;


class UserService {
  var token = "";
  Future getEmailOfUserConnected() async {
//     final storage = FlutterSecureStorage();
// token = (await storage.read(key: 'token')) ?? '';
    String url = "http://10.0.2.2:8083/Management/User/getEmailOfUserConnected";
    var response = await http.get(Uri.parse(url), headers: {
      "Accept": "application/json",
      "AUTHORIZATION": "Bearer $token"
    });
    final data = jsonDecode(response.body);

    print("hi i'm the user connected");
    if (response.statusCode == 200) {
      print(data.toString());
      return data;
    } else {
      var res = response.statusCode;
      print("error!!!!!$res");
    }
    return null;
  }

  Future login() async {
    String url = "http://10.0.2.2:8083/Management/User/login";
    var response = await http.post(Uri.parse(url),
        headers: {"Accept": "application/json"},
        body: {"email": "nouhailaelaalami286", "password": "AZERTY"});
    final data = jsonDecode(response.body);
    print("hello i'm the tokens return");
    if (response.statusCode == 200) {
      this.token = data['access_token'];

      // final storage = FlutterSecureStorage();
      // await storage.write(key: 'token', value: token);
      print(data.toString());

      return data;
    } else {
      print("error!!!!!");
    }
    return null;
  }
}
