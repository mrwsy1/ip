package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {
    protected String directory;

    public Storage(String directory) {
        this.directory = directory;
    }

    public void readFile(String directory) {
        try {
            File f = new File(directory);
            if (!f.exists()) {
                f.getParentFile().mkdir();
                f.createNewFile();
            }
            Scanner inF = new Scanner(f);
            while (inF.hasNext()) {
                String lineF = inF.nextLine();
                String[] lineFSplit = lineF.trim().split(" \\| ", 4);
                /* lineFSplit is split into 4 parts
                 * 0: task type
                 * 1: completion status
                 * 2: contents
                 * 3: conditions
                 * */
                switch (lineFSplit[0]) {
                case "T":
                    Todo newTodo = new Todo(lineFSplit[2]);
                    newTodo.isDone = Boolean.parseBoolean(lineFSplit[1]);
                    TaskList.restoreSaved(newTodo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(lineFSplit[2], lineFSplit[3]);
                    newDeadline.isDone = Boolean.parseBoolean(lineFSplit[1]);
                    TaskList.restoreSaved(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(lineFSplit[2], lineFSplit[3]);
                    newEvent.isDone = Boolean.parseBoolean(lineFSplit[1]);
                    TaskList.restoreSaved(newEvent);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* method to overwrite the file with all tasks in the array */
    public void writeWholeFile(ArrayList<Task> array) throws IOException {
        FileWriter fw = new FileWriter(directory);
        for (int i = 0; i < TaskList.getSize(); i++) {
            String textToAdd = array.get(i).toFile();
            fw.write(textToAdd + "\n");
        }
        fw.close();
    }

    /* method to add new task into the file */
    public void writeFile(Task newTask) throws IOException {
        FileWriter fw = new FileWriter(directory, true);
        String textToAdd = newTask.toFile();
        fw.write(textToAdd + "\n");
        fw.close();
    }
}
