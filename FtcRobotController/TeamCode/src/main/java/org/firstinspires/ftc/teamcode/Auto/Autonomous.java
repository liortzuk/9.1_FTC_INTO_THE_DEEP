package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.Elevator.Elevator2;
import org.firstinspires.ftc.teamcode.OpMode;
import org.firstinspires.ftc.teamcode.PID;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="AUOTONOMOUS_NO_GYRO_BASKET", group="Robot")
public class Autonomous extends OpMode {



    @Override
    public void run(){
        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);
        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);

        drive_abs_point(driveTrain, -0.5, 0.5);

        sleep(100000);

        // preload sample
        drive_abs_point(driveTrain, -0.74, 0.5);
        turn_to_relative_pos(driveTrain,45);
        sleep(100);

        Sample_to_Basket(lift);

        //first sample
        drive_abs_point(driveTrain,-0.65,2);
        turn_to_relative_pos(driveTrain,315);

        Take_Sample(lift);
        Transfer_sample(lift);

        drive_abs_point(driveTrain,-0.74,0.5);
        turn_to_relative_pos(driveTrain,45);

        Sample_to_Basket(lift);

        // second sample
        drive_abs_point(driveTrain,-1,2);
        turn_to_relative_pos(driveTrain,315);

        Take_Sample(lift);
        Transfer_sample(lift);

        drive_abs_point(driveTrain,-0.74,0.5);
        turn_to_relative_pos(driveTrain,45);

        Sample_to_Basket(lift);



    }

    @Override
    protected void end() {

    }




}
