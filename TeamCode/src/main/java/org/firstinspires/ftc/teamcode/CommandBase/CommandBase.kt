package org.firstinspires.ftc.teamcode.CommandBase

import com.acmerobotics.dashboard.telemetry.TelemetryPacket

interface Command {
    fun run(packet: TelemetryPacket): Boolean
}

class InstantCommand(val lambdaCommand: LambdaCommand): Command {
    override fun run(packet: TelemetryPacket): Boolean {
        lambdaCommand.run()
        return true
    }

    fun interface LambdaCommand{
        fun run()
    }
}

class SequentialCommand(vararg commands: Command): Command {
    var commandList = commands.asList()

    override fun run(packet: TelemetryPacket): Boolean {
        if(commandList.isEmpty()) return true
        if(commandList.first().run(packet)) {
            commandList = commandList.drop(1)
            run(packet)
        }
        return false
    }

}

class RunUntilCommand(val runnableCommand: Command, val untilCommand: Command) : Command {
    override fun run(packet: TelemetryPacket): Boolean {
        runnableCommand.run(packet)
        return untilCommand.run(packet)
    }
}

class WaitUntilCommand(val booleanFunction: BooleanFunction) : Command {
    override fun run(packet: TelemetryPacket): Boolean {
        return booleanFunction.run()
    }

    fun interface BooleanFunction {
        fun run(): Boolean
    }
}

class SleepCommand(val deltaTime: Double): Command {
    var startTime: Double = -1.0

    override fun run(packet: TelemetryPacket): Boolean {
        //start waiting from the first run
        if(startTime == -1.0){
            startTime = System.nanoTime() / 1e9
        }
        return startTime + deltaTime <= System.nanoTime() / 1e9
    }
}
