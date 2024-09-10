package com.Dmedic.dotsmedic.Clases


import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

class Unidad(var nameUnidad: String,
             var tipoMedida: String,
             var registros: MutableList<Pair <Double, LocalDateTime> >
             ) {
    @RequiresApi(Build.VERSION_CODES.O)
    constructor() : this(
        "Unidad",
        "u",
        mutableListOf( Pair( 1.0, LocalDateTime.parse("2023-03-24T14:30:21") ) )
    )

    fun addRegistro(newValor: Double, tiempo: LocalDateTime){
        registros.add(Pair(newValor, tiempo))
    }
    fun getRegistro(): MutableList<Pair<Double, LocalDateTime>>{
        return registros
    }
}