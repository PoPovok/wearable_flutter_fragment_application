# Wearable Flutter Fragment Application

Flutter plugin that prevents the common issues that are present while making a flutter based application for Wear OS. It also provides a widget to make your pages dynamically dismissible.

## Description 

The following issues are fixed by the plugin:
- Black screen when cancelling on dismiss on Wear OS 2.0

| *without plugin* | *with plugin* |
|:-:|:-:|
| ![Black screen exapmle](https://popovok.github.io/markdown_files/wearable_flutter_fragment_application/wear_close_flutter_activity.gif) | ![Black screen fix exapmle](https://popovok.github.io/markdown_files/wearable_flutter_fragment_application/wear_close_flutter_fragment.gif) |

- No way to navigate back on flutter pages

| *without plugin* | *with plugin* |
|:-:|:-:|
| ![Navigation exapmle](https://popovok.github.io/markdown_files/wearable_flutter_fragment_application/wear_navigate_flutter_activity.gif) | ![Navigation fix exapmle](https://popovok.github.io/markdown_files/wearable_flutter_fragment_application/wear_navigate_flutter_fragment.gif) |

It uses a full screen flutter fragment and a special layout to make the application working as intended, examples can be found on the git repository to help you customize your application.

## Examples:
### example: 
Basic example on how to make Flutter application with the plugin.
### example_customization: 
Helps you to extend the android wrapper of your app, shows you a basic method channel implementation.

![Customization exapmle](https://popovok.github.io/markdown_files/wearable_flutter_fragment_application/wear_customization_example.gif)

### example_complex: 
Presents you the DismissibleContainer

![Complex exapmle](https://popovok.github.io/markdown_files/wearable_flutter_fragment_application/wear_complex_example.gif)

## Setup:
### Android

Change the default activity in your manifest to the one that the plugin provides, or the inherited one that you make.

```kotlin
<activity
	android:name="hu.popoapps.wearable_flutter_fragment_application.activity.WearableFragmentActivity"
	android:exported="true">
	<intent-filter>
		<action android:name="android.intent.action.MAIN" />
		<category android:name="android.intent.category.LAUNCHER" />
	</intent-filter>
</activity>
```

### Flutter
You must include `WearableFragmentApplication().observers` in your `MaterialApp.navigatorObservers`

```dart
MaterialApp(
        title: 'Wearable Flutter Fragment Application Example',
        theme: ThemeData(),
        navigatorObservers: WearableFragmentApplication().observers,
        home: Container());
```

### Important note
Don't extend the function of `MainActivity : FlutterAcivity()` (eg. for `MethodChannel`), the app won't use it. You can inherit from the Android wrapper activity and extend its functions yourself:
- WearableCoreFragmentActivity: does not set content view
- WearableFragmentActivity: sets a basic content view

## Usage:
To use this plugin, add `wearable_flutter_fragment_application` as a dependency in your `pubspec.yaml` file. See: https://pub.dev/packages/wearable_flutter_fragment_application/install

I use [SlideableNavigator](https://pub.dev/packages/swipeable_page_route) for the examples, because the plugin does not yet have a wear OS styled one right now.

```dart
class MyApp extends StatelessWidget {
  const MyApp({super.key});

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

  const ExamplePage(this.background, {super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: background,
        body: Center(
            child: CustomButton(
                onPressed: () =>
                    Navigator.of(context).push(SwipeablePageRoute(
                        builder: (context) => ExamplePage(generateColor()))),
                title: "New page",
                image: const Icon(Icons.open_in_new, color: Colors.white))));
  }
}
```

## Support
- Min. System requirement is Android 7.1 - Wear OS 2.0

## Tested on
- Moto 360 gen 2 - Android 7.1 - Wear OS 2.0

## TODO: 
- Wear OS style page route
