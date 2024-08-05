package org.firstinspires.ftc.teamcode.Variables

import com.acmerobotics.dashboard.config.Config
import org.checkerframework.common.value.qual.DoubleVal
import org.firstinspires.ftc.teamcode.Variables.PIDCoefValues.anglePIDF
import org.firstinspires.ftc.teamcode.Variables.PIDCoefValues.anglePIDP
import org.firstinspires.ftc.teamcode.Variables.PIDCoefValues.pidSlideD
import org.firstinspires.ftc.teamcode.Variables.PIDCoefValues.pidSlideF
import org.firstinspires.ftc.teamcode.Variables.PIDCoefValues.pidSlideI
import org.firstinspires.ftc.teamcode.Variables.PIDCoefValues.pidSlideP
import org.firstinspires.ftc.teamcode.Variables.PIDCoefValues.speedPIDF
import org.firstinspires.ftc.teamcode.Variables.PIDCoefValues.speedPIDP

class PIDCOEF(@JvmField var p: Double, @JvmField var i: Double, @JvmField var d: Double, @JvmField var f: Double){

}

object PIDCoefs {
    var pidcoefSlide =  PIDCOEF(pidSlideP, pidSlideI, pidSlideD, pidSlideF)
    var pidcoefSpeed = PIDCOEF(speedPIDP, 0.0, 0.0, speedPIDF)
    var pidcoefAngle = PIDCOEF(anglePIDP, 0.0, 0.0, anglePIDF)
}

@Config
object PIDCoefValues{
    @JvmField
    var pidSlideD: Double = 0.0
    @JvmField
    var pidSlideI: Double = 0.0
    @JvmField
    var pidSlideP: Double = 0.0
    @JvmField
    var pidSlideF: Double = 0.0


    @JvmField
    var speedPIDP: Double = 0.0
    @JvmField
    var speedPIDF: Double = 0.0

    @JvmField
    var anglePIDP: Double = 0.0
    @JvmField
    var anglePIDF: Double = 0.0
}