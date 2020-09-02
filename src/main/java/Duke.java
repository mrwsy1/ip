import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?\n");

        Task[] array = new Task[100];
        String line;
        Scanner in = new Scanner(System.in);
        boolean looper = true;

        while(looper) {
            line = in.nextLine();
            String lineCmd = line.trim().split(" ")[0];
            switch(lineCmd){
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                looper = false;
                break;
            case "list":
                if (Task.getNumTask()==0) {
                    System.out.println("No tasks recorded.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    displayList(array);
                }
                break;
            case "done":
                String taskIndexStr = line.trim().substring(5);
                int taskIndex = Integer.parseInt(taskIndexStr);
                if (taskIndex==0 || taskIndex>Task.getNumTask()) {
                    System.out.println("Invalid input.");
                } else if (array[taskIndex-1].isDone) {
                    System.out.println("Task is already done.");
                } else {
                    array[taskIndex-1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(array[taskIndex-1].toString());
                }
                break;
            case "todo":
                String todoContent = line.trim().substring(5);
                Todo newTodo = new Todo(todoContent);
                array[Task.getNumTask()-1] = newTodo;
                System.out.println("Got it. I've added this task:");
                System.out.println("\t[T][\u2718] " + todoContent);
                System.out.println("Now you have " + Task.getNumTask() + " tasks in the list.");
                break;
            case "deadline":
                int byPos = line.indexOf("/by");
                String dlContent = line.trim().substring(9, byPos-1);
                String dlBy = line.trim().split("/by ")[1];
                Deadline newDeadline = new Deadline(dlContent, dlBy);
                array[Task.getNumTask()-1] = newDeadline;
                System.out.println("Got it. I've added this task:");
                System.out.println("\t[D][\u2718] " + dlContent + " (by: " + dlBy + ")");
                System.out.println("Now you have " + Task.getNumTask() + " tasks in the list.");
                break;
            case "event":
                int atPos = line.indexOf("/at");
                String eventContent = line.trim().substring(6, atPos-1);
                String eventAt = line.trim().split("/at ")[1];
                Event newEvent = new Event(eventContent, eventAt);
                array[Task.getNumTask()-1] = newEvent;
                System.out.println("Got it. I've added this task:");
                System.out.println("\t[E][\u2718] " + eventContent + " (at: " + eventAt + ")");
                System.out.println("Now you have " + Task.getNumTask() + " tasks in the list.");
                break;
            default:
                System.out.println("Invalid command.");
            }
        } //end while loop
    } //end of main

    public static void displayList(Task[] array) {
        for (int i=0; i<Task.getNumTask(); i++) {
            System.out.println(i+1 + "." + array[i].toString());
        }
    } //end of displayList
}
