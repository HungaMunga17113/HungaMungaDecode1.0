package org.firstinspires.ftc.teamcode.Encoder_Drive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
// adb connect 192.168.43.1:5555
@Autonomous
public class VelocityEncoderAuton extends LinearOpMode {

    DcMotorEx intake;
    public DcMotorEx leftFront;
    public DcMotorEx rightFront;
    public DcMotorEx leftBack;
    public DcMotorEx rightBack;

    // Motor + Wheel Constants
    final double wheelDiameterInches = 3.79953;
    final double ticksPerRev = 537.6;
    final double ticksPerInch = (ticksPerRev) / (Math.PI * wheelDiameterInches);

    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");

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

        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        //Turn - (27.5,1)
        //Turn - (53,0.5)
        waitForStart();
        vertical(48,1);
        turn(53,0.5);
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

    public void intake(double speed, long time) {
        intake.setVelocity(speed);
        sleep(time);
        intake.setVelocity(0);
    }

    public void stopMotors() {
        leftFront.setVelocity(0);
        leftBack.setVelocity(0);
        rightFront.setVelocity(0);
        rightBack.setVelocity(0);
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

    }
