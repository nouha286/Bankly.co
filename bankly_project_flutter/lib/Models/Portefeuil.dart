import 'dart:ffi';

import 'package:flutter/material.dart';

class Portefeuil {
  String message;

  int id;
  String reference;
  String proprietaire;
  double solde;

  Portefeuil(
      this.id, this.proprietaire, this.reference, this.solde, this.message);

  // factory Portefeuil.fromMap(Map portefeuilMap) {
  //   return Portefeuil(
  //        portefeuilMap['id'],
  //       portefeuilMap['proprietaire'],
  //        portefeuilMap['reference'],
  //       portefeuilMap['solde'],
  //        portefeuilMap['message']);
  // }
}
