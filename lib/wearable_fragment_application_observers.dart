import 'package:flutter/material.dart';

class WearableFragmentApplication {

  WearableFragmentApplication._(){
    dismissibleContainerObserver = RouteObserver<ModalRoute<void>>();
    observers = [dismissibleContainerObserver];
  }

  static final WearableFragmentApplication _instance = WearableFragmentApplication._();

  factory WearableFragmentApplication() {
    return _instance;
  }

  late final RouteObserver<ModalRoute<void>> dismissibleContainerObserver;
  late final List<NavigatorObserver> observers;
}