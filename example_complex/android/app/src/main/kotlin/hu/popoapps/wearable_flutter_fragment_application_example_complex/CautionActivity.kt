package hu.popoapps.wearable_flutter_fragment_application_example_complex

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import hu.popoapps.wearable_flutter_fragment_application.activity.WearableCoreFragmentActivity
import hu.popoapps.wearable_flutter_fragment_application_example_complex.databinding.ActivityCautionBinding

/**
 * Makes a custom loading screen behind the flutter fragment application
 */
class CautionActivity : WearableCoreFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCautionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeTextLater(binding.caution)
        setupDismissableApplicationConfigurator(binding.fragmentSwipeable)
    }

    /**
     * Error message can be seen when the plugin is used incorrectly
     */
    @SuppressLint("SetTextI18n")
    private fun changeTextLater(textView: TextView) {
        Thread {
            Thread.sleep(3000)
            runOnUiThread {
                textView.text =
                    "The Flutter fragment has been popped off.\nIf you want to programmatically go back to a previous activity or exit the application, please use:\n\n'SystemChannels.platform\n.invokeMethod('SystemNavigator.pop');'"
            }
        }.start()
    }
}