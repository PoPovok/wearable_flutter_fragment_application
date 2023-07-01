package hu.popoapps.wearable_flutter_fragment_application.activity

import androidx.fragment.app.FragmentActivity
import hu.popoapps.wearable_flutter_fragment_application.data.DismissibleApplicationConfiguration
import hu.popoapps.wearable_flutter_fragment_application.core.DismissibleApplicationConfigurator
import hu.popoapps.wearable_flutter_fragment_application.layout.DismissibleLayout

open class WearableCoreFragmentActivity : FragmentActivity() {
    protected lateinit var dismissibleApplicationConfigurator: DismissibleApplicationConfigurator

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

    open fun setupDismissableApplicationConfigurator(dismissibleLayout: DismissibleLayout) {
        setDismissibleApplicationConfigurator(dismissibleLayout)
        dismissibleApplicationConfigurator.setupFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissibleApplicationConfigurator.destroyEngine()
    }
}