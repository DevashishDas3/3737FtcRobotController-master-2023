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
    //        // Save reference to Hardware map
    //        hwMap = ahwMap;
    //
    //        // Define and Initialize Motors
    //        leftFront  = hwMap.get(DcMotor.class, "LF");
    //        leftBack  = hwMap.get(DcMotor.class, "LB");
    //        rightFront  = hwMap.get(DcMotor.class, "RF");
    //        rightBack  = hwMap.get(DcMotor.class, "RB");
    //
    //        // Reversing left motors
    //        rightFront.setDirection(DcMotor.Direction.FORWARD);
    //        leftFront.setDirection(DcMotor.Direction.REVERSE);
    //        rightBack.setDirection(DcMotor.Direction.FORWARD);
    //        leftBack.setDirection(DcMotor.Direction.REVERSE);
    //
    //        // Set all motors to zero power'
    //        setMotorPowers(0);
    //
    //        // Set all motors to run without encoders.
    //        // May want to use RUN_USING_ENCODERS if encoders are installed.
    //        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    //        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    //        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    //        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

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

            // Setup a variable for each drive wheel to save power level for telemetry

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            LFPower = Range.clip(drive + turn, -1.0, 1.0) ;
            RFPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            leftFront.setPower(LFPower);
            rightDrive.setPower(RFPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}

