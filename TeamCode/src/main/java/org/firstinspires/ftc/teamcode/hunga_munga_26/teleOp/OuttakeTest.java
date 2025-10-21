package org.firstinspires.ftc.teamcode.hunga_munga_26.teleOp;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp
public class OuttakeTest extends OpMode {

    // Initializing/making motor names
    DcMotor leftFront, leftBack, rightFront, rightBack;
    DcMotorEx leftOuttake, rightOuttake;
    //Initialize Variables
    private boolean shooting = false;
    private boolean returning = false;
    ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    /*

    (Button) Initialize Period, before you press start on your program.
     */
    public void init() {



        //set hardware map names (aka what the controller understands)
        //leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        //leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        //rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        //rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftOuttake = hardwareMap.get(DcMotorEx.class, "leftOuttake");
        rightOuttake = hardwareMap.get(DcMotorEx.class, "rightOuttake");

        // Motor power goes from -maxSpeed -> maxSpee
        // Sets motor direction. Says which direction the motor will turn when given full power of maxSpeed
        leftOuttake.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightOuttake.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // When no power (aka no joysticks moving (idle) ), robot should brake on stop
        //leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftOuttake.setDirection(DcMotorSimple.Direction.REVERSE);
        rightOuttake.setDirection(DcMotorSimple.Direction.FORWARD);

        leftOuttake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightOuttake.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        leftOuttake.setTargetPosition(0);
        rightOuttake.setTargetPosition(0);
    }

    /*
    (Button) What happens when you press Start/Play
    You constantly update all your code
    Basically just keep all your code over here
     */
    public void loop() {

        shootTest();
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

    public void shootTest() {
        int outtakePosition = 134;
        if (gamepad1.a && !shooting) {
            resetOuttake();
            shooting = true;
            //shootStartTime = System.currentTimeMillis();
            //private boolean returns = false;
            leftOuttake.setTargetPosition(outtakePosition);
            rightOuttake.setTargetPosition(outtakePosition);
            leftOuttake.setPower(1);
            rightOuttake.setPower(1);
        }

        //long fireDuration = 500;
        if (shooting) {
            //if (System.currentTimeMillis() - shootStartTime >= fireDuration) {
            if (leftOuttake.getCurrentPosition() >= outtakePosition) {
                leftOuttake.setPower(-1);
                rightOuttake.setPower(-1);
                returning = true;
                shooting = false;
                //returnStartTime = System.currentTimeMillis();

            }
        }
        //long returnDuration = 1000;
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
    //public void returnTest() {
        //if (gamepad1.b && !returns) {
           // returns=true;
           // shootStartTime = System.currentTimeMillis();
          //  leftOuttake.setPower(-1);
          //  rightOuttake.setPower(-1);
       // }
      //  long returnsDuration = 5000;
      //  if (returns) {
        //    if (System.currentTimeMillis() - shootStartTime >= returnsDuration) {
         //       returns=false;
          //      leftOuttake.setVelocity(0);
          //      rightOuttake.setVelocity(0);
          //  }
     //   }
 //   }
}







