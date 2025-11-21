package org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_other.tuning;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Roadrunner.Drawing;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Roadrunner.TankDrive;

public class LocalizationTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        //new telemetry instance
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            //Starting position of the robot in teleOp
            MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, Math.toRadians(90)));


            waitForStart();

            while (opModeIsActive()) {
                //Drive Code, sets wheel powers. Robo centric, regular movement with joysticks
                drive.setDrivePowers(new PoseVelocity2d(
                        new Vector2d(
                                -gamepad1.left_stick_y,
                                -gamepad1.left_stick_x
                        ),
                        -gamepad1.right_stick_x
                ));

                //Updates robot position so the code constantly knows where it is at all times
                drive.updatePoseEstimate();
/*
                The actual robot position is stored in this "pose" variable of data type Pose2d,
                which basically gets the pose from the localizer.
                /Because the poseEstimate was updated, the pose variable is constantly updating and retrieving
                the current position of the robot

 */
                Pose2d pose = drive.localizer.getPose();

                //X, Y, and Heading positions are all displayed in telemetry FROM the pose variable made earlier (above)
                telemetry.addData("x", pose.position.x);
                telemetry.addData("y", pose.position.y);
                telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
                //updates telemetry
                telemetry.update();

                //TelemetryPacket type variable made called "packet"
                TelemetryPacket packet = new TelemetryPacket();
                //We use the packet and add a field overlay with stroke color blue to show and display robot position on the field on ftc dashboard
                packet.fieldOverlay().setStroke("#3F51B5");
                Drawing.drawRobot(packet.fieldOverlay(), pose);
                FtcDashboard.getInstance().sendTelemetryPacket(packet);


                //ADDED BY ISHAAN
                Action park = drive.actionBuilder(pose)
                        .strafeToLinearHeading(new Vector2d(-3, 5), Math.toRadians(-180),
                                new TranslationalVelConstraint(80))
                        .build();
                if (gamepad1.a) {
                    Actions.runBlocking(park);
                }


            }
        } else if (TuningOpModes.DRIVE_CLASS.equals(TankDrive.class)) {
            TankDrive drive = new TankDrive(hardwareMap, new Pose2d(0, 0, 0));

            waitForStart();

            while (opModeIsActive()) {
                drive.setDrivePowers(new PoseVelocity2d(
                        new Vector2d(
                                -gamepad1.left_stick_y,
                                0.0
                        ),
                        -gamepad1.right_stick_x
                ));

                drive.updatePoseEstimate();

                Pose2d pose = drive.localizer.getPose();
                telemetry.addData("x", pose.position.x);
                telemetry.addData("y", pose.position.y);
                telemetry.addData("heading (deg)", Math.toDegrees(pose.heading.toDouble()));
                telemetry.update();

                TelemetryPacket packet = new TelemetryPacket();
                packet.fieldOverlay().setStroke("#3F51B5");
                Drawing.drawRobot(packet.fieldOverlay(), pose);
                FtcDashboard.getInstance().sendTelemetryPacket(packet);
            }
        } else {
            throw new RuntimeException();
        }
    }
}
