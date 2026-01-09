package airport;

public final class ExtraSecurityTurnaroundPlan extends TurnaroundPlanDecorator {

	public ExtraSecurityTurnaroundPlan(TurnaroundPlan inner) {
		super(inner);
	}

	@Override
	public int schedulingMinutes() {
		return super.schedulingMinutes() + 7;
	}

	@Override
	public String description() {
		return super.description() + " + ExtraSecurity";
	}
}
