package org.firstinspires.ftc.teamcode.hunga_munga_26.teleOp;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.VoltageSensor;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

@TeleOp
public class V2IntakeShooterTest extends OpMode {
    Deadline gamepadRateLimit = new Deadline(250, TimeUnit.MILLISECONDS);

    DcMotor intake;
    DcMotor transfer;
    DcMotorEx leftOuttake, rightOuttake;
    //Initialize Variables
    /*
    (Button) Initialize Period, before you press start on your program.
     */
    public void init() {

        //set hardware map names (aka what the controller understands)
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        transfer = hardwareMap.get(DcMotorEx.class, "transfer");

        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        transfer.setDirection(DcMotorSimple.Direction.FORWARD);

        leftOuttake = hardwareMap.get(DcMotorEx.class, "leftOuttake");
        rightOuttake = hardwareMap.get(DcMotorEx.class, "rightOuttake");

        leftOuttake.setDirection(DcMotorSimple.Direction.REVERSE);
        rightOuttake.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void loop() {
        Intake();
        Transfer();
        shootTest();
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
            intake.setPower(transferPower);
        } else if (gamepad1.y) {
            intake.setPower(-transferPower);
        } else {
            intake.setPower(0);
        }
    }
    public void shootTest() {
        double outtakePower = 0.97;
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
    }
}