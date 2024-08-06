package org.firstinspires.ftc.teamcode.Autonomous

import com.outoftheboxrobotics.photoncore.Photon
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.apache.commons.math3.geometry.euclidean.twod.Line
import org.firstinspires.ftc.teamcode.Autonomous.auto_funcs.setupAuto
import org.firstinspires.ftc.teamcode.CommandBase.commands
import org.firstinspires.ftc.teamcode.Pathing.path_planner
import org.firstinspires.ftc.teamcode.Pathing.test_linie
import org.firstinspires.ftc.teamcode.Variables.system_funcs.drivetrain
import org.firstinspires.ftc.teamcode.Variables.system_funcs.init_teleop

@Autonomous
class linetest: LinearOpMode(){
   override fun runOpMode() = setupAuto(this, path_planner.test_linie(test_linie) )
}

@Autonomous
class red: LinearOpMode(){
    override fun runOpMode() = setupAuto(this, commands.auto(true))
}

@Autonomous
class blue: LinearOpMode(){
    override fun runOpMode() = setupAuto(this, commands.auto(false))
}