import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:swipeable_page_route/swipeable_page_route.dart';
import 'package:wearable_flutter_fragment_application/dismissible_container.dart';
import 'package:wearable_flutter_fragment_application/wearable_fragment_application_observers.dart';

import 'dart:math' as math;

import 'buttons.dart';

Color generateColor() {
  final randomNumber = math.Random().nextDouble();
  return Color((randomNumber * 0xFFFFFF).toInt()).withOpacity(1.0);
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Wearable Flutter Fragment Application Example',
        theme: ThemeData(),
        navigatorObservers: WearableFragmentApplication().observers,
        home: DismissibleContainer(isDismissible: true, child: ExamplePage(generateColor())));
  }
}

class ExamplePage extends StatelessWidget {
  final Color background;
  final MethodChannel exampleMethodChannel = const MethodChannel("ChangeActivity");

  const ExamplePage(this.background, {super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: background,
        body: Center(
            child: SingleChildScrollView(
                child: Padding(
                    padding: const EdgeInsets.fromLTRB(8, 32, 8, 32),
                    child: Column(mainAxisSize: MainAxisSize.min, children: [
                      CustomButton(
                          onPressed: () {
                            Navigator.of(context).push(SwipeablePageRoute(
                                builder: (context) => ExamplePage(
                                      generateColor(),
                                    )));
                          },
                          title: "New page",
                          image: const Icon(Icons.open_in_new, color: Colors.white)),
                      CustomButton(
                          onPressed: () {
                            exampleMethodChannel.invokeMethod("openAndroidActivity");
                          },
                          title: "New Android page",
                          image: const Icon(Icons.android, color: Colors.white)),
                      CustomButton(
                          onPressed: () {
                            exampleMethodChannel.invokeMethod("openFlutterFragmentActivity");
                          },
                          title: "New Flutter page",
                          image: const Icon(Icons.flutter_dash, color: Colors.white)),
                    ])))));
  }
}
