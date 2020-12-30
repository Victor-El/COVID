package me.codeenzyme.covid

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(object: Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) // default impl
                    /*return String.format(
                        "Class: %s; Method: %s at Line: %d",
                        element.className,
                        element.methodName,
                        element.lineNumber
                    )*/
                }
            })
        } else {
            // Release tree
            Timber.plant(object: Timber.Tree() {
                @SuppressLint("LogNotTimber")
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                    if ((priority == Log.ERROR).or(priority == Log.WARN)) {
                        Log.d("Runtime Crashlytics: $tag", "sent $message to Crashlytics")
                    }
                }
            })
        }
    }
}