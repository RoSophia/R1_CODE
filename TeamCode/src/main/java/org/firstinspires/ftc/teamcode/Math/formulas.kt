package org.firstinspires.ftc.teamcode.Math

object formulas {

    fun max(a: Double, b: Double): Double{
        return if(a > b) {
            a
        }else {
            b
        }
    }

    fun min(a: Double, b: Double): Double{
        return if(a < b) {
            a
        }else {
            b
        }
    }

    fun abs(a: Double): Double{
        return if(a < 0){
            -a
        }
        else{
            a
        }
    }

    fun angdiff(ang1: Double, ang2: Double): Double{
        return ang1
    }

    fun Double.format(digits: Int) = "%.${digits}f".format(this)
}