package duke;

public class Ui {

    /* displays DUKE logo and welcome message */
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

    /* displays exit message */
    public static void displayByeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /* prints out added task message depending on type of task */
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
