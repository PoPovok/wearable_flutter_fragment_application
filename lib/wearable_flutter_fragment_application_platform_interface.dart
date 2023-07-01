import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'wearable_flutter_fragment_application_method_channel.dart';

abstract class WearableFlutterFragmentApplicationPlatform extends PlatformInterface {
  /// Constructs a WearableFlutterFragmentApplicationPlatform.
  WearableFlutterFragmentApplicationPlatform() : super(token: _token);

  static final Object _token = Object();

  static WearableFlutterFragmentApplicationPlatform _instance = MethodChannelWearableFlutterFragmentApplication();

  /// The default instance of [WearableFlutterFragmentApplicationPlatform] to use.
  ///
  /// Defaults to [MethodChannelWearableFlutterFragmentApplication].
  static WearableFlutterFragmentApplicationPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [WearableFlutterFragmentApplicationPlatform] when
  /// they register themselves.
  static set instance(WearableFlutterFragmentApplicationPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
