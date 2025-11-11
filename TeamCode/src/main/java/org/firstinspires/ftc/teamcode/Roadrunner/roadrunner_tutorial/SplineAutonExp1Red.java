package org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates.MecanumDrive;
import org.firstinspires.ftc.teamcode.Roadrunner.subsystems.Intake;
import org.firstinspires.ftc.teamcode.Roadrunner.subsystems.Outtake;

@Config
@Autonomous(name = "Gate RR Red Auton")
public class SplineAutonExp1Red extends LinearOpMode {


    @Override
    public void runOpMode() {
        //Pose that the robot starts at
        Pose2d initialPose = new Pose2d(-55, 46, Math.toRadians(127));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Intake intake = new Intake(hardwareMap);
        Outtake outtake = new Outtake(hardwareMap);

        //-----------------------Paths-----------------------\\
        Action shoot1path = drive.actionBuilder(initialPose)
                .waitSeconds(0.1)
                .strafeToConstantHeading(new Vector2d(-47,47))
                .waitSeconds(0.25)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.35)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.35)
                .stopAndAdd(outtake.idle())
                .build();

        Action intake1path = drive.actionBuilder(new Pose2d(-47, 47, Math.toRadians(127)))
                .strafeToLinearHeading(new Vector2d(-11.5,21),Math.toRadians(90))
                .stopAndAdd(intake.in())
                .strafeToConstantHeading(new Vector2d(-11.5,50))
                .splineToConstantHeading(new Vector2d(-45,45),Math.toRadians(180))

                /*
                .lineToYLinearHeading(50, Math.toRadians(60))
                .lineToYSplineHeading(42.67, Math.toRadians(120))
                */
                .build();

        Action shoot2path = drive.actionBuilder(new Pose2d(-45, 45, Math.toRadians(180)))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(127))
                .stopAndAdd(intake.idle())
                .waitSeconds(0.25)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.35)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.35)
                .stopAndAdd(outtake.idle())
                .build();

        Action intake2path = drive.actionBuilder(new Pose2d(-47, 47, Math.toRadians(127)))
                .strafeToLinearHeading(new Vector2d(12,24.5),Math.toRadians(90))
                .stopAndAdd(intake.in())
                .strafeToConstantHeading(new Vector2d(12,40))
                //.strafeToConstantHeading(new Vector2d(9,50))
//                .lineToYLinearHeading(56, Math.toRadians(65))
//                .lineToYSplineHeading(50, Math.toRadians(115))
                .splineToConstantHeading(new Vector2d(-45,45),Math.toRadians(175))
                .build();

        Action shoot3path = drive.actionBuilder(new Pose2d(-45, 45, Math.toRadians(175)))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(127))
                .stopAndAdd(intake.idle())
                .waitSeconds(0.25)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.35)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.35)
                .stopAndAdd(outtake.idle())
                .build();

        Action intake3path = drive.actionBuilder(new Pose2d(-47, 47, Math.toRadians(127)))
                .strafeToLinearHeading(new Vector2d(37,22),Math.toRadians(90))
                .stopAndAdd(intake.in())
                .strafeToConstantHeading(new Vector2d(35,50))
                .build();

        Action shoot4path = drive.actionBuilder(new Pose2d(35, 50, Math.toRadians(90)))
                //.strafeToConstantHeading(new Vector2d(31.5,50))
//                .lineToYLinearHeading(53.5, Math.toRadians(65))
//                .lineToYSplineHeading(45, Math.toRadians(115))
                .splineToConstantHeading(new Vector2d(-30,36),Math.toRadians(175))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(127))
                .stopAndAdd(intake.idle())
                .waitSeconds(0.25)
                .stopAndAdd(outtake.shoot())
                .waitSeconds(0.35)
                .stopAndAdd(outtake.down())
                .waitSeconds(0.35)
                .stopAndAdd(outtake.idle())
                .build();

        Action extra = drive.actionBuilder(new Pose2d(-47, 47, Math.toRadians(127)))
                .strafeToLinearHeading(new Vector2d(2,47),Math.toRadians(270))
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
                        new SequentialAction(
                                extra
                        )
                )
        );
    }
}