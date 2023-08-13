package hu.popoapps.wearable_flutter_fragment_application.activity

import android.os.Bundle
import android.view.View
import hu.popoapps.wearable_flutter_fragment_application.layout.DismissibleLayout

/**
 * Sets a basic content view to use the plugin. No modifications necessary.
 */
open class WearableFragmentActivity : WearableCoreFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DismissibleLayout(this).apply {
            id = View.generateViewId()
            setContentView(this)
            setupDismissableApplicationConfigurator(this)
        }
    }
}