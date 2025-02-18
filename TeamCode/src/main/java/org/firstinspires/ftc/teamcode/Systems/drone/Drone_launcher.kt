package org.firstinspires.ftc.teamcode.Systems.drone

import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Systems.drone.drone_vars.droneLaunch
import org.firstinspires.ftc.teamcode.Variables.system_funcs.hardwareMap
import org.firstinspires.ftc.teamcode.Variables.system_funcs.tp
import org.firstinspires.ftc.teamcode.Variables.system_vars.droneInit

class Drone_launcher {

    val launcher = hardwareMap.servo.get("DRONE")
    val launchertwo = hardwareMap.servo.get("DRONE2")

    fun init(){
        launcher.position = droneInit
        launchertwo.position = 0.0
        //autoupdate_tp(tp, "DRONE", "INITIALIZED")
    }

    fun launch(){
        launcher.position = droneLaunch
    }

    fun launchtwo(){
        launchertwo.position = 0.5
    }
}