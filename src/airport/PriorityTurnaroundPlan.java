package airport;

public final class PriorityTurnaroundPlan extends TurnaroundPlanDecorator {

    public PriorityTurnaroundPlan(TurnaroundPlan inner) {
        super(inner);
    }

    @Override
    public int schedulingMinutes() {
        return super.schedulingMinutes() - 5;
    }

    @Override
    public String description() {
        return super.description() + " + Priority";
    }
}
