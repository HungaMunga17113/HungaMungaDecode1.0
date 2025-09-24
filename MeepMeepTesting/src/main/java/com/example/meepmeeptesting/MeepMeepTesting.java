package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(20, -60, 0))
//                .lineToX(30)
//                .turn(Math.toRadians(90))
//                .lineToY(30)
//                .turn(Math.toRadians(90))
//                .lineToX(0)
//                .strafeToLinearHeading(new Vector2d(-50,-30),Math.toRadians(90),new TranslationalVelConstraint(90))
//                .waitSeconds(2)
//                .splineToSplineHeading(new Pose2d(20,20,Math.toRadians(90)),Math.toRadians(0))
                //convert this into a loop for autonomous mode
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(45,-60),Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(45,0))
                //intake1 over
                .strafeToLinearHeading(new Vector2d(-20,20),Math.toRadians(125))
                .waitSeconds(5)
                //shooting pos1
                .strafeToConstantHeading(new Vector2d(45,-60))
                //loading
                .waitSeconds(4)
                .strafeToLinearHeading(new Vector2d(10,-60),Math.toRadians(120))
                //shooting pos2
                .waitSeconds(5)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}