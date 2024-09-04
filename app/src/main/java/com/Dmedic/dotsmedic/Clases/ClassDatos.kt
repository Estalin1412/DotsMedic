package com.Dmedic.dotsmedic.Clases

class ClassDatos(
    var Humedad: Double,
    var Temperatura: Double,
    var TipoDato: String,
    var mpu: MPU
        ) {
    constructor():this(0.0,0.0," ", MPU())

    /*
    public fun getHumedad(): Double{
        return Humedad
    }
    public fun getTemperatura(): Double{
        return Temperatura
    }
    public fun getTipoDato(): String{
        return TipoDato
    }
    public fun getMPU(): MPU{
        return mpu
    }
    */
}