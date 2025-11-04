package org.firstinspires.ftc.teamcode.hunga_munga_26.new_code_here;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorTemplateRRLM2 {
    public final DcMotorEx outtake1;
    public final DcMotorEx outtake2;

    public final DcMotorEx intake;


    // Motor_Template is the class name
    public MotorTemplateRRLM2(HardwareMap hardwareMap) {
        // Gets motor name, will be set once we know which motor is which
        // Outtake Motor 1
        outtake1 = hardwareMap.get(DcMotorEx.class, "motor");
        outtake1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtake1.setDirection(DcMotorSimple.Direction.FORWARD);
        //Outtake Motor 2
        outtake2 = hardwareMap.get(DcMotorEx.class, "motor");
        outtake2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        outtake2.setDirection(DcMotorSimple.Direction.REVERSE);
        //Main Intake Motor
        intake = hardwareMap.get(DcMotorEx.class, "motor");
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
    }
}

//
//    //-----------------------------Position 1--------------------------------------\\
//    // Goal is to move the motors until the encoder position is greater than 100
//    public class MotorEx_Position1 implements Action {
//        private boolean initialized = false;
//        // Sets motor power to -0.8(reverse), keeps running until returns false
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            if (!initialized) {
//                motor.setPower(-0.8); //Motor Speed
//                initialized = true;
//            }
//            // Sets motor power to -0.8(reverse), keeps running until returns false
//            // If false, signals motor to fully stop by applying tiny amounts of power ( 0.1)
//            double pos = motor.getCurrentPosition();
//            packet.put("motorPos", pos);
//            if (pos > 100.0) {
//                return true;
//            } else {
//                motor.setPower(0.01); //negative gravity
//                return false;
//            }
//        }
//    }
//    public Action toPos1(){
//        return new MotorEx_Position1();
//    }
//
//    //----------------------------Position2----------------------------------\\
//    // Goal is to move the robot to a target between 395 and 405 ticks
//    public class Position2 implements Action {
//        private boolean initialized = false;
//        // Power of 0.8, and keeps running until ticks is inside range of 395-405 and returns false
//        // False signals to stop, true signals that the program is still running
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            if (!initialized) {
//                motor.setPower(0.8);
//                initialized = true;
//            }
//
//            double pos = motor.getCurrentPosition();
//            packet.put("motorPos", pos);
//            if (pos < 395 || pos > 405) {
//                return true;
//            } else {
//                motor.setPower(0.01); //negative gravity(opposes motion)
//                return false;
//            }
//        }
//    }
//    public Action toPos2() {
//        return new Position2();
//    }
//
//    //--------------------------Position 3---------------------------------\\
//    // The goal is to move the motor until the encoder position is at least 2000
//    public class Position3 implements Action {
//        private boolean initialized = false;
//        // It sets the power to 0.8, which means the robot is moving forward.
//        // It keeps running until the 2000 mark is reached and will ultimately stop running
//        @Override
//        public boolean run(@NonNull TelemetryPacket packet) {
//            if (!initialized) {
//                motor.setPower(0.8);
//                initialized = true;
//            }
//
//            double pos = motor.getCurrentPosition();
//            packet.put("motorPos", pos);
//            if (pos < 2000) {
//                return true;
//            } else {
//                motor.setPower(0.01); //negative gravity(opposes motion)
//                return false;
//            }
//        }
//    }
//    public Action toPos3() {
//        return new Position3();
//    }
//
//}
