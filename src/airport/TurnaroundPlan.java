package airport;

public interface TurnaroundPlan {
	TurnaroundTask rootTask();

	SchedulingStrategy schedulingStrategy();

	int schedulingMinutes();

	String description();
}
