package org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_other;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp
public class Vision extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()

                .build();


        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {

        }

    }
}
