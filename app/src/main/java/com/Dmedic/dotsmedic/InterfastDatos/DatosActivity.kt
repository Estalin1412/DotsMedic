package com.Dmedic.dotsmedic.InterfastDatos

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.Dmedic.dotsmedic.Clases.ClassDatos
import com.Dmedic.dotsmedic.Clases.MPU
import com.Dmedic.dotsmedic.Clases.SensorMPU

import com.Dmedic.dotsmedic.R
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database


class DatosActivity : AppCompatActivity() {

    /*----------------------------------------Declaracion de variable-----------------------------*/
    //Variable el vizualizador
    lateinit var tvDatos: TextView

    //Para conexión con firebase
    var database = Firebase.database
    var myRef = database.getReference("messenge")

    //Objetos para la base de datos
    var mpuInstance = MPU(SensorMPU(1,2,3), SensorMPU(4,5,6))
    var medidas= ClassDatos(50.0, 22.0, "PrimerDato", mpuInstance)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_datos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        InitComponents()
        ListenerComponents()

    }


    /*----------------------------------FUNCIONESD PARA EJECUTAR----------------------------------*/

    private fun InitComponents(){

        tvDatos = findViewById(R.id.tvDatos)
        FirebaseApp.initializeApp(this)


    }
    private fun ListenerComponents(){

        myRef.setValue(medidas)


        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //Para obtener datos de base de datos
                val value = snapshot.getValue(ClassDatos::class.java)

                //Para cambiar el texto del textView
                tvDatos.setText(value?.Temperatura.toString())
            }

            override fun onCancelled(error: DatabaseError){
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

    }
}