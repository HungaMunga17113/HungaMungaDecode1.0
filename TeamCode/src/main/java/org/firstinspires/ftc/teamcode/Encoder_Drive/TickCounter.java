package org.firstinspires.ftc.teamcode.Encoder_Drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;


@TeleOp(name = "Tick Counter")
public class TickCounter extends LinearOpMode {
    DcMotorEx intake;
    public DcMotorEx leftFront;
    public DcMotorEx rightFront;
    public DcMotorEx leftBack;
    public DcMotorEx rightBack;

    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");

        //Resets all encoder values to 0
        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        //TODO: Set motor directions here, when a positive power is applied, it should go forward
        leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        rightFront.setDirection(DcMotorEx.Direction.REVERSE);
        leftBack.setDirection(DcMotorEx.Direction.FORWARD);
        rightBack.setDirection(DcMotorEx.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        // Run until the end of the match
        while (opModeIsActive()) {
            // Get the current position
            int leftFrontPausePosition = leftFront.getCurrentPosition();
            int leftBackPausePosition = leftBack.getCurrentPosition();
            int rightFrontPausePosition = rightFront.getCurrentPosition();
            int rightBackPausePosition = rightBack.getCurrentPosition();

            int tickAverage = (leftFront.getCurrentPosition() + leftBack.getCurrentPosition() + rightFront.getCurrentPosition() + rightBack.getCurrentPosition())/4;
            // Add the position data to the telemetry output
            telemetry.addData("leftFront Motor Position", leftFrontPausePosition);
            telemetry.addData("leftBack Motor Position", leftBackPausePosition);
            telemetry.addData("rightFront Motor Position", rightFrontPausePosition);
            telemetry.addData("rightBack Motor Position", rightBackPausePosition);
            telemetry.addData("Tick Average", tickAverage);
            // Update the display on the Driver Station
            telemetry.update();
        }


    }
}