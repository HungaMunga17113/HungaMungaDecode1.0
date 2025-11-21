package org.firstinspires.ftc.teamcode.hunga_munga_26.teleOp;

import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates.MecanumDrive;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;
import java.util.concurrent.TimeUnit;

@TeleOp
public class RedParkTele extends OpMode {
    Deadline gamepadRateLimit = new Deadline(250, TimeUnit.MILLISECONDS);

    DcMotor leftFront, leftBack, rightFront, rightBack;
    DcMotor intake;
    DcMotor leftOuttake, rightOuttake;

    ElapsedTime outtakeTime = new ElapsedTime();

    private enum outtakeModes {Shoot, Return, Rest}
    private outtakeModes pivotMode;
    double outtakePower = 0.99;
    MecanumDrive drive;
    private enum Mode { DRIVER_CONTROL, AUTO_PARK, AUTO_PARK_CORNER }
    private Mode mode = Mode.DRIVER_CONTROL;
    private Action parkAction;

    // Parking pose
    //private static final double PARK_X_RED = 3;
    //private static final double PARK_Y_RED = 86;
    //private static final double PARK_X_RED = 0;
    //private static final double PARK_Y_RED = 0;
    private static final double PARK_X_RED = 40;
    private static final double PARK_Y_RED = 53;
    private static final double PARK_HEADING_RED = Math.toRadians(120);

    private double getParkX() {
        return PARK_X_RED;
    }

    private double getParkY() {
        return PARK_Y_RED;
    }

    private double getParkHeading() {
        return PARK_HEADING_RED;
    }

    @Override
    public void init() {
        leftFront  = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack   = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack  = hardwareMap.get(DcMotor.class, "rightBack");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        leftOuttake  = hardwareMap.get(DcMotorEx.class, "leftOuttake");
        rightOuttake = hardwareMap.get(DcMotorEx.class, "rightOuttake");

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);
        leftOuttake.setDirection(DcMotorSimple.Direction.REVERSE);
        rightOuttake.setDirection(DcMotorSimple.Direction.FORWARD);

        outtakeTime.reset();

        //drive = new MecanumDrive(hardwareMap, new Pose2d(-7.75, 45, 270));
        drive = new MecanumDrive(hardwareMap, new Pose2d(-20, 55, 90));
    }

    @Override
    public void loop() {
        switch (mode) {
            case DRIVER_CONTROL:
                Drive();
                Intake();
                Outtake();

                if (gamepad1.a) {
                    Pose2d currentPose = drive.localizer.getPose();

                    parkAction = drive.actionBuilder(currentPose)
                            .strafeToLinearHeading(new Vector2d(getParkX(), getParkY()),getParkHeading())
                            .build();

                    mode = Mode.AUTO_PARK;
                }
                break;

            case AUTO_PARK:
                if (parkAction != null) {
                    TelemetryPacket packet = new TelemetryPacket();
                    boolean running = parkAction.run(packet);

                    telemetry.addData("X", drive.localizer.getPose().position.x);
                    telemetry.addData("Y", drive.localizer.getPose().position.y);
                    telemetry.addData("Heading",
                            Math.toDegrees(drive.localizer.getPose().heading.toDouble()));

                    if (!running) {
                        parkAction = null;
                        mode = Mode.DRIVER_CONTROL;
                    }
                }
                break;

        }

    }

    public void Drive() {
        double max;

        double axial = -gamepad1.left_stick_y;
        double lateral = gamepad1.left_stick_x;
        double yaw = gamepad1.right_stick_x;
        double drivePower = 0.95 - (0.6 * gamepad1.left_trigger);

        double leftFrontPower = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower = axial - lateral + yaw;
        double rightBackPower = axial + lateral - yaw;

        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }
        rightFront.setPower(rightFrontPower*drivePower);
        rightBack.setPower(rightBackPower*drivePower);
        leftFront.setPower(leftFrontPower*drivePower);
        leftBack.setPower(leftBackPower*drivePower);
    }

    private void Intake() {
        double intakePower = 1;
        if (gamepad1.right_trigger > 0.15) {
            intake.setPower(intakePower);
        } else if (gamepad1.y) {
            intake.setPower(-intakePower);
        } else {
            intake.setPower(0);
        }
    }

    private void Outtake() {
        if (gamepad1.left_bumper && !leftOuttake.isBusy()) {
            pivotMode = outtakeModes.Shoot;
            leftOuttake.setPower(outtakePower);
            rightOuttake.setPower(outtakePower);
            outtakeTime.reset();
        }
        if (pivotMode == outtakeModes.Shoot && outtakeTime.milliseconds() > 400) {
            pivotMode = outtakeModes.Return;
            leftOuttake.setPower(-outtakePower);
            rightOuttake.setPower(-outtakePower);
            outtakeTime.reset();
        }
        if (pivotMode == outtakeModes.Return && outtakeTime.milliseconds() > 750) {
            pivotMode = outtakeModes.Rest;
            leftOuttake.setPower(-0.167);
            rightOuttake.setPower(-0.167);
            outtakeTime.reset();
        }
    }
}


