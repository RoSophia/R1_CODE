package org.firstinspires.ftc.teamcode.TeleOp

import com.outoftheboxrobotics.photoncore.Photon
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.Algorithms.PIDF
import org.firstinspires.ftc.teamcode.Algorithms.action
import org.firstinspires.ftc.teamcode.Algorithms.chain_actioner
import org.firstinspires.ftc.teamcode.Algorithms.color_detection
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.CommandBase.commands
import org.firstinspires.ftc.teamcode.Localizer.ThreeWheelLocalizer
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.armstack
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.emptystack
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.fourbarfinalpos
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.fourbarinit
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.larmDown
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.larmUp
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.rarmDown
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.rarmUp
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.clawRotateMaxPos
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.lclawClosePos
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.lclawOpenPos
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.rclawClosePos
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.rclawOpenPos
import org.firstinspires.ftc.teamcode.Systems.colorsensor.ColorSensor
import org.firstinspires.ftc.teamcode.Systems.intake.Intake
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.lidClosePos
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.lidOpenPos
import org.firstinspires.ftc.teamcode.Systems.slides.slides_vars.lslideRange
import org.firstinspires.ftc.teamcode.Systems.slides.slides_vars.ltargetposition
import org.firstinspires.ftc.teamcode.Systems.slides.slides_vars.rslideRange
import org.firstinspires.ftc.teamcode.Systems.slides.slides_vars.rtargetposition
import org.firstinspires.ftc.teamcode.Systems.slides.slides_vars.slideforce
import org.firstinspires.ftc.teamcode.Variables.PIDCoefs.pidcoefSlide
import org.firstinspires.ftc.teamcode.Variables.system_funcs
import org.firstinspires.ftc.teamcode.Variables.system_funcs.arm
import org.firstinspires.ftc.teamcode.Variables.system_funcs.camera
import org.firstinspires.ftc.teamcode.Variables.system_funcs.claws
import org.firstinspires.ftc.teamcode.Variables.system_funcs.currentcommand
import org.firstinspires.ftc.teamcode.Variables.system_funcs.drivetrain
import org.firstinspires.ftc.teamcode.Variables.system_funcs.droneLauncher
import org.firstinspires.ftc.teamcode.Variables.system_funcs.hardwareMap
import org.firstinspires.ftc.teamcode.Variables.system_funcs.imew
import org.firstinspires.ftc.teamcode.Variables.system_funcs.init_teleop
import org.firstinspires.ftc.teamcode.Variables.system_funcs.intake
import org.firstinspires.ftc.teamcode.Variables.system_funcs.localizer
import org.firstinspires.ftc.teamcode.Variables.system_funcs.slides
import org.firstinspires.ftc.teamcode.Variables.system_funcs.telemetryPacket
import org.firstinspires.ftc.teamcode.Variables.system_funcs.tp
import org.firstinspires.ftc.teamcode.Variables.system_funcs.update
import org.firstinspires.ftc.teamcode.Variables.system_vars.clawRotateInit
import org.firstinspires.ftc.teamcode.Variables.system_vars.intakeInit
import org.firstinspires.ftc.teamcode.Variables.system_vars.larmInit
import org.firstinspires.ftc.teamcode.Variables.system_vars.rarmInit
import org.firstinspires.ftc.teamcode.Variables.system_vars.trigtresh

var isgoingup: Boolean = false
var rpid: PIDF = PIDF(pidcoefSlide)
var lpid: PIDF = PIDF(pidcoefSlide)

var isopen: Boolean = false
var isposchanged: Boolean = false
var isintaking: Boolean = false
var isintaking2: Boolean = false
var isarmgoingup: Boolean = false
var isarmgoingdown: Boolean = false
var isarmgoinginit: Boolean = false
var isresetting: Boolean = false
var islaunchedd: Boolean = false
val chainactioner =  chain_actioner()
var isgoingdown: Boolean = false
var isgoinginit: Boolean = false
var ishanging: Boolean = false

@TeleOp
class testcumoneda: LinearOpMode(){

    override fun runOpMode() {
        init_teleop(this)
        //localizer = ThreeWheelLocalizer(hardwareMap)
        //localizer.init()
        waitForStart()
        while(!isStopRequested){
            drivetrain.dummydriverobotcentric()
          //  localizer.update()
        }
    }

}

@TeleOp
class teleopSENSOR: LinearOpMode(){
    lateinit var sensor: ColorSensor
    var isnotseeing: Boolean = false
    var ep = ElapsedTime()
    override fun runOpMode() {
        init_teleop(this)
        sensor = ColorSensor()

        waitForStart()
        ep.startTime()
        while(!isStopRequested){
            if(ep.milliseconds() > 1000){
                if(sensor.got2pixels()){
                    ep.reset()
                    ep.startTime()
                    gamepad2.rumble(200)
                }
            }

            autoupdate_tp(tp, "RIGHTSENSOR", "${sensor.rsensor.red()}" +" ${sensor.rsensor.blue()}" + " ${sensor.rsensor.green()}" + " ${sensor.rsensor.alpha()}")
            autoupdate_tp(tp, "LEFTSENSOR", "${sensor.lsensor.red()}" +" ${sensor.lsensor.blue()}" + " ${sensor.lsensor.green()}" + " ${sensor.lsensor.alpha()}")
        }
    }
}

