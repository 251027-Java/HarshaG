import java.util.Scanner;

public class LetterGrade {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double grade;
        try {
            System.out.print("Enter number grade: ");
            grade = input.nextDouble();

            System.out.print("Your letter grade: ");
            if (grade <= 100 && grade >= 90){
                System.out.print("A");
            } else if (grade < 90 && grade >= 80){
                System.out.print("B");
            } else if (grade < 80 && grade >= 70){
                System.out.print("C");
            } else if (grade < 70 && grade >= 60){
                System.out.print("D");
            } else {
                System.out.print("F");
            }


        } catch (Exception e){
            System.out.println("That wasn't a number!");
            grade = 0;
        }

        input.close();

    }
}
