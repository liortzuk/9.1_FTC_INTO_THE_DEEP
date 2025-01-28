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
@Disabled
public class malulinomous extends OpMode {
    @Override
    protected void run() {
        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);
        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);

        double botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        //go to center
        run_to_point(driveTrain,2, 0, 0.7, 0, botHeading);
        sleep(100);
        driveTrain.turnToGyro_plus(90);
        sleep(100);
        run_to_point(driveTrain,-0.6,0,0.5,0, botHeading);
        sleep(100);

        //lift elevator to put specimen
        lift.Move_Elevator(1400);
        sleep(100);
        run_to_point(driveTrain,-0.3,0,1,0,botHeading);
        sleep(100);
        lift.Move_Elevator(-400);
        sleep(100);


        //return elevator
        run_to_point(driveTrain,1.2,0,1,0, botHeading);
        sleep(100);
        lift.Move_Elevator(-1000);
        sleep(100);

        // going to take sample
        run_to_point(driveTrain,3,1,0,0,botHeading);
        sleep(100);
        driveTrain.turnToGyro_minus(75);
        sleep(100);
        lift.move_intake_AG(0.99);
        sleep(250);
        lift.move_intake_AG(0.44); intake_AR.setPosition(0.85); sleep(1000);
        sleep(200);

        //put in basket
        driveTrain.turnToGyro_plus(45);
        sleep(100);
        run_to_point(driveTrain,2,0,0.8,0,0);
        sleep(150);
        run_to_point(driveTrain,0.4,0,-1,0,0);
        sleep(500);
        lift.Move_Elevator(8000);
        intake_AR.setPosition(0);
        sleep(1000);

        intake_AR.setPosition(0.7);
        sleep(1000);
        lift.Move_Elevator(-8000);
        sleep(500);

        //go to park
        driveTrain.turnToGyro_minus(25);
        sleep(250);
        run_to_point(driveTrain,3,0,1,0,botHeading);
        sleep(300);
        run_to_point(driveTrain,1,1,0,0,botHeading);

        sleep(5000);





    }

    @Override
    protected void end() {

    }
}
