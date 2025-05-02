import java.util.Scanner;

public class Arrays {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("""
                    Choose an option:
                    1) Part 1: Understanding Arrays
                    2) Part 2: Declaring and Initializing Arrays
                    3) Part 3: Traversing and Updating Arrays
                    4) Part 4: Common Array Operations
                    5) Exit""");

            System.out.print("Your option: ");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    partOne();
                    break;
                case 2:
                    partTwo();
                    break;
                case 3:
                    partThree();
                    break;
                case 4:
                    partFour();
                    break;
                case 5:
                    System.out.println("¡GOODBYE!");
                    break;
                default:
                    System.out.println("Choose a number in range 1-5");
            }
        } while (option != 5);

    }


    public static void partOne() {
        int[] numbers;
        numbers = new int[5];

        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        numbers[3] = 40;
        numbers[4] = 50;

        System.out.println(numbers[0]);
    }

    public static void partTwo() {
        int ARRAY_SIZE = 5;
        double[] arrayDouble = new double[ARRAY_SIZE];

        arrayDouble[0] = 45.89;

        for (int i = 1; i < ARRAY_SIZE-1; i++) {
            arrayDouble[i] = arrayDouble[i-1] * 2;
        }

        arrayDouble[ARRAY_SIZE-1] = 10.00;

        for (double value : arrayDouble) {
            System.out.println(value);
        }
    }

    public static void partThree() {
        int ARRAY_SIZE = 5;
        double[] arrayDouble = new double[ARRAY_SIZE];

        arrayDouble[0] = 45.89;

        for (int i = 1; i < ARRAY_SIZE-1; i++) {
            arrayDouble[i] = arrayDouble[i-1] * 2;
        }

        arrayDouble[ARRAY_SIZE-1] = 10.00;

        arrayDouble[2] = 100.50;

        for (double value : arrayDouble) {
            System.out.println(value);
        }
    }

    public static void partFour () {
        int ARRAY_SIZE = 10;
        double[] arrayDouble = new double[ARRAY_SIZE];

        for (int i = 0; i < ARRAY_SIZE; i++) {
            arrayDouble[i] = (i+1) * 3;
        }

        System.out.println("LENGTH: " + arrayDouble.length);

        for (double value : arrayDouble) {
            System.out.println(value);
        }
    }

}