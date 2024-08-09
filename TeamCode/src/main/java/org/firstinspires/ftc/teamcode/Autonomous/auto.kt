package org.firstinspires.ftc.teamcode.Autonomous

import com.outoftheboxrobotics.photoncore.Photon
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.apache.commons.math3.geometry.euclidean.twod.Line
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Autonomous.auto_funcs.setupAuto
import org.firstinspires.ftc.teamcode.CommandBase.Command
import org.firstinspires.ftc.teamcode.CommandBase.InstantCommand
import org.firstinspires.ftc.teamcode.CommandBase.commands
import org.firstinspires.ftc.teamcode.Pathing.Trajectory
import org.firstinspires.ftc.teamcode.Pathing.path_planner
import org.firstinspires.ftc.teamcode.Pathing.test_linie
import org.firstinspires.ftc.teamcode.Variables.system_funcs.drivetrain
import org.firstinspires.ftc.teamcode.Variables.system_funcs.init_teleop
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pp
import org.firstinspires.ftc.teamcode.Variables.system_funcs.telemetryPacket
import org.firstinspires.ftc.teamcode.Variables.system_funcs.update
var runningcommand: Command? = null

@Autonomous
class linetest: LinearOpMode(){
    //override fun runOpMode() = setupAuto(this, path_planner.test_linie(test_linie) )
    override fun runOpMode() {
        init_teleop(this)
        runningcommand = commands.test()
        update()
        waitForStart()
        while(!isStopRequested){
            if(runningcommand != null){
                if(runningcommand!!.run(telemetryPacket)){
                    runningcommand = null
                    autoupdate_tp("COMMAND", "NULLIFIED")
                }
            }
            pp.update()
            update()
        }
    }
}

@Autonomous
class red: LinearOpMode(){
    override fun runOpMode() = setupAuto(this, commands.auto(true))
}

@Autonomous
class blue: LinearOpMode(){
    override fun runOpMode() = setupAuto(this, commands.auto(false))
}