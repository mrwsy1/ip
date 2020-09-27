package duke;

import java.util.Scanner;

public class Parser {
    protected static Scanner in = new Scanner(System.in);
    protected static String line;

    public static String readCmd() {
        line = in.nextLine();
        return line;
    }

    public static boolean executeCmd(String line) {
        String lineCmd = line.trim().split(" ")[0];
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
            default:
                System.out.println("Invalid command.");
            } //end switch statement
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
