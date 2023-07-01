package hu.popoapps.wearable_flutter_fragment_application.core

import hu.popoapps.wearable_flutter_fragment_application.data.DismissibleApplicationConfiguration
import io.flutter.embedding.android.FlutterFragment
import io.flutter.plugin.common.MethodChannel

open class DismissibleApplicationConfigurator(
    val configuration: DismissibleApplicationConfiguration,
    val flutterComponentsProvider: FlutterComponentsProvider = FlutterComponentsProvider(configuration.context)
) {
    var flutterFragment: FlutterFragment? = null
        protected set
    private var fragmentMethodChannel: MethodChannel? = null

    companion object {
        private const val FRAGMENT_CHANNEL = "WearableFragmentApplication"
    }

    open fun setupFragment() {
        attachFlutterFragment()
        setUpMethodChannel()
    }

    open fun attachFlutterFragment() {
        flutterFragment =
            flutterComponentsProvider.getCachedFlutterFragment(configuration.supportFragmentManager).run {
                this ?: flutterComponentsProvider.buildCachedFlutterFragment().apply {
                    configuration.supportFragmentManager
                        .beginTransaction()
                        .add(configuration.dismissibleLayout.id, this, flutterComponentsProvider.flutterFragmentTag)
                        .addToBackStack(flutterComponentsProvider.flutterFragmentTag)
                        .commit()
                }
            }
    }

    open fun destroyEngine(){
        flutterComponentsProvider.flutterEngine.destroy()
    }

    private fun setUpMethodChannel() {
        fragmentMethodChannel = fragmentMethodChannel ?: MethodChannel(
            flutterComponentsProvider.flutterEngine.dartExecutor.binaryMessenger,
            FRAGMENT_CHANNEL
        ).apply {
            setMethodCallHandler { call, _ ->
                if (call.method == "hasPageLeft") {
                    configuration.dismissibleLayout.setDismissibleState(!(call.arguments as Boolean))
                }
            }
        }
    }
}
