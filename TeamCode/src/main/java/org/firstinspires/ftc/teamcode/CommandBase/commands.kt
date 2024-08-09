package org.firstinspires.ftc.teamcode.CommandBase

import com.qualcomm.hardware.ams.AMSColorSensor.Wait
import org.apache.commons.math3.ode.SecondaryEquations
import org.firstinspires.ftc.teamcode.Autonomous.Pose
import org.firstinspires.ftc.teamcode.Pathing.Trajectory
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.bluebackdrop
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.bluepreload
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.droptostack
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.failsafe
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.park
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.redbackdrop
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.redpreload
import org.firstinspires.ftc.teamcode.Pathing.auto_trajectories.stacktodrop
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.fourbarfinalpos
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.fourbarinit
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.larmDown
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.intakeMotorPower
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.lidClosePos
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.lidOpenPos
import org.firstinspires.ftc.teamcode.Variables.system_funcs.arm
import org.firstinspires.ftc.teamcode.Variables.system_funcs.claws
import org.firstinspires.ftc.teamcode.Variables.system_funcs.intake
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pp
import org.firstinspires.ftc.teamcode.Variables.system_funcs.sensor
import org.firstinspires.ftc.teamcode.Variables.system_funcs.slides

object commands{
    fun transfer(): Command{
        return SequentialCommand(
            InstantCommand { arm.goInit() },
            InstantCommand { arm.fourbar.position = fourbarinit },
            InstantCommand { intake.intakeServo.position = 0.8},
            WaitUntilCommand { intake.intakeServo.position == 0.8 },
            InstantCommand { intake.intakeMotor.power = intakeMotorPower},
            SleepCommand(0.2),
            InstantCommand { intake.stop() },
            InstantCommand { intake.lidServo.position = lidOpenPos },
            //WaitUntilCommand { intake.lidServo.position == lidOpenPos },
            SleepCommand(0.2),
            InstantCommand { arm.goDown() },
            SleepCommand(0.2),
            InstantCommand { claws.grab() },
            SleepCommand(0.2),
            InstantCommand { arm.goInit() },
            InstantCommand { arm.fourbar.position =  fourbarinit},
            SleepCommand(0.2),
            InstantCommand { intake.lidServo.position = lidClosePos }
        )
    }

    fun putpreloaddown(): Command{
        return SequentialCommand(
            InstantCommand { intake.lidServo.position = lidOpenPos },
            WaitUntilCommand { intake.lidServo.position == lidOpenPos },
            InstantCommand { arm.goPreloadDown() },
            SleepCommand(0.2),
            InstantCommand { claws.droppurple() },
            SleepCommand(0.2),
            InstantCommand { arm.goUp() }
        )
    }

    fun goup(): Command{
        return SequentialCommand(
            InstantCommand { arm.goUp() },
            SleepCommand(0.1),
            InstantCommand { arm.fourbar.position =  fourbarfinalpos }
        )
    }

    fun goinit(): Command{
        return SequentialCommand(
            InstantCommand { claws.rotator.position = 0.05},
            InstantCommand { claws.drop() },
            SleepCommand(0.1),
            InstantCommand { arm.goInit() },
            SleepCommand(0.2),
            InstantCommand { arm.fourbar.position =  fourbarinit }
        )
    }

    fun raiseslides(i: Int): Command{
        return SequentialCommand(
            InstantCommand { slides.up() },
            SleepCommand(0.2 * (4 - i)),
            InstantCommand { slides.stop() },
        )
    }

    fun lowerslides(i: Int): Command{
        return SequentialCommand(
            InstantCommand { slides.down() },
            SleepCommand(0.2 * (4 - i)),
            InstantCommand { slides.stop() },
        )
    }

    fun cycle(cycle: Int): Command{
        return SequentialCommand(
            InstantCommand { claws.drop() },
            InstantCommand { pp.followtraj(droptostack) },
            WaitUntilCommand { !pp.busy },
            InstantCommand { intake.intakeServo.position = 0.9 - (3 -cycle) * 0.1},
            InstantCommand { pp.followtraj(failsafe) },
            RunUntilCommand ( InstantCommand{ intake.take() }, InstantCommand{ sensor.got2pixels() } ),
            transfer(),
            InstantCommand { pp.followtraj(stacktodrop)},
            WaitUntilCommand { !pp.busy },
            raiseslides(cycle),
            SleepCommand(0.2),
            InstantCommand { arm.goUp() },
            SleepCommand(0.2),
            InstantCommand { claws.drop() },
            SleepCommand(0.2),
            lowerslides(cycle),
            InstantCommand { arm.goInit() },
        )
    }

    fun auto(isred: Boolean): Command{
        return SequentialCommand(
            InstantCommand { pp.followtraj(if(isred) redpreload else bluepreload) },
            WaitUntilCommand { !pp.busy },
            putpreloaddown(),
            InstantCommand { pp.followtraj(if(isred) redbackdrop else bluebackdrop) },
            WaitUntilCommand { !pp.busy },
            SleepCommand(0.2),
            InstantCommand { claws.dropyellow() },
            SleepCommand(0.1),
            InstantCommand { arm.goInit() },
            cycle(3),
            cycle(2),
            cycle(1),
            InstantCommand { pp.followtraj(park) }
        )
    }

    fun test(): Command{
        return InstantCommand{pp.followtraj(Trajectory(Pose(0.0, 0.0, 0.0), Pose(100.0,0.0,0.0)))}
    }
}