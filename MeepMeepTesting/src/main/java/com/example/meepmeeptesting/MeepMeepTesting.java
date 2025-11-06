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

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(48, 48, Math.toRadians(50)))
                .strafeToConstantHeading(new Vector2d(-47,47))
                .waitSeconds(1.5)
                //1st 3 balls over
                .strafeToLinearHeading(new Vector2d(-12,22),Math.toRadians(90),new TranslationalVelConstraint(70))
                .strafeToConstantHeading(new Vector2d(-12,55),new TranslationalVelConstraint(20))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(50),new TranslationalVelConstraint(70))
                .waitSeconds(1.5)
                //2nd 3 balls over
                .strafeToLinearHeading(new Vector2d(15,22),Math.toRadians(90),new TranslationalVelConstraint(70))
                .strafeToConstantHeading(new Vector2d(15,62),new TranslationalVelConstraint(20))
                .strafeToConstantHeading(new Vector2d(15,50))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(50),new TranslationalVelConstraint(70))
                .waitSeconds(1.5)
                //3rd 3 balls over
                .strafeToLinearHeading(new Vector2d(35,22),Math.toRadians(90),new TranslationalVelConstraint(70))
                .strafeToConstantHeading(new Vector2d(35,62),new TranslationalVelConstraint(20))
                .strafeToConstantHeading(new Vector2d(35,50))
                .strafeToLinearHeading(new Vector2d(-47,47),Math.toRadians(50),new TranslationalVelConstraint(70))
                .waitSeconds(1.5)
                //4th 3 balls over




// .turn(Math.toRadians(90))
//                .lineToY(30)
//                .turn(Math.toRadians(90))
//                .lineToX(0)
//                .strafeToLinearHeading(new Vector2d(-50,-30),Math.toRadians(90),new TranslationalVelConstraint(90))
//                .waitSeconds(2)
//                .splineToSplineHeading(new Pose2d(20,20,Math.toRadians(90)),Math.toRadians(0))

                .build());

        BufferedImage bg = null;
        try {
            bg = ImageIO.read(new File("MeepMeepTesting/src/main/java/com/example/meepmeeptesting/DECODE.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        meepMeep.setBackground(bg)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)

                .addEntity(myBot)
                .start();
    }
}