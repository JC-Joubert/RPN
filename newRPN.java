import java.util.Scanner;

class StackNode {
    StackNode(double data, StackNode underneath) {
        this.data = data;
        this.underneath = underneath;
    }

    final StackNode underneath;
    final double data;
}

class RPN {
    private void into(double newData) {
        top = new StackNode(newData, top);
    }

    private double outOf() {
        double topData = top.data;
        top = top.underneath;
        return topData;
    }

    private RPN(String command) {
        top = null;
        this.command = command;
    }

    private double get() {
        double a;
        double b;
        int j;

        for(int i = 0; i < command.length( ); i++) {
                // if it's a digit
            if(Character.isDigit(command.charAt(i))) {
                double number;

                // get a string of the number
                StringBuilder temp = new StringBuilder();
                for(j = 0; (j < 100) && (Character.isDigit(command.charAt(i)) || (command.charAt(i) == '.')); j++, i++) {
                    temp.append(command.charAt(i));
                }

                // convert to double and add to the stack
                number = Double.parseDouble(temp.toString());
                into(number);
            } else if(command.charAt(i) == '+') {
                b = outOf( );
                a = outOf( );
                into(a + b);
            } else if(command.charAt(i) == '-') {
                b = outOf( );
                a = outOf( );
                into(a - b);
            } else if(command.charAt(i) == '*') {
                b = outOf( );
                a = outOf( );
                into(a * b);
            } else if(command.charAt(i) == '/') {
                b = outOf( );
                a = outOf( );
                into(a / b);
            }
            else if(command.charAt(i) == '^') {
                b = outOf( );
                a = outOf( );
                into(Math.pow(a, b));
            } else if(command.charAt(i) != ' ') {
                throw new IllegalArgumentException( );
            }
        }

        double val = outOf( );

        if(top != null) {
            throw new IllegalArgumentException( );
        }

        return val;
    }

    private final String command;
    private StackNode top;

    /* main method */
    public static void main(String[] args) {
        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter RPN expression or \"quit\".");
            String line = in.nextLine( );
            if(line.equals("quit")) {
                break;
            } else {
                RPN calc = new RPN(line);
                System.out.printf("Answer is %f\n", calc.get( ));
            }
        }
    }
}