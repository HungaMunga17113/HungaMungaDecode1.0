package org.firstinspires.ftc.teamcode.roadrunner_tutorial.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DT_TeleOpTest extends OpMode {

    // Initializing/making motor names
    DcMotor leftFront, leftBack, rightFront, rightBack;

    /*
    (Button) Initialize Period, before you press start on your program.
    set hardware map names (aka what the controller understands)
     */
    public void init() {
        leftFront  = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack   = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack  = hardwareMap.get(DcMotor.class, "rightBack");

        // Motor power goes from -1.0 -> 1.0
        // Sets motor direction. Says which direction the motor will turn when given full power of 1.0
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    /*
    (Button) What happens when you press Start/Play
    You constantly update all your code
    Basically just keep all your code over here
     */

    public void loop() {

    }

    /*
    Left Joystick ---------
        1. Up/Down - Robot goes forward, and backwards | all motors 1.0 power, all motors -1.0 power
        2. Left/Right:
            a. Robot strafes left | (rightFront, leftBack 1.0) & (rightBack, leftFront -1.0)
            b. Robot strafes right | (rightFront, leftBack -1.0) & (rightBack, leftFront 1.0)

    Right Joystick ---------
        3. Turn:
            a. Robot strafes left | (rightFront, leftBack 1.0) & (rightBack, leftFront -1.0)
            b. Robot strafes left | (rightFront, leftBack -1.0) & (rightBack, leftFront 1.0)
     */

    private void Drive() {
        //Forward - If left joystick y is greater than 0,
        // make robot go forward by setting positive power to all motors
        if (gamepad1.left_stick_y > 0) {
            leftFront.setPower(1);
            leftBack.setPower(1);
            rightFront.setPower(1);
            rightBack.setPower(1);
        }

    }

}
