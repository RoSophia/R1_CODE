package org.firstinspires.ftc.teamcode.Systems.drivetrain

//import org.firstinspires.ftc.teamcode.Variables.system_funcs.batteryVoltageSensor
import android.os.SystemClock.sleep
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1
import org.firstinspires.ftc.teamcode.Algorithms.PIDF
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.autocase
import org.firstinspires.ftc.teamcode.Variables.system_funcs.hardwareMap
import org.firstinspires.ftc.teamcode.Variables.system_funcs.imew
import org.firstinspires.ftc.teamcode.Variables.system_funcs.lom
import org.firstinspires.ftc.teamcode.Variables.system_funcs.tp
import java.lang.Math.PI
import java.lang.Math.max


class Drivetrain {

    private val lbmotor = hardwareMap.dcMotor.get("LB")
    private val lfmotor = hardwareMap.dcMotor.get("LF")
    private val rbmotor = hardwareMap.dcMotor.get("RB")
    private val rfmotor = hardwareMap.dcMotor.get("RF")



    fun init(){
        lbmotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        lbmotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        lfmotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        lfmotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rbmotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        rbmotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        rfmotor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        rfmotor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }


    fun drive(){
        var theta = Math.atan2(lom.gamepad1.left_stick_x.toDouble(), lom.gamepad1.left_stick_y.toDouble())
        var sin = Math.sin(theta - PI/4)
        var cos = Math.cos(theta- PI/4)

        var power = Math.hypot(lom.gamepad1.left_stick_x.toDouble(), lom.gamepad1.left_stick_y.toDouble())

        var direction = -lom.gamepad1.right_stick_y.toDouble()

        lbmotor.power = power * sin + direction
        rbmotor.power = power * sin - direction
        lfmotor.power = power * cos + direction
        rfmotor.power = power * cos - direction

        if(power + Math.abs(direction) > 1){
            lbmotor.power /= power + direction
            rbmotor.power /= power + direction
            lfmotor.power /= power + direction
            rfmotor.power /= power + direction
        }
    }

    fun testing(){
        var power = if(lom.gamepad1.left_stick_x != 0.0F) 1.0 else 0.0

        lbmotor.power = power
        rbmotor.power = power
        rfmotor.power = power
        lfmotor.power = power
    }

    fun dummydrive(){
       // var slow = 1.0 - slowdown * 0.75
        var heading = imew.yaw
        autoupdate_tp(tp, "HEADING", heading.toString())
    //    autoupdate_tp(tp, "LEFTODO",leftParallelEncoder.currentPosition.toString())
    //    autoupdate_tp(tp, "RIGHTODO", rightParallelEncoder.currentPosition.toString())
    //    autoupdate_tp(tp, "BACKODO", perpendicularEncoder.currentPosition.toString())
        var speed = -lom.gamepad1.left_stick_y
        var strafe = -lom.gamepad1.left_stick_x
        var turn = lom.gamepad1.right_stick_x

        var fieldcentricspeed = speed*Math.cos(heading)-strafe*Math.sin(heading)
        var fieldcentricstrafe = speed*Math.sin(heading)+strafe*Math.cos(heading)

        lfmotor.power = (fieldcentricspeed + turn + fieldcentricstrafe)
        rfmotor.power = -(fieldcentricspeed - turn + fieldcentricstrafe)
        lbmotor.power = (fieldcentricspeed + turn - fieldcentricstrafe)
        rbmotor.power = -(fieldcentricspeed - turn - fieldcentricstrafe)
    }

    fun dummydriverobotcentric(){
        val y = -lom.gamepad1.left_stick_y // Remember, Y stick value is reversed

        val x = -lom.gamepad1.left_stick_x // Counteract imperfect strafing

        val rx = lom.gamepad1.right_stick_x

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]

        val frontLeftPower = (y + x + rx) / 1.0
        val backLeftPower = (y - x + rx) / 1.0
        val frontRightPower = (-y + x - rx) / 1.0
        val backRightPower = (-y - x - rx) / 1.0

        lfmotor.power = frontLeftPower
        lbmotor.power = backLeftPower
        rfmotor.power = frontRightPower
        rbmotor.power = backRightPower


        autoupdate_tp(tp, "merge trenu", "daaaaaaaa")
    }

    fun autodrive(speed: Double, turn: Double, strafe: Double, heading: Double){
        val ms = speed * Math.sin(heading + imew.yaw)
        val mc = speed * Math.cos(heading + imew.yaw)

        val lfPower = ms + turn + strafe
        val rfPower = mc - turn - strafe
        val lbPower = mc + turn - strafe
        val rbPower = ms - turn + strafe

        lfmotor.power = lfPower
        rfmotor.power = -rfPower
        lbmotor.power = lbPower
        rbmotor.power = -rbPower
    }

    fun strafeleft(){
        lfmotor.power = 1.0
        rbmotor.power = -1.0
        lbmotor.power = 1.0
        lfmotor.power = -1.0
        sleep(3000)
    }

    fun straferight(){
        lfmotor.power = 1.0
        rbmotor.power = -1.0
        lbmotor.power = 1.0
        lfmotor.power = -1.0
        sleep(3000)
    }

    fun gm0drive(){
        rbmotor.direction = DcMotorSimple.Direction.REVERSE
        rfmotor.direction = DcMotorSimple.Direction.REVERSE
        val y = lom.gamepad1.left_stick_y.toDouble() // Remember, Y stick value is reversed

        val x = -lom.gamepad1.left_stick_x * 1.1 // Counteract imperfect strafing

        val rx = -lom.gamepad1.right_stick_x.toDouble()

        val denominator = max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0)
        val frontLeftPower = (y + x + rx) / denominator
        val backLeftPower = (y - x + rx) / denominator
        val frontRightPower = (y - x - rx) / denominator
        val backRightPower = (y + x - rx) / denominator

        lfmotor.power = frontLeftPower
        lbmotor.power = backLeftPower
        rbmotor.power = backRightPower
        rfmotor.power = frontRightPower

    }

    fun pidtopreload(){
     //   var turn = anglepid.update(quality_of_life_funcs.angDiff(imew.yaw, headings[autocase]))
    }
}