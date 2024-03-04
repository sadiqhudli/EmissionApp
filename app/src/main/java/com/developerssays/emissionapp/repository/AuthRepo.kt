package com.developerssays.emissionapp.repository



import com.developerssays.emissionapp.model.UserData
import com.developerssays.emissionapp.utils.Resource
import com.developerssays.emissionapp.utils.await
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject


interface AuthRepo{
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
   suspend fun signUp(name:String,email: String, password: String): Resource<FirebaseUser>
    fun logout()

}


class AuthImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
  //  private val firestore: FirebaseFirestore
) : AuthRepo {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String,
                               password: String):
            Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }



    override suspend fun signUp(name: String,email: String,
                                password: String):
            Resource<FirebaseUser> {
       return try {
         val result =  firebaseAuth.createUserWithEmailAndPassword(email,password).await()
          result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
           Resource.Success(result.user!!)
       }catch (e:Exception){
           e.printStackTrace()
           Resource.Failure(e)
       }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }


}