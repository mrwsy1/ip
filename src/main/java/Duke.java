import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        displayWelcomeMsg();

        Task[] array = new Task[100];
        String line;
        Scanner in = new Scanner(System.in);
        boolean looper = true;

        while(looper) {
            line = in.nextLine();
            String lineCmd = line.trim().split(" ")[0];
            switch(lineCmd){
            case "bye":
                displayByeMsg();
                looper = false;
                break;
            case "list":
                cmdList(array);
                break;
            case "done":
                cmdDone(array, line);
                break;
            case "todo":
                cmdTodo(array, line);
                break;
            case "deadline":
                cmdDeadline(array, line);
                break;
            case "event":
                cmdEvent(array, line);
                break;
            default:
                System.out.println("Invalid command.");
            } //end switch statement
        } //end while loop
    }

    /* method for event command */
    public static void cmdEvent(Task[] array, String line) {
        try {
            int atPos = line.indexOf("/at");
            String eventContent = line.trim().substring(6, atPos-1);
            String eventAt = line.trim().split("/at ")[1];
            Event newEvent = new Event(eventContent, eventAt);
            array[Task.getNumTask()-1] = newEvent;
            System.out.println("Got it. I've added this task:");
            System.out.println("\t[E][\u2718] " + eventContent + " (at: " + eventAt + ")");
            System.out.println("Now you have " + Task.getNumTask() + " tasks in the list.");
        } catch (Exception e) {
            System.out.println("Please enter a valid event.");
        }
    }

    /* method for deadline command */
    public static void cmdDeadline(Task[] array, String line) {
        try {
            int byPos = line.indexOf("/by");
            String dlContent = line.trim().substring(9, byPos-1);
            String dlBy = line.trim().split("/by ")[1];
            Deadline newDeadline = new Deadline(dlContent, dlBy);
            array[Task.getNumTask()-1] = newDeadline;
            System.out.println("Got it. I've added this task:");
            System.out.println("\t[D][\u2718] " + dlContent + " (by: " + dlBy + ")");
            System.out.println("Now you have " + Task.getNumTask() + " tasks in the list.");
        } catch (Exception e) {
            System.out.println("Please enter a valid deadline.");
        }
    }

    /* method for todo command */
    public static void cmdTodo(Task[] array, String line) {
        try {
            String todoContent = line.trim().substring(5);
            Todo newTodo = new Todo(todoContent);
            array[Task.getNumTask()-1] = newTodo;
            System.out.println("Got it. I've added this task:");
            System.out.println("\t[T][\u2718] " + todoContent);
            System.out.println("Now you have " + Task.getNumTask() + " tasks in the list.");
        } catch (Exception e) {
            System.out.println("Please enter a valid task.");
        }
    }

    /* method for done command */
    public static void cmdDone(Task[] array, String line) {
        try {
            String taskIndexStr = line.trim().substring(5);
            int taskIndex = Integer.parseInt(taskIndexStr);
            if (Task.getNumTask()==0){
                System.out.println("You do not have any tasks available.");
            } else if (taskIndex==0 || taskIndex>Task.getNumTask()) {
                System.out.println("Please input an index within range (1~"
                        + Task.getNumTask() + ").");
            } else if (array[taskIndex-1].isDone) {
                System.out.println("Task is already done.");
            } else {
                array[taskIndex-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t" + array[taskIndex-1].toString());
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    /* method for list command */
    public static void cmdList(Task[] array) {
        if (Task.getNumTask()==0) {
            System.out.println("No tasks recorded.");
        } else {
            System.out.println("Here are the tasks in your list:");
            displayList(array);
        }
    }

    /* displays exit message */
    public static void displayByeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /* displays DUKE logo and welcome message */
    public static void displayWelcomeMsg() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?\n");
    }

    /* prints out contents of task array in order */
    public static void displayList(Task[] array) {
        for (int i=0; i<Task.getNumTask(); i++) {
            System.out.println(i+1 + "." + array[i].toString());
        }
    }
}
