import java.util.Scanner;

public class StudentGrades {
    public static void main(String[] args) {

        int[] studentGrades = new int[10];

        Scanner scanner = new Scanner(System.in);

        for (int i=0; i<studentGrades.length; i++) {
            System.out.print("Grade " + (i+1) + ": ");
            studentGrades[i] = scanner.nextInt();
        }

        int sumGrades = 0, averageGrade;
        for (int grade: studentGrades) {
            sumGrades += grade;
        }

        averageGrade = sumGrades/studentGrades.length;

        System.out.println("\nAVERAGE GRADE: " + averageGrade);

        int minGrade = Integer.MAX_VALUE, maxGrade = Integer.MIN_VALUE;

        for (int grade: studentGrades) {
            if (grade < minGrade) minGrade = grade;
            if (grade > maxGrade) maxGrade = grade;
        }

        System.out.println("LOWEST GRADE: " + minGrade);
        System.out.println("HIGHEST GRADE: " + maxGrade);

        System.out.println("\nALL THE GRADES");

        for (int grade: studentGrades) {
            System.out.println(grade);
        }

    }
}