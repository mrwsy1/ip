package duke;

import java.util.Scanner;

public class Parser {
    protected static Scanner in = new Scanner(System.in);
    protected static String line;

    /**
     * Reads user input.
     *
     * @return the input from user
     */
    public static String readCmd() {
        line = in.nextLine();
        return line;
    }

    /**
     * Determines the action to take based on user input,
     * and returns a boolean for the while loop condition.
     *
     * @param line the input from user
     * @return a boolean
     */
    public static boolean executeCmd(String line) {
        String lineCmd = line.trim().toLowerCase().split(" ")[0];
        try {
            switch (lineCmd) {
            case "bye":
                Ui.displayByeMsg();
                return false;
            case "list":
                TaskList.cmdList();
                break;
            case "done":
                TaskList.cmdDone(line);
                break;
            case "todo":
                TaskList.cmdTodo(line);
                break;
            case "deadline":
                TaskList.cmdDeadline(line);
                break;
            case "event":
                TaskList.cmdEvent(line);
                break;
            case "delete":
                TaskList.cmdDelete(line);
                break;
            case "find":
                TaskList.cmdFind(line);
                break;
            default:
                System.out.println("Invalid command.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
