package com.example.androidappwithfluttermodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.androidappwithfluttermodule.ui.theme.AndroidAppWithFlutterModuleTheme
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel
import kotlin.random.Random

class MainActivityWithCompose : ComponentActivity() {

    private lateinit var flutterEngine: FlutterEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupFlutterEngine()
        startMethodChannel()
        setContent {
            AndroidAppWithFlutterModuleTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    StartFlutterModule(onClick = { openFlutterActivity() })
                }
            }
        }
    }

    private fun setupFlutterEngine() {
        flutterEngine = FlutterEngine(this)
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        FlutterEngineCache.getInstance().put(ENGINE_NAME, flutterEngine)
    }

    private fun openFlutterActivity() {
        startActivity(FlutterActivity.withCachedEngine(ENGINE_NAME).build(this))
    }

    private fun startMethodChannel() {
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "getRandomNumber") {
                val rand = Random.nextInt(100)
                result.success(rand)
            } else {
                result.notImplemented()
            }
        }
    }

    companion object {
        private const val ENGINE_NAME = "ENGINE_HOME"
        private const val CHANNEL = "example.com/channel"
    }
}

@Composable
fun StartFlutterModule(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF0175C2)),
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.align(Alignment.Center),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        ) {
            Text(text = "Open Flutter Module", color = Color.White)
        }
    }
}
