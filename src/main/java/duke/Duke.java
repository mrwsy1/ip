package duke;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        displayWelcomeMsg();

        Task[] array = new Task[100];
        String line;
        Scanner in = new Scanner(System.in);
        boolean looper = true;
        readFile(array);

        while(looper) {
            line = in.nextLine();
            String lineCmd = line.trim().split(" ")[0];
            try {
                switch(lineCmd){
                case "bye":
                    writeWholeFile(array);
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        } //end while loop
    }

    /* method to read from file */
    public static void readFile(Task[] array) {
        try {
            File f = new File("data/tasks.txt");
            if (!f.exists()){
                f.getParentFile().mkdir();
                f.createNewFile();
            }
            Scanner inf = new Scanner(f);
            while (inf.hasNext()) {
                String linef = inf.nextLine();
                String[] linefSplit = linef.trim().split(" \\| ", 4);
                /* linefSplit is split into 4 parts
                * 0: task type
                * 1: completion status
                * 2: contents
                * 3: conditions
                * */
                switch(linefSplit[0]){
                case "T":
                    Todo newTodo = new Todo(linefSplit[2]);
                    newTodo.isDone = Boolean.parseBoolean(linefSplit[1]);
                    array[Task.getNumTask()-1] = newTodo;
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(linefSplit[2], linefSplit[3]);
                    newDeadline.isDone = Boolean.parseBoolean(linefSplit[1]);
                    array[Task.getNumTask()-1] = newDeadline;
                    break;
                case "E":
                    Event newEvent = new Event(linefSplit[2], linefSplit[3]);
                    newEvent.isDone = Boolean.parseBoolean(linefSplit[1]);
                    array[Task.getNumTask()-1] = newEvent;
                    break;
//                default:
//                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* method to overwrite the file with all tasks in the array */
    public static void writeWholeFile (Task[] array) throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        for (int i=0; i<Task.getNumTask(); i++) {
            String textToAdd = array[i].toFile();
            fw.write(textToAdd + "\n");
        }
        fw.close();
    }

    /* method to add new task into the file */
    public static void writeFile (Task newTask) throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt", true);
            String textToAdd = newTask.toFile();
            fw.write(textToAdd + "\n");
        fw.close();
    }

    /* method for event command */
    public static void cmdEvent(Task[] array, String line) {
        try {
            int atPos = line.indexOf("/at");
            String eventContent = line.trim().substring(6, atPos-1);
            String eventAt = line.trim().split("/at ")[1];
            Event newEvent = new Event(eventContent, eventAt);
            array[Task.getNumTask()-1] = newEvent;
            writeFile(newEvent);
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
            writeFile(newDeadline);
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
            writeFile(newTodo);
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
                System.out.println("duke.Task is already done.");
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
        System.out.println("Hello! I'm duke.Duke\n" +
                "What can I do for you?\n");
    }

    /* prints out contents of task array in order */
    public static void displayList(Task[] array) {
        for (int i=0; i<Task.getNumTask(); i++) {
            System.out.println(i+1 + "." + array[i].toString());
        }
    }
}
