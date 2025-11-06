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
        Action path1 = drive.actionBuilder(initialPose)
                .strafeToConstantHeading(new Vector2d(-47,47))
                .waitSeconds(1.5)
                .build();

        Action path2 = drive.actionBuilder(new Pose2d(-58, 57, Math.toRadians(315)))
                .strafeToLinearHeading(new Vector2d(-12,22),Math.toRadians(90),new TranslationalVelConstraint(70))
                .strafeToConstantHeading(new Vector2d(-12,55),new TranslationalVelConstraint(20))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(125),new TranslationalVelConstraint(70))
                .waitSeconds(1.5)
                .build();

        Action path3 = drive.actionBuilder(new Pose2d(-58, 57, Math.toRadians(315)))
                .strafeToLinearHeading(new Vector2d(15,22),Math.toRadians(90),new TranslationalVelConstraint(70))
                .strafeToConstantHeading(new Vector2d(15,62),new TranslationalVelConstraint(20))
                .strafeToConstantHeading(new Vector2d(15,50))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(125),new TranslationalVelConstraint(70))
                .waitSeconds(1.5)
                .build();

        Action path4 = drive.actionBuilder(new Pose2d(-58, 57, Math.toRadians(315)))
                .strafeToLinearHeading(new Vector2d(35,22),Math.toRadians(90),new TranslationalVelConstraint(70))
                .strafeToConstantHeading(new Vector2d(35,62),new TranslationalVelConstraint(20))
                .strafeToConstantHeading(new Vector2d(35,50))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(125),new TranslationalVelConstraint(70))
                .waitSeconds(1.5)
                .build();

        Action path5 = drive.actionBuilder(new Pose2d(-58, 57, Math.toRadians(315)))
                .strafeToLinearHeading(new Vector2d(2.5,47),Math.toRadians(270),new TranslationalVelConstraint(70))
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
//                        new ParallelAction(
//                                path1,
//                                motor.toPos3(),
//                                intake.in(),
//                                outtake.shoot()
//                        ),
                        new SequentialAction(
                                path1
                        ),
                        new SequentialAction(
                                path2
                        ),
                        new SequentialAction(
                                path3
                        ),
                        new SequentialAction(
                                path4
                        ),
                        new SequentialAction(
                                path5
                        )



                )
        );


    }
}