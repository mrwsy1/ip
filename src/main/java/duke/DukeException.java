package duke;

public class DukeException extends Exception {
    protected String error;

    public DukeException(String error) {
        this.error = error;
    }

    /**
     * Prints out error message according to type of exception.
     * type1: invalid cmd
     * type2: invalid input
     * type3: invalid deadline
     * type4: invalid event
     */
    public void getException() {
        switch (this.error) {
        case "type1":
            System.out.println("<ERROR> Please enter a valid command.");
            break;
        case "type2":
            System.out.println("<ERROR> Please enter a valid input.");
            break;
        case "type3":
            System.out.println("<ERROR> Please enter a valid input.\n" +
                    "Format: (Task) /by (time)");
            break;
        case "type4":
            System.out.println("<ERROR> Please enter a valid input.\n" +
                    "Format: (Task) /at (time)");
            break;
        }
    }
}
