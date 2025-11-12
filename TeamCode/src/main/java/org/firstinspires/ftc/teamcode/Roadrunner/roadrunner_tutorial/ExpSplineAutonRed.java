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
@Autonomous(name = "RR Red Auton")
public class ExpSplineAutonRed extends LinearOpMode {


    @Override
    public void runOpMode() {
        //Pose that the robot starts at
        Pose2d initialPose = new Pose2d(-55, 46, Math.toRadians(127));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Intake intake = new Intake(hardwareMap);
        Outtake outtake = new Outtake(hardwareMap);

        //-----------------------Paths-----------------------\\
        Action shoot1path = drive.actionBuilder(initialPose)
                .waitSeconds(0.3)
                .strafeToConstantHeading(new Vector2d(-45,45))
                .waitSeconds(0.25)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.45)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.45)
                .stopAndAdd(outtake.idle())
                .build();

        Action intake1path = drive.actionBuilder(new Pose2d(-45, 45, Math.toRadians(127)))
                .strafeToLinearHeading(new Vector2d(-13,20),Math.toRadians(90))
                .stopAndAdd(intake.in())
                .strafeToConstantHeading(new Vector2d(-13,55),new TranslationalVelConstraint(20))
                .build();

        Action shoot2path = drive.actionBuilder(new Pose2d(-13, 45, Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(-45,45),Math.toRadians(127))
                .stopAndAdd(intake.idle())
                .waitSeconds(0.25)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.45)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.45)
                .stopAndAdd(outtake.idle())
                .build();

        Action intake2path = drive.actionBuilder(new Pose2d(-45, 45, Math.toRadians(125)))
                .strafeToLinearHeading(new Vector2d(14,20),Math.toRadians(90))
                .stopAndAdd(intake.in())
                .strafeToConstantHeading(new Vector2d(14,62),new TranslationalVelConstraint(20))
//                .lineToYLinearHeading(56, Math.toRadians(65))
//                .lineToYSplineHeading(50, Math.toRadians(115))
                .build();

        Action shoot3path = drive.actionBuilder(new Pose2d(9, 50, Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(-45,45),Math.toRadians(127))
                .stopAndAdd(intake.idle())
                .waitSeconds(0.25)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.45)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.45)
                .stopAndAdd(outtake.idle())
                .build();

        Action intake3path = drive.actionBuilder(new Pose2d(-45, 45, Math.toRadians(125)))
                .strafeToLinearHeading(new Vector2d(37,22),Math.toRadians(90))
                .stopAndAdd(intake.in())
                .strafeToConstantHeading(new Vector2d(35,49))
                .build();

        Action shoot4path = drive.actionBuilder(new Pose2d(32, 49, Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(-31,27),Math.toRadians(190))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(127),new TranslationalVelConstraint(80))
                .stopAndAdd(intake.idle())
                .waitSeconds(0.25)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.45)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.45)
                .stopAndAdd(outtake.idle())
                .build();

        Action extra = drive.actionBuilder(new Pose2d(-45, 45, Math.toRadians(125)))
                .strafeToLinearHeading(new Vector2d(2.5,45),Math.toRadians(270))
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
                                //       intake.in(),
                                intake3path
                                //       intake.idle()
                        ),
                        new SequentialAction(
                                shoot4path
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