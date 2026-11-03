package com.example.securehandover

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.securehandover.core.navigation.SecureHandoverNavGraph
import com.example.securehandover.core.ui.theme.SecureHandoverTheme
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.handleDeeplinks
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var supabaseClient: SupabaseClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        processIntent(intent)

        setContent {
            SecureHandoverTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SecureHandoverNavGraph(
                        supabaseClient = supabaseClient
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        processIntent(intent)
    }

    private fun processIntent(intent: Intent?) {
        val targetIntent = intent ?: return
        if (targetIntent.data == null) return

        try {
            supabaseClient.handleDeeplinks(
                intent = targetIntent,
                onSessionSuccess = { _ ->
                    // Sesión importada con éxito; actualiza sessionStatus en Supabase
                },
                onError = { error ->
                    error.printStackTrace()
                }
            )
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            // Se limpia la data para evitar re-canjear el código PKCE si la actividad se recrea
            targetIntent.data = null
        }
    }
}
