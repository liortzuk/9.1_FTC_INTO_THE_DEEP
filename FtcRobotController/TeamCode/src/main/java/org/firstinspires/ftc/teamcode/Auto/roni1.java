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
        Imu.resetYaw();
        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);
        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);

        // first point
        drive_relative_distance(driveTrain, -0.66, 0.2, false);
        sleep(500);
        turn(driveTrain,45);
        sleep(500);
        drive_relative_distance(driveTrain, 0, -1.2, false);
        Sample_to_Basket(lift);

        // second point
        /*
        turn(driveTrain, -40);
        sleep(500);
        Take_SampleP1(lift);
        sleep(500);
        drive_relative_distance(driveTrain, 0.15, 0, false);
        sleep(500);
        drive_relative_distance(driveTrain, 0, 1.5, false);
        sleep(500);
        Take_SampleP2(lift);
        sleep(500);

        sleep(1000);

         */

    }

    @Override
    protected void end() {

    }
}
