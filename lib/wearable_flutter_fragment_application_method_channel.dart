import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'wearable_flutter_fragment_application_platform_interface.dart';

/// An implementation of [WearableFlutterFragmentApplicationPlatform] that uses method channels.
class MethodChannelWearableFlutterFragmentApplication extends WearableFlutterFragmentApplicationPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('wearable_flutter_fragment_application');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
