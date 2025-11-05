package org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.ftc.Actions;
import org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_other.MecanumDrive;
import org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates.Motor_Template;
import org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates.Servo_Template;
import org.firstinspires.ftc.teamcode.Roadrunner.subsystems.Intake;
import org.firstinspires.ftc.teamcode.Roadrunner.subsystems.Outtake;

import java.lang.Math;

@Config
@Autonomous(name = "3 Path Autonomous")
public class YourSampleAuton extends LinearOpMode {

    @Override
    public void runOpMode() {
        // Initial pose
        Pose2d initialPose = new Pose2d(50, 50, Math.toRadians(45));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        Motor_Template motor = new Motor_Template(hardwareMap);
        Servo_Template servo = new Servo_Template(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        Outtake outtake = new Outtake(hardwareMap);

        //----------------------- Main Paths -----------------------\\
        Action pathA = drive.actionBuilder(initialPose)
                .strafeToConstantHeading(new Vector2d(47,47))
                .strafeToLinearHeading(new Vector2d(11,11), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(11,53))
                .strafeToLinearHeading(new Vector2d(45,45), Math.toRadians(45))
                .build();

        Action pathB = drive.actionBuilder(new Pose2d(45,45, Math.toRadians(45)))
                .strafeToLinearHeading(new Vector2d(-13,13.5), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(-13,61))
                .strafeToConstantHeading(new Vector2d(-13,50))
                .strafeToLinearHeading(new Vector2d(47,47), Math.toRadians(45))
                .build();

        Action pathC = drive.actionBuilder(new Pose2d(47,47, Math.toRadians(45)))
                .strafeToLinearHeading(new Vector2d(-34,25), Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(-34,61))
                .strafeToConstantHeading(new Vector2d(-34,55))
                .strafeToLinearHeading(new Vector2d(45,45), Math.toRadians(45))
                .build();

        //----------------------- Init -----------------------\\
        Actions.runBlocking(new SequentialAction(
                servo.toPos1(),
                motor.toPos2()
        ));

        waitForStart();
        if (isStopRequested()) return;

        //----------------------- Run -----------------------\\
        Actions.runBlocking(
                new SequentialAction(
                        // First Pickup + Shoot
                        new ParallelAction(
                                pathA,
                                motor.toPos3(),
                                intake.in(),
                                outtake.shoot()
                        ),

                        new SleepAction(1),

                        // Second Pickup + Shoot
                        new ParallelAction(
                                pathB,
                                motor.toPos3(),
                                intake.in(),
                                outtake.shoot()
                        ),

                        new SleepAction(1),

                        // Third Pickup + Shoot
                        new ParallelAction(
                                pathC,
                                motor.toPos3(),
                                intake.in(),
                                outtake.shoot()
                        ),

                        new SequentialAction(
                                motor.toPos2(),
                                servo.toPos2()
                        )
                )
        );
    }
}
