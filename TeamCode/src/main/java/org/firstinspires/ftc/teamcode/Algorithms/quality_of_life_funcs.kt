package org.firstinspires.ftc.teamcode.Algorithms

import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.Math.formulas
import java.lang.Math.PI
import java.lang.Math.floor

object quality_of_life_funcs {
    fun autoupdate_tp(telemetry: Telemetry, string: String, string2: String){
        telemetry.addData(string, string2)
        telemetry.update()
    }

    fun posdiff(servoPos1: Double, servoPos2: Double) = formulas.abs(servoPos1 - servoPos2) < 0.001

    @JvmStatic
    fun floatMod(o1: Double, o2: Double): Double {
        return o1 - floor(o1 / o2) * o2
    }

    @JvmStatic
    fun angNorm(o1: Double): Double {
        return floatMod(o1, 2 * PI)
    }

    @JvmStatic
    fun angDiff(o1: Double, o2: Double): Double {
        return floatMod(o2 - o1 + PI, PI * 2) - PI
    }

    @JvmStatic
    fun liniuta(a: Double, b: Double, f: Double): Double {
        return a + f * (b - a)
    }
}
