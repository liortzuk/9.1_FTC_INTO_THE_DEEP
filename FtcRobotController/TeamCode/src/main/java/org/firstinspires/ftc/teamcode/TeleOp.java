package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;import static java.lang.Math.abs;
import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;

import org.firstinspires.ftc.teamcode.Elevator.Elevator;
import org.firstinspires.ftc.teamcode.Elevator.Elevator2;

@Config
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {

    @Override
    protected void postInit() {
        Imu.resetYaw();
    }

    @Override
    public void run(){


        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);
        Elevator2 lift = new Elevator2(armL, armR, intake_center, intake_left, intake_right, intake_AR, intake_AL, intAR);

        boolean is_down = false;

        while (opModeIsActive()) {

            double forward = -gamepad1.left_stick_y;//-1 to 1
            double turn = gamepad1.right_stick_x;
            double drift = gamepad1.left_stick_x;
            double botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);


            driveTrain.drive(-forward, -turn, drift, botHeading);

            if(gamepad1.x && !is_down){lift.extend(1, 2); lift.move_ARM(.75, 1.5); is_down = true;}
            else if(gamepad1.x && is_down){lift.extend(-1, 4); lift.move_ARM(-.2, 1); is_down = false;}

            if(gamepad1.a){lift.Move_Elevator(2800);}

            if (gamepad1.b){lift.spin(1, 5);}


            telemetry.addData("Gyro: ", botHeading);
            telemetry.addData("EL: ", armL.getCurrentPosition());
            telemetry.addData("ER: ", armR.getCurrentPosition());
            telemetry.update();

        }
    }
    @Override
    protected void end() {

    }

}
