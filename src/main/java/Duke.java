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

        while(true) {
            line = in.nextLine();
            //exit command
            if (line.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.equalsIgnoreCase("list")) { //list command
                if (Task.getNumTask()==0) {
                    System.out.println("No tasks recorded.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    displayList(array);
                }
            } else if (line.trim().toLowerCase().startsWith("done ")) { //done command
                String taskIndexStr = line.trim().substring(5);
                int taskIndex = Integer.parseInt(taskIndexStr);
                if (taskIndex==0 || taskIndex>Task.getNumTask()) {
                    System.out.println("Invalid input.");
                } else if (array[taskIndex-1].isDone) {
                    System.out.println("Task is already done.");
                } else {
                    array[taskIndex-1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + array[taskIndex-1].getStatusIcon()
                            + "] " + array[taskIndex-1].getDescription());
                }
            } else { //add command
                Task newTask = new Task(line);
                array[Task.getNumTask()-1] = newTask;
                System.out.println("added: " + line);
            }
        } //end while loop
    } //end of main

    public static void displayList(Task[] array) {
        for (int i=0; i<Task.getNumTask(); i++) {
            System.out.println(i+1 + ". [" + array[i].getStatusIcon()
                    + "] " + array[i].getDescription());
        }
    } //end of displayList
}
//done 2
//012345