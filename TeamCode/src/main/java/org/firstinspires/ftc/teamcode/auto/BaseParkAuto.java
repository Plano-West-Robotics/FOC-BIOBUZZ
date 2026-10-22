package org.firstinspires.ftc.teamcode.auto;

import com.pedropathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;

import com.pedropathing.ivy.Scheduler;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

public class BaseParkAuto extends OpMode
{
    //poses
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(8, 133, 90);
    private final Pose park = poseFactory.of(12, 120, 270);

    //paths
    private Path park() {
        return line(start, park).constant(park);
    }

    @Override
    public final void init()
    {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        telemetry.update();
    }

//    @Override
//    public final void init_loop()
//    {
//        telemetry.update();
//    }

    @Override
    public final void start()
    {
        schedule(follow(follower, park()));
    }

    @Override
    public final void loop()
    {
        follower.update();
        Scheduler.execute();
        telemetry.update();
    }
}
