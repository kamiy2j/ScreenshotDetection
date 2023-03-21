package com.kamran.screenshotdetection

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kamran.screenshotdetection.ui.theme.ScreenshotDetectionTheme

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
class MainActivity : ComponentActivity(), Activity.ScreenCaptureCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenshotDetectionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun onScreenCaptured() {
        // Do whatever you want
        Log.d("MainActivity", "onScreenCaptured: Screenshot detected")
    }

    override fun onStart() {
        super.onStart()
        // Register callback to detect screenshot
        registerScreenCaptureCallback(mainExecutor, this)
    }

    override fun onStop() {
        super.onStop()
        // unregister callback to detect screenshot
        unregisterScreenCaptureCallback(this)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScreenshotDetectionTheme {
        Greeting("Android")
    }
}