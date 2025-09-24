package org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RunIntake_Template {
    private final DcMotorEx intakeMotor;

    // RunIntake_Template is the class for the intake part of the robot
    public RunIntake_Template(HardwareMap hardwareMap) {
       // Gets name of motor from intake
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
       // Important, when motor's power is 0, won't brake but will float/roam in area
        intakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        // Default spin direction
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    // --------------------- Intake In --------------------- \\
    // All three actions either set motors power to (1.0 , 0.0 , -1.0) -- Signs are self explanatory

    // Pulls balls in to the robot
    public class IntakeIn implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeMotor.setPower(1.0); // full power in
            packet.put("intakeMode", "IN"); // telemetry
            return false; // keep running forever until interrupted
        }
    }
    public Action in() {
        return new IntakeIn();
    }

    // --------------------- Intake Idle --------------------- \\
    public class IntakeIdle implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeMotor.setPower(0.0); // stop
            packet.put("intakeMode", "IDLE"); // telemetry
            return false; // keep motor at idle until interrupted; floats so intake will work freely
        }
    }
    public Action idle() {
        return new IntakeIdle();
    }

    // --------------------- Intake Out --------------------- \\

   // Pushes balls out into the goal
    public class IntakeOut implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeMotor.setPower(-1.0); // full power out
            packet.put("intakeMode", "OUT"); // telemetry
            return false; // keep running forever until interrupted
        }
    }
    public Action out() {
        return new IntakeOut();
    }
}
