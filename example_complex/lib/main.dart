import 'package:flutter/material.dart';

import 'package:swipeable_page_route/swipeable_page_route.dart';
import 'package:wearable_flutter_fragment_application/dismissible_container.dart';
import 'package:wearable_flutter_fragment_application/wearable_fragment_application_observers.dart';

import 'buttons.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Wearable Flutter Fragment Application Example',
        theme: ThemeData(),
        navigatorObservers: WearableFragmentApplication().observers,
        home: const NonDismissmissibleExamplePage());
  }
}

class NonDismissmissibleExamplePage extends StatelessWidget {
  const NonDismissmissibleExamplePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: Colors.deepPurple,
        body: Center(
            child: SingleChildScrollView(
                child: Padding(
                    padding: const EdgeInsets.fromLTRB(8, 32, 8, 32),
                    child: Column(
                      mainAxisSize: MainAxisSize.min,
                      children: const [
                        Text(
                          "This is a non-dismissible page",
                          style: TextStyle(fontSize: 8),
                        ),
                        PageContent()
                      ],
                    )))));
  }
}

class DismissibleExamplePage extends StatefulWidget {
  const DismissibleExamplePage({super.key});

  @override
  State<StatefulWidget> createState() => _DismissibleExamplePage();
}

class _DismissibleExamplePage extends State<DismissibleExamplePage> {
  bool isDismissible = true;

  @override
  Widget build(BuildContext context) {
    return DismissibleContainer(
        isDismissible: isDismissible,
        child: Scaffold(
            backgroundColor: isDismissible ? Colors.green : Colors.orange,
            body: Center(
                child: SingleChildScrollView(
                    child: Padding(
                        padding: const EdgeInsets.fromLTRB(8, 32, 8, 32),
                        child: Column(
                          mainAxisSize: MainAxisSize.min,
                          children: [
                            const Text(
                              "This is a dismissible page",
                              style: TextStyle(fontSize: 8),
                            ),
                            const PageContent(),
                            CustomButton(
                                onPressed: () {
                                  setState(() {
                                    isDismissible = !isDismissible;
                                  });
                                },
                                title: "Change dismissible state",
                                theme: isDismissible ? Colors.orange : Colors.green,
                                image: const Icon(Icons.change_circle_outlined, color: Colors.white)),
                            Text("The page is currently${isDismissible ? "" : " not"} dismissible",
                                style: const TextStyle(fontSize: 8))
                          ],
                        ))))));
  }
}

class PageContent extends StatelessWidget {
  const PageContent({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(mainAxisSize: MainAxisSize.min, children: [
      CustomButton(
          onPressed: () {
            Navigator.of(context).push(SwipeablePageRoute(builder: (context) => const DismissibleExamplePage()));
          },
          title: "New Dismissible page",
          image: const Icon(Icons.open_in_new, color: Colors.white)),
      CustomButton(
          onPressed: () {
            Navigator.of(context).push(SwipeablePageRoute(builder: (context) => const NonDismissmissibleExamplePage()));
          },
          title: "New Non-dismissible page",
          image: const Icon(Icons.open_in_new, color: Colors.white)),
      CustomButton(
          onPressed: () {
            Navigator.of(context).pop();
          },
          title: "Pop back manually",
          theme: Colors.red,
          image: const Icon(Icons.arrow_back, color: Colors.white)),
    ]);
  }
}
