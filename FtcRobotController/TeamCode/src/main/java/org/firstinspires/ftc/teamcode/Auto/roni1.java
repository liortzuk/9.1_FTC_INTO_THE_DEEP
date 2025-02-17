package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.Elevator.Elevator2;
import org.firstinspires.ftc.teamcode.OpMode;
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="red basket / roni_1 :)", group="Robot")
public class roni1 extends OpMode {
    ElapsedTime time = new ElapsedTime();
    @Override
    protected void run() {

        double botDeg = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);
        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);

        // first point(score)
        drive_abs_point(driveTrain, -.64, 0.02);
        sleep(100);
        turn_to_abs_pos(driveTrain,310);
        Sample_to_Basket(lift);
        sleep(21000);
//
        // secound point(center)
        turn_to_abs_pos(driveTrain,30);
        drive_abs_point(driveTrain, -0.5, 0.5);
        sleep(10000);



     /*
        drive_abs_point(driveTrain, 0, 1);
        sleep(1000);
        drive_abs_point(driveTrain, 2, 4.5);
        turn_to_relative_pos(driveTrain, 115);
        sleep(50000);

        double botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        botDeg = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);



        // start
        driveTrain.turnToGyro_plus(Math.abs(botDeg));
        sleep(100);

        run_to_point(driveTrain,4, 0, 0.5, 0, botHeading);
        sleep(100);

        driveTrain.turnToGyro_minus(-15);
        sleep(200);

        run_to_point(driveTrain,5.4, 0, -0.5, 0, 0);
        sleep(100);

        // score
        lift.Move_Elevator(8000);
        intake_AR.setPosition(0);
        sleep(1000);

        intake_AR.setPosition(0.7);
        sleep(500);
        lift.Move_Elevator(-8000);
        sleep(100);

        run_to_point(driveTrain,1.5, 0, 0.5, 0, 0);
        sleep(100);

        // check point
        driveTrain.turnToGyro_plus(27);
        sleep(100);

        run_to_point(driveTrain, 1.45, 0, -0.5, 0, 0);
        sleep(200);

        lift.extend(-1,1);
        lift.move_intake_AG(1);
        sleep(500);

        intake_center_angle.setPosition(0.6);
        sleep(100);
        roni2_intake.setPosition(1);
        sleep(500);

        run_to_point(driveTrain, 1.65, 0, 0.5, 0, 0);
        sleep(100);

        roni2_intake.setPosition(0.3);
        sleep(1000);

        // change
        intake_AR.setPosition(.8);
        lift.extend(.2, 1);
        intake_center_angle.setPosition(0.2);
        sleep(500);

        lift.move_intake_AG(0.6);
        sleep(1000);
        roni2_intake.setPosition(1);
        sleep(1000);
        intake_center_angle.setPosition(0.7);
        lift.extend(-0.56, 1);

        driveTrain.turnToGyro_minus(-20);
        sleep(200);


        run_to_point(driveTrain, 2.5, 0, -0.5, 0, 0);
        sleep(100);

        // score
        lift.Move_Elevator(8000);
        intake_AR.setPosition(0);
        sleep(500);

        intake_AR.setPosition(0.7);
        sleep(500);
        lift.Move_Elevator(-8000);
        sleep(200);

      */


    }

    @Override
    protected void end() {

    }
}
