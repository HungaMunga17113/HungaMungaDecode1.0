package org.firstinspires.ftc.teamcode.hunga_munga_26.new_code_here;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
//HUNGA
@Disabled
@Autonomous
public class Auton1 extends LinearOpMode {
    //How to calculate ticks per inch
    //Ticks multiplied by inches in same distance
    //private final double ticksPerIn = 45;

    DcMotorEx intake;
    DcMotorEx outtake;
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

        //--------------------- INITIALIZE ---------------------\\
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        outtake = hardwareMap.get(DcMotorEx.class, "outake");
        outtake.setDirection(DcMotorSimple.Direction.FORWARD);

        //------------------------- RUN ------------------------\\
        //waits for the Driver Hub to receive "start" input
        waitForStart();
        //TODO: Pathing, use the functions below to move the robot
        //**NOTE - NEVER set power to 1.0 at the start, start at 0.5 and work your way up.
        // 1.0 loses lots of consistency, and the robot movements would be choppy

        vertical(500,0.5,5);


    }

    public void intake(double speed, long time) {
        intake.setVelocity(speed);
        sleep(time);
        intake.setVelocity(0);
    }

    public void outake(double speed, long time) {
        outtake.setVelocity(speed);
        sleep(time);
        outtake.setVelocity(0);
    }

    // This function is in Ticks, which is more accurate than Inches
    public void vertical(int ticks, double speed, long seconds) {
        reset();

        leftFront.setTargetPosition(ticks);
        leftBack.setTargetPosition(ticks);
        rightFront.setTargetPosition(ticks);
        rightBack.setTargetPosition(ticks);

        leftFront.setVelocity(speed);
        leftBack.setVelocity(speed);
        rightFront.setVelocity(speed);
        rightBack.setVelocity(speed);

        sleep(seconds * 1000);
    }

    // Inches is not as accurate as Ticks
    public void verticalInInches(double inches, double speed, long seconds) {
        reset();
        int ticks = toTicks(inches);

        leftFront.setTargetPosition(ticks);
        leftBack.setTargetPosition(ticks);
        rightFront.setTargetPosition(ticks);
        rightBack.setTargetPosition(ticks);

        leftFront.setVelocity(speed);
        leftBack.setVelocity(speed);
        rightFront.setVelocity(speed);
        rightBack.setVelocity(speed);

        sleep(seconds * 1000);
    }

    //Positive value will strafe right, Negative value will strafe left
    // This function is in Ticks, which is more accurate than Inches
    public void strafe(int ticks, double speed, long seconds) {
        reset();

        leftFront.setTargetPosition(ticks);
        leftBack.setTargetPosition(-ticks);
        rightFront.setTargetPosition(-ticks);
        rightBack.setTargetPosition(ticks);

        leftFront.setVelocity(speed);
        leftBack.setVelocity(speed);
        rightFront.setVelocity(speed);
        rightBack.setVelocity(speed);

        sleep(seconds * 1000);
    }

    // Inches is not as accurate as Ticks
    public void strafeInInches(double inches, double speed, long seconds) {
        reset();
        int ticks = toTicks(inches);

        leftFront.setTargetPosition(ticks);
        leftBack.setTargetPosition(-ticks);
        rightFront.setTargetPosition(-ticks);
        rightBack.setTargetPosition(ticks);

        leftFront.setVelocity(speed);
        leftBack.setVelocity(speed);
        rightFront.setVelocity(speed);
        rightBack.setVelocity(speed);

        sleep(seconds * 1000);
    }
    //Positive value will turn right, Negative value will turn left
    public void turn(int ticks, double speed, long seconds) {
        reset();

        leftFront.setTargetPosition(-ticks);
        leftBack.setTargetPosition(-ticks);
        rightFront.setTargetPosition(ticks);
        rightBack.setTargetPosition(ticks);

        leftFront.setVelocity(speed);
        leftBack.setVelocity(speed);
        rightFront.setVelocity(speed);
        rightBack.setVelocity(speed);

        sleep(seconds * 1000);
    }

    public void turnInInches(double inches, double speed, long seconds) {
        reset();
        int ticks = toTicks(inches);

        leftFront.setTargetPosition(-ticks);
        leftBack.setTargetPosition(-ticks);
        rightFront.setTargetPosition(ticks);
        rightBack.setTargetPosition(ticks);

        leftFront.setVelocity(speed);
        leftBack.setVelocity(speed);
        rightFront.setVelocity(speed);
        rightBack.setVelocity(speed);

        sleep(seconds * 1000);
    }

    //Converts ticks into inches
    //Input: inches
    //Output/return: ticks
    public int toTicks(double inches) {
        double ticks = inches * 45;
        return (int) ticks;
    }

    public void reset() {
        //Resets all encoder values to 0
        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        //Sets target position to 0
        leftFront.setTargetPosition(0);
        rightFront.setTargetPosition(0);
        leftBack.setTargetPosition(0);
        rightBack.setTargetPosition(0);

        //Sets motor mode to Run to Position to make sure no motor has a target position set (0)
        leftFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

}