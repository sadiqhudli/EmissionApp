package com.developerssays.emissionapp.di

import android.app.Application
import com.developerssays.emissionapp.repository.AuthImp
import com.developerssays.emissionapp.repository.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent

@HiltAndroidApp
class EmissionApp : Application() {

}



