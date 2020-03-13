package tic.tac.toe;
import java.util.Scanner;

public class ConsoleInputOutput implements InputOutput {
    public int getInput() {
        int returnValue = 0;
        Scanner sc = new Scanner(System.in);
        try {
            returnValue = sc.nextInt();
        }
        catch (Exception e) {

        }
        return returnValue;
    }

    public void sendOutput(String output) {
        System.out.println(output);
    }
}
