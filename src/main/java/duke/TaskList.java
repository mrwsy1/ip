package duke;

import java.util.ArrayList;

public class TaskList {
    protected static Storage storage;
    private static ArrayList<Task> array;

    public TaskList(Storage dupe, String directory) {
        array = new ArrayList<>();
        storage = dupe;
        storage.readFile(directory);
    }

    /**
     * Adds saved data from file to the ArrayList.
     *
     * @param savedTask saved data from file
     */
    public static void restoreSaved(Task savedTask) {
        array.add(savedTask);
    }

    /**
     * Finds the number of tasks currently in the ArrayList.
     *
     * @return size of the ArrayList
     */
    public static int getSize() {
        return array.size();
    }

    /**
     * Finds all tasks in the ArrayList that contains a keyword.
     *
     * @param line contains user input
     */
    public static void cmdFind(String line) {
        try {
            String key = line.trim().substring(5);
            int matched = 0;
            for (int i = 0; i < array.size(); i++) {
                Task temp = array.get(i);
                if (temp.description.contains(key)) {
                    matched++;
                    if (matched == 1) {
                        System.out.println("Here are the matching tasks in your list:");
                    }
                    System.out.println(matched + "." + temp.toString());
                }
            }
            if (matched == 0) {
                System.out.println("No matching tasks found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid keyword.");
        }
    }

    /**
     * Deletes a task from the ArrayList.
     *
     * @param line contains user input
     */
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
                storage.writeWholeFile(array);
                System.out.println("Noted. I've removed this task:");
                System.out.println("\t" + temp);
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    /**
     * Adds an event to the ArrayList.
     *
     * @param line contains user input
     */
    public static void cmdEvent(String line) {
        try {
            int atPos = line.indexOf("/at");
            String eventContent = line.trim().substring(6, atPos - 1);
            String eventAt = line.trim().split("/at ")[1];
            Event newEvent = new Event(eventContent, eventAt);
            array.add(newEvent);
            storage.writeFile(newEvent);
            Ui.displayAddMsg(3, eventContent, eventAt);
        } catch (Exception e) {
            System.out.println("Please enter a valid event.");
        }
    }

    /**
     * Adds a deadline to the ArrayList
     *
     * @param line contains user input
     */
    public static void cmdDeadline(String line) {
        try {
            int byPos = line.indexOf("/by");
            String dlContent = line.trim().substring(9, byPos - 1);
            String dlBy = line.trim().split("/by ")[1];
            Deadline newDeadline = new Deadline(dlContent, dlBy);
            array.add(newDeadline);
            storage.writeFile(newDeadline);
            Ui.displayAddMsg(2, dlContent, dlBy);
        } catch (Exception e) {
            System.out.println("Please enter a valid deadline.");
        }
    }

    /**
     * Adds a to-do to the ArrayList.
     *
     * @param line contains user input
     */
    public static void cmdTodo(String line) {
        try {
            String todoContent = line.trim().substring(5);
            Todo newTodo = new Todo(todoContent);
            array.add(newTodo);
            storage.writeFile(newTodo);
            Ui.displayAddMsg(1, todoContent, null);
        } catch (Exception e) {
            System.out.println("Please enter a valid task.");
        }
    }

    /**
     * Marks a task in the ArrayList as done.
     *
     * @param line contains user input
     */
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

    /**
     * Lists out all tasks in the ArrayList.
     */
    public static void cmdList() {
        if (array.size() == 0) {
            System.out.println("No tasks recorded.");
        } else {
            System.out.println("Here are the tasks in your list:");
            displayList();
        }
    }

    /**
     * Prints out all tasks in the ArrayList in order.
     */
    public static void displayList() {
        for (int i = 0; i < array.size(); i++) {
            System.out.println(i + 1 + "." + array.get(i).toString());
        }
    }
}
