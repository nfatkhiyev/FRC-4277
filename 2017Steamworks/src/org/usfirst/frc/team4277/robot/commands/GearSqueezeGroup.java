package org.usfirst.frc.team4277.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearSqueezeGroup extends CommandGroup {

    public GearSqueezeGroup() {
    	addSequential(new GearSqueezeToZero());
    	addSequential(new GearSqueeze());
    	
        // addSequential(new Command1());

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
