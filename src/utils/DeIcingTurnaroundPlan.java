package utils;

import airport.TurnaroundPlan;
import airport.TurnaroundPlanDecorator;

public final class DeIcingTurnaroundPlan extends TurnaroundPlanDecorator {

	public DeIcingTurnaroundPlan(TurnaroundPlan inner) {
		super(inner);
	}
	
	@Override
	public int schedulingMinutes() {
		return super.schedulingMinutes() + 12;
	}
	
	@Override
	public String description() {
		return super.description() + " + DeIcing";
	}
	
}
