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

import org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates.MecanumDriveV1;
import org.firstinspires.ftc.teamcode.Roadrunner.subsystems.Intake;
import org.firstinspires.ftc.teamcode.Roadrunner.subsystems.Outtake;

@Config
@Autonomous(name = "Align Red Auton")
public class Align_Red_Auton extends LinearOpMode {


    @Override
    public void runOpMode() {
        //Pose that the robot starts at
        Pose2d initialPose = new Pose2d(-55, 46, Math.toRadians(127));
        MecanumDriveV1 drive = new MecanumDriveV1(hardwareMap, initialPose);
        Intake intake = new Intake(hardwareMap);
        Outtake outtake = new Outtake(hardwareMap);


        //-----------------------Paths-----------------------\\
        Action shoot1path = drive.actionBuilder(initialPose)
                .strafeToConstantHeading(new Vector2d(-45,45))
                .waitSeconds(0.22)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.335)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.335)
                .stopAndAdd(outtake.idle())
                .build();

        Action intake1path = drive.actionBuilder(new Pose2d(-45, 45, Math.toRadians(127)))
                .strafeToLinearHeading(new Vector2d(-13.5,22),Math.toRadians(90))
                .stopAndAdd(intake.in())
                .strafeToConstantHeading(new Vector2d(-14.5,55),new TranslationalVelConstraint(17.5))

                .strafeToConstantHeading(new Vector2d(-13,43))
                .strafeToLinearHeading(new Vector2d(-3.15,60),Math.toRadians(180))
                .waitSeconds(0.3)
                /*
                .lineToYLinearHeading(50, Math.toRadians(60))
                .lineToYSplineHeading(42.67, Math.toRadians(120))
                */
                .build();

        Action shoot2path = drive.actionBuilder(new Pose2d(-3.15, 60, Math.toRadians(180)))
                .strafeToLinearHeading(new Vector2d(-45,45),Math.toRadians(125))
                .stopAndAdd(intake.idle())
                .waitSeconds(0.22)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.335)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.335)
                .stopAndAdd(outtake.idle())
                .build();

        Action intake2path = drive.actionBuilder(new Pose2d(-45, 45, Math.toRadians(125)))
                .strafeToLinearHeading(new Vector2d(11.1,17),Math.toRadians(90))
                .stopAndAdd(intake.in())
                .strafeToConstantHeading(new Vector2d(10.10,62),new TranslationalVelConstraint(20))
                //.strafeToConstantHeading(new Vector2d(9,50))
                .lineToYLinearHeading(53.5, Math.toRadians(65))
                .lineToYSplineHeading(42.5, Math.toRadians(115))
                .build();

        Action shoot3path = drive.actionBuilder(new Pose2d(10.10, 43, Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(-45,45),Math.toRadians(125))
                .stopAndAdd(intake.idle())
                .waitSeconds(0.22)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.335)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.335)
                .stopAndAdd(outtake.idle())
                .build();

        Action intake3path = drive.actionBuilder(new Pose2d(-45, 45, Math.toRadians(125)))
                .strafeToLinearHeading(new Vector2d(32.75,17.75),Math.toRadians(90))
                .stopAndAdd(intake.in())
                .strafeToConstantHeading(new Vector2d(31.25,62),new TranslationalVelConstraint(20))
                .build();

        Action shoot4path = drive.actionBuilder(new Pose2d(31.25, 62, Math.toRadians(90)))
                //.strafeToConstantHeading(new Vector2d(31.5,50))
                .lineToYLinearHeading(53.5, Math.toRadians(65))
                .lineToYSplineHeading(41.5, Math.toRadians(115))
                .strafeToLinearHeading(new Vector2d(-47.25,45),Math.toRadians(125))
                .stopAndAdd(intake.idle())
                .waitSeconds(0.22)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.335)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.335)
                .stopAndAdd(outtake.idle())
                .build();

        Action extra = drive.actionBuilder(new Pose2d(-47.25, 45, Math.toRadians(125)))
                .strafeToLinearHeading(new Vector2d(-20,55),Math.toRadians(90))
                .build();
        Action extraTest = drive.actionBuilder(new Pose2d(-45, 45, Math.toRadians(125)))
                .strafeToLinearHeading(new Vector2d(-20,55),Math.toRadians(90))
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
                        ),
                        /*
                        new SequentialAction(
                                intake1path
                        ),
                        new SequentialAction(
                                shoot2path
                        ),
                        new SequentialAction(
                                intake2path
                        ),
                        new SequentialAction(
                                shoot3path
                        ),
                        new SequentialAction(
                                intake3path
                        ),
                        new SequentialAction(
                                shoot4path
                        ),
                         */
                        new SequentialAction(
                                extraTest
                        )
                )
        );
    }
}