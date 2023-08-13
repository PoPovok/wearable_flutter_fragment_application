package hu.popoapps.wearable_flutter_fragment_application_example_customization

import android.os.Bundle
import hu.popoapps.wearable_flutter_fragment_application.activity.WearableCoreFragmentActivity
import hu.popoapps.wearable_flutter_fragment_application.core.DismissibleApplicationConfigurator
import hu.popoapps.wearable_flutter_fragment_application.core.FlutterComponentsProvider
import hu.popoapps.wearable_flutter_fragment_application.layout.DismissibleLayout
import hu.popoapps.wearable_flutter_fragment_application_example_customization.databinding.ActivitySimpleFlutterFragmentBinding

/**
 * Flutter activity to be navigated to from the application
 */
class SimpleFlutterFragmentActivity : WearableCoreFragmentActivity() {

    /**
     * Example of setting a custom view for the android wrapper
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivitySimpleFlutterFragmentBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            setupDismissableApplicationConfigurator(this.fragmentSwipeable)
        }
    }

    /**
     * Setting a custom flutter fragment for the activity
     */
    override fun setDismissibleApplicationConfigurator(dismissibleLayout: DismissibleLayout) {
        dismissibleApplicationConfigurator = DismissibleApplicationConfigurator(
            getDefaultDismissableApplicationConfiguration(dismissibleLayout),
            FlutterComponentsProvider(context = this, entrypointName = "anotherActivity")
        )
    }
}