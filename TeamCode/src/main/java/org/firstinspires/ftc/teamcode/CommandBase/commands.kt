package org.firstinspires.ftc.teamcode.CommandBase

import com.qualcomm.hardware.ams.AMSColorSensor.Wait
import org.apache.commons.math3.ode.SecondaryEquations
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.fourbarfinalpos
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.fourbarinit
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.larmDown
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.lidClosePos
import org.firstinspires.ftc.teamcode.Systems.intake.intake_vars.lidOpenPos
import org.firstinspires.ftc.teamcode.Variables.system_funcs.arm
import org.firstinspires.ftc.teamcode.Variables.system_funcs.claws
import org.firstinspires.ftc.teamcode.Variables.system_funcs.intake
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pp

object commands{
    fun transfer(): Command{
        return SequentialCommand(
            SleepCommand(2.0),
            InstantCommand { intake.take() },
            SleepCommand(0.2),
            InstantCommand { intake.spit() },
            SleepCommand(0.2),
            InstantCommand { intake.intakeMotor.power = 0.0 },
            InstantCommand { arm.goInit() },
            InstantCommand { arm.fourbar.position = fourbarinit },
            InstantCommand { intake.intakeServo.position = 0.75},
            WaitUntilCommand { intake.intakeServo.position == 0.75 },
            InstantCommand { intake.lidServo.position = lidOpenPos },
            WaitUntilCommand { intake.lidServo.position == lidOpenPos },
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
            InstantCommand { arm.fourbar.position =  fourbarfinalpos }
        )
    }

    fun goinit(): Command{
        return SequentialCommand(
            InstantCommand { claws.drop() },
            SleepCommand(0.1),
            InstantCommand { arm.goInit() },
            InstantCommand { arm.fourbar.position =  fourbarinit }
        )
    }

    fun cycle(cycle: Int): Command{
        return SequentialCommand()
    }

    fun auto(isred: Boolean): Command{
        return SequentialCommand(
            InstantCommand { pp.followtraj(if(isred) redpreload[0] else bluepreload[1]) },
            WaitUntilCommand { !pp.busy },
            putpreloaddown(),
            InstantCommand { pp.followtraj(if(isred) redpreload[1] else bluepreload[1]) },
            WaitUntilCommand { !pp.busy },
            SleepCommand(0.2),
            InstantCommand { claws.dropyellow() },
            SleepCommand(0.1),
            InstantCommand { arm.goInit() },
            cycle(3),
            InstantCommand { pp.followtraj(park) }
        )
    }
}