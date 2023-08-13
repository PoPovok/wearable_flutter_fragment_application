import 'package:flutter/material.dart';
import 'package:wearable_flutter_fragment_application/dismissible_container.dart';
import 'package:wearable_flutter_fragment_application/wearable_fragment_application_observers.dart';

class AnotherApp extends StatelessWidget {
  const AnotherApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Wearable Flutter Fragment Application Example',
        theme: ThemeData(),
        navigatorObservers: WearableFragmentApplication().observers,
        home: const DismissibleContainer(child: AnotherAppExamplePage(),));
  }
}

class AnotherAppExamplePage extends StatelessWidget {
  const AnotherAppExamplePage({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      body: Center(
          child: Text("This is a Flutter Fragment Activity!", style: TextStyle(fontSize: 10))));
  }

}