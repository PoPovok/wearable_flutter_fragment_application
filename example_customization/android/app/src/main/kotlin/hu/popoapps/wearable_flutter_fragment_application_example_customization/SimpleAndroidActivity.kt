package hu.popoapps.wearable_flutter_fragment_application_example_customization

import android.app.Activity
import android.os.Bundle
import hu.popoapps.wearable_flutter_fragment_application_example_customization.databinding.ActivitySimpleAndroidBinding

/**
 * Android activity to be navigated to from the application
 */
class SimpleAndroidActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivitySimpleAndroidBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }
    }
}