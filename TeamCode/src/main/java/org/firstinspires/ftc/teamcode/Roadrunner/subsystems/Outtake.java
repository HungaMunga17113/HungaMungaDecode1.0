package org.firstinspires.ftc.teamcode.Roadrunner.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Outtake {
    public final DcMotorEx leftOuttake,rightOuttake;


    public Outtake(HardwareMap hardwareMap) {
        leftOuttake = hardwareMap.get(DcMotorEx.class, "leftOuttake");
        rightOuttake = hardwareMap.get(DcMotorEx.class, "rightOuttake");
        leftOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightOuttake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftOuttake.setDirection(DcMotorSimple.Direction.REVERSE);
        rightOuttake.setDirection(DcMotorSimple.Direction.FORWARD);


    }

    //-----------------------------Out--------------------------------------\\
    public class OuttakeOut implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            leftOuttake.setPower(1.0);
            rightOuttake.setPower(1.0);
            return false;
        }
    }
    public Action shoot() {
        return new OuttakeOut();
    }
    // ----------------------------Idle-------------------------------------\\
    public class OuttakeIdle implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            leftOuttake.setPower(-0.3);
            rightOuttake.setPower(-0.3);
            return false;
        }
    }
    public Action idle() {
        return new OuttakeIdle();
    }
    //-----------------------------Down--------------------------------------\\
    public class OuttakeDown implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            leftOuttake.setPower(-1.0);
            rightOuttake.setPower(-1.0);
            return false;
        }
    }
    public Action down() {
        return new OuttakeDown();
    }


}


