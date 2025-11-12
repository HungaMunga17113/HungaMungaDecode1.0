package com.example.meepmeeptesting;

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

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-55, 46, Math.toRadians(127)))
                .waitSeconds(0.1)
                .strafeToConstantHeading(new Vector2d(-45,45))
                .waitSeconds(0.25)
                .waitSeconds(0.35)
                .waitSeconds(0.35)
                .strafeToLinearHeading(new Vector2d(-13,20),Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(-13,55),new TranslationalVelConstraint(20))
                .strafeToLinearHeading(new Vector2d(-45,45),Math.toRadians(127))
                .waitSeconds(0.25)
                .waitSeconds(0.35)
                .waitSeconds(0.35)
                .strafeToLinearHeading(new Vector2d(14,20),Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(14,62),new TranslationalVelConstraint(20))
                .strafeToConstantHeading(new Vector2d(14,40),new TranslationalVelConstraint(20))
                .strafeToLinearHeading(new Vector2d(-45,45),Math.toRadians(127))
                .waitSeconds(0.25)
                .waitSeconds(0.35)
                .waitSeconds(0.35)
                .strafeToLinearHeading(new Vector2d(37,22),Math.toRadians(90))
                //pick up 4th set
                .strafeToConstantHeading(new Vector2d(35,50))
                .splineToConstantHeading(new Vector2d(-31,27),Math.toRadians(190))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(127),new TranslationalVelConstraint(80))
                .waitSeconds(0.25)
                .waitSeconds(0.35)
                .waitSeconds(0.35)
                .strafeToLinearHeading(new Vector2d(2.5,45),Math.toRadians(270))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}