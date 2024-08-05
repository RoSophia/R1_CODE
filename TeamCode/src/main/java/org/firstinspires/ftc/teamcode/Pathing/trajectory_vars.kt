package org.firstinspires.ftc.teamcode.Pathing

import com.acmerobotics.dashboard.config.Config
import org.firstinspires.ftc.teamcode.Autonomous.Pose
import org.firstinspires.ftc.teamcode.Autonomous.PreloadTrajectorySet
import org.firstinspires.ftc.teamcode.Autonomous.Vector3Trajectories
import org.firstinspires.ftc.teamcode.Autonomous.auto_vars.autocase
import java.util.Vector

@Config
object red_long_vals{

    @JvmField
    val Preload0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    @JvmField
    val PreloadtoBack0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    private val preloadSet0 = PreloadTrajectorySet(Preload0, PreloadtoBack0)
    private val preloadSet1 = PreloadTrajectorySet(Preload1, PreloadtoBack1)
    private  val preloadSet2 = PreloadTrajectorySet(Preload2, PreloadtoBack2)

    private val preloadSequences = Vector3Trajectories(preloadSet0, preloadSet1, preloadSet2)
    @JvmField
    val StartPreload = preloadSequences[autocase][0]
    @JvmField
    val PreloadBackDrop = preloadSequences[autocase][1]

    @JvmField
    val Park = Trajectory(Pose(0.0, 0.0, 0.0), Pose(100.0, 0.0, 0.0))
}

@Config
object red_short_vals{
    @JvmField
    val Preload0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    @JvmField
    val PreloadtoBack0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    private val preloadSet0 = PreloadTrajectorySet(Preload0, PreloadtoBack0)
    private val preloadSet1 = PreloadTrajectorySet(Preload1, PreloadtoBack1)
    private  val preloadSet2 = PreloadTrajectorySet(Preload2, PreloadtoBack2)

    private val preloadSequences = Vector3Trajectories(preloadSet0, preloadSet1, preloadSet2)
    @JvmField
    val StartPreload = preloadSequences[autocase][0]
    @JvmField
    val PreloadBackDrop = preloadSequences[autocase][1]

    @JvmField
    val Park = Trajectory(Pose(0.0, 0.0, 0.0), Pose(100.0, 0.0, 0.0))
}

@Config
object blue_long_vals {
    @JvmField
    val Preload0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    @JvmField
    val PreloadtoBack0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    private val preloadSet0 = PreloadTrajectorySet(Preload0, PreloadtoBack0)
    private val preloadSet1 = PreloadTrajectorySet(Preload1, PreloadtoBack1)
    private  val preloadSet2 = PreloadTrajectorySet(Preload2, PreloadtoBack2)

    private val preloadSequences = Vector3Trajectories(preloadSet0, preloadSet1, preloadSet2)
    @JvmField
    val StartPreload = preloadSequences[autocase][0]
    @JvmField
    val PreloadBackDrop = preloadSequences[autocase][1]

    @JvmField
    val Park = Trajectory(Pose(0.0, 0.0, 0.0), Pose(100.0, 0.0, 0.0))
}

@Config
object blue_short_vals {
    @JvmField
    val Preload0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val Preload2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    @JvmField
    val PreloadtoBack0 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack1 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))
    @JvmField
    val PreloadtoBack2 = Trajectory(Pose(0.0, 0.0, 0.0), Pose(0.0, 0.0, 0.0))

    private val preloadSet0 = PreloadTrajectorySet(Preload0, PreloadtoBack0)
    private val preloadSet1 = PreloadTrajectorySet(Preload1, PreloadtoBack1)
    private  val preloadSet2 = PreloadTrajectorySet(Preload2, PreloadtoBack2)

    private val preloadSequences = Vector3Trajectories(preloadSet0, preloadSet1, preloadSet2)
    @JvmField
    val StartPreload = preloadSequences[autocase][0]
    @JvmField
    val PreloadBackDrop = preloadSequences[autocase][1]

    @JvmField
    val Park = Trajectory(Pose(0.0, 0.0, 0.0), Pose(100.0, 0.0, 0.0))
}

@Config
object test_linie{
    @JvmField
    var Liniuta = Trajectory(Pose(0.0, 0.0, 0.0), Pose(100.0, 0.0, 2.0))
}