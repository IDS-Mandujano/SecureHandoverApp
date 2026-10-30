package com.example.securehandover.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import com.example.securehandover.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.realtime.realtime
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage

@Module
@InstallIn(SingletonComponent::class)
object SupabaseModule {

    @Provides
    @Singleton
    fun provideSupabaseClient() : SupabaseClient {
        return createSupabaseClient (
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_ANON_KEY
        ) {
            install(Auth)
            install(Postgrest)
            install(Realtime)
            install(Storage)
        }
    }

    @Provides
    @Singleton
    fun provideSupabaseAuth(supabaseClient: SupabaseClient): Auth {
        return supabaseClient.auth
    }

    @Provides
    @Singleton
    fun provideSupabasePostgrest(supabaseClient: SupabaseClient): Postgrest {
        return supabaseClient.postgrest
    }

    @Provides
    @Singleton
    fun provideSupabaseRealtime(supabaseClient: SupabaseClient): Realtime {
        return supabaseClient.realtime
    }

    @Provides
    @Singleton
    fun provideSupabaseStorage(supabaseClient: SupabaseClient): Storage {
        return supabaseClient.storage
    }
}