package org.firstinspires.ftc.teamcode.Roadrunner.roadrunner_tutorial.base_subsystem_templates;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Servo_Template {
    private final Servo servo;

   // Servo is used to hold the object that represents the physical servo motor connected
    public Servo_Template(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "servo");
    }

    //-------------------------------Position 1-------------------------------\\
    // @NonNull TelemetryPacket packet is a method Road Runner calls to execute the action
    public class Servo_Position1 implements Action {
        @Override
      // Tells servo to move to position 0.0
      // Servos position typically a value between 0.0 and 1.0 which is the range of motion
       public boolean run(@NonNull TelemetryPacket packet) {
            servo.setPosition(0);
            return false;
        }
    }
    // False indicates to run only once
    public Action toPos1() {
        return new Servo_Position1();
    }

    //-------------------------------Position 2-------------------------------\\
    // Tells servo to move to position 0.6 which is a little over halfway of its range
    public class Servo_Position2 implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            servo.setPosition(0.6);
            return false;
        }
    }
    public Action toPos2() {
        return new Servo_Position2();
    }
}
