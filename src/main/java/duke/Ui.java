package duke;

public class Ui {

    /**
     * Displays the DUKE logo and greeting message.
     */
    public static void displayWelcomeMsg() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke!\n" +
                "What can I do for you?\n");
    }

    /**
     * Displays the exit message.
     */
    public static void displayByeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints task added message depending on the type of task
     * @param taskType specifies the type of task
     * @param content contains the description of the task
     * @param condition contains a date or time
     */
    public static void displayAddMsg(int taskType, String content, String condition) {
        switch (taskType) {
        case 1:
            System.out.println("Got it. I've added this task:");
            System.out.println("\t[T][\u2718] " + content);
            break;
        case 2:
            System.out.println("Got it. I've added this task:");
            System.out.println("\t[D][\u2718] " + content + " (by: " + condition + ")");
            break;
        case 3:
            System.out.println("Got it. I've added this task:");
            System.out.println("\t[E][\u2718] " + content + " (at: " + condition + ")");
            break;
        }
        System.out.println("Now you have " + TaskList.getSize() + " tasks in the list.");
    }
}
