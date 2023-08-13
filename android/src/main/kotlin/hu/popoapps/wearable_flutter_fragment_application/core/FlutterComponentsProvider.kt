package hu.popoapps.wearable_flutter_fragment_application.core

import android.content.Context
import androidx.fragment.app.FragmentManager
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

/**
 * It builds properties by the provided parameters that can be used by the plugin
 */
data class FlutterComponentsProvider(
    val flutterEngineTag: String,
    val flutterFragmentTag: String,
    val flutterEngine: FlutterEngine,
) {
    /**
     * In case DartEntrypoint is not defined, it uses the default one.
     */
    constructor(
        context: Context,
        entrypoint: DartExecutor.DartEntrypoint? = null,
        flutterEngineTag: String = buildFlutterEngineTag(),
        flutterFragmentTag: String = buildFlutterFragmentTag(),
        flutterEngine: FlutterEngine? = null
    ) : this(
        flutterEngineTag,
        flutterFragmentTag,
        flutterEngine ?: FlutterEngine(context).apply {
            entrypoint ?: dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
            FlutterEngineCache.getInstance().put(flutterEngineTag, this)
        }
    )

    /**
     * In case DartEntrypoint is defined by a name, it creates a new entrypoint by it.
     * The Dart starting method of the application must be in the same folder where the main method is/was,
     * it must be annotated with @pragma("entrypointName") tag in order not to be wiped out after build.
     * (The entrypointName above is the same as the value of the entrypointName in your constructor)
     */
    constructor(
        context: Context,
        entrypointName: String,
        flutterEngineTag: String = buildFlutterEngineTag(),
        flutterFragmentTag: String = buildFlutterFragmentTag(),
        flutterEngine: FlutterEngine? = null
    ) : this(
        flutterEngineTag,
        flutterFragmentTag,
        flutterEngine ?: FlutterEngine(context).apply {
            dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint("flutter_assets", entrypointName))
            FlutterEngineCache.getInstance().put(flutterEngineTag, this)
        }
    )

    fun getCachedFlutterFragment(supportFragmentManager: FragmentManager): FlutterFragment? {
        return supportFragmentManager.findFragmentByTag(flutterFragmentTag) as FlutterFragment?
    }

    fun buildCachedFlutterFragment(): FlutterFragment {
        return FlutterFragment.withCachedEngine(flutterEngineTag)
            .shouldAutomaticallyHandleOnBackPressed(true)
            .shouldAttachEngineToActivity(true)
            .build() as FlutterFragment
    }

    companion object {
        private const val TAG_FLUTTER_ENGINE_DEFAULT = "flutter_engine"
        private const val TAG_FLUTTER_FRAGMENT_DEFAULT = "flutter_fragment"
        private var uniqueEngineNumber = 0
        private var uniqueFragmentNumber = 0

        /**
         * It is used if FlutterEngineTag is not defined by default.
         */
        fun buildFlutterEngineTag(): String {
            return TAG_FLUTTER_ENGINE_DEFAULT + (uniqueEngineNumber++)
        }

        /**
         * It is used if FlutterFragmentTag is not defined by default.
         */
        fun buildFlutterFragmentTag(): String {
            return TAG_FLUTTER_FRAGMENT_DEFAULT + (uniqueFragmentNumber++)
        }
    }
}