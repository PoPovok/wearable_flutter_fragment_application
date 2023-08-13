package hu.popoapps.wearable_flutter_fragment_application.activity

import androidx.fragment.app.FragmentActivity
import hu.popoapps.wearable_flutter_fragment_application.data.DismissibleApplicationConfiguration
import hu.popoapps.wearable_flutter_fragment_application.core.DismissibleApplicationConfigurator
import hu.popoapps.wearable_flutter_fragment_application.layout.DismissibleLayout

/**
 * Inheriting from this class is necessary to use the plugin.
 * The descendants must set a content view that implements
 * hu.popoapps.wearable_flutter_fragment_application.layout.DismissibleLayout
 */
open class WearableCoreFragmentActivity : FragmentActivity() {
    protected lateinit var dismissibleApplicationConfigurator: DismissibleApplicationConfigurator

    /**
     * Initializes dismissibleApplicationConfigurator with a default configuration
     * @param dismissibleLayout The specified layout in this plugin that must be included in your view.
     */
    open fun setDismissibleApplicationConfigurator(dismissibleLayout: DismissibleLayout) {
        dismissibleApplicationConfigurator =
            DismissibleApplicationConfigurator(getDefaultDismissableApplicationConfiguration(dismissibleLayout))
    }

    protected fun getDefaultDismissableApplicationConfiguration(dismissibleLayout: DismissibleLayout): DismissibleApplicationConfiguration {
        return DismissibleApplicationConfiguration(
            dismissibleLayout = dismissibleLayout,
            supportFragmentManager = supportFragmentManager,
            context = this
        )
    }

    /**
     * It can be used on the onCreate method of the descendants.
     * Sets up the core of the plugin.
     * @param dismissibleLayout The specified layout in this plugin that must be included in your view.
     */
    open fun setupDismissableApplicationConfigurator(dismissibleLayout: DismissibleLayout) {
        setDismissibleApplicationConfigurator(dismissibleLayout)
        dismissibleApplicationConfigurator.setupFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissibleApplicationConfigurator.destroyEngine()
    }
}