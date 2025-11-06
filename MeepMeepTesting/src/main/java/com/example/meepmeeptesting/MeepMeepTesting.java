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

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(50, 50, Math.toRadians(45)))
                .strafeToConstantHeading(new Vector2d(47,47))
                //1st 3 balls over
                .strafeToLinearHeading(new Vector2d(11,11),Math.toRadians(90),new TranslationalVelConstraint(90))
                .strafeToConstantHeading(new Vector2d(11,53))
                .strafeToLinearHeading(new Vector2d(47,47),Math.toRadians(45),new TranslationalVelConstraint(90))
                //2nd 3 balls over
                .strafeToLinearHeading(new Vector2d(-13,13.5),Math.toRadians(90),new TranslationalVelConstraint(90))
                .strafeToConstantHeading(new Vector2d(-13,61))
                .strafeToConstantHeading(new Vector2d(-13,50))
                .strafeToLinearHeading(new Vector2d(47,47),Math.toRadians(45),new TranslationalVelConstraint(90))
                //3rd 3 balls over
                .strafeToLinearHeading(new Vector2d(-34,25),Math.toRadians(90),new TranslationalVelConstraint(90))
                .strafeToConstantHeading(new Vector2d(-34,61))
                .strafeToConstantHeading(new Vector2d(-34,55))
                .strafeToLinearHeading(new Vector2d(47,47),Math.toRadians(45),new TranslationalVelConstraint(90))
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