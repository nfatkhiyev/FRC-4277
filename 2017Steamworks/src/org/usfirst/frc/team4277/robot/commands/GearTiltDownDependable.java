package org.usfirst.frc.team4277.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearTiltDownDependable extends CommandGroup {

    public GearTiltDownDependable() {
    	addSequential(new GearTiltDown());
    	
    	//adjustment code
    	

        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());

        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
    }
}
