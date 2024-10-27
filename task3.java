import java.util.Scanner;

/**
 * Main class to handle area, volume, and fraction calculations.
 * This class includes methods for calculating the area of a circle,
 * lateral surface area of a cone, volume of a cone, and methods for
 * handling fractions and their simplification.
 * @author  Nahid Alam
 */
public class Main {
    // Creation of scanner object.
    private static Scanner inputScanner = new Scanner(System.in);

    // Constants
    static final int QUIT = -1;

    /**
     * Injects a custom input scanner (used for unit testing).
     *
     * @param customInputScanner - custom scanner object.
     */
    public static void injectInput(final Scanner customInputScanner) {
        inputScanner = customInputScanner; // No longer hides the class variable
    }

    /**
     * Main method to run the program.
     * It handles reading radius, height, and fractions, calculating their
     * respective areas, volumes, and simplified forms.
     *
     * @param args - command-line arguments.
     */
    public static void main(final String[] args) {
        int radius = 0;
        int height = 0;
        int numerator = 0;
        int denominator = 0;

        // Print the header of the program for area and volume.
        System.out.println("----------------------------------");
        System.out.println("# Test of area and volume methods");
        System.out.println("----------------------------------");

        // Loop for area and volume
        while (true) {
            radius = input();
            if (radius == QUIT) {
                break;
            }

            height = input();
            if (height == QUIT) {
                break;
            }

            System.out.println("r = " + radius + ", h = " + height);
            System.out.printf("Circle area: %.2f %n", area(radius));
            System.out.printf("Cone area: %.2f %n", area(radius, height));
            System.out.printf("Cone volume: %.2f %n", volume(radius, height));
        }

        // Print the header of the program for fractions.
        System.out.println("----------------------------------");
        System.out.println("# Test of the fractional methods");
        System.out.println("----------------------------------");

        // Loop for fractions
        while (true) {
            numerator = input();
            if (numerator == QUIT) {
                break;
            }

            denominator = input();
            if (denominator == QUIT) {
                break;
            }

            System.out.printf("%d/%d = ", numerator, denominator);
            printFraction(fraction(numerator, denominator));
        }
    }

    /**
     * Reads user input, ensures it is a valid integer or quits when "q" is entered.
     *
     * @return integer value or -1 if 'q' is entered.
     */
    public static int input() {
        while (true) {
            String input = inputScanner.next();
            if (input.equals("q")) {
                return QUIT;
            }
            try {
                // Parse input and return absolute value
                int value = Integer.parseInt(input);
                return Math.abs(value);
            } catch (NumberFormatException e) {
                // Invalid input, ignore and continue asking for input
                System.out.println(
                    "Invalid input, please enter a valid number or 'q' to quit."
                );
            }
        }
    }

    /**
     * Calculates the area of a circle given its radius.
     *
     * @param radius - the radius of the circle.
     * @return the area of the circle.
     */
    public static double area(final int radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    /**
     * Calculates the lateral surface area of a cone.
     *
     * @param radius - the radius of the cone's base.
     * @param height - the height of the cone.
     * @return the lateral surface area of the cone.
     */
    public static double area(final int radius, final int height) {
        double s = pythagoras(radius, height); // Hypotenuse
        return Math.PI * radius * s;
    }

    /**
     * Calculates the hypotenuse using Pythagoras' theorem.
     *
     * @param sideA - one side of the triangle.
     * @param sideB - the other side of the triangle.
     * @return the length of the hypotenuse.
     */
    public static double pythagoras(final int sideA, final int sideB) {
        return Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));
    }

    /**
     * Calculates the volume of a cone given its radius and height.
     *
     * @param radius - the radius of the cone's base.
     * @param height - the height of the cone.
     * @return the volume of the cone.
     */
    public static double volume(final int radius, final int height) {
        return (Math.PI * Math.pow(radius, 2) * height) / 3;
    }

    /**
     * Simplifies a fraction and returns the integer part, numerator,
     * and denominator.
     *
     * @param numerator   - the numerator of the fraction.
     * @param denominator - the denominator of the fraction.
     * @return an array containing the integer part, fraction's numerator,
     * and denominator.
     */
    public static int[] fraction(final int numerator, final int denominator) {
        if (denominator == 0) {
            return null; // Division by zero error
        }
        if (numerator == 0) {
            return new int[] {0, 0, 0}; // Zero case
        }

        int[] result = new int[3];
        result[0] = numerator / denominator; // Integer part
        result[1] = numerator % denominator; // Fraction numerator
        result[2] = denominator; // Fraction denominator

        // Simplify the fraction
        int gcdValue = gcd(result[1], result[2]);
        result[1] /= gcdValue;
        result[2] /= gcdValue;

        return result;
    }

    /**
     * Calculates the greatest common divisor using Euclid's algorithm.
     *
     * @param a - first number.
     * @param b - second number.
     * @return the greatest common divisor.
     */
    public static int gcd(final int a, final int b) {
        int aCopy = a;
        int bCopy = b;
        while (bCopy != 0) {
            int temp = bCopy;
            bCopy = aCopy % bCopy;
            aCopy = temp;
        }
        return aCopy;
    }

    /**
     * Prints a fraction based on its integer part, numerator, and denominator.
     *
     * @param parts - an array containing the integer part, numerator,
     * and denominator.
     */
    public static void printFraction(final int[] parts) {
        if (parts == null) {
            System.out.println("Error");
        } else if (parts[0] == 0 && parts[1] == 0) {
            System.out.println("0");
        } else if (parts[0] == 0) {
            System.out.printf("%d/%d%n", parts[1], parts[2]);
        } else if (parts[1] == 0) {
            System.out.println(parts[0]);
        } else {
            System.out.printf("%d %d/%d%n", parts[0], parts[1], parts[2]);
        }
    }
}

