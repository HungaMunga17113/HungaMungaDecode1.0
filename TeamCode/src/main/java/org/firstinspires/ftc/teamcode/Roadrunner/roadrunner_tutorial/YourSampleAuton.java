package org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates.MecanumDrive;
import org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates.MecanumDrive;
import org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates.Motor_Template;
import org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates.Servo_Template;
import org.firstinspires.ftc.teamcode.Roadrunner.subsystems.Intake;
import org.firstinspires.ftc.teamcode.Roadrunner.subsystems.Outtake;

@Config
@Autonomous(name = "Your Own Autonomous!")
public class YourSampleAuton extends LinearOpMode {


    @Override
    public void runOpMode() {
        //Pose that the robot starts at
        Pose2d initialPose = new Pose2d(50, 50, Math.toRadians(45));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Motor_Template motor = new Motor_Template(hardwareMap);
        Servo_Template servo = new Servo_Template(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Outtake outtake = new Outtake(hardwareMap);


        //-----------------------Paths-----------------------\\
        //                .strafeToConstantHeading(new Vector2d(47,47))
        //                .strafeToLinearHeading(new Vector2d(11,11),Math.toRadians(90),new TranslationalVelConstraint(90))
        //                .strafeToConstantHeading(new Vector2d(11,53))
        //                .strafeToLinearHeading(new Vector2d(45,45),Math.toRadians(45),new TranslationalVelConstraint(90))
        //                .strafeToLinearHeading(new Vector2d(-13,13.5),Math.toRadians(90),new TranslationalVelConstraint(90))
        //                .strafeToConstantHeading(new Vector2d(-13,61))
        //                .strafeToConstantHeading(new Vector2d(-13,50))
        //                .strafeToLinearHeading(new Vector2d(47,47),Math.toRadians(45),new TranslationalVelConstraint(90))
        //                .strafeToLinearHeading(new Vector2d(-34,25),Math.toRadians(90),new TranslationalVelConstraint(90))
        //                .strafeToConstantHeading(new Vector2d(-34,61))
        //                .strafeToConstantHeading(new Vector2d(-34,55))
        //                .strafeToLinearHeading(new Vector2d(45,45),Math.toRadians(45),new TranslationalVelConstraint(90))
        Action path1 = drive.actionBuilder(initialPose)
                .strafeToConstantHeading(new Vector2d(47,47))
                .build();

        //Calling path2 ASSUMES the robot is at (-58,57) BEFORE the path is run as shown below
        Action path2 = drive.actionBuilder(new Pose2d(47, 47, Math.toRadians(45)))
                .strafeToLinearHeading(new Vector2d(11,11),Math.toRadians(90),new TranslationalVelConstraint(90))
                .build();

        Action path3 = drive.actionBuilder(new Pose2d(11, 11, Math.toRadians(90)))
                .strafeToConstantHeading(new Vector2d(11,53))
                .build();

        Action path4 = drive.actionBuilder(new Pose2d(11, 53, Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(45,45),Math.toRadians(45),new TranslationalVelConstraint(90))
                .build();

        Action path5 = drive.actionBuilder(new Pose2d(45, 45, Math.toRadians(45)))
                .strafeToLinearHeading(new Vector2d(-13,13.5),Math.toRadians(90),new TranslationalVelConstraint(90))
                .build();

        Action path6 = drive.actionBuilder(new Pose2d(-13, 13.5, Math.toRadians(90)))
                .strafeToConstantHeading(new Vector2d(-13,61))
                .build();

        Action path7 = drive.actionBuilder(new Pose2d(13, 61, Math.toRadians(90)))
                .strafeToConstantHeading(new Vector2d(-13,50))
                .build();

        Action path8 = drive.actionBuilder(new Pose2d(-13, 50, Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(47,47),Math.toRadians(45),new TranslationalVelConstraint(90))
                .build();

        Action path9 = drive.actionBuilder(new Pose2d(47, 47, Math.toRadians(45)))
                .strafeToLinearHeading(new Vector2d(-34,25),Math.toRadians(90),new TranslationalVelConstraint(90))
                .build();

        Action path10 = drive.actionBuilder(new Pose2d(-34, 25, Math.toRadians(90)))
                .strafeToConstantHeading(new Vector2d(-34,61))
                .build();

        Action path11 = drive.actionBuilder(new Pose2d(-34, 61, Math.toRadians(90)))
                .strafeToConstantHeading(new Vector2d(-34,55))
                .build();

        Action path12 = drive.actionBuilder(new Pose2d(-34, 55, Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(45,45),Math.toRadians(45),new TranslationalVelConstraint(90))
                .build();




        // Initialize (What happens before when you press start)
        Actions.runBlocking(
                new SequentialAction(
                    servo.toPos1(),
                    motor.toPos2()
                )
        );

        waitForStart();

        if (isStopRequested()) return;

        //Run (What happens when you press start)
        Actions.runBlocking(
                new SequentialAction(

                        //----------First Path!----------\\

                        //Runs path 1 *WHILE* motor moves to position 3
                        new ParallelAction(
                                path1,
                                motor.toPos3(),
                                intake.in(),
                                outtake.shoot()
                        ),

                        //----------Second Path!----------\\

                        //Runs path 2 *AFTER*
                        //(motor moves to position 2 *WHILE* servo moves to position 2)
                        new SequentialAction(
                                path12,
                                new ParallelAction(
                                        motor.toPos2(),
                                        servo.toPos2()
                                ),
                                new SleepAction(1)

                        )

                )
        );


    }
}