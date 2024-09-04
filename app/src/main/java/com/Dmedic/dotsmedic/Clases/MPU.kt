package com.Dmedic.dotsmedic.Clases

import com.Dmedic.dotsmedic.Clases.SensorMPU

class   MPU(var Ac: SensorMPU, var Gi: SensorMPU){

    constructor(): this( SensorMPU(), SensorMPU() )

}   