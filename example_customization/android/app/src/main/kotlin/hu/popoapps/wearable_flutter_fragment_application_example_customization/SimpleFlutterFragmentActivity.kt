package hu.popoapps.wearable_flutter_fragment_application_example_customization

import android.os.Bundle
import hu.popoapps.wearable_flutter_fragment_application.activity.WearableCoreFragmentActivity
import hu.popoapps.wearable_flutter_fragment_application.core.DismissibleApplicationConfigurator
import hu.popoapps.wearable_flutter_fragment_application.core.FlutterComponentsProvider
import hu.popoapps.wearable_flutter_fragment_application.layout.DismissibleLayout
import hu.popoapps.wearable_flutter_fragment_application_example_customization.databinding.ActivitySimpleFlutterFragmentBinding

class SimpleFlutterFragmentActivity: WearableCoreFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivitySimpleFlutterFragmentBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
            setupDismissableApplicationConfigurator(this.fragmentSwipeable)
        }
    }

    override fun setDismissibleApplicationConfigurator(dismissibleLayout: DismissibleLayout) {
        dismissibleApplicationConfigurator = DismissibleApplicationConfigurator(
            getDefaultDismissableApplicationConfiguration(dismissibleLayout),
            FlutterComponentsProvider(this, "anotherActivity")
        )
    }
}