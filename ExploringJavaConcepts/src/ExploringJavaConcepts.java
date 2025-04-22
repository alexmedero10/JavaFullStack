import java.util.Scanner;

public class ExploringJavaConcepts {
    public static void main(String[] args) {

        // PART 1
        int age = 25;
        double height = 68.89;
        char initial = 'A';
        boolean isStudent = true;

        System.out.println("Hi, my name is Alex, I'm " + age + " years old and my height is " + height);
        System.out.println("I got a tattoo of mi initial letter: " + initial);
        System.out.println("If you ask me if i'm a student, i will say that it's " + isStudent);


        // PART 2
        int counter = 10;
        counter++;
        System.out.println("Counter increment: " + counter);
        counter--;
        System.out.println("Counter decrement: " + counter);

        for (int i=0; i<5; i++) {
            System.out.println("Counter 'for loop' increment: " + counter++);
        }
        System.out.println("Counter 'for loop' increment: " + counter);

        while (counter > 10) {
            System.out.println("Counter 'while' decrement: " + counter--);
        }
        System.out.println("Counter 'while' decrement: " + counter);

        // PART 3
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa tu nombre: ");
        String firstName = scanner.next();

        System.out.print("Ingresa tu apellido: ");
        String lastName = scanner.next();

        String fullName = firstName + " " + lastName;
        String fullNameUpperCase = fullName.toUpperCase();

        char firstCharacterFullName = fullName.charAt(0);

        int counterFirstCharacter = 0;
        for (int i=0; i<fullNameUpperCase.length(); i++) {
            if (fullNameUpperCase.charAt(i) == firstCharacterFullName) {
                counterFirstCharacter++;
            }
        }

        System.out.println("Full Name: " + fullName);
        System.out.println("Full Name Upper Case: " + fullNameUpperCase);
        System.out.println("Number of times letter " + firstCharacterFullName + " appears: " + counterFirstCharacter);

        // PART 4
        int scoreA = 75;
        int scoreB = 100;
        int scoreC = 90;
        int averageScore = (scoreA + scoreB + scoreC) / 3;

        if (averageScore >= 90 && averageScore <= 100) {
            System.out.println("Excellent");
        } else if (averageScore >= 70 && averageScore <= 89) {
            System.out.println("Good");
        } else if (averageScore >= 50 && averageScore <= 69) {
            System.out.println("Average");
        } else if (averageScore < 50) {
            System.out.println("Poor");
        }

        int day = 3;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
        }


        // PART 5
        float num1 = 0.0f, num2 = 0.0f, result = 0.0f;
        char operation;

        Scanner scannerOperation = new Scanner(System.in);
        System.out.print("Primer número: ");
        num1 = scannerOperation.nextFloat();
        System.out.print("Segundo número: ");
        num2 = scannerOperation.nextFloat();
        System.out.print("Operación (+, -, *, /): ");
        operation = scannerOperation.next().charAt(0);

        if (operation == '+') {
            result = num1 + num2;
        } else if (operation == '-') {
            result = num1 + num2;
        } else if (operation == '*') {
            result = num1 * num2;
        } else if (operation == '/') {
            result = num1 / num2;
        }

        int operatorsIncDec = 0;
        System.out.println("For increment write: 1\nFor decrement write: 2\nNo action: Any button");
        operatorsIncDec = scannerOperation.nextInt();
        switch (operatorsIncDec) {
            case 1:
                result++;
                break;
            case 2:
                result--;
                break;
            default:
                System.out.println("Any action");
        }

        System.out.println("Result: " + result);
    }
}