package org.firstinspires.ftc.teamcode.Localizer

import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.autoupdate_tp
import org.firstinspires.ftc.teamcode.Autonomous.Pose
import org.firstinspires.ftc.teamcode.Variables.system_funcs.hardwareMap
import org.firstinspires.ftc.teamcode.Variables.system_funcs.tp
import org.firstinspires.ftc.teamcode.hardware.Encoder
import java.lang.Math.cos
import java.lang.Math.sin


class ThreeWheelLocalizer(): Localizer{

    private val FORWARD_TICKS_PER_CM: Double = 223863.0/300.0
    private val LATERAL_TICKS_PER_CM: Double = 222970.0/300.0

    var robotpose = Pose()

    //offsets in cm from center of rotation,
    //y coord for parallels,
    //x coord for perpendicular
    //going right = -
    //going letftt = +

    private val LEFT_PARALLEL_OFFSET: Double = 12.0
    private val RIGHT_PARALLEL_OFFSET: Double = 12.0
    private val PERPENDICULAR_OFFSET: Double = 15.0

    private val leftParallelEncoder = hardwareMap.dcMotor.get("RF")
    private val rightParallelEncoder = hardwareMap.dcMotor.get("RB")
    private val perpendicularEncoder = hardwareMap.dcMotor.get("INTAKE")

    private var lastLeftParallelReading: Int = 0
    private var lastRightParallelReading: Int = 0
    private var lastPerpendicularReading: Int = 0


    fun init() {
        rightParallelEncoder.direction = DcMotorSimple.Direction.REVERSE

        leftParallelEncoder.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        rightParallelEncoder.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        perpendicularEncoder.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER

        leftParallelEncoder.mode = DcMotor.RunMode.RUN_USING_ENCODER
        rightParallelEncoder.mode = DcMotor.RunMode.RUN_USING_ENCODER
        perpendicularEncoder.mode = DcMotor.RunMode.RUN_USING_ENCODER
    }

    fun update() {

        var deltaLeftParallelReading: Double = (leftParallelEncoder.currentPosition - lastLeftParallelReading).toDouble()
        var deltaRightParallelReading: Double = (rightParallelEncoder.currentPosition - lastRightParallelReading).toDouble()
        var deltaPerpendicularReading: Double = (perpendicularEncoder.currentPosition - lastPerpendicularReading).toDouble()

        lastLeftParallelReading += deltaLeftParallelReading.toInt()
        lastRightParallelReading += deltaRightParallelReading.toInt()
        lastPerpendicularReading += deltaPerpendicularReading.toInt()

        deltaLeftParallelReading /= FORWARD_TICKS_PER_CM
        deltaRightParallelReading /= FORWARD_TICKS_PER_CM
        deltaPerpendicularReading /= LATERAL_TICKS_PER_CM

        val deltaHeading: Double = (deltaRightParallelReading - deltaLeftParallelReading)/ 24

        deltaLeftParallelReading += deltaHeading * LEFT_PARALLEL_OFFSET
        deltaRightParallelReading += deltaHeading * RIGHT_PARALLEL_OFFSET
        deltaPerpendicularReading += deltaHeading * PERPENDICULAR_OFFSET

        val relativeDeltaForward = (deltaLeftParallelReading+deltaRightParallelReading)/2
        val relativeDeltaStrafe = deltaPerpendicularReading

        robotpose += Pose(
                relativeDeltaForward * kotlin.math.cos(robotpose.heading + deltaHeading / 2) - relativeDeltaStrafe * kotlin.math.sin(robotpose.heading + deltaHeading / 2),
                relativeDeltaForward * kotlin.math.sin(robotpose.heading + deltaHeading / 2) + relativeDeltaStrafe * kotlin.math.cos(robotpose.heading + deltaHeading / 2),
                deltaHeading
        )

        robotpose.heading = AngleUnit.normalizeRadians(robotpose.heading)

        autoupdate_tp(tp, "LEFTODO", "${leftParallelEncoder.currentPosition}")
        autoupdate_tp(tp, "RIGHTODO", "${rightParallelEncoder.currentPosition}")
        autoupdate_tp(tp, "BACKODO", "${perpendicularEncoder.currentPosition}")
        autoupdate_tp(tp, "HEADINGODO", "${deltaHeading}")
        autoupdate_tp(tp, "ROBOTPOS", String.format("%.4f", robotpose.x) + "\n" + String.format("%.4f", robotpose.y) + "\n" + String.format("%.4f", robotpose.heading))
    }
}