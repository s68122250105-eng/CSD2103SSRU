ข้อ 5 การแบ่งอาร์เรย์ตามค่า k
    // =========================================================================
    // อัลกอริทึมที่ 1: Recursive Partition
    // เมธอดหลักตามโจทย์: static void partitionRecursive(int[] a, int k, int left, int right)
    // =========================================================================
  public class RecursivePartition {

    static void partitionRecursive(int[] a, int k, int left, int right) {

        if (left >= right)
            return;

        if (a[left] <= k) {
            partitionRecursive(a, k, left + 1, right);
        }
        else if (a[right] > k) {
            partitionRecursive(a, k, left, right - 1);
        }
        else {

            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;

            partitionRecursive(a, k, left + 1, right - 1);
        }
    }

    public static void main(String[] args) {

        int[] a = {12,4,7,15,3,10,8};

        partitionRecursive(a,8,0,a.length-1);

        for(int n:a)
            System.out.print(n+" ");
    }
}
    // =========================================================================
    // อัลกอริทึมที่ 2: Iterative Partition
    // เมธอดหลักตามโจทย์: static void partitionIterative(int[] a, int k)
    // =========================================================================
public class IterativePartition {

    static void partitionIterative(int[] a, int k){

        int left = 0;
        int right = a.length - 1;

        while(left < right){

            if(a[left] <= k)
                left++;

            else if(a[right] > k)
                right--;

            else{

                int temp = a[left];
                a[left] = a[right];
                a[right] = temp;

                left++;
                right--;
            }
        }
    }

    public static void main(String[] args){

        int[] a = {12,4,7,15,3,10,8};

        partitionIterative(a,8);

        for(int n:a)
            System.out.print(n+" ");
    }
}
    // =========================================================================
    // อัลกอริทึมที่ 3: Sorting-Based Algorithm
    // เมธอดหลักตามโจทย์: static void partitionBySorting(int[] a, int k)
    // =========================================================================
import java.util.Arrays;

public class PartitionBySorting {

    static void partitionBySorting(int[] a, int k){

        Arrays.sort(a);

        for(int n : a)
            System.out.print(n + " ");
    }

    public static void main(String[] args){

        int[] a = {12,4,7,15,3,10,8};

        partitionBySorting(a,8);
    }
}
