import 'package:flutter_test/flutter_test.dart';
import 'package:wearable_flutter_fragment_application/wearable_flutter_fragment_application.dart';
import 'package:wearable_flutter_fragment_application/wearable_flutter_fragment_application_platform_interface.dart';
import 'package:wearable_flutter_fragment_application/wearable_flutter_fragment_application_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockWearableFlutterFragmentApplicationPlatform
    with MockPlatformInterfaceMixin
    implements WearableFlutterFragmentApplicationPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final WearableFlutterFragmentApplicationPlatform initialPlatform = WearableFlutterFragmentApplicationPlatform.instance;

  test('$MethodChannelWearableFlutterFragmentApplication is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelWearableFlutterFragmentApplication>());
  });

  test('getPlatformVersion', () async {
    WearableFlutterFragmentApplication wearableFlutterFragmentApplicationPlugin = WearableFlutterFragmentApplication();
    MockWearableFlutterFragmentApplicationPlatform fakePlatform = MockWearableFlutterFragmentApplicationPlatform();
    WearableFlutterFragmentApplicationPlatform.instance = fakePlatform;

    expect(await wearableFlutterFragmentApplicationPlugin.getPlatformVersion(), '42');
  });
}
