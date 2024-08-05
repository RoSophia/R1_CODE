package org.firstinspires.ftc.teamcode.PurePursuit

import org.firstinspires.ftc.teamcode.Algorithms.PIDF
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.angDiff
import org.firstinspires.ftc.teamcode.Autonomous.Pose
import org.firstinspires.ftc.teamcode.Pathing.Trajectory
import org.firstinspires.ftc.teamcode.Variables.PIDCoefs.pidcoefAngle
import org.firstinspires.ftc.teamcode.Variables.PIDCoefs.pidcoefSpeed
import org.firstinspires.ftc.teamcode.PurePursuit.purepursuit_vars.radius
import org.firstinspires.ftc.teamcode.Variables.system_funcs.drivetrain
import org.firstinspires.ftc.teamcode.Variables.system_funcs.localizer
import kotlin.math.abs
import kotlin.math.atan

class purepursuit {
    lateinit var traj: Trajectory
    var lookahead = Pose()
    var a: Double = 0.0
    val someconstant: Double = 0.001
    var haveTraj: Boolean = false
    var done: Boolean = false
    var error: Boolean = false
    var speedpid = PIDF(pidcoefSpeed)
    var turnpid = PIDF(pidcoefAngle)
    val tolerance: Double = 0.0
    val angtolerance: Double = 0.0

    val busy: Boolean
        get() {
            return haveTraj && !done && !error
        }

    fun initpurepursuit(traj_real: Trajectory){
        traj = traj_real
        a = 0.0
        lookahead = traj.startpos
    }

    fun isincircle(lookahead: Pose, robotPos: Pose, radius: Double): Boolean {
        return (lookahead - robotPos).distance() < radius
    }

    fun update() {
        val robotPos = localizer.robotpose
        val err = robotPos - traj.endpos
        var speed: Double
        var turn: Double
        var heading: Double

        while (a <= 1.0 && isincircle(lookahead, robotPos, radius)) {
            lookahead = traj[a]
            a += someconstant
        }
        speed = speedpid.update((lookahead-robotPos).distance())
        turn = turnpid.update(angDiff(lookahead.heading, robotPos.heading))
        heading = atan((lookahead.x - robotPos.x) / (lookahead.y - robotPos.y))

        drivetrain.autodrive(speed, turn, 0.0,  heading)

        if(err.distance() < tolerance && err.heading < angtolerance){
            haveTraj = false
            done = true
        }
    }

    fun followtraj(t: Trajectory){
        haveTraj = true
        done = false
        initpurepursuit(t)
    }
}