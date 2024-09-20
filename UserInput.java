import java.util.Scanner;
import java.lang.StringBuilder;

/*** 
 * Handles user input along with implementing input validation for different
 * input types (int, char, string).
 ***/
public class UserInput
{
    private final Scanner USER;
    private final StringBuilder build = new StringBuilder();
    private int terminalLimit = 3;

    public UserInput()
    { USER = new Scanner(System.in); }

    /**
     * Checks user integer input.
     * @param prompt The message displayed to the user.
     * @param min The minimum acceptable integer value.
     * @param max The maximum acceptable integer value.
     * @return The validated integer input from the user, or min - 1 if the user types "exit".
     */
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
                // If the user types out "exit" (case INsensitive)...
                if((output = USER.next()).equalsIgnoreCase("exit")) {
                    return min - 1;
                // Else if the output is within the given range...
                } else if((intOutput = Integer.parseInt(output)) >= min && intOutput < max) {
                    return intOutput;
                }
                // Else, outputs the invalid input error string.
                System.out.printf("%s", build.toString());
            } catch(NumberFormatException e) {
                System.out.printf("%s", build.toString());
            }
        }
    }

    /**
     * Prompts the user to choose from a list of valid options.
     * @param prompt The message displayed to the user.
     * @param choices An array of valid options the user can choose from.
     * @return The validated choice input from the user, or "exit" if the user types "exit".
     */
    public String choiceInput(String prompt, String[] choices)
    {
        build.setLength(0);
        build.append("That input is invalid. Enter one of the valid following:\n");
        build.append(choices[0]);

        for(int i = 1; i < choices.length; i++) {
            build.append(", ");
            // This prevent an output that is too long for the terminal screen.
            // The limit is variable and able to be changed.
            if(i % terminalLimit == 0) { build.append("\n"); }
            build.append(choices[i]);
        }
        if(build.charAt(build.length() - 1) != '\n' ) { build.append("\n"); }

        String output;
        while(true) {
            System.out.printf("%s", prompt);
            output = USER.next();
            // Enables user to force exit.
            if(output.equalsIgnoreCase("exit"))
            { return output; }

            for(String choice: choices) {
                if(choice.equalsIgnoreCase(output))
                { return output; }
            }

            // Outputs the invalid input error string.
            System.out.printf("%s", build.toString());

        }
    }

    /**
     * Prompts the user to input a boolean value.
     * @param prompt The message displayed to the user.
     * @return The validated boolean input from the user.
     */
    public boolean trueFalseInput(String prompt)
    {
        build.setLength(0);
        build.append("That input is invalid. Enter one of the following: [T]rue, [F]alse\n");
        
        String output;
        while(true) {
            System.out.printf("%s", prompt);
            output = USER.next();
            // User has the choice to enter the first letter or the whole word.
            if(output.equalsIgnoreCase("t")
                    || output.equalsIgnoreCase("true")) {
                return true;

            } else if(output.equalsIgnoreCase("f")
                    || output.equalsIgnoreCase("false")) {
                return false;
            }

            System.out.printf("%s", build.toString());
        }
    }
}