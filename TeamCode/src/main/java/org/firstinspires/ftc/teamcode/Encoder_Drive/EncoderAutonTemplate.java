package org.firstinspires.ftc.teamcode.Encoder_Drive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class EncoderAutonTemplate extends LinearOpMode {
    //How to calculate ticks per inch
    //Ticks multiplied by inches in same distance
    //private final double ticksPerIn = 45;

    DrivetrainHardware drivetrainHardware = new DrivetrainHardware(hardwareMap);
    DriveFunctions df = new DriveFunctions();

    DcMotorEx intake;

    @Override
    public void runOpMode() {

        //--------------------- INITIALIZE ---------------------\\
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        drivetrainHardware.getDrivetrainHardware();


        //------------------------- RUN ------------------------\\
        //waits for the Driver Hub to receive "start" input
        waitForStart();
        //TODO: Pathing, use the functions below to move the robot
        //**NOTE - NEVER set power to 1.0 at the start, start at 0.5 and work your way up.
        // 1.0 loses lots of consistency, and the robot movements would be choppy

        df.vertical(500,0.5,5);


    }

    public void intake(double speed, long time) {
        intake.setVelocity(speed);
        sleep(time);
        intake.setVelocity(0);
    }

}