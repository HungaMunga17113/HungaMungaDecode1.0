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

public class BlueTest {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(90, 70, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-55, -46, Math.toRadians(235)))
                .waitSeconds(0.1)
                .strafeToConstantHeading(new Vector2d(-45,-45))
                .waitSeconds(0.25)
                .waitSeconds(0.35)
                .waitSeconds(0.35)
                .strafeToLinearHeading(new Vector2d(-12,-20),Math.toRadians(270))
                .strafeToConstantHeading(new Vector2d(-13,-55),new TranslationalVelConstraint(17.5))
                .lineToYLinearHeading(-50, Math.toRadians(300))
                .lineToYSplineHeading(-42.67, Math.toRadians(240))
                .strafeToLinearHeading(new Vector2d(-45,-45),Math.toRadians(235))
                .waitSeconds(0.25)
                .waitSeconds(0.35)
                .waitSeconds(0.35)
                .strafeToLinearHeading(new Vector2d(10.25,-20),Math.toRadians(270))
                .strafeToConstantHeading(new Vector2d(9.25,-62),new TranslationalVelConstraint(20))
                .lineToYLinearHeading(-56, Math.toRadians(295))
                .lineToYSplineHeading(-50, Math.toRadians(245))
                .strafeToLinearHeading(new Vector2d(-45,-45),Math.toRadians(235))
                .waitSeconds(0.25)
                .waitSeconds(0.35)
                .waitSeconds(0.35)
                .strafeToLinearHeading(new Vector2d(33.5,-17.75),Math.toRadians(270))
                .strafeToConstantHeading(new Vector2d(32,-62),new TranslationalVelConstraint(20))
                .lineToYLinearHeading(-53.5, Math.toRadians(295))
                .lineToYSplineHeading(-45, Math.toRadians(245))
                .strafeToLinearHeading(new Vector2d(-45,-45),Math.toRadians(235))
                .waitSeconds(0.25)
                .waitSeconds(0.35)
                .waitSeconds(0.35)
                .strafeToLinearHeading(new Vector2d(2.5,-45),Math.toRadians(90))
                .build());



        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}