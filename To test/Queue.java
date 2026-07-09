import java.util.LinkedList;
import java.util.Queue;

public class QueueCaseStudy {
    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();

        queue.add("P001");
        queue.add("P002");
        queue.add("P003");
        queue.add("P004");
        queue.add("P005");

        System.out.println("Initial Queue");
        System.out.println(queue);

        System.out.println("\nPatients Being Served");

        for (int i = 1; i <= 2; i++) {
            if (!queue.isEmpty()) {
                System.out.println(queue.remove());
            } else {
                System.out.println("Queue is empty.");
            }
        }

        queue.add("P006");
        queue.add("P007");

        System.out.println("\nNext Patient: " + queue.peek());
        System.out.println("Number of Waiting Patients: " + queue.size());

        System.out.println("\nCurrent Queue");
        System.out.println(queue);

        System.out.println("\nExplanation");
        System.out.println("A Queue follows the FIFO (First In, First Out) principle.");
        System.out.println("The first patient to enter the queue is served first.");
    }
}