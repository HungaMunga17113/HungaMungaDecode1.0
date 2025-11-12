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

public class SplineTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(65, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-55, 46, Math.toRadians(127)))
                .waitSeconds(0.5)
                .strafeToConstantHeading(new Vector2d(-47,47))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(-11.5,21),Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(-11.5,55))
                .splineToConstantHeading(new Vector2d(-45,45),Math.toRadians(180))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(127))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(14,24.5),Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(14,60))
                .strafeToConstantHeading(new Vector2d(14,24.5))
                .splineToConstantHeading(new Vector2d(-45,45),Math.toRadians(175))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(127))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(37,22),Math.toRadians(90))
                .strafeToConstantHeading(new Vector2d(35,50))
                .splineToConstantHeading(new Vector2d(-30,36),Math.toRadians(175))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(127))
                .waitSeconds(2)
                .strafeToLinearHeading(new Vector2d(2,47),Math.toRadians(270))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}