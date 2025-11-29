package org.firstinspires.ftc.teamcode.hunga_munga_26.teleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


import org.firstinspires.ftc.robotcore.internal.system.Deadline;
import org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates.MecanumDriveV1;

import java.util.concurrent.TimeUnit;

@TeleOp
public class V2IntakeShooterTest extends OpMode {
    Deadline gamepadRateLimit = new Deadline(250, TimeUnit.MILLISECONDS);

    DcMotor intake;
    DcMotor transfer;
    DcMotorEx leftOuttake, rightOuttake;
    DcMotor leftFront, leftBack, rightFront, rightBack;
    //Initialize Variables
    /*
    (Button) Initialize Period, before you press start on your program.
     */
    MecanumDriveV1 drive;

    public void init() {
        leftFront  = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack   = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack  = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        //set hardware map names (aka what the controller understands)
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        transfer = hardwareMap.get(DcMotorEx.class, "transfer");

        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        transfer.setDirection(DcMotorSimple.Direction.FORWARD);

        leftOuttake = hardwareMap.get(DcMotorEx.class, "leftOuttake");
        rightOuttake = hardwareMap.get(DcMotorEx.class, "rightOuttake");

        leftOuttake.setDirection(DcMotorSimple.Direction.REVERSE);
        rightOuttake.setDirection(DcMotorSimple.Direction.FORWARD);
        drive = new MecanumDriveV1(hardwareMap, new Pose2d(-20, 55, 90));

    }

    public void loop() {
        Drive();
        Intake();
        Transfer();
        shootTest();
    }
    private void Drive() {
            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x
            ));

            drive.updatePoseEstimate();
    }
    private void Intake() {
        double intakePower = 1;
        if (gamepad1.right_trigger > 0.15) {
            intake.setPower(intakePower);
        } else if (gamepad1.x) {
            intake.setPower(-intakePower);
        } else {
            intake.setPower(0);
        }
    }

    private void Transfer() {
        double transferPower = 1;
        if (gamepad1.right_bumper) {
            transfer.setPower(transferPower);
        } else if (gamepad1.y) {
            transfer.setPower(-transferPower);
        } else {
            transfer.setPower(0);
        }
    }
    public void shootTest() {
        double outtakePower = 0.97;
        /*
        if (gamepad1.left_bumper) {
            leftOuttake.setPower(outtakePower);
            rightOuttake.setPower(outtakePower);
        } else if (gamepad1.a) {
            leftOuttake.setPower(-outtakePower);
            rightOuttake.setPower(-outtakePower);
        } else {
            leftOuttake.setPower(0);
            rightOuttake.setPower(0);
        }
         */
        leftOuttake.setPower(1);
        rightOuttake.setPower(1);
    }
}