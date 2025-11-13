package org.firstinspires.ftc.teamcode.hunga_munga_26.teleOp;

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
public class TestTeleopWithParking extends OpMode {
    Deadline gamepadRateLimit = new Deadline(250, TimeUnit.MILLISECONDS);

    DcMotor leftFront, leftBack, rightFront, rightBack;
    DcMotor intake;
    DcMotor leftOuttake, rightOuttake;

    ElapsedTime outtakeTime = new ElapsedTime();

    private enum outtakeModes {Shoot, Return, Rest}
    private outtakeModes pivotMode;
    double outtakePower = 0.99;

    // --- Auto Park ---
    private enum Mode { DRIVER_CONTROL, AUTO_PARK }
    private Mode mode = Mode.DRIVER_CONTROL;
    private MecanumDrive rrDrive;
    private Action parkAction;

    // Alliance selection
    private enum Alliance { RED, BLUE }
    private Alliance alliance = Alliance.RED; // change to BLUE if needed

    // Parking pose
    private static final double PARK_X_RED = 38;
    private static final double PARK_Y_RED = -33;
    private static final double PARK_HEADING_RED = Math.toRadians(90);

    private static final double PARK_X_BLUE = 38;
    private static final double PARK_Y_BLUE = 33; // mirrored Y for blue
    private static final double PARK_HEADING_BLUE = Math.toRadians(90);

    private double getParkX() {
        return (alliance == Alliance.RED) ? PARK_X_RED : PARK_X_BLUE;
    }

    private double getParkY() {
        return (alliance == Alliance.RED) ? PARK_Y_RED : PARK_Y_BLUE;
    }

    private double getParkHeading() {
        return (alliance == Alliance.RED) ? PARK_HEADING_RED : PARK_HEADING_BLUE;
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
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
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

        // Initialize Road Runner drive
        rrDrive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        telemetry.addLine("TeleOp + AutoPark Initialized");
    }

    @Override
    public void loop() {
        switch (mode) {
            case DRIVER_CONTROL:
                Drive();
                Intake();
                Outtake();

                // Press A to start auto park
                if (gamepad1.a) {
                    Pose2d currentPose = rrDrive.localizer.getPose();

                    // Build action using supported methods
                    parkAction = rrDrive.actionBuilder(currentPose)
                            .strafeTo(new Vector2d(getParkX(), getParkY()))
                            .turnTo(getParkHeading())
                            .build();

                    mode = Mode.AUTO_PARK;
                    telemetry.addLine("Auto Parking Started!");
                }
                break;

            case AUTO_PARK:
                if (parkAction != null) {
                    TelemetryPacket packet = new TelemetryPacket();
                    boolean running = parkAction.run(packet);

                    telemetry.addData("AutoPark", running ? "Running..." : "Done");
                    telemetry.addData("X", rrDrive.localizer.getPose().position.x);
                    telemetry.addData("Y", rrDrive.localizer.getPose().position.y);
                    telemetry.addData("Heading",
                            Math.toDegrees(rrDrive.localizer.getPose().heading.toDouble()));

                    if (!running) {
                        parkAction = null;
                        mode = Mode.DRIVER_CONTROL;
                        telemetry.addLine("Parking complete! Back to manual control.");
                    }
                }
                break;
        }

        telemetry.addData("Mode", mode);
        telemetry.addData("Alliance", alliance);
        telemetry.update();
    }

    // ---------------- Existing subsystems ----------------

    public void Drive() {
        double axial = gamepad1.left_stick_y;
        double lateral = -gamepad1.left_stick_x;
        double yaw = -gamepad1.right_stick_x;
        double drivePower = 0.95 - (0.6 * gamepad1.left_trigger);

        double leftFrontPower = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower = axial - lateral + yaw;
        double rightBackPower = axial + lateral - yaw;

        double max = Math.max(
                Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower)),
                Math.max(Math.abs(leftBackPower), Math.abs(rightBackPower))
        );

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        leftFront.setPower(leftFrontPower * drivePower);
        rightFront.setPower(rightFrontPower * drivePower);
        leftBack.setPower(leftBackPower * drivePower);
        rightBack.setPower(rightBackPower * drivePower);
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

    private double getBatteryVoltage() {
        double result = Double.POSITIVE_INFINITY;
        for (VoltageSensor sensor : hardwareMap.voltageSensor) {
            double voltage = sensor.getVoltage();
            if (voltage > 0) result = Math.min(result, voltage);
        }
        return result;
    }
}
