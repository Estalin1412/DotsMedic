package com.Dmedic.dotsmedic.InterfastDatos

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.Dmedic.dotsmedic.R

class ObjetosGenerales_viewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val tvNameDato0: TextView = view.findViewById(R.id.tvNameDato0)
    private val tvNameDato: TextView = view.findViewById(R.id.tvNameDato)

    private val divider0: View = view.findViewById(R.id.vDivider0)
    private val divider: View = view.findViewById(R.id.vDivider)

    fun render(taskDatosGenerales: ObjetosDatosGenerales){

        when(taskDatosGenerales){
            ObjetosDatosGenerales.datoGeneral1 -> {
                tvNameDato0.text = "Humedad"
                tvNameDato.text = "Temperatura"
                divider0.setBackgroundColor(
                    ContextCompat.getColor(divider0.context, R.color.white))
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.black))
            }
            ObjetosDatosGenerales.datoGeneral2 -> {
                tvNameDato0.text = "Leucocitos"
                tvNameDato.text = "Presion"
            }
            ObjetosDatosGenerales.datoGeneral3 -> {
                tvNameDato0.text = "pasado"
                tvNameDato.text = "Pfds"

            }
            ObjetosDatosGenerales.datoGeneral4 -> {
                tvNameDato0.text = "pasado"
                tvNameDato.text = "Pfds"

            }
            ObjetosDatosGenerales.datoGeneral5 -> {
                tvNameDato0.text = "pasado"
                tvNameDato.text = "Pfds"

            }
        }
    }
}