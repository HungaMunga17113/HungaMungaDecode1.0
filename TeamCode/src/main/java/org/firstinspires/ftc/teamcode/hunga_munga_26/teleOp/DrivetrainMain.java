package org.firstinspires.ftc.teamcode.hunga_munga_26.teleOp;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

@Disabled
@TeleOp
public class DrivetrainMain extends OpMode {

    Deadline gamepadRateLimit = new Deadline(250, TimeUnit.MILLISECONDS);
    DcMotor leftFront, rightFront, rightBack, leftBack;

    DcMotorEx leftOuttake, rightOuttake;
    DcMotorEx intake;

    private boolean shooting = false;
    private boolean returning = false;
    IMU imu;

    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(IMU.class, "imu");


        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        ));
        imu.initialize(parameters);


    }

    @Override
    public void loop() {
        FSDDrive();
    }

    private void FSDDrive() {
        double vertical = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double turn = -gamepad1.right_stick_x;

        double drivePower = 0.95 - (0.6 * gamepad1.right_trigger);

        if (gamepadRateLimit.hasExpired() && gamepad1.a) {
            imu.resetYaw();
            gamepadRateLimit.reset();
        }

        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double adjustedVertical = vertical * Math.cos(heading) - strafe * Math.sin(heading);
        double adjustedStrafe = vertical * Math.sin(heading) + strafe * Math.cos(heading);


        double max = Math.max(Math.abs(adjustedStrafe) + Math.abs(adjustedVertical) + Math.abs(turn), 1);

        double RFPower = ((turn + (adjustedVertical - adjustedStrafe)) / max) * drivePower;
        double RBPower = ((turn + (adjustedVertical + adjustedStrafe)) / max) * drivePower;
        double LFPower = (((-turn) + (adjustedVertical + adjustedStrafe)) / max) * drivePower;
        double LBPower = (((-turn) + (adjustedVertical - adjustedStrafe)) / max) * drivePower;

        rightFront.setPower(RFPower);
        rightBack.setPower(RBPower);
        leftFront.setPower(LFPower);
        leftBack.setPower(LBPower);


        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");

        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(0);
        rightFront.setTargetPosition(0);
        leftBack.setTargetPosition(0);
        rightBack.setTargetPosition(0);

        leftFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        rightFront.setDirection(DcMotorEx.Direction.REVERSE);
        leftBack.setDirection(DcMotorEx.Direction.FORWARD);
        rightBack.setDirection(DcMotorEx.Direction.REVERSE);


        int outtakePosition = 134;
        if (gamepad1.a && !shooting) {
            resetOuttake();
            shooting = true;
            leftOuttake.setTargetPosition(outtakePosition);
            rightOuttake.setTargetPosition(outtakePosition);
            leftOuttake.setPower(1);
            rightOuttake.setPower(1);
        }
        if (shooting) {
            if (leftOuttake.getCurrentPosition() >= outtakePosition) {
                leftOuttake.setPower(-1);
                rightOuttake.setPower(-1);
                returning = true;
                shooting = false;
            }
        }
        if (returning) {
            if (leftOuttake.getCurrentPosition() <= 0) {
                returning = false;
                shooting = false;
                leftOuttake.setPower(0);
                rightOuttake.setPower(0);
            }
        }


    }

    private void resetOuttake() {
        leftOuttake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightOuttake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftOuttake.setTargetPosition(0);
        rightOuttake.setTargetPosition(0);
    }
    private void Intake () {

        if (gamepad1.right_trigger > 0) {
            intake.setPower(1);
        } else if (gamepad1.x){
            intake.setPower(-1);
        }
        else {
            intake.setPower(0);
        }

        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake = hardwareMap.get(DcMotorEx.class, "intake");

        leftOuttake = hardwareMap.get(DcMotorEx.class, "leftOuttake");
        rightOuttake = hardwareMap.get(DcMotorEx.class, "rightOuttake");

        leftOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftOuttake.setDirection(DcMotorSimple.Direction.REVERSE);
        rightOuttake.setDirection(DcMotorSimple.Direction.FORWARD);


    }

}



