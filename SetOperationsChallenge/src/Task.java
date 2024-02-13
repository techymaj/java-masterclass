public class Task implements Comparable {

    @Override
    public int compareTo(Object o) {
        Task task = (Task) o;

        // sort by project name and then by task description
        return (this.projectName + this.taskDescription)
                .compareTo(task.projectName + task.taskDescription);
    }

    enum Status {
        ASSIGNED, IN_PROGRESS, NOT_YET_ASSIGNED
    }

    enum Priority {
        HIGH, MEDIUM, LOW
    }

    private String assignee;
    private String projectName;
    private String taskDescription;
    private Status status;
    private Priority priority;

    public Task(String assignee, String projectName, String taskDescription, Status status, Priority priority) {
        this.assignee = assignee;
        this.projectName = projectName;
        this.taskDescription = taskDescription;
        this.status = status;
        this.priority = priority;
    }

    public Task(String assignee, String projectName, String taskDescription, Priority priority) {
        this(assignee, projectName, taskDescription, assignee == null ? Status.IN_PROGRESS : Status.ASSIGNED, priority);
    }

    public Task(String projectName, String taskDescription, Priority priority) {
        this(null, projectName, taskDescription, priority);
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "%-20s %-25s %-10s %-10s %s".formatted(assignee, projectName, taskDescription, status, priority);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!projectName.equals(task.projectName)) return false;
        return taskDescription.equals(task.taskDescription);
    }

    @Override
    public int hashCode() {
        int result = projectName.hashCode();
        result = 31 * result + taskDescription.hashCode();
        return result;
    }
}
