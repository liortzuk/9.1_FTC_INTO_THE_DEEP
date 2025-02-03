package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;import static java.lang.Math.abs;
import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;

import org.firstinspires.ftc.teamcode.Elevator.Elevator2;
import org.firstinspires.ftc.teamcode.OpMode;

@Config
@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends OpMode {

    @Override
    protected void postInit() {
        Imu.resetYaw();  intake_AR.setPosition(0.55);
    }


    @Override
    public void run(){


        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);
        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);

        boolean is_up = false;


        double forward = -gamepad1.left_stick_y; //-1 to 1
        double turn = gamepad1.right_stick_x;
        double drift = gamepad1.left_stick_x;
        double botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double botDeg = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        boolean is_in = false;
        boolean is_down = false;


        while (opModeIsActive()) {
            forward = -gamepad1.left_stick_y;
            turn = gamepad1.right_stick_x;
            drift = gamepad1.left_stick_x;
            botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            botDeg = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

            if(gamepad1.right_trigger > 0.0){

                driveTrain.drive(forward * 0.2, drift * 0.2, turn * 0.2, botHeading);
            }else{
                driveTrain.drive(forward, drift, turn, botHeading);
            }

            if (gamepad1.a){roni2_intake.setPosition(0.4); }
            else if (gamepad1.b) {roni2_intake.setPosition(0.6);}

            if (gamepad1.y){intake_center_angle.setPosition(1); lift.extend(-1, 1); lift.move_intake_AG(0.95);}
            if (gamepad1.x){intake_center_angle.setPosition(0.55); lift.extend(-1, 1); lift.move_intake_AG(1);}

            if(gamepad1.dpad_up && !is_up){lift.move_intake_AG(0.75);is_up = true;}
            else if (gamepad1.dpad_down && is_up) {lift.move_intake_AG(1); is_up = false;}

            if(gamepad1.dpad_left){intake_AR.setPosition(0.3);}



            if(gamepad1.right_bumper ){
                intake_AR.setPosition(.8);
                lift.extend(1,0.2);
                intake_center_angle.setPosition(0.2);
                sleep(500);

                lift.move_intake_AG(0.6);
                sleep(1000);
                roni2_intake.setPosition(1);
                sleep(1000);
                intake_center_angle.setPosition(0.7);
                lift.extend(-0.75, 1);



            }  if (gamepad1.left_bumper ) {
                lift.Move_Elevator(8000);
                intake_AR.setPosition(0);
                sleep(500);

                intake_AR.setPosition(0.7);
                sleep(500);
                lift.Move_Elevator(-8000);
                sleep(500);
                intake_center_angle.setPosition(1);
                sleep(100);

            }


            if(gamepad1.back){Imu.resetYaw();}

            telemetry.addData("IMU: ", botDeg);
            telemetry.addData("center x: ", DriveFrontRight.getCurrentPosition());
            telemetry.addData("y: ", DriveBackLeft.getCurrentPosition());
            telemetry.update();

        }

    }
    @Override
    protected void end() {

    }


}
