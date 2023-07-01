package hu.popoapps.wearable_flutter_fragment_application_example_customization

import android.content.Intent
import android.os.Bundle
import hu.popoapps.wearable_flutter_fragment_application.activity.WearableFragmentActivity
import io.flutter.plugin.common.MethodChannel

class WearableCustomFragmentActivity: WearableFragmentActivity() {
    var customMethodChannel: MethodChannel? = null


    // View is already set
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpCustomMethodChannel()
    }

    private fun setUpCustomMethodChannel() {
        customMethodChannel = MethodChannel(
            dismissibleApplicationConfigurator.flutterComponentsProvider.flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).apply {
            setMethodCallHandler { call, _ ->
                when (call.method) {
                    "openAndroidActivity" -> {
                        startActivity(Intent(this@WearableCustomFragmentActivity, SimpleAndroidActivity::class.java))
                    }
                    "openFlutterFragmentActivity" -> {
                        startActivity(Intent(this@WearableCustomFragmentActivity, SimpleFlutterFragmentActivity::class.java))
                    }
                    else -> {}
                }
            }
        }
    }

    companion object {
        private const val CHANNEL = "ChangeActivity"
    }
}