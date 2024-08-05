package org.firstinspires.ftc.teamcode.Pathing

import com.acmerobotics.dashboard.config.Config
import org.firstinspires.ftc.teamcode.Algorithms.chain_actioner
import org.firstinspires.ftc.teamcode.Algorithms.quality_of_life_funcs.posdiff
import org.firstinspires.ftc.teamcode.CommandBase.Command
import org.firstinspires.ftc.teamcode.CommandBase.InstantCommand
import org.firstinspires.ftc.teamcode.CommandBase.RunUntilCommand
import org.firstinspires.ftc.teamcode.CommandBase.SequentialCommand
import org.firstinspires.ftc.teamcode.CommandBase.WaitUntilCommand
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.larmPreload
import org.firstinspires.ftc.teamcode.Systems.arm.arm_vars.larmUp
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.lclawOpenPos
import org.firstinspires.ftc.teamcode.Systems.claws.claws_vars.rclawOpenPos
import org.firstinspires.ftc.teamcode.Variables.system_funcs.arm
import org.firstinspires.ftc.teamcode.Variables.system_funcs.claws
import org.firstinspires.ftc.teamcode.Variables.system_funcs.pp

@Config
object path_planner {
    fun redlong(vals: red_long_vals): chain_actioner {
        val trajectorystack = chain_actioner()
        trajectorystack.addTrajectory(vals.StartPreload)
                       .addAction({arm.goPreloadDown()}, {posdiff(arm.larm.position, larmPreload)})
                       .addAction({claws.dropPreload()}, {posdiff(claws.lclaw.position, lclawOpenPos)})
                       .addAction({arm.goUp()}, {posdiff(arm.larm.position, larmUp)})
                       .addTrajectory(vals.PreloadBackDrop)
                       .addAction({claws.dropPreloadBackdrop()}, {posdiff(claws.rclaw.position, rclawOpenPos)})
                       .addTrajectory(vals.Park)
        return trajectorystack
    }

    fun redshort(vals: red_short_vals): chain_actioner{
        val trajectorystack = chain_actioner()
        trajectorystack.addTrajectory(vals.StartPreload)
            .addAction({arm.goPreloadDown()}, {posdiff(arm.larm.position, larmPreload)})
            .addAction({claws.dropPreload()}, {posdiff(claws.lclaw.position, lclawOpenPos)})
            .addAction({arm.goUp()}, {posdiff(arm.larm.position, larmUp)})
            .addTrajectory(vals.PreloadBackDrop)
            .addAction({claws.dropPreloadBackdrop()}, {posdiff(claws.rclaw.position, rclawOpenPos)})
            .addTrajectory(vals.Park)

        return trajectorystack
    }

    fun bluelong(vals: blue_long_vals): chain_actioner{
        val trajectorystack = chain_actioner()
        trajectorystack.addTrajectory(vals.StartPreload)
            .addAction({arm.goPreloadDown()}, {posdiff(arm.larm.position, larmPreload)})
            .addAction({claws.dropPreload()}, {posdiff(claws.lclaw.position, lclawOpenPos)})
            .addAction({arm.goUp()}, {posdiff(arm.larm.position, larmUp)})
            .addTrajectory(vals.PreloadBackDrop)
            .addAction({claws.dropPreloadBackdrop()}, {posdiff(claws.rclaw.position, rclawOpenPos)})
            .addTrajectory(vals.Park)

        return trajectorystack
    }

    fun blueshort(vals: blue_short_vals): chain_actioner{
        val trajectorystack = chain_actioner()
        trajectorystack.addTrajectory(vals.StartPreload)
            .addAction({arm.goPreloadDown()}, {posdiff(arm.larm.position, larmPreload)})
            .addAction({claws.dropPreload()}, {posdiff(claws.lclaw.position, lclawOpenPos)})
            .addAction({arm.goUp()}, {posdiff(arm.larm.position, larmUp)})
            .addTrajectory(vals.PreloadBackDrop)
            .addAction({claws.dropPreloadBackdrop()}, {posdiff(claws.rclaw.position, rclawOpenPos)})
            .addTrajectory(vals.Park)

        return trajectorystack
    }

    fun test_linie(vals: test_linie): Command {
        return SequentialCommand(
            InstantCommand { pp.followtraj(vals.Liniuta)},
            WaitUntilCommand{ !pp.busy },
            InstantCommand { arm.goPreloadDown() }
        )
    }
}