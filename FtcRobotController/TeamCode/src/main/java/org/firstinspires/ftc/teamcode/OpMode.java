package org.firstinspires.ftc.teamcode;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.FtcDashboard;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.Elevator.Elevator2;


public abstract class OpMode extends LinearOpMode {

    protected Servo intake_AR, intAR, roni_IA, intake_center_angle, roni2_intake;
    protected CRServo intake_right, intake_left;
    protected DcMotorEx DriveFrontLeft, DriveFrontRight, DriveBackLeft, DriveBackRight, armR, armL;
    protected ElapsedTime runtime = new ElapsedTime();

    protected IMU Imu;

    FtcDashboard dashboard;

    void initialize() {
        DriveFrontLeft = hardwareMap.get(DcMotorEx.class, "FL");
        DriveFrontLeft.setDirection(DcMotorEx.Direction.FORWARD);
        DriveFrontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        DriveFrontLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        DriveFrontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        DriveFrontRight = hardwareMap.get(DcMotorEx.class, "FR");
        DriveFrontRight.setDirection(DcMotorEx.Direction.REVERSE);
        DriveFrontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        DriveFrontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        DriveFrontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        DriveBackLeft = hardwareMap.get(DcMotorEx.class, "BL");
        DriveBackLeft.setDirection(DcMotorEx.Direction.FORWARD);
        DriveBackLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        DriveBackLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        DriveBackLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        DriveBackRight = hardwareMap.get(DcMotorEx.class, "BR");
        DriveBackRight.setDirection(DcMotorEx.Direction.REVERSE);
        DriveBackRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        DriveBackRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        DriveBackRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        armR = hardwareMap.get(DcMotorEx.class,"ER");
        armR.setDirection(DcMotorEx.Direction.REVERSE );
        armR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        armR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armR.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);


        armL = hardwareMap.get(DcMotorEx.class,"EL");
        armL.setDirection(DcMotorEx.Direction.REVERSE);
        armL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        armL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armL.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);


        intake_right = hardwareMap.get(CRServo.class, "axonR");
        intake_left = hardwareMap.get(CRServo.class, "axonL");

        intake_AR = hardwareMap.get(Servo.class, "ointR");
        intake_AR.setPosition(0.75);

        roni_IA = hardwareMap.get(Servo.class, "roni");
        roni_IA.setPosition(0.2);

        roni2_intake = hardwareMap.get(Servo.class, "roni2");

        intAR =  hardwareMap.get(Servo.class, "intAR");

        intake_center_angle = hardwareMap.get(Servo.class, "intakeWheel");

        Imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
        Imu.initialize(parameters);
        Imu.resetYaw();



    }


    @Override
    public void runOpMode() throws InterruptedException  {
        initialize();
        waitForStart();
        postInit();

        dashboard = FtcDashboard.getInstance();
        if (opModeIsActive()) {
            run();
        }

        end();
    }

    protected void postInit() {

    }
    protected abstract void run();

    protected abstract void end();



    public void Move_all(){
        Elevator2 lift = new Elevator2(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);;

        lift.Move_Elevator(8000);
        intake_AR.setPosition(0);
        sleep(1000);

        intake_AR.setPosition(0.7);
        sleep(1000);
        lift.Move_Elevator(-8000);
    }


    public void drive_abs_point(DriveTrain driveTrain,double pos_x,  double pos_y) {
        pos_x = pos_x * 15000;
        pos_y = pos_y * 5000;

        double power_x, power_y = 0;
        double botHeading;

        while (DriveBackLeft.getCurrentPosition() < pos_y + 100 || DriveBackLeft.getCurrentPosition() > pos_y - 100){
            power_y = sigmoid_velocity_control(DriveBackLeft.getCurrentPosition(), pos_y);
            botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            driveTrain.drive(power_y, 0, 0, botHeading);
        }driveTrain.stop();

        while (DriveFrontRight.getCurrentPosition() < pos_x + 100 || DriveFrontRight.getCurrentPosition() > pos_x - 100){
            power_x = sigmoid_velocity_control(DriveFrontRight.getCurrentPosition(), pos_y);
            botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            driveTrain.drive(0, power_x, 0, botHeading);
        }driveTrain.stop();
    }

