package org.firstinspires.ftc.teamcode.hunga_munga_26.teleOp;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

@TeleOp
public class NewTest2 extends OpMode {
    Deadline gamepadRateLimit = new Deadline(250, TimeUnit.MILLISECONDS);

    DcMotor leftFront, leftBack, rightFront, rightBack;
    DcMotor intake;
    DcMotor leftOuttake, rightOuttake;
    IMU imu;

    private ElapsedTime outtakeTime = new ElapsedTime();

    private enum outtakeModes {Shoot, Return, Rest};
    private outtakeModes pivotMode;
    double outtakePower = 1;
    /*
    (Button) Initialize Period, before you press start on your program.
     */
    public void init() {

        //set hardware map names (aka what the controller understands)
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        leftOuttake = hardwareMap.get(DcMotorEx.class, "leftOuttake");
        rightOuttake = hardwareMap.get(DcMotorEx.class, "rightOuttake");

        // When no power (aka no joysticks moving (idle) ), robot should brake on stop
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        leftOuttake.setDirection(DcMotorSimple.Direction.REVERSE);
        rightOuttake.setDirection(DcMotorSimple.Direction.FORWARD);

        outtakeTime.reset();
    }

    public void loop() {
        Drive();
        Intake();
        Outtake();
    }

    public void Drive() {
        double max;

        double axial = gamepad1.left_stick_y;
        double lateral = -gamepad1.left_stick_x;
        double yaw = -gamepad1.right_stick_x;
        double drivePower = 0.95 - (0.6 * gamepad1.left_trigger);
        //double leftFrontPower = axial + lateral - yaw;
        //double rightFrontPower =axial - lateral + yaw;
        //double leftBackPower = axial - lateral + yaw;
        //double rightBackPower = axial + lateral - yaw;
        double leftFrontPower = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower = axial - lateral + yaw;
        double rightBackPower = axial + lateral - yaw;

        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }
        rightFront.setPower(rightFrontPower*drivePower);
        rightBack.setPower(rightBackPower*drivePower);
        leftFront.setPower(leftFrontPower*drivePower);
        leftBack.setPower(leftBackPower*drivePower);
        //if (gamepad1.x) {
           // leftFront.setPower(1);
        //}
        //if (gamepad1.y) {
        //    leftBack.setPower(1);
        //}
        //if (gamepad1.a) {
          //  rightFront.setPower(1);
        //}
        //if (gamepad1.b) {
         //   rightBack.setPower(1);
        //}

    }

    private void Intake() {
        double intakePower = 1;
        if (gamepad1.right_trigger > 0.15) {
            intake.setPower(intakePower);
        } else if (gamepad1.y) {
            intake.setPower(-intakePower);
        } else {
            intake.setPower(0);
        }
    }

    private void Outtake() {
        if (gamepad1.right_bumper) {
            pivotMode = outtakeModes.Shoot;
            leftOuttake.setPower(outtakePower);
            rightOuttake.setPower(outtakePower);
            outtakeTime.reset();
        } else if (gamepad1.left_bumper) {
            pivotMode = outtakeModes.Return;
            leftOuttake.setPower(-outtakePower);
            rightOuttake.setPower(-outtakePower);
            outtakeTime.reset();
        } else {
            double downTime = 1.0;
            if (outtakeTime.seconds() < downTime) {
                leftOuttake.setPower(-0.05);
                rightOuttake.setPower(-0.05);
            } else {
                leftOuttake.setPower(0);
                rightOuttake.setPower(0);
            }

            pivotMode = outtakeModes.Rest;
        }
    }
}

