package org.firstinspires.ftc.teamcode.Auto;

import org.firstinspires.ftc.teamcode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.Elevator.Elevator2;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="blue basket / emily1 :(", group="Robot")

public class emily1 extends OpMode {
    @Override
    protected void run() {
        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);
        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);

        double botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double botDeg = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        driveTrain.turnToGyro_plus(Math.abs(botDeg));
        sleep(500);

        // get to basket
        run_to_point(driveTrain,2,0,1,0,botHeading);
        sleep(100);
        run_to_point(driveTrain,2,-1,0,0,botHeading);
        sleep(100);
        driveTrain.turnToGyro_minus(-15);
        sleep(100);

        // adjust placement
        run_to_point(driveTrain,1.68,0,-1,0,0);
        sleep(100);

        // put in basket
        lift.Move_Elevator(8000);
        sleep(500);
        intake_AR.setPosition(0.2);
        sleep(500);
        intake_AR.setPosition(0.6);
        sleep(100);
        lift.Move_Elevator(-8000);
        sleep(500);

        driveTrain.turnToGyro_plus(15);
        sleep(100);










    }

    @Override
    protected void end() {

    }
}
