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

        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);
        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);

        double botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double botDeg = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);



        // start
        driveTrain.turnToGyro_plus(Math.abs(botDeg));
        sleep(100);

        run_to_point(driveTrain,4, 0, 0.5, 0, botHeading);
        sleep(100);

        driveTrain.turnToGyro_minus(-15);
        sleep(100);

        run_to_point(driveTrain,4.5, 0, -0.5, 0, 0);
        sleep(500);

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
        driveTrain.turnToGyro_plus(20);
        sleep(100);

        lift.extend(-1,1);
        lift.move_intake_AG(1);
        sleep(500);

        intake_center_angle.setPosition(0.5);
        sleep(100);
        roni2_intake.setPosition(1);
        sleep(100);
        run_to_point(driveTrain, 1, 0, -0.5, 0, 0);
        sleep(200);

        run_to_point(driveTrain, 1.5, 0, 0.5, 0, 0);
        sleep(100);
        roni2_intake.setPosition(0.4);
        sleep(100);

        // change
        intake_AR.setPosition(.8);
        lift.extend(.5, 1);
        intake_center_angle.setPosition(0.2);
        sleep(500);

        lift.move_intake_AG(0.6);
        sleep(500);
        roni2_intake.setPosition(1);
        sleep(1000);
        intake_center_angle.setPosition(0.7);
        lift.extend(-0.75, 1);

        run_to_point(driveTrain, 2, 0, -0.5, 0, 0);
        sleep(100);
        driveTrain.turnToGyro_minus(-25);
        run_to_point(driveTrain, 2, 0, -0.5, 0, 0);
        sleep(100);

        // score
        lift.Move_Elevator(8000);
        intake_AR.setPosition(0);
        sleep(500);


       intake_AR.setPosition(0.7);
        sleep(500);
        lift.Move_Elevator(-8000);
        sleep(100);
    }

    @Override
    protected void end() {

    }
}
