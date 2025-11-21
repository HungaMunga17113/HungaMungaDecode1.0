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

public class ExpV2Red {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-55, 46, Math.toRadians(36)))
                .strafeToLinearHeading(new Vector2d(-12,13),Math.toRadians(127),new TranslationalVelConstraint(80))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-12,55),Math.toRadians(90),new TranslationalVelConstraint(80))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-12,13),Math.toRadians(127),new TranslationalVelConstraint(80))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(14,30),Math.toRadians(90),new TranslationalVelConstraint(80))
                .strafeToConstantHeading(new Vector2d(14,60),new TranslationalVelConstraint(30))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(14,30),Math.toRadians(90),new TranslationalVelConstraint(80))
                .strafeToLinearHeading(new Vector2d(-12,13),Math.toRadians(127),new TranslationalVelConstraint(80))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(37,24),Math.toRadians(90),new TranslationalVelConstraint(80))
                .strafeToConstantHeading(new Vector2d(37,60),new TranslationalVelConstraint(30))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-12,13),Math.toRadians(127),new TranslationalVelConstraint(80))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(2,38),Math.toRadians(270),new TranslationalVelConstraint(80))
                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
