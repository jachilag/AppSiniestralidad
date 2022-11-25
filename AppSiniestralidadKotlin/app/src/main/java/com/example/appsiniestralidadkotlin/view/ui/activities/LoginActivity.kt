package com.example.appsiniestralidadkotlin.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appsiniestralidadkotlin.R
import com.example.appsiniestralidadkotlin.databinding.ActivityMainBinding
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

enum class ProvidersType{
    BASIC,
    GOOGLE,
    FACEBOOK
}

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var signup: TextView
    lateinit var home: Button
    lateinit var recuperar: TextView
    lateinit var iniGoogle: Button
    lateinit var iniFacebook: Button
    lateinit var callbackManager: CallbackManager
    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var buttonFacebookLogin: LoginButton
    val Req_Code: Int = 123
    lateinit var mGoogleSignInClient: GoogleSignInClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        callbackManager = CallbackManager.Factory.create();
        buttonFacebookLogin = findViewById(R.id.login_button_facebook)

        buttonFacebookLogin.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
            LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    loginResult?.let{
                        handleFacebookAccessToken(loginResult.accessToken)
                    }
                }

                override fun onCancel() {
                    Toast.makeText(this@LoginActivity, "login cancelado", Toast.LENGTH_LONG).show()
                }

                override fun onError(error: FacebookException) {
                    Toast.makeText(this@LoginActivity, "No se puede logear usuario", Toast.LENGTH_LONG).show()
                }
            })
        }


        firebaseAuth = Firebase.auth
        var email = findViewById<EditText>(R.id.login_email)
        var password = findViewById<EditText>(R.id.login_password)

        signup = findViewById(R.id.textView_registrarse)
        signup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        home = findViewById(R.id.btn_log_ini)
        home.setOnClickListener {
//            startActivity(Intent(this, HomeActivity::class.java))
            login(email.text.toString(), password.text.toString())
        }

        recuperar = findViewById(R.id.textView_olvidar)
        recuperar.setOnClickListener {
            startActivity(Intent(this, RestoreActivity::class.java))
        }

        iniGoogle = findViewById(R.id.btn_log_google)
        iniGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            mGoogleSignInClient = GoogleSignIn.getClient(this, googleConf)
            firebaseAuth = FirebaseAuth.getInstance()

            iniGoogle.setOnClickListener { view: View? ->
                Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
                signInGoogle()
            }
        }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext, user?.email.toString(), Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",Toast.LENGTH_SHORT).show()
                    Firebase.auth.signOut()
                }
            }
    }

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { Task ->
                if (Task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext, user?.uid.toString(), Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                } else {
                    Toast.makeText(baseContext, "Error", Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)

        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
        handleResult(task)
    }

    private fun handleResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }
    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Req_Code)
    }
    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                //finish()
            }
        }
    }
}


//package com.example.appsiniestralidadkotlin.view.ui.activities
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.appsiniestralidadkotlin.R
//import com.example.appsiniestralidadkotlin.databinding.ActivityMainBinding
//import com.facebook.AccessToken
//import com.facebook.CallbackManager
//import com.facebook.FacebookCallback
//import com.facebook.FacebookException
//import com.facebook.login.LoginManager
//import com.facebook.login.LoginResult
//import com.facebook.login.widget.LoginButton
//import com.google.firebase.auth.FacebookAuthProvider
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.ktx.Firebase
//
//enum class ProvidersType{
//    BASIC,
//    GOOGLE,
//    FACEBOOK
//}
//
//class LoginActivity: AppCompatActivity() {
//    lateinit var binding: ActivityMainBinding
//    lateinit var signup: TextView
//    lateinit var home: Button
//    lateinit var recuperar: TextView
//    lateinit var iniGoogle: Button
//    lateinit var iniFacebook: Button
//    lateinit var callbackManager: CallbackManager
//    lateinit var firebaseAuth: FirebaseAuth
//    private lateinit var buttonFacebookLogin: LoginButton
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//
//        callbackManager = CallbackManager.Factory.create();
//        buttonFacebookLogin = findViewById(R.id.login_button_facebook)
//
//        buttonFacebookLogin.setOnClickListener {
//            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))
//            LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
//                    override fun onSuccess(loginResult: LoginResult) {
//                        loginResult?.let{
//                            handleFacebookAccessToken(loginResult.accessToken)
//                        }
//                    }
//
//                    override fun onCancel() {
//                        Toast.makeText(this@LoginActivity, "login cancelado", Toast.LENGTH_LONG).show()
//                    }
//
//                    override fun onError(error: FacebookException) {
//                        Toast.makeText(this@LoginActivity, "No se puede logear usuario", Toast.LENGTH_LONG).show()
//                    }
//                })
//        }
//
//
//        firebaseAuth = Firebase.auth
//        var email = findViewById<EditText>(R.id.login_email)
//        var password = findViewById<EditText>(R.id.login_password)
//
//        signup = findViewById(R.id.textView_registrarse)
//        signup.setOnClickListener {
//            startActivity(Intent(this, SignUpActivity::class.java))
//        }
//
//        home = findViewById(R.id.btn_log_ini)
//        home.setOnClickListener {
////            startActivity(Intent(this, HomeActivity::class.java))
//            login(email.text.toString(), password.text.toString())
//        }
//
//        recuperar = findViewById(R.id.textView_olvidar)
//        recuperar.setOnClickListener {
//            startActivity(Intent(this, RestoreActivity::class.java))
//        }
//
//        iniGoogle = findViewById(R.id.btn_log_google)
//        iniGoogle.setOnClickListener {
//            Toast.makeText(this, "Redirigiendo a google", Toast.LENGTH_LONG).show()
//        }
//    }
//
//    private fun handleFacebookAccessToken(token: AccessToken) {
//        val credential = FacebookAuthProvider.getCredential(token.token)
//        firebaseAuth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    val user = firebaseAuth.currentUser
//                    Toast.makeText(baseContext, user?.email.toString(), Toast.LENGTH_LONG).show()
//                    startActivity(Intent(this, HomeActivity::class.java))
//                } else {
//                    Toast.makeText(baseContext, "Authentication failed.",Toast.LENGTH_SHORT).show()
//                    Firebase.auth.signOut()
//                }
//            }
//    }
//
//    fun login(email: String, password: String) {
//        firebaseAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { Task ->
//                if (Task.isSuccessful) {
//                    val user = firebaseAuth.currentUser
//                    Toast.makeText(baseContext, user?.uid.toString(), Toast.LENGTH_LONG).show()
//                    startActivity(Intent(this, HomeActivity::class.java))
//                } else {
//                    Toast.makeText(baseContext, "Error", Toast.LENGTH_LONG).show()
//                }
//            }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        callbackManager.onActivityResult(requestCode,resultCode, data)
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Pass the activity result back to the Facebook SDK
//        callbackManager.onActivityResult(requestCode, resultCode, data)
//    }
//}
//
//
