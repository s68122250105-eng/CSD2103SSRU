import java.util.Scanner;

public class EvenOdd {
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);

        int number;

        System.out.print("Enter number: ");
        number = kb.nextInt();

        if (number % 2 == 0) {
            System.out.println("Even number");
        } else {
            System.out.println("Odd number");
        }

        kb.close();
    }
}
