package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.Elevator.Elevator2;
import org.firstinspires.ftc.teamcode.OpMode;
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="red specimen / roni_2 :)", group="Robot")
@Disabled
public class roni2 extends OpMode{
    @Override
    protected void run() {
        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);
        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);

        double botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double botDeg = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        driveTrain.turnToGyro_plus(Math.abs(botDeg));
        sleep(500);

        run_to_point(driveTrain,4, 0, 0.5, 0, botHeading);
        sleep(500);

        run_to_point(driveTrain,1, -0.5, 0, 0, botHeading);
        sleep(500);

        driveTrain.turnToGyro_minus(-45);
        sleep(100);
        driveTrain.turnToGyro_minus(-140);
        sleep(500);

        lift.Move_Elevator(6000);
        sleep(1000);

        run_to_point(driveTrain,2.4, 0, -0.5, 0, botHeading);
        sleep(500);

        lift.Move_Elevator(-6000);
        sleep(500);

        run_to_point(driveTrain,1,0,0.5,0,botHeading);
        sleep(100);

        driveTrain.turnToGyro_plus(45);

        sleep(500);
        driveTrain.turnToGyro_plus(90);
        sleep(500);

        Imu.resetYaw();
    }

    @Override
    protected void end() {

    }
}