// up?
   public void Transfer_sample(Elevator2 lift){
       intake_AR.setPosition(.8);
       lift.extend(0.2,1);
       intake_center_angle.setPosition(0.2);
       sleep(500);

       lift.move_intake_AG(0.6);
       sleep(1000);
       roni2_intake.setPosition(1);
       sleep(500);
       lift.extend(-0.56,1);
   }

   public void Sample_to_Basket(Elevator2 lift){
        lift.Move_Elevator(8000);
        intake_AR.setPosition(0);
        sleep(500);

        intake_AR.setPosition(0.55);
        sleep(500);
        lift.Move_Elevator(-8000);
        sleep(200);
   }

    public double sigmoid_velocity_control(double current_pos, double wanted_pos){
        double power = 2*(1 / (1 + Math.pow(Math.exp(1), -cal_v(wanted_pos, current_pos) / cal_y(wanted_pos, current_pos))-0.5));
        return power;
    }

    public double cal_y(double wanted_pos, double current_pos){
        return (wanted_pos - current_pos) / 4;
    }

    public double cal_v(double wanted_pos, double current_pos){
        return (wanted_pos - current_pos) / (Math.pow(10, -10) + Math.abs(wanted_pos - current_pos));
    }


    public void turn_to_relative_pos(DriveTrain driveTrain, double degrees){

        PID pid = new PID(5, 3, 0, 0, 0);
        double rx = 0;
        double botHeading;

        double pos_x = Math.abs(DriveFrontRight.getCurrentPosition());
        double pos_y = Math.abs(DriveBackLeft.getCurrentPosition());

        degrees = degrees / 2;

        // 90 = x: 1949 Y: -4101

        double x = 21.66 * degrees;
        double y = 45.57 * degrees;

        pid.setWanted(x + y);

        double xy =  x + y;

        while (xy > Math.abs(DriveBackLeft.getCurrentPosition() - pos_y) + Math.abs(DriveFrontRight.getCurrentPosition() - pos_x)){
            rx = pid.update(Math.abs(DriveBackLeft.getCurrentPosition() - pos_y) + Math.abs(DriveFrontRight.getCurrentPosition() - pos_x));
            botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            driveTrain.drive(0,0, rx, botHeading);

            telemetry.addData("current: ", Math.abs(DriveBackLeft.getCurrentPosition() - pos_y) + Math.abs(DriveFrontRight.getCurrentPosition() - pos_x));
            telemetry.addData("wanted: ", xy);
            telemetry.update();
        }driveTrain.stop();

    }


    public void run_to_point(DriveTrain driveTrain,double pos, double power_x, double power_y, double power_rx, double botHeading){

        pos = pos * 1500;

        DriveBackLeft.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        DriveFrontRight.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        if(Math.abs(power_y) > Math.abs(power_x)){
            while (Math.abs(DriveBackLeft.getCurrentPosition()) < Math.abs(pos)){
                driveTrain.drive(power_y, power_x, power_rx, botHeading);
                telemetry.addData("IMU", botHeading);
                telemetry.addData("BL", DriveBackLeft.getCurrentPosition());
                telemetry.update();
            }
            driveTrain.stop();
        }else if(Math.abs(power_x) > Math.abs(power_y)){
            while (Math.abs(DriveFrontRight.getCurrentPosition()) < Math.abs(pos)){
                driveTrain.drive(power_y, power_x, power_rx, botHeading);
                telemetry.addData("IMU", botHeading);
                telemetry.addData("BR", DriveFrontRight.getCurrentPosition());
                telemetry.update();
            }
            driveTrain.stop();
        }else if(Math.abs(power_rx) > Math.abs(power_y)){
            while (Math.abs(DriveFrontRight.getCurrentPosition()) < Math.abs(pos)){
                driveTrain.drive(power_y, power_x, power_rx, botHeading);
                telemetry.addData("IMU", botHeading);
                telemetry.addData("BR", DriveFrontRight.getCurrentPosition());
                telemetry.update();
            }
            driveTrain.stop();
        }


    }

}

