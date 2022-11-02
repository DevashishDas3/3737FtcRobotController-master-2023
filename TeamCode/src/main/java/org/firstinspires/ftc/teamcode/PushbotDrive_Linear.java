// I still need to update this (init hwmap) (dev)

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
@Disabled
public class BasicOpMode_Linear extends LinearOpMode {

    //    public void init(HardwareMap ahwMap) {


    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;



    leftFront  = hwMap.get(DcMotor.class, "LF");
    leftBack  = hwMap.get(DcMotor.class, "LB");
    rightFront  = hwMap.get(DcMotor.class, "RF");
    rightBack  = hwMap.get(DcMotor.class, "RB");

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive  = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double LFPower;
            double RFPower;
            double LBPower;
            double RBPower;


            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            LFPower = Range.clip(drive + turn, -1.0, 1.0) ;
            RFPower   = Range.clip(drive - turn, -1.0, 1.0) ;



            // Send calculated power to wheels
            leftFront.setPower(LFPower);
            rightDrive.setPower(RFPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}

