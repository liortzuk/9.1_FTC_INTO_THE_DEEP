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

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="idodienomous", group="Robot")
@Disabled
public class Autonomous extends OpMode {


    Trajectory t1;
    ElapsedTime time = new ElapsedTime();

    final static int error = 29;


    @Override
    public void run(){
        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);
        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);

        double botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double botDeg = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        driveTrain.turnToGyro_plus(Math.abs(botDeg));
        sleep(500);
        run_to_point(driveTrain,1.5, 0, 1, 0, botHeading);
        sleep(500);
        driveTrain.turnToGyro_minus(-50);
        sleep(500);

        run_to_point(driveTrain,6.7, 0, -1, 0, botHeading);
        sleep(500);
        driveTrain.turnToGyro_plus(20);
        run_to_point(driveTrain,0.35, 0, -1, 0, botHeading);

        lift.Move_Elevator(8000);
        sleep(100);

        intake_AR.setPosition(0);
        sleep(1000);

        // new take
        intake_AR.setPosition(0.7);
        sleep(100);

        run_to_point(driveTrain,0.3,0,0.7,0,botHeading);
        sleep(200);


        lift.Move_Elevator(-8000);
        sleep(500);

        driveTrain.turnToGyro_plus(40);
        sleep(100);

        run_to_point(driveTrain, 0.3,0,1,0,botHeading); // pos = 2 tiles
        sleep(100);

        lift.extend(-0.5,1);
        sleep(500);

        intake_AR.setPosition(1);
        sleep(500);


        run_to_point(driveTrain, 1,0,0.5,0,botHeading);
        sleep(500);

        botDeg = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        telemetry.addData("imu: ", botDeg);
        telemetry.update();




    }

    @Override
    protected void end() {

    }




}
