
import 'wearable_flutter_fragment_application_platform_interface.dart';

class WearableFlutterFragmentApplication {
  Future<String?> getPlatformVersion() {
    return WearableFlutterFragmentApplicationPlatform.instance.getPlatformVersion();
  }
}
