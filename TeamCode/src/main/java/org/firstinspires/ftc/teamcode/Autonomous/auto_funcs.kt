package org.firstinspires.ftc.teamcode.Autonomous

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.autocase
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.isRed
import org.firstinspires.ftc.teamcode.CommandBase.Command
import org.firstinspires.ftc.teamcode.Variables.system_funcs.arm
import org.firstinspires.ftc.teamcode.Variables.system_funcs.camera
import org.firstinspires.ftc.teamcode.Variables.system_funcs.init_teleop
//import org.firstinspires.ftc.teamcode.Variables.system_funcs.pipeline
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pp
import org.firstinspires.ftc.teamcode.Variables.system_funcs.telemetryPacket
import org.firstinspires.ftc.teamcode.Variables.system_funcs.update
import java.lang.Thread.sleep

object auto_funcs {

    fun setupAuto(lom: LinearOpMode, path: Command?) {
        init_teleop(lom)
        autoupdate_tp("CASE", autocase)
        autoupdate_tp("ISRED", isRed)
        camera.stop()
        arm.fourbar.position = 1.0
        sleep(500)
        arm.fourbar.position = 0.7
        var runningcommand = path
        while (!lom.isStopRequested) {

            if(runningcommand != null){
                if(runningcommand.run(telemetryPacket)){
                    runningcommand = null
                }
            }
            pp.update()
            update()
        }
    }
}