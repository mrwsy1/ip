package duke;

public class Duke {
    protected Storage storage;
    protected static TaskList array;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public Duke(String directory) {
        Ui.displayWelcomeMsg();
        storage = new Storage(directory);
        array = new TaskList(storage, directory);
    }

    public void run() {
        boolean looper = true;
        String line;
        while (looper) {
            try {
                line = Parser.readCmd();
                looper = Parser.executeCmd(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
