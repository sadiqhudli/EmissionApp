package com.developerssays.emissionapp.viewmodel

import com.developerssays.emissionapp.model.UserData
import com.developerssays.emissionapp.repository.AuthRepo
import com.developerssays.emissionapp.utils.Constans
import com.developerssays.emissionapp.utils.Resource
import com.developerssays.emissionapp.utils.await
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


class FireStoreRepo @Inject constructor(
    val firestore: FirebaseFirestore,
    val authRepo: AuthRepo
) {

    val user = authRepo.currentUser
     fun hasUser(user:FirebaseAuth) : FirebaseAuth {
         val currentUser = FirebaseAuth.getInstance()
       if (currentUser == null) {
           val user = FirebaseAuth.getInstance().currentUser

       }
         return currentUser
     }

    fun getUserId():String =user?.uid.orEmpty()

    suspend fun addUserInfo(userData: UserData): Void? {
      //  val userInfo = UserData(userId = userId,email = email,name = name)
        val  documentReference = firestore.collection(Constans.COLLECTION_NAME)
            .document(user!!.email.toString())
            .set(userData)
            .await()
        return  documentReference

    }




    suspend fun getUserInfo() :Resource<FirebaseFirestore>{
        return try {
            val result = firestore.collection(Constans.COLLECTION_NAME)
                .document(Constans.DOCUMENT_ID)
                .get()
                .await()
            Resource.Success(firestore)

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}