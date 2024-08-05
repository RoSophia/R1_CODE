package org.firstinspires.ftc.teamcode.Autonomous

import com.outoftheboxrobotics.photoncore.Photon
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.apache.commons.math3.geometry.euclidean.twod.Line
import org.firstinspires.ftc.teamcode.Autonomous.auto_funcs.setupAuto
import org.firstinspires.ftc.teamcode.Pathing.path_planner
import org.firstinspires.ftc.teamcode.Pathing.test_linie
import org.firstinspires.ftc.teamcode.Variables.system_funcs.drivetrain
import org.firstinspires.ftc.teamcode.Variables.system_funcs.init_teleop


@Autonomous
@Photon
class autoRedLong : LinearOpMode(){
    override fun runOpMode() {} //= runAuto(this, isRed = true, isLong = true)
}

@Autonomous
@Photon
class autoRedShort : LinearOpMode(){
    override fun runOpMode() {}// = runAuto(this, isRed = true, isLong = false)
}

@Autonomous
@Photon
class autoBlueLong : LinearOpMode(){
    override fun runOpMode() {}//= runAuto(this, isRed = false, isLong = true)
}

@Autonomous
@Photon
class autoBlueShort : LinearOpMode(){
    override fun runOpMode() {}//runAuto(this, isRed = false, isLong = false)

}

@Autonomous
class parcarealbastru: LinearOpMode(){
    override fun runOpMode() {
        init_teleop(this)
        waitForStart()
        drivetrain.straferight()
    }
}

@Autonomous
class parcarerosu: LinearOpMode(){
    override fun runOpMode() {
        init_teleop(this)
        waitForStart()
        drivetrain.strafeleft()
    }
}

@Autonomous
class linetest: LinearOpMode(){
   override fun runOpMode() = setupAuto(this, true, true, path_planner.test_linie(test_linie) )
}