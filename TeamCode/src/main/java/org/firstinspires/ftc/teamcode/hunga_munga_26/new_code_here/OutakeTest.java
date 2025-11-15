package org.firstinspires.ftc.teamcode.hunga_munga_26.new_code_here;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Disabled
@TeleOp(name = "IntakeTesting", group = "Testing")
public abstract class OutakeTest extends OpMode {

    private DcMotorEx outakemotorleft;
    private DcMotorEx outakemotorright;
    @Override
    public void init() {
        // Map motor from Robot Configuration (must be named "intake")
        outakemotorleft = hardwareMap.get(DcMotorEx.class, "OutakeLeft");
        outakemotorright = hardwareMap.get(DcMotorEx.class, "OutakeRight");
        // Coast when stopped (good for intake rollers)
        outakemotorleft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        outakemotorright.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        // Set default direction (flip if needed)
        outakemotorleft.setDirection(DcMotorSimple.Direction.FORWARD);
        outakemotorright.setDirection(DcMotorSimple.Direction.FORWARD);
        telemetry.addLine("Intake initialized. Left Trigger = In, Right Trigger = Out");
    }

    @Override
    public void loop() {
        double outPower = gamepad1.right_trigger; // 0.0 to 1.0

         if (outPower > 0) {
            // Outtake OUT (reverse)
            outakemotorleft.setPower(-outPower); // proportional to trigger pull
            outakemotorright.setPower(-outPower);
            telemetry.addLine("OutakeLeft: SLEEP");
            telemetry.addLine("OutakeRight: SLEEP");
        } else {
            // Stop
            outakemotorleft.setPower(0.0);
            outakemotorright.setPower(0.0);
            telemetry.addLine("OutakeLeft: STOPPED");
            telemetry.addLine("OutakeRight: STOPPED");
        }

        telemetry.update();
    }
}
