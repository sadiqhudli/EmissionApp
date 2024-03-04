package com.developerssays.emissionapp.di

import com.developerssays.emissionapp.repository.AuthImp
import com.developerssays.emissionapp.repository.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(imp: AuthImp): AuthRepo =imp

    @Provides
    fun providesFirebaseFireStore():FirebaseFirestore = FirebaseFirestore.getInstance()


}