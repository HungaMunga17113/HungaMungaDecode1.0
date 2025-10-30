package org.firstinspires.ftc.teamcode.Encoder_Drive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
// adb connect 192.168.43.1:5555
@Autonomous
public class FinalBlueAuton extends LinearOpMode {

    DcMotorEx intake;
    DcMotorEx leftOuttake;
    DcMotorEx rightOuttake;
    public DcMotorEx leftFront;
    public DcMotorEx rightFront;
    public DcMotorEx leftBack;
    public DcMotorEx rightBack;

    // Motor + Wheel Constants
    final double wheelDiameterInches = 4.29449;
    final double ticksPerRev = 537.6;
    final double shootSpeed = 1;
    final double ticksPerInch = (ticksPerRev) / (Math.PI * wheelDiameterInches);

    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        leftOuttake = hardwareMap.get(DcMotorEx.class, "leftOuttake");
        rightOuttake = hardwareMap.get(DcMotorEx.class, "rightOuttake");
        intake = hardwareMap.get(DcMotorEx.class, "intake");

        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        leftOuttake.setDirection(DcMotorSimple.Direction.REVERSE);
        rightOuttake.setDirection(DcMotorSimple.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Turn - (27.5,1)
        //Turn - (53,0.5)
        waitForStart();
        //First 3 balls
        vertical (18,0.45);
        sleep(350);
        shoot();
        //Second 3 balls
        vertical (36.5,1.233);
        sleep(50);
        turn(36,0.5);
        strafe(23,0.5);
        sleep(50);
        //Intaking second 3 balls
        vertical(-27,3,2);
        vertical(7,0.5);
        vertical(-4,1.5,1);
        sleep(300);
        vertical(29,1.5, 1);
        sleep(50);
        //Returning to the goal
        turn(-36,0.5);
        sleep(50);
        vertical(-26.8411,2);
        sleep(200);
        //Shooting second 3 balls
        shootDown();
        sleep(350);
        shoot();
        sleep(50);
        //Third 3 balls
        vertical(26.8411,1.5);
        sleep(50);
        turn(30,0.5);
        sleep(50);
        strafe(50,1);
        sleep(50);
        //Intaking third 3 balls
        vertical(-25,3,2);
        vertical(7,0.5);
        vertical(-4,1.5,1);
        sleep(50);
        vertical(30,1.5,1);
        sleep(50);
        //Returning to goal
        turn(-29,0.95);
        sleep(50);
        vertical(40,1.45);
        sleep(50);
        turn(30,0.5);
        sleep(200);
        //Shooting last 3 balls
        shootDown();
        sleep(350);
        shoot();

        //vertical(42,2,0);
        //turn(-26.5,0.5);
        //sleep(2000);
        //turn(-26.5,0.5);
        //strafe(12,1,0);
        //vertical(24,1.5,0);
        //vertical(-24,1.5,0);
        //strafe(-12,1,0);
        //turn(26.5,0.5);

    }
    public void vertical(double inchesPerSecond, double seconds) {
        reset();
        double ticksPerSecond = inchesPerSecond * ticksPerInch;
        leftFront.setVelocity(ticksPerSecond);
        leftBack.setVelocity(ticksPerSecond);
        rightFront.setVelocity(ticksPerSecond);
        rightBack.setVelocity(ticksPerSecond);
        sleep((long) (seconds * 1000));
        stopMotors();
    }

    public void vertical(double inchesPerSecond, double seconds, double inchesPerSecondIntake) {
        reset();
        double ticksPerSecond = inchesPerSecond * ticksPerInch;
        leftFront.setVelocity(ticksPerSecond);
        leftBack.setVelocity(ticksPerSecond);
        rightFront.setVelocity(ticksPerSecond);
        rightBack.setVelocity(ticksPerSecond);
        intake.setPower(1);

        sleep((long) (seconds * 1000));
        stopMotors();
    }

    public void strafe(double inchesPerSecond, double seconds) {
        reset();
        double ticksPerSecond = inchesPerSecond * ticksPerInch;
        leftFront.setVelocity(ticksPerSecond);
        leftBack.setVelocity(-ticksPerSecond);
        rightFront.setVelocity(-ticksPerSecond);
        rightBack.setVelocity(ticksPerSecond);

        sleep((long) (seconds * 1000));
        stopMotors();
    }

    public void strafe(double inchesPerSecond, double seconds, double inchesPerSecondIntake) {
        reset();
        double ticksPerSecond = inchesPerSecond * ticksPerInch;
        double ticksPerSecondIntake = inchesPerSecondIntake * ticksPerInch;
        leftFront.setVelocity(ticksPerSecond);
        leftBack.setVelocity(-ticksPerSecond);
        rightFront.setVelocity(-ticksPerSecond);
        rightBack.setVelocity(ticksPerSecond);
        //intake.setVelocity(ticksPerSecondIntake);

        sleep((long) (seconds * 1000));
        stopMotors();
    }

    public void turn(double inchesPerSecond, double seconds) {
        reset();
        double ticksPerSecond = inchesPerSecond * ticksPerInch;

        leftFront.setVelocity(ticksPerSecond);
        leftBack.setVelocity(ticksPerSecond);
        rightFront.setVelocity(-ticksPerSecond);
        rightBack.setVelocity(-ticksPerSecond);

        sleep((long) (seconds * 1000));
        stopMotors();
    }
    public void shoot() {
        stopMotors();
        //resetShoot();
        rightOuttake.setPower(shootSpeed);
        leftOuttake.setPower(shootSpeed);
        sleep(850);
        rightOuttake.setPower(-shootSpeed);
        leftOuttake.setPower(-shootSpeed);
        sleep(950);
        rightOuttake.setPower(-0.05);
        leftOuttake.setPower(-0.05);

    }

    public void shootDown() {
        rightOuttake.setPower(-shootSpeed);
        leftOuttake.setPower(-shootSpeed);
        sleep(1000);
        rightOuttake.setPower(-0.05);
        leftOuttake.setPower(-0.05);

    }
    public void stopMotors() {
        leftFront.setVelocity(0);
        leftBack.setVelocity(0);
        rightFront.setVelocity(0);
        rightBack.setVelocity(0);
        intake.setPower(0);
    }

    public void reset() {
        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }
    public void resetShoot() {
        leftOuttake.setVelocity(0);
        rightOuttake.setVelocity(0);
    }

}


