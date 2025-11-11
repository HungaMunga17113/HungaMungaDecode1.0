package org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates.MecanumDrive;
import org.firstinspires.ftc.teamcode.Roadrunner.subsystems.Intake;
import org.firstinspires.ftc.teamcode.Roadrunner.subsystems.Outtake;

@Config
@Autonomous(name = "RR Blue 9 Auton")
public class RR_Blue_9_Auton extends LinearOpMode {


    @Override
    public void runOpMode() {
        //Pose that the robot starts at
        Pose2d initialPose = new Pose2d(-55, -46, Math.toRadians(235));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Intake intake = new Intake(hardwareMap);
        Outtake outtake = new Outtake(hardwareMap);


        //-----------------------Paths-----------------------\\
        Action shoot1path = drive.actionBuilder(initialPose)
                .waitSeconds(0.3)
                .strafeToConstantHeading(new Vector2d(-45,-45))
                .waitSeconds(0.25)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.5)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.5)
                .stopAndAdd(outtake.idle())
                .build();

        Action intake1path = drive.actionBuilder(new Pose2d(-45, -45, Math.toRadians(235)))
                .strafeToLinearHeading(new Vector2d(-13.5,-20),Math.toRadians(270))
                .stopAndAdd(intake.in())
                .strafeToConstantHeading(new Vector2d(-14.5,-55),new TranslationalVelConstraint(17.5))
                //.lineToYLinearHeading(37.5, Math.toRadians(80))
                //.lineToYSplineHeading(55, Math.toRadians(100))
                .lineToYLinearHeading(-50, Math.toRadians(300))
                .lineToYSplineHeading(-42.67, Math.toRadians(240))
                .build();

        Action shoot2path = drive.actionBuilder(new Pose2d(-14.5, -45, Math.toRadians(270)))
                .strafeToLinearHeading(new Vector2d(-45,-45),Math.toRadians(235))
                .stopAndAdd(intake.idle())
                .waitSeconds(0.25)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.5)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.5)
                .stopAndAdd(outtake.idle())
                .build();

        Action intake2path = drive.actionBuilder(new Pose2d(-45, -45, Math.toRadians(235)))
                .strafeToLinearHeading(new Vector2d(11,-17),Math.toRadians(270))
                .stopAndAdd(intake.in())
                .strafeToConstantHeading(new Vector2d(10,-62),new TranslationalVelConstraint(20))
                //.strafeToConstantHeading(new Vector2d(9,50))
                .lineToYLinearHeading(-53.5, Math.toRadians(295))
                .lineToYSplineHeading(-43, Math.toRadians(245))
                .build();

        Action shoot3path = drive.actionBuilder(new Pose2d(10, -43, Math.toRadians(245)))
                .strafeToLinearHeading(new Vector2d(-45,-45),Math.toRadians(235))
                .stopAndAdd(intake.idle())
                .waitSeconds(0.25)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.5)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.5)
                .stopAndAdd(outtake.idle())
                .build();

        Action intake3path = drive.actionBuilder(new Pose2d(-45, -45, Math.toRadians(235)))
                .strafeToLinearHeading(new Vector2d(33.5,-17),Math.toRadians(270))
                .stopAndAdd(intake.in())
                .strafeToConstantHeading(new Vector2d(32,-62),new TranslationalVelConstraint(20))
                .build();

        Action shoot4path = drive.actionBuilder(new Pose2d(32, -62, Math.toRadians(270)))
                //.strafeToConstantHeading(new Vector2d(31.5,50))
                .lineToYLinearHeading(-53.5, Math.toRadians(295))
                .lineToYSplineHeading(-45, Math.toRadians(245))
                .strafeToLinearHeading(new Vector2d(-45,-45),Math.toRadians(235))
                .stopAndAdd(intake.idle())
                .waitSeconds(0.25)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.5)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.5)
                .stopAndAdd(outtake.idle())
                .build();

        Action extra = drive.actionBuilder(new Pose2d(-45, -45, Math.toRadians(235)))
                .strafeToLinearHeading(new Vector2d(2.5,-45),Math.toRadians(90))
                .build();




        // Initialize (What happens before when you press start)
        Actions.runBlocking(
                new SequentialAction(
                        //        intake.in()
                )
        );

        waitForStart();

        if (isStopRequested()) return;

        //Run (What happens when you press start)
        Actions.runBlocking(
                new SequentialAction(
                        new SequentialAction(
                                shoot1path
                                //  outtake.shoot(),
                                //  outtake.down(),
                                //  outtake.idle()
                        ),
                        new SequentialAction(
                                //   intake.in(),
                                intake1path
                                //    intake.idle()
                        ),
                        new SequentialAction(
                                shoot2path
                                //    outtake.shoot(),
                                //    outtake.down(),
                                //    outtake.idle()
                        ),
                        new SequentialAction(
                                //       intake.in(),
                                intake2path
                                //      intake.idle()
                        ),
                        new SequentialAction(
                                shoot3path
                                //       outtake.shoot(),
                                //       outtake.down(),
                                //       outtake.idle()
                        ),
                        new SequentialAction(
                                extra
                        )
                )
        );
    }
}