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

        String[] array = new String[100];
        int looper = 1;
        int listNum = 0;
        while(looper==1) {
            String line;
            String byeStr = "bye";
            String listStr = "list";
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equalsIgnoreCase(byeStr)) {
                System.out.println("Bye. Hope to see you again soon!");
                looper=0;
            } else if (line.equalsIgnoreCase(listStr)){
                displayList(array);
            }
            else {
                array[listNum] = line;
                System.out.println("added: " + line);
                listNum++;
            }
        } //end while loop
    } //end of main

    public static void displayList(String[] array){
        for (int i=0; i<array.length; i++){
            String item = array[i];
            if (item==null)
                break;
            System.out.println(i+1 + ". " + item);
        }
    } //end of displayList
}
