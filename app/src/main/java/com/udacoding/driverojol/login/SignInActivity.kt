package com.udacoding.driverojol.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.iid.FirebaseInstanceId
import com.udacoding.driverojol.MainActivity
import com.udacoding.driverojol.R
import com.udacoding.driverojol.register.SignUpActivity
import com.udacoding.driverojol.register.User
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SignInActivity : AppCompatActivity() {
    var googleSignInClient: GoogleSignInClient? = null
    private var auth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        auth = FirebaseAuth.getInstance()

        linkSignUp.onClick {
            startActivity<SignUpActivity>()
        }

        btnLogin.onClick {

            if(signInEmail.text.isEmpty() || signInPassword.text.isEmpty())
            {
                alert {
                    title = "Warning !!"
                    message = "Email/Password tidak Boleh Kosong"
                    noButton {
                        title = "dismiss"
                    }


                }.show()
            }else{

                authUserlogin(signInEmail.text.toString(),signInPassword.text.toString())

            }
        }

        signUpbuttonGmail.onClick {
            signIn()

        }

    }

    private fun signIn() {
        val gson = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gson)


        val signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, 4)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 4) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)

                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {

            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?) {

        val credential1 = GoogleAuthProvider.getCredential(acct?.idToken, null)
        auth?.signInWithCredential(credential1)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    checkDatabase(task.result?.user?.uid,acct)

                } else {

                }
            }
    }

    private fun checkDatabase(uid: String?, acct: GoogleSignInAccount?) {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Driver")
        val query =myRef.orderByChild("uid").equalTo(auth?.uid)

        query.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {

                val users = p0.getValue(User::class.java)

                if(users?.uid != null){
                    startActivity<MainActivity>()
                }
                else{
                    insertUser(acct?.displayName ?: "", acct?.email ?: "", "087", uid)

                }
            }
        })
    }

    fun insertUser(name: String, email: String, hp: String, uid: String?): Boolean {


        val token = FirebaseInstanceId.getInstance().token

        val user = User()
        user.email = email
        user.name = name
        user.hp = hp
        user.uid = uid
        user.latitude = "0.0"
        user.longitude = "0.0"
        user.token = token
        val database = FirebaseDatabase.getInstance()
        val key = database.reference.push().key
        val myRef = database.getReference("Driver")
        key?.let { myRef.child(it).setValue(user) }
        startActivity<MainActivity>()
        return true

    }

    private fun authUserlogin(email: String, password: String){
        auth?.signInWithEmailAndPassword(email,password)?.addOnCompleteListener {task ->
            if(task.isSuccessful){
                startActivity<MainActivity>()
                finish()
            }else{
                //finish()
                toast("Login Failed")
            }
        }

    }
}