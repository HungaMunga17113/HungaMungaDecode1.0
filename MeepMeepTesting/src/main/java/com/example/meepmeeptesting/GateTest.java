package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GateTest {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(90, 70, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-55, -46, Math.toRadians(235)))
                        .strafeToConstantHeading(new Vector2d(-45,45))
                        .waitSeconds(0.225)
                        .waitSeconds(0.335)
                        .waitSeconds(0.335)

                    .strafeToLinearHeading(new Vector2d(-13.5,20),Math.toRadians(90))
                    .strafeToConstantHeading(new Vector2d(-14.5,55),new TranslationalVelConstraint(17.5))

                    .strafeToConstantHeading(new Vector2d(-13,43))
                    .strafeToLinearHeading(new Vector2d(-5,60.25),Math.toRadians(180))
                    .waitSeconds(0.3)
                    .strafeToLinearHeading(new Vector2d(-45,45),Math.toRadians(125))
                    .waitSeconds(0.225)
                    .waitSeconds(0.335)
                    .waitSeconds(0.335)
                    .strafeToLinearHeading(new Vector2d(10.55,17),Math.toRadians(90))
                    .strafeToConstantHeading(new Vector2d(9.55,62),new TranslationalVelConstraint(20))
                    .lineToYLinearHeading(53.5, Math.toRadians(65))
                    .lineToYSplineHeading(45, Math.toRadians(115))
                    .strafeToLinearHeading(new Vector2d(-45,45),Math.toRadians(125))
                    .waitSeconds(0.225)
                    .waitSeconds(0.335)
                    .waitSeconds(0.335)
                    .strafeToLinearHeading(new Vector2d(32.75,17.75),Math.toRadians(90))
                    .strafeToConstantHeading(new Vector2d(31.25,62),new TranslationalVelConstraint(20))
                //.strafeToConstantHeading(new Vector2d(31.5,50))
                    .lineToYLinearHeading(53.5, Math.toRadians(65))
                    .lineToYSplineHeading(45, Math.toRadians(115))
                    .strafeToLinearHeading(new Vector2d(-47.25,45),Math.toRadians(125))
                    .waitSeconds(0.225)
                    .waitSeconds(0.335)
                    .waitSeconds(0.335)

                    .strafeToLinearHeading(new Vector2d(-7.75,45),Math.toRadians(270))
                    .build());



        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}