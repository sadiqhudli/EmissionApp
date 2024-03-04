package com.developerssays.emissionapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developerssays.emissionapp.model.UserData
import com.developerssays.emissionapp.repository.AuthRepo
import com.developerssays.emissionapp.utils.Constans
import com.developerssays.emissionapp.utils.Resource
import com.developerssays.emissionapp.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authrepo: AuthRepo,
    private val fireStoreRepo:FireStoreRepo
    )  :ViewModel(){

    val currentUser: FirebaseUser?
        get() = authrepo.currentUser


    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    private val _signUnFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val signUnFlow: StateFlow<Resource<FirebaseUser>?> = _signUnFlow

/// var savedAddresses : MutableLiveData<List<AddressItem>> = MutableLiveData()
    private val _userInfoFlow = MutableStateFlow<FirebaseFirestore?>(null)
    val userinfoFlow: StateFlow<FirebaseFirestore?> = _userInfoFlow

    init {
        if (authrepo.currentUser!=null){
            _loginFlow.value = Resource.Success(authrepo.currentUser!!)
        }

    }



    fun loginUser(email:String,password:String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading
        val results = authrepo.login(email, password)
        _loginFlow.value= results

    }


    fun signUp(name:String,email: String,password: String)=
        viewModelScope.launch {
        _signUnFlow.value = Resource.Loading
        val result = authrepo.signUp(name, email, password)
        _signUnFlow.value = result
    }
    // Mohammed@786

    fun logout() {
        authrepo.logout()
        _loginFlow.value = null
        _signUnFlow.value = null
    }

    fun addUserInfo(userData: UserData) = viewModelScope.launch {
        fireStoreRepo.addUserInfo(userData)
    }



    suspend fun addserCheck(userData: UserData): Void? {
        val t= userData.userId?.let {
            fireStoreRepo.firestore.collection(Constans.COLLECTION_NAME)
                .document("userId").set(userData)
                .await()
        }
        return t
    }


    fun fetchData() =
        viewModelScope.launch {
         val re =  fireStoreRepo.getUserInfo()
            re
        }
        }
