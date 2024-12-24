package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;import static java.lang.Math.abs;
import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;

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
        Elevator2 lift = new Elevator2(armL, armR, intake_center, intake_left, intake_right, intake_AR, intAR, telemetry);

        boolean is_down = false;
        boolean is_up = false;
        boolean basketMoved = false;
        boolean spin_pressed = false;


        while (opModeIsActive()) {

            double forward = -gamepad1.left_stick_y; //-1 to 1
            double turn = gamepad1.right_stick_x;
            double drift = gamepad1.left_stick_x;
            double botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            double botDeg = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);


            driveTrain.drive(forward, drift, turn, botHeading);

            if(gamepad1.dpad_right && !is_down){lift.extend(1, 2); lift.move_ARM(.2, .5); is_down = true;}
            else if(gamepad1.dpad_left && is_down){lift.extend(-1, 4); lift.move_ARM(-0.5, 1); is_down = false;}

            // if(gamepad1.b){lift.move_all_lol(-1,3,3000, 0.7, 0.1); is_down=false;}


            if(gamepad1.x && basketMoved){lift.Move_out_basket(.7,3); basketMoved = true;}
            else if (gamepad1.x && !basketMoved){lift.Move_out_basket(-.7,3); basketMoved = false;}
            if (gamepad1.a && !spin_pressed ){lift.spin(1,0.5); spin_pressed = true;}
            else if (gamepad2.a && spin_pressed) {lift.spin(-1,0.5);spin_pressed = false;}

            // put all this inside the while loop, was outside for some reason??
            if (gamepad1.dpad_up && !is_up){lift.Move_Elevator(3100); is_up = true;}
            else if(gamepad1.dpad_down && is_up){lift.Move_Elevator(-2800); is_up = false;}

            if(gamepad1.y){intake_AR.setPosition(1);}
            if(gamepad1.b){intake_AR.setPosition(0);}

            if(gamepad1.back){Imu.resetYaw();}
            // botDeg was local -> warning when outside
            telemetry.addData("Gyro: ", botDeg);
            telemetry.addData("EL: ", armL.getCurrentPosition());
            telemetry.addData("ER: ", armR.getCurrentPosition());
            telemetry.addData("servo power: ", intake_center.getPower());
            telemetry.update();

        }




    }
    @Override
    protected void end() {

    }

}
