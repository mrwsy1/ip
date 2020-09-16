package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {

    private static ArrayList<Task> array = new ArrayList<>();

    public static void main(String[] args) {
        displayWelcomeMsg();

        String line;
        Scanner in = new Scanner(System.in);
        boolean looper = true;
        readFile();

        while (looper) {
            line = in.nextLine();
            String lineCmd = line.trim().split(" ")[0];
            try {
                switch (lineCmd) {
                case "bye":
                    writeWholeFile();
                    displayByeMsg();
                    looper = false;
                    break;
                case "list":
                    cmdList();
                    break;
                case "done":
                    cmdDone(line);
                    break;
                case "todo":
                    cmdTodo(line);
                    break;
                case "deadline":
                    cmdDeadline(line);
                    break;
                case "event":
                    cmdEvent(line);
                    break;
                case "delete":
                    cmdDelete(line);
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
    public static void readFile() {
        try {
            File f = new File("data/tasks.txt");
            if (!f.exists()) {
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
                switch (linefSplit[0]) {
                case "T":
                    Todo newTodo = new Todo(linefSplit[2]);
                    newTodo.isDone = Boolean.parseBoolean(linefSplit[1]);
                    array.add(newTodo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(linefSplit[2], linefSplit[3]);
                    newDeadline.isDone = Boolean.parseBoolean(linefSplit[1]);
                    array.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(linefSplit[2], linefSplit[3]);
                    newEvent.isDone = Boolean.parseBoolean(linefSplit[1]);
                    array.add(newEvent);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* method to overwrite the file with all tasks in the array */
    public static void writeWholeFile() throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        for (int i = 0; i < array.size(); i++) {
            String textToAdd = array.get(i).toFile();
            fw.write(textToAdd + "\n");
        }
        fw.close();
    }

    /* method to add new task into the file */
    public static void writeFile(Task newTask) throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt", true);
        String textToAdd = newTask.toFile();
        fw.write(textToAdd + "\n");
        fw.close();
    }

    /* method for delete command */
    public static void cmdDelete(String line) {
        try {
            String taskIndexStr = line.trim().substring(7);
            int taskIndex = Integer.parseInt(taskIndexStr);
            if (array.size() == 0) {
                System.out.println("You do not have any tasks available.");
            } else if (taskIndex == 0 || taskIndex > array.size()) {
                System.out.println("Please input an index within range (1~"
                        + array.size() + ").");
            } else {
                String temp = array.get(taskIndex - 1).toString();
                array.remove(taskIndex - 1);
                writeWholeFile();
                System.out.println("Noted. I've removed this task:");
                System.out.println("\t" + temp);
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    /* method for event command */
    public static void cmdEvent(String line) {
        try {
            int atPos = line.indexOf("/at");
            String eventContent = line.trim().substring(6, atPos - 1);
            String eventAt = line.trim().split("/at ")[1];
            Event newEvent = new Event(eventContent, eventAt);
            array.add(newEvent);
            writeFile(newEvent);
            displayAddMsg(3, eventContent, eventAt);
        } catch (Exception e) {
            System.out.println("Please enter a valid event.");
        }
    }

    /* method for deadline command */
    public static void cmdDeadline(String line) {
        try {
            int byPos = line.indexOf("/by");
            String dlContent = line.trim().substring(9, byPos - 1);
            String dlBy = line.trim().split("/by ")[1];
            Deadline newDeadline = new Deadline(dlContent, dlBy);
            array.add(newDeadline);
            writeFile(newDeadline);
            displayAddMsg(2, dlContent, dlBy);
        } catch (Exception e) {
            System.out.println("Please enter a valid deadline.");
        }
    }

    /* method for todo command */
    public static void cmdTodo(String line) {
        try {
            String todoContent = line.trim().substring(5);
            Todo newTodo = new Todo(todoContent);
            array.add(newTodo);
            writeFile(newTodo);
            displayAddMsg(1, todoContent, null);
        } catch (Exception e) {
            System.out.println("Please enter a valid task.");
        }
    }

    /* method for done command */
    public static void cmdDone(String line) {
        try {
            String taskIndexStr = line.trim().substring(5);
            int taskIndex = Integer.parseInt(taskIndexStr);
            if (array.size() == 0) {
                System.out.println("You do not have any tasks available.");
            } else if (taskIndex == 0 || taskIndex > array.size()) {
                System.out.println("Please input an index within range (1~"
                        + array.size() + ").");
            } else if (array.get(taskIndex - 1).isDone) {
                System.out.println("duke.Task is already done.");
            } else {
                array.get(taskIndex - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t" + array.get(taskIndex - 1).toString());
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    /* method for list command */
    public static void cmdList() {
        if (array.size() == 0) {
            System.out.println("No tasks recorded.");
        } else {
            System.out.println("Here are the tasks in your list:");
            displayList();
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
        System.out.println("Now you have " + array.size() + " tasks in the list.");
    }

    /* prints out contents of task array in order */
    public static void displayList() {
        for (int i = 0; i < array.size(); i++) {
            System.out.println(i + 1 + "." + array.get(i).toString());
        }
    }
}
