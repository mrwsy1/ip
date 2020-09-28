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

    public static void restoreSaved(Task savedTask) {
        array.add(savedTask);
    }

    public static int getSize() {
        return array.size();
    }

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
                storage.writeWholeFile(array);
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
            storage.writeFile(newEvent);
            Ui.displayAddMsg(3, eventContent, eventAt);
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
            storage.writeFile(newDeadline);
            Ui.displayAddMsg(2, dlContent, dlBy);
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
            storage.writeFile(newTodo);
            Ui.displayAddMsg(1, todoContent, null);
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

    /* prints out contents of task array in order */
    public static void displayList() {
        for (int i = 0; i < array.size(); i++) {
            System.out.println(i + 1 + "." + array.get(i).toString());
        }
    }
}
