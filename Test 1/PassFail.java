import java.util.Scanner;

public class PassFail {
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);

        int midterm;
        int finalScore;
        int total;

        System.out.print("Enter midterm score: ");
        midterm = kb.nextInt();

        System.out.print("Enter final score: ");
        finalScore = kb.nextInt();

        total = midterm + finalScore;

        System.out.println("Total score = " + total);

        if (total >= 50) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }

        kb.close();
    }
}
