package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numTask = 0; //track total no. of task

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numTask++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static int getNumTask() {
        return numTask;
    }

    public String getDescription() {
        return description;
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public String toFile() {
        return isDone + " | " + getDescription();
    }
}
