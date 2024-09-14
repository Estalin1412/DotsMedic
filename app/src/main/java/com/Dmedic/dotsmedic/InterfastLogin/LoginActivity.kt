package com.Dmedic.dotsmedic.InterfastLogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ButtonBarLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.Dmedic.dotsmedic.R
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import android.widget.Toast
import com.Dmedic.dotsmedic.ActivitysNews.MainActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var tvEmail: TextView
    lateinit var tvPassword: TextView

    lateinit var btnRegistrar: Button
    lateinit var btnAcceder: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        funInitComponents()
        funInitListeners()
    }

    private fun funInitComponents(){
        tvEmail = findViewById(R.id.tvEmail)
        tvPassword = findViewById(R.id.tvPassword)

        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnAcceder = findViewById(R.id.btnAcceder)

    }
    private fun funInitListeners(){
        //Configurar el boton de registrar
        btnRegistrar.setOnClickListener{
            val email = tvEmail.text.toString().trim()
            val password = tvPassword.text.toString().trim()
            if( email.isNotEmpty() && password.isNotEmpty() ){
                createAccount(email,password)
            }else{

                Toast.makeText(this,"Ingrese de nuevo su email y contraseña correctamente", Toast.LENGTH_SHORT).show()
            }
        }
        //Configurar el boton de accder
        btnAcceder.setOnClickListener {

            val email = tvEmail.text.toString().trim()
            val password = tvPassword.text.toString().trim()
            if(email.isNotEmpty() && password.isNotEmpty() ) {

                signIn(email,password)
            }else{
                Toast.makeText(this, "Ingrese su correo y contraseña correctamente", Toast.LENGTH_SHORT).show()
            }

        }

    }


    // Crear nueva cuenta de usuario
    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Éxito en el registro
                    Log.d("Auth", "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // Fallo en el registro
                    Log.w("Auth", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Registro fallido.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }
    // Iniciar sesión con un usuario existente
    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Éxito en el inicio de sesión
                    Log.d("Auth", "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    val intent =Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Fallo en el inicio de sesión
                    Log.w("Auth", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Autenticación fallida.", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    // Actualiza la UI según el usuario autenticado
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            Toast.makeText(this, "Bienvenido: ${user.email}", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Por favor, regístrese o inicie sesión.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
        // Verificar si el usuario ha iniciado sesión
        val currentUser = auth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
        }
    }


}

