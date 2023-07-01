package hu.popoapps.wearable_flutter_fragment_application.core

import android.content.Context
import androidx.fragment.app.FragmentManager
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

data class FlutterComponentsProvider(
    val flutterEngineTag: String,
    val flutterFragmentTag: String,
    val flutterEngine: FlutterEngine,
) {
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

        fun buildFlutterEngineTag(): String {
            return TAG_FLUTTER_ENGINE_DEFAULT + (uniqueEngineNumber++)
        }

        fun buildFlutterFragmentTag(): String {
            return TAG_FLUTTER_FRAGMENT_DEFAULT + (uniqueFragmentNumber++)
        }
    }
}