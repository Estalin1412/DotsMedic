package com.Dmedic.dotsmedic.ActivitysNews

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ButtonBarLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.Dmedic.dotsmedic.InterfastConsulta.ConsultaActivity
import com.Dmedic.dotsmedic.InterfastDatos.DatosActivity
import com.Dmedic.dotsmedic.InterfastDots.DotsActivity
import com.Dmedic.dotsmedic.InterfastMedicina.MedicinaActivity
import com.Dmedic.dotsmedic.InterfastSalud.SaludActivity
import com.Dmedic.dotsmedic.R

class MainActivity : AppCompatActivity() {
    //Para los botones

    lateinit var buttonDots: ImageButton
    lateinit var buttonSalud1: ImageButton
    lateinit var buttonDatos2: ImageButton
    lateinit var buttonMedicina3: ImageButton
    lateinit var buttonConsulta4: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Ejecutar funciones

        InitComponets()

        InitListenerComponents()

    }


    /*-----------------------------------DEFINICION DE FUNCIONES-----------------------------------*/

    private fun InitComponets(){
        setContentView(R.layout.activity_main)
        buttonDots =findViewById(R.id.btDots)
        buttonSalud1 = findViewById(R.id.btSalud1)
        buttonDatos2 = findViewById(R.id.btDatos2)
        buttonMedicina3 = findViewById(R.id.btMedicina3)
        buttonConsulta4 = findViewById(R.id.btConsulta4)
    }

    private fun InitListenerComponents(){
        buttonDots.setOnClickListener{
            navigateToDotsAcitvity()
        }
        buttonSalud1.setOnClickListener{
            navigateToSaludActivity1()
        }
        buttonDatos2.setOnClickListener{
            navigateToDatosActivity2()
        }
        buttonMedicina3.setOnClickListener{
            navigateToMedicinaActivity3()
        }
        buttonConsulta4.setOnClickListener{
            navigateToConsultaActivity4()
        }
    }


    /*--------------------------FUNCIONES PARA BOTONES-------------------------------------------*/

    private fun navigateToDotsAcitvity(){

        val intent = Intent(this, DotsActivity::class.java)
        startActivity(intent)

    }
    private fun navigateToSaludActivity1(){
        val intent = Intent(this, SaludActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToDatosActivity2(){
        val intent = Intent(this, DatosActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMedicinaActivity3(){
        val intent = Intent(this, MedicinaActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToConsultaActivity4(){
        val intent = Intent(this, ConsultaActivity::class.java)
        startActivity(intent)
    }
}