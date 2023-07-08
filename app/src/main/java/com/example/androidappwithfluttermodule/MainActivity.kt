package com.example.androidappwithfluttermodule

// import io.flutter.embedding.android.FlutterActivity
// import io.flutter.embedding.engine.FlutterEngine
// import io.flutter.plugin.common.MethodChannel
// import kotlin.random.Random

// class MainActivity : FlutterActivity() {
//
//    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
//        super.configureFlutterEngine(flutterEngine)
//        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
//            if (call.method == "getRandomNumber") {
//                val rand = Random.nextInt(100)
//                result.success(rand)
//            } else {
//                result.notImplemented()
//            }
//        }
//    }
//
//    companion object {
//        private const val CHANNEL = "example.com/channel"
//    }
// }
//
