package com.Dmedic.dotsmedic.InterfastDatos

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.Dmedic.dotsmedic.Clases.ClassDatos
import com.Dmedic.dotsmedic.Clases.MPU
import com.Dmedic.dotsmedic.Clases.SensorMPU

import com.Dmedic.dotsmedic.R
import com.Electronica117_MQTT.electronica117_mqtt.Interfaces.MQTTListener
import com.Electronica117_MQTT.electronica117_mqtt.MQTT
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.gson.Gson


class DatosActivity : AppCompatActivity() {

    /*-----------------PARA CONEXION MQTT----------------------------------------*/


    /*PARA DATOS GENERALES--------------------------------------------------------------------*/
    //Listado de datos anteriores
    private val datosGenerales = listOf(
        ObjetosDatosGenerales.datoGeneral1,
        ObjetosDatosGenerales.datoGeneral2,
        ObjetosDatosGenerales.datoGeneral3,
        ObjetosDatosGenerales.datoGeneral4,
        ObjetosDatosGenerales.datoGeneral5,

    )

    //Recycler view task
    private lateinit var rvDatosGenerales: RecyclerView
    private lateinit var objetosGeneralesAdapter: ObjetosDatosGenerales_adapter

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
        initUI()

    }


    /*----------------------------------FUNCIONESD PARA EJECUTAR----------------------------------*/

    private fun InitComponents(){



        tvDatos = findViewById(R.id.tvDatos)
        FirebaseApp.initializeApp(this)

        //Inicializar el recyclerView
        rvDatosGenerales = findViewById(R.id.rvPartDatosGenerales)



    }
    private fun ListenerComponents(){
        //Para guardar medida en base de  datos fire base
        myRef.setValue(medidas)


        myRef.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //Para obtener datos de base de datos
                val value = snapshot.getValue(ClassDatos::class.java)
                //Part73: Para que si cambies la base de datos tambien se cambie el text view
                val gson = Gson()
                val myJsonStr = gson.toJson(value)

                //Para cambiar el texto del textView
                tvDatos.setText(myJsonStr/*value?.Humedad.toString()*/)
            }

            override fun onCancelled(error: DatabaseError){
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

        //PARA MQTT
        /*
        MQTT.ConnectMQTT(object : MQTTListener{
            override fun onSuccess(){
                runOnUiThread {
                    Toast.makeText(applicationContext, "Conectado!!", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(){
                runOnUiThread {
                    Toast.makeText(applicationContext, "No está conectado...", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
        */


    }
    private fun initUI(){
        //Para mostrar los datos en pantalla con el recyclerview
        objetosGeneralesAdapter = ObjetosDatosGenerales_adapter(datosGenerales)
        rvDatosGenerales.layoutManager = LinearLayoutManager(this , LinearLayoutManager.VERTICAL, false)
        rvDatosGenerales.adapter = objetosGeneralesAdapter
    }
}