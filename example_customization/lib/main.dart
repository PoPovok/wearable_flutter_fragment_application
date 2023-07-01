import 'package:flutter/material.dart';
import 'package:wearable_flutter_fragment_application_example_customization/anotherapp.dart';
import 'package:wearable_flutter_fragment_application_example_customization/mainapp.dart';

void main() {
  runApp(const MainApp());
}

/// Dont't forget to @pragma each custom entrypoint or it won't be compiled
@pragma("anotherActivity")
void anotherActivity(){
  runApp(const AnotherApp());
}
