package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.Elevator.Elevator2;
import org.firstinspires.ftc.teamcode.OpMode;
import org.firstinspires.ftc.teamcode.PID;
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="AUOTONOMOUS_NO_GYRO_BASKET", group="Robot")
@Disabled
public class Autonomous extends OpMode {
    @Override
    public void run(){
        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);
        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);

        //preload sample
        drive_relative_distance(driveTrain,0 , 0.85,false);
        sleep(500);

        turn(driveTrain,-45);
        sleep(200);

        drive_relative_distance(driveTrain,0 , -1,false);
        sleep(100);
        drive_relative_distance(driveTrain,0 , -0.22,false);

        Sample_to_Basket(lift);
        sleep(100);
        drive_relative_distance(driveTrain,0,0.3,false);
        sleep(100);
        turn(driveTrain,45);
        sleep(100);
        drive_relative_distance(driveTrain,0,-0.3,false);
        sleep(100);
        Take_SampleP1(lift);
        sleep(100);
        drive_relative_distance(driveTrain,0,0.2,false);
        sleep(100);
        roni2_intake.setPosition(0.6);
        sleep(100);
        Take_SampleP2(lift);



//
//        turn(driveTrain,10);
//        drive_relative_distance(driveTrain,0.2,0.1);
//        sleep(100);
//
//        Take_SampleP1(lift);
//        Transfer_sample(lift);
//
//        drive_relative_distance(driveTrain,0,0.15);
//        sleep(100);
//        Take_SampleP2(lift);
//
//        drive_relative_distance(driveTrain,-0.37,-0.3);
//        turn(driveTrain,300);
//        drive_relative_distance(driveTrain,0,-.1);
//        sleep(100);
//
//        Sample_to_Basket(lift);
//
//        turn(driveTrain,25);
//        drive_relative_distance(driveTrain,0.1,0.2);
//        sleep(100);
//
//        Take_SampleP1(lift);
//        drive_relative_distance(driveTrain,0,0.15);
//        sleep(100);
//        Take_SampleP2(lift);
//
//        Transfer_sample(lift);
//        drive_relative_distance(driveTrain,-0.1,-0.2);
//        turn(driveTrain,300);
//        drive_relative_distance(driveTrain,0,-0.1);
//        sleep(100);
//
//        Sample_to_Basket(lift);
//
//        turn(driveTrain,25);
//        drive_relative_distance(driveTrain,-0.1,0.2);
//        sleep(100);
//
//        Take_SampleP1(lift);
//        drive_relative_distance(driveTrain,0,0.1);
//        sleep(100);
//        Take_SampleP2(lift);
//
//        Transfer_sample(lift);
//
//        drive_relative_distance(driveTrain,0.15,-0.1);
//        drive_relative_distance(driveTrain,-0.05,-0.1);
//        turn(driveTrain,300);
//
//
//
//
//




    }

    @Override
    protected void end() {

    }




}
