package org.firstinspires.ftc.teamcode.Variables

import com.acmerobotics.dashboard.config.Config

@Config
object system_vars {

    //init
    @JvmField
    var clawRotateInit: Double = 0.05
    @JvmField
    var intakeInit: Double = 0.62

    @JvmField
    var droneInit: Double = 0.5
    @JvmField
    var larmInit: Double = 0.38 //0.95
    @JvmField
    var rarmInit: Double = 0.6  //0.39


    //generals
    @JvmField
    var trigtresh: Double = 0.02

    var imuoffset: Double = 0.0



    @JvmField
    var MPupVelocity: Double = 3.0
    @JvmField
    var MPupAcceleration: Double = 8.0
    @JvmField
    var MPupDecceleration: Double = 1.5

    @JvmField
    var MPdownVelocity: Double = 4.0
    @JvmField
    var MPdownAcceleration: Double  = 6.0
    @JvmField
    var MPdownDecceleration: Double = 1.3

    @JvmField
    var MPinitVelocity: Double = 3.5
    @JvmField
    var MPinitAcceleration: Double = 7.0
    @JvmField
    var MPinitDecceleration: Double = 1.4

}