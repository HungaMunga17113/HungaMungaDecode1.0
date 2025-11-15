package org.firstinspires.ftc.teamcode.Encoder_Drive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
    // adb connect 192.168.43.1:5555
@Disabled
@Autonomous
public class VelocityAutonPath1 extends LinearOpMode {

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
    final double shootSpeed = 0.5;
    final int outtakePosition = 134;
    final double ticksPerInch = (ticksPerRev) / (Math.PI * wheelDiameterInches);

    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        leftOuttake = hardwareMap.get(DcMotorEx.class, "leftOuttake");
        rightOuttake = hardwareMap.get(DcMotorEx.class, "rightOuttake");


        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftOuttake.setDirection(DcMotorSimple.Direction.FORWARD);
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

        turn(28.5,1);
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
        double ticksPerSecondIntake = inchesPerSecondIntake * ticksPerInch;
        leftFront.setVelocity(ticksPerSecond);
        leftBack.setVelocity(ticksPerSecond);
        rightFront.setVelocity(ticksPerSecond);
        rightBack.setVelocity(ticksPerSecond);
        //intake.setVelocity(ticksPerSecondIntake);

        sleep((long) (seconds * 1000));
        leftFront.setVelocity(0);
        leftBack.setVelocity(0);
        rightFront.setVelocity(0);
        rightBack.setVelocity(0);
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
    public void shoot(long seconds) {
        leftOuttake.setTargetPosition(outtakePosition);
        rightOuttake.setTargetPosition(outtakePosition);
        rightOuttake.setPower(shootSpeed);
        leftOuttake.setPower(shootSpeed);
        sleep(1000);
        leftOuttake.setTargetPosition(-outtakePosition);
        rightOuttake.setTargetPosition(-outtakePosition);
        rightOuttake.setPower(shootSpeed);
        leftOuttake.setPower(shootSpeed);
        sleep(seconds * 1000);

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

