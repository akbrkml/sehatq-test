package com.badrun.sehatq_test.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.badrun.sehatq_test.MainActivity
import com.badrun.sehatq_test.R
import com.badrun.sehatq_test.base.BaseActivity
import com.badrun.sehatq_test.databinding.LoginActivityBinding
import com.badrun.sehatq_test.utils.ext.showMessage
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<LoginActivityBinding>(), View.OnClickListener {

    private val viewModel: LoginViewModel by viewModel()

    override val bindingInflater: (LayoutInflater) -> LoginActivityBinding
        get() = LoginActivityBinding::inflate

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager

    override fun onCreated(state: Bundle?) {
        initViews()
        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        callbackManager = CallbackManager.Factory.create()

        binding.btnSignInWithFb.setReadPermissions("email", "public_profile")
        binding.btnSignInWithFb.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
            }
        })
    }

    private fun initViews() {
        binding.btnSignIn.setOnClickListener(this)
        binding.btnSignInWithGoogle.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) launchMainActivity()
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN_GOOGLE)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        showProgressBar()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    if (user != null) launchMainActivity()
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    showMessage(binding.containerLogin, "Authentication Failed.")
                }

                hideProgressBar()
            }
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")
        showProgressBar()

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    if (user != null) launchMainActivity()

                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    showMessage(binding.containerLogin, "Authentication failed.")
                }

                hideProgressBar()
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN_GOOGLE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
            }
        }

        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun launchMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun hideProgressBar() {
        binding.pbLoading.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.pbLoading.visibility = View.VISIBLE
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_sign_in -> launchMainActivity()
            R.id.btn_sign_in_with_google -> signInGoogle()
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
        private const val RC_SIGN_IN_GOOGLE = 9001
    }
}