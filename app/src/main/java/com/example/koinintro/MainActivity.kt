package com.example.koinintro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.koinintro.ui.theme.KoinIntroTheme
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

// we want the Print class should be alive as long as the activity is alive
// then make activity implement AndroidScopeComponent.
class MainActivity : ComponentActivity(), AndroidScopeComponent {
    //viewModel injection in the activity or fragment by the koin
//    private val viewModel by viewModel<MainViewModel>()

    // class Print class injection in activity
    override val scope: Scope by activityScope()// there are also several scopes like fragmentScope, serviceScope
    // override val scope: Scope by activityRetainedScope() // it is used to make dependency survive the configuration changes
    private val print by inject<Print>()

    // injection of the dependencies of same types with qualifier
    private val hello by inject<String>(named("hello"))
    private val bye by inject<String>(named("bye"))

    // If you want to inject any object in the activity, for example in this project project lets inject MyApi
//    private val myApi = get<MyApi>() // this is immediate injection of myApi, as the activity will be created myApi will be injected.
//    private val myApi by inject<MyApi>() // this is the lazy injection, when first time the object will be used in the activity then it will be injected.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinIntroTheme {
                // viewModel injection in the composable
                val viewModel = getViewModel<MainViewModel>()
                viewModel.doNetworkCall()
                print.printHelloWorld()
                println("$hello $bye")
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KoinIntroTheme {
        Greeting("Android")
    }
}