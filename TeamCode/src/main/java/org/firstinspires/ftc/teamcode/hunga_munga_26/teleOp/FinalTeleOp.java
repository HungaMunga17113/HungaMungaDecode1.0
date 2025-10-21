package org.firstinspires.ftc.teamcode.hunga_munga_26.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class FinalTeleOp extends OpMode {

    DcMotor leftFront, leftBack, rightFront, rightBack;
    DcMotor intake;
    DcMotor leftOuttake, rightOuttake;
    private boolean shooting = false;
    private boolean returning = false;
    double maxSpeed = 1.0;
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

        leftOuttake.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightOuttake.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        leftOuttake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightOuttake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftOuttake.setTargetPosition(0);
        rightOuttake.setTargetPosition(0);
    }

    public void loop() {
        Drive();
        Intake();
    }

    /*
    Left Joystick ---------
        1. Up/Down - Robot goes forward, and backwards | all motors maxSpeed power, all motors -maxSpeed power
        2. Left/Right:
            a. Robot strafes left | (rightFront, leftBack maxSpeed) & (rightBack, leftFront -maxSpeed)
            b. Robot strafes right | (rightFront, leftBack -maxSpeed) & (rightBack, leftFront maxSpeed)

    Right Joystick ---------
        3. Turn:
            a. Robot strafes left | (rightFront, leftBack maxSpeed) & (rightBack, leftFront -maxSpeed)
            b. Robot strafes left | (rightFront, leftBack -maxSpeed) & (rightBack, leftFront maxSpeed)
     */
    private void Drive() {
        //Forward - If left joystick y is greater than 0,
        //Make robot go forward by setting positive power to all motors
        if (gamepad1.left_stick_y < 0) {
            leftFront.setPower(maxSpeed);
            leftBack.setPower(maxSpeed);
            rightFront.setPower(maxSpeed);
            rightBack.setPower(maxSpeed);
        }

        //Backward - If left joystick y is less than 0,
        //Make robot go backward by setting negative power to all motors
        else if (gamepad1.left_stick_y > 0) {
            leftFront.setPower(-maxSpeed);
            leftBack.setPower(-maxSpeed);
            rightFront.setPower(-maxSpeed);
            rightBack.setPower(-maxSpeed);
        }

        //Strafe left - If left joystick x is less than 0,
        //Make robot go left by setting negative power to leftFront and rightBack motors
        //And setting positive power to leftBack and rightFront
        else if (gamepad1.left_stick_x < 0) {
            leftFront.setPower(-maxSpeed);
            leftBack.setPower(maxSpeed);
            rightFront.setPower(maxSpeed);
            rightBack.setPower(-maxSpeed);
        }

        //Strafe right - If left joystick x is more than 0,
        //Make robot go right by setting positive power to leftBack and rightFront motors
        //And setting positive power to leftFront and rightBack
        else if (gamepad1.left_stick_x > 0) {
            leftFront.setPower(maxSpeed);
            leftBack.setPower(-maxSpeed);
            rightFront.setPower(-maxSpeed);
            rightBack.setPower(maxSpeed);
        }

        //Turn Left - If right joystick x is less than 0,
        //Make robot go left by setting positive power to rightBack and rightFront motors
        //And setting negative power to leftFront and leftBack
        else if (gamepad1.right_stick_x < 0) {
            leftFront.setPower(-maxSpeed);
            leftBack.setPower(-maxSpeed);
            rightFront.setPower(maxSpeed);
            rightBack.setPower(maxSpeed);
        }

        //Turn Right - If right joystick x is more than 0,
        //Make robot go right by setting negative power to rightBack and rightFront motors
        //And setting positive power to leftFront and leftBack
        else if (gamepad1.right_stick_x > 0) {
            leftFront.setPower(maxSpeed);
            leftBack.setPower(maxSpeed);
            rightFront.setPower(-maxSpeed);
            rightBack.setPower(-maxSpeed);
        }

        // If no joysticks moving, idle motors
        else {
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);
        }

    }
    private void Intake() {

        if (gamepad1.right_trigger > 0) {
            intake.setPower(1);
        }
        else {
            intake.setPower(0);
        }

    }
    public void shootTest() {
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
    public void resetOuttake() {
        leftOuttake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightOuttake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftOuttake.setTargetPosition(0);
        rightOuttake.setTargetPosition(0);

        leftOuttake.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightOuttake.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

}
