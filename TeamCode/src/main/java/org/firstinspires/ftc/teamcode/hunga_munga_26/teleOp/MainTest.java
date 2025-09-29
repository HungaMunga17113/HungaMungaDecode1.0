package org.firstinspires.ftc.teamcode.hunga_munga_26.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MainTest extends OpMode {

    // Initializing/making motor names
    DcMotor leftFront, leftBack, rightFront, rightBack;
    DcMotorEx leftShooter, rightShooter;
    //Initialize Variables
    double maxSpeed = 1.0;
    double maxVelocity = 100;
    /*
    (Button) Initialize Period, before you press start on your program.
     */
    public void init() {

        //set hardware map names (aka what the controller understands)
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftShooter = hardwareMap.get(DcMotorEx.class, "leftshooter");
        rightShooter = hardwareMap.get(DcMotorEx.class, "rightshooter");

        // Motor power goes from -maxSpeed -> maxSpee
        // Sets motor direction. Says which direction the motor will turn when given full power of maxSpeed

        leftShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // When no power (aka no joysticks moving (idle) ), robot should brake on stop
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        
    }

    /*
    (Button) What happens when you press Start/Play
    You constantly update all your code
    Basically just keep all your code over here
     */
    public void loop() {
//        Drive();
        
        if (gamepad1.a) {
            setShooter(maxVelocity);
        }
        
        else {
            setShooter(0);
        }
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

        if (gamepad1.right_trigger > 0) {
            rightShooter.setPower(-1.0);
            leftShooter.setPower(1.0);
        } else if (gamepad1.left_trigger > 0) {
            rightShooter.setPower(-1.0);
            leftShooter.setPower(1.0);
        } else {
            rightShooter.setPower(0);
            leftShooter.setPower(0);
        }


    }
    
    private void setShooter(double velocity) {
        rightShooter.setVelocity(velocity);
        leftShooter.setVelocity(velocity);
    }
    

}