@TeleOp
class teleopHAIDEEEEEEEEE: LinearOpMode(){
    var x: Int = 0
    override fun runOpMode() {
        lateinit var sensor: ColorSensor
        init_teleop(this)
        sensor = ColorSensor()
        var ep = ElapsedTime()
        var RAAAAAAAAAH: Boolean = false
        var rah: Boolean = false
        var raaaaaaaah: Boolean = false
        var armactionstack = chain_actioner()
        var skrrrrrr: Boolean = false
        var isnotinitpos: Boolean = false
        var ishanging2: Boolean = false
        var isnotrotatingleft: Boolean = false
        var isnotrotatingright: Boolean = false

        waitForStart()
        ep.startTime()

        while(!isStopRequested){
            if(currentcommand != commands.transfer()){
                if(sensor.got2pixels()){
                    ep.reset()
                    ep.startTime()
                    gamepad2.rumble(200)
                    currentcommand = commands.transfer()
                }
            }
            //TRANSFER TO INIT
            if(gamepad2.a && !RAAAAAAAAAH){

                /*
                golden sequence

                intake.intakeServo.position = 0.75
                arm.goInit()
                intake.lidServo.position= lidOpenPos
                sleep(400) //250
                arm.goDown()
                sleep(450) //350
                claws.grab()
                sleep(500) //450
                arm.goInit()
                sleep(500) //350
                intake.lidServo.position = lidClosePos*/
                sleep(300)

                currentcommand = commands.transfer()
            }
            RAAAAAAAAAH = gamepad2.a

            //TRANSFER DOWN
            if(gamepad2.b && !rah){
                currentcommand = commands.goinit()
            }
            rah = gamepad2.b

            //UP
            if(gamepad2.x && !raaaaaaaah){
                currentcommand = commands.goup()
            }
            raaaaaaaah = gamepad2.x

            //INTAKE
            if(gamepad2.right_bumper && !isintaking) {
                autoupdate_tp(tp, "alo intake", "da")
                intake.take()
            }

            if(gamepad2.left_bumper && !isintaking2) {
                intake.spit()
            }

            if((!gamepad2.left_bumper && isintaking2) || (!gamepad2.right_bumper && isintaking)){
                autoupdate_tp(tp, "intake zi nu", "nu")
                intake.stop()
            }
            isintaking = gamepad2.right_bumper
            isintaking2 = gamepad2.left_bumper

            //DRIVETRAIN
           // drivetrain.dummydriverobotcentric()
            drivetrain.gm0drive()

            //IMEW RESET
            if(gamepad1.ps && !isresetting){
                imew.reset()
            }
            isresetting = gamepad1.ps

            //DRONE
            if(gamepad2.dpad_up && !islaunchedd){
                droneLauncher.launch()
            }
            islaunchedd = gamepad2.dpad_up

            //INTAKE POS
            if(gamepad1.b && !isposchanged){
                autoupdate_tp(tp, "alo intakepos", "daaaaaa")
                intake.changepos()
            }
            isposchanged = gamepad1.b

            if(gamepad1.a && !isnotinitpos){
                intake.intakeServo.position = intakeInit
            }
            isnotinitpos = gamepad1.a


            slides.run()

            if(gamepad2.left_trigger < 0.5 && !isnotrotatingleft){
                claws.rotator.position += 0.1
                if(claws.rotator.position > clawRotateMaxPos){
                    claws.rotator.position = clawRotateInit
                }
            }
            isnotrotatingleft = gamepad2.left_trigger > 0.5

            if(gamepad2.right_trigger < 0.5 && !isnotrotatingright){
                claws.rotator.position -= 0.1
                if(claws.rotator.position <  clawRotateInit){
                    claws.rotator.position = clawRotateInit
                }
            }
            isnotrotatingright = gamepad2.right_trigger > 0.5

            if(gamepad2.dpad_down && !ishanging) {
                slides.lslide.power = -1.0
                slides.rslide.power = 1.0
                sleep(3000)
            }
            ishanging = gamepad2.dpad_down

            if(currentcommand != null){
                if(currentcommand!!.run(telemetryPacket)){
                    currentcommand = null
                }
            }

            update()
        }
    }
}

@TeleOp
class teleopcapac: LinearOpMode(){

    override fun runOpMode() {
        init_teleop(this)
        while(!isStopRequested){
        }

    }

}
