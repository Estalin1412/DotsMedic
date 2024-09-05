package com.Dmedic.dotsmedic.InterfastDatos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Dmedic.dotsmedic.R

class ObjetosDatosGenerales_adapter (private val datosGenerales: List<ObjetosDatosGenerales>): RecyclerView.Adapter<ObjetosGenerales_viewHolder>()
{

    // Para crear una vista visual para que vaya pasando informacion
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjetosGenerales_viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_datos, parent, false)
        return ObjetosGenerales_viewHolder(view)
    }


    //Metodo del tamaño de la lista
    override fun getItemCount(): Int = datosGenerales.size

    override fun onBindViewHolder(holder: ObjetosGenerales_viewHolder, position: Int) {
        holder.render(datosGenerales[position])
    }


}