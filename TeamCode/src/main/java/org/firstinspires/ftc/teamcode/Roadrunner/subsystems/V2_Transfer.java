package org.firstinspires.ftc.teamcode.Roadrunner.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class V2_Transfer {
    public final DcMotorEx transfer;


    public V2_Transfer(HardwareMap hardwareMap) {
        transfer = hardwareMap.get(DcMotorEx.class, "intake");
        transfer.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    }

    //-----------------------------Out--------------------------------------\\
    public class transferOut implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            transfer.setPower(1.0);
            return false;
        }
    }
    public Action out() {
        return new transferOut();
    }
    // ----------------------------Idle-------------------------------------\\
    public class transferIdle implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            transfer.setPower(0);
            return false;
        }
    }
    public Action idle() {
        return new transferIdle();
    }
    //-----------------------------Down--------------------------------------\\
    public class transferIn implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            transfer.setPower(-1.0);
            return false;
        }
    }
    public Action in() {
        return new transferIn();
    }


}
