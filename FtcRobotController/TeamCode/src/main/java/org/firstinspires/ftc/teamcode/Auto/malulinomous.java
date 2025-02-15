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
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="malulinomous", group="Robot")
public class malulinomous extends OpMode {
    @Override
    protected void run() {
        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);
        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);

        double botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // preload specimen
        drive_abs_point(driveTrain,0.3,1);

        turn_to_relative_pos(driveTrain,180);
        Specimen_Drop(lift,driveTrain);

        // specimen 2
        drive_abs_point(driveTrain,3.5,0.5);

        //pickup
        turn_to_relative_pos(driveTrain,180);
        lift.Move_Elevator(1200);
        sleep(100);
        roni_IA.setPosition(0.85);
        drive_abs_point(driveTrain,3.5,0.05);
        roni_IA.setPosition(0.2);

        //go to center
        drive_abs_point(driveTrain,0.3,1);
        turn_to_relative_pos(driveTrain,180);
        Specimen_Drop(lift,driveTrain);

        drive_abs_point(driveTrain,0,0);

    }

    @Override
    protected void end() {

    }
}
