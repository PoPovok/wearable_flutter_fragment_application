package hu.popoapps.wearable_flutter_fragment_application.data

import android.content.Context
import androidx.fragment.app.FragmentManager
import hu.popoapps.wearable_flutter_fragment_application.layout.DismissibleLayout

data class DismissibleApplicationConfiguration(
    val dismissibleLayout: DismissibleLayout,
    val supportFragmentManager: FragmentManager,
    val context: Context
)
