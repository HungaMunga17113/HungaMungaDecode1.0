package org.firstinspires.ftc.teamcode.Encoder_Drive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
public class DrivetrainHardware extends LinearOpMode {
    //---------------- INITIALIZATION ----------------\\
    //Drive Motor Config
    //Declare Motors
    DcMotorEx leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
    DcMotorEx rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
    DcMotorEx leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
    DcMotorEx rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");

    DcMotorEx intake = hardwareMap.get(DcMotorEx.class, "intake");

    @Override
    public void runOpMode() throws InterruptedException {
        //Resets all encoder values to 0
        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        //Sets motor target positions to 0
        leftFront.setTargetPosition(0);
        rightFront.setTargetPosition(0);
        leftBack.setTargetPosition(0);
        rightBack.setTargetPosition(0);

        //Set RunMode to Run to Position
        leftFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        //When power of 0 is applied, it will brake, rather than glide with inertia
        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        //TODO: Set motor directions here, when a positive power is applied, it should go forward
        leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        rightFront.setDirection(DcMotorEx.Direction.REVERSE);
        leftBack.setDirection(DcMotorEx.Direction.FORWARD);
        rightBack.setDirection(DcMotorEx.Direction.REVERSE);

        intake.setDirection(DcMotorSimple.Direction.FORWARD);
    }
}
