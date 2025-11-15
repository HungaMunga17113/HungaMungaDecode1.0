package org.firstinspires.ftc.teamcode.hunga_munga_26.new_code_here;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Disabled
@TeleOp(name = "IntakeTesting", group = "Testing")
public class IntakeTest extends OpMode {

    private DcMotorEx intakeMotor;

    @Override
    public void init() {
        // Map motor from Robot Configuration (must be named "intake")
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");

        // Coast when stopped (good for intake rollers)
        intakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);

        // Set default direction (flip if needed)
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addLine("Intake initialized. Left Trigger = In, Right Trigger = Out");
    }

    @Override
    public void loop() {
        double inPower = gamepad1.left_trigger;   // 0.0 to 1.0
//        double outPower = gamepad1.right_trigger; // 0.0 to 1.0

        if (inPower > 0) {
            // Intake IN (forward)
            intakeMotor.setPower(inPower); // proportional to trigger pull
            telemetry.addLine("Intake: IN");
//        } else if (outPower > 0) {
//            // Outtake OUT (reverse)
//            intakeMotor.setPower(-outPower); // proportional to trigger pull
//            telemetry.addLine("Intake: OUT");
        } else {
            // Stop
            intakeMotor.setPower(0.0);
            telemetry.addLine("Intake: STOPPED");
        }

        telemetry.update();
    }
}
