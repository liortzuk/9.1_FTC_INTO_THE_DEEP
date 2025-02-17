package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrainSub;
import org.firstinspires.ftc.teamcode.Elevator.Elevator2;
import org.firstinspires.ftc.teamcode.Elevator.Elevator_based;
import org.firstinspires.ftc.teamcode.command.Move_EL;
import org.firstinspires.ftc.teamcode.command.drive;
import org.firstinspires.ftc.teamcode.command.extend;
import org.firstinspires.ftc.teamcode.command.reset_imu;
import org.firstinspires.ftc.teamcode.opMode.OPMODE_BASE;

@TeleOp
@Disabled
public class teleopBase extends OPMODE_BASE {

    @Override
    public void runOpMode(){
        initialize();
        waitForStart();

        GamepadEx driverOp = new GamepadEx(gamepad1);
        double forward;
        double turn;
        double drift;
        double botHeading;
        DriveTrainSub subDrive = new DriveTrainSub(BR, BL, FR, FL, telemetry, Imu);

        boolean is_in = false;
        boolean is_down = false;
        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);
        Elevator_based lift_Base = new Elevator_based(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);

        extend ex_in = new extend(lift_Base,1, 1);
        extend ex_out = new extend(lift_Base,-1, 1);

        boolean is_up = false;

        Button intake_1 = new GamepadButton(
                driverOp, GamepadKeys.Button.Y
        );
        Button intake_2 = new GamepadButton(
                driverOp, GamepadKeys.Button.X
        );

        Button up = new GamepadButton(
                driverOp, GamepadKeys.Button.RIGHT_BUMPER
                );

        Button down = new GamepadButton(
                driverOp, GamepadKeys.Button.LEFT_BUMPER
        );

        Button reset = new GamepadButton(
                driverOp, GamepadKeys.Button.BACK
        );

        Move_EL el_up = new Move_EL(lift_Base, 8000);
        Move_EL el_down = new Move_EL(lift_Base, 8000);

        reset_imu imu = new reset_imu(subDrive);

        while (opModeIsActive()) {
            forward = -gamepad1.left_stick_y; //-1 to 1
            turn = gamepad1.right_stick_x;
            drift = gamepad1.left_stick_x;
            botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            drive dr = new drive(subDrive,forward, drift, turn, botHeading);

            up.whenPressed(el_up);
            down.whenPressed(el_down);
            reset.whenPressed(imu);

            schedule(dr);

            intake_1.whenPressed(ex_out);
            intake_2.whenPressed(ex_in);

            CommandScheduler.getInstance().run();
        }
    }
}
