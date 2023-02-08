import java.util.Scanner;
import java.lang.StringBuilder;

public class UserInput
{
    private final Scanner USER;
    private final StringBuilder build = new StringBuilder();

    public UserInput()
    { USER = new Scanner(System.in); }

    public int intInput(String prompt, int min, int max)
    {
        build.setLength(0);
        build.append("That input is invalid. Enter a value between ");
        build.append(min);
        build.append(" - ");
        build.append(max);
        build.append("\n");
        
        String output;
        int intOutput;
        while(true) {
            System.out.printf("%s", prompt);
            try {
                if((output = USER.next()).equalsIgnoreCase("exit")) {
                    return min - 1;
                } else if((intOutput = Integer.parseInt(output)) >= min && intOutput < max) {
                    return intOutput;
                }
                System.out.printf("%s", build.toString());
            } catch(NumberFormatException e) {
                System.out.printf("%s", build.toString());
            }
        }
    }

    public String choiceInput(String prompt, String[] choices)
    {
        build.setLength(0);
        build.append("That input is invalid. Enter one of the valid following:\n");
        build.append(choices[0]);
        for(int i = 1; i < choices.length; i++) {
            build.append(", ");
            if(i % 3 == 0) { build.append("\n"); }
            build.append(choices[i]);
        }
        if(build.charAt(build.length() - 1) != '\n' ) { build.append("\n"); }

        String output;
        while(true) {
            System.out.printf("%s", prompt);
            output = USER.next();
            /* Force exit if user wishes to exit */
            if(output.equalsIgnoreCase("exit")) { return output; }

            for(String choice: choices) {
                if(choice.equalsIgnoreCase(output)) { return output; }
            }

            System.out.printf("%s", build.toString());

        }
    }

    public boolean trueFalseInput(String prompt)
    {
        build.setLength(0);
        build.append("That input is invalid. Enter one of the following: [T]rue, [F]alse\n");
        
        String output;
        while(true) {
            System.out.printf("%s", prompt);
            output = USER.next();
            if(output.equalsIgnoreCase("t") || output.equalsIgnoreCase("true")) {
                return true;
            } else if(output.equalsIgnoreCase("f") || output.equalsIgnoreCase("false")) {
                return false;
            }

            System.out.printf("%s", build.toString());
        }
    }
}