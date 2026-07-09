import java.util.Stack;

public class StackCaseStudy {
    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();

        stack.push("Type Data");
        stack.push("Type Structure");
        stack.push("Delete Structure");
        stack.push("Type Algorithm");
        stack.push("Type Java");

        System.out.println("=== All Commands in the Stack ===");
        System.out.println(stack);

        System.out.println("\nUndo 2 Actions");

        for (int i = 1; i <= 2; i++) {
            if (!stack.isEmpty()) {
                String undo = stack.pop();
                System.out.println("Undo: " + undo);
            } else {
                System.out.println("Stack is empty.");
            }
        }

        System.out.println("\nStack After Undo");
        System.out.println(stack);

        System.out.println("\nExplanation");
        System.out.println("A Stack follows the LIFO (Last In, First Out) principle.");
        System.out.println("The most recently added item is removed first.");
    }
}