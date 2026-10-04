import 'package:flutter/material.dart';
import 'anotherapp.dart';
import 'mainapp.dart';

void main() {
  runApp(const MainApp());
}

/// Dont't forget to @pragma each custom entrypoint or it won't be compiled
@pragma("vm:entry-point")
@pragma("anotherActivity")
void anotherActivity(){
  runApp(const AnotherApp());
}
