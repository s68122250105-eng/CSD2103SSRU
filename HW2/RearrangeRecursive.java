ข้อ 4 การจัดกลุ่มจำนวนคู่และจำนวนคี่
    // =========================================================================
    // อัลกอริทึมที่ 1: Recursive Two-Pointer
    // เมธอดหลักตามโจทย์: static void rearrangeRecursive(int[] a, int left, int right)
    // =========================================================================
  public class RearrangeRecursive {

    static void rearrangeRecursive(int[] a, int left, int right) {

        if (left >= right)
            return;

        if (a[left] % 2 == 0) {
            rearrangeRecursive(a, left + 1, right);
        }
        else if (a[right] % 2 != 0) {
            rearrangeRecursive(a, left, right - 1);
        }
        else {

            int temp = a[left];
            a[left] = a[right];
            a[right] = temp;

            rearrangeRecursive(a, left + 1, right - 1);
        }
    }

    public static void main(String[] args) {

        int[] a = {7,2,9,4,1,6,3,8};

        rearrangeRecursive(a,0,a.length-1);

        for(int n : a)
            System.out.print(n+" ");
    }
}
    // =========================================================================
    // อัลกอริทึมที่ 2: Iterative Two-Pointer
    // เมธอดหลักตามโจทย์: static void rearrangeTwoPointer(int[] a)
    // =========================================================================
public class RearrangeIterative {

    static void rearrangeTwoPointer(int[] a){

        int left = 0;
        int right = a.length-1;

        while(left < right){

            if(a[left] %2==0)
                left++;

            else if(a[right]%2!=0)
                right--;

            else{

                int temp=a[left];
                a[left]=a[right];
                a[right]=temp;

                left++;
                right--;
            }
        }
    }

    public static void main(String[] args){

        int[] a={7,2,9,4,1,6,3,8};

        rearrangeTwoPointer(a);

        for(int n:a)
            System.out.print(n+" ");
    }
}
    // =========================================================================
    // อัลกอริทึมที่ 3: Extra Array
    // เมธอดหลักตามโจทย์: static int[] rearrangeExtraArray(int[] a)
    // =========================================================================
public class RearrangeExtraArray {

    static int[] rearrangeExtraArray(int[] a){

        int[] result = new int[a.length];
        int index = 0;

        for(int n:a){
            if(n%2==0)
                result[index++] = n;
        }

        for(int n:a){
            if(n%2!=0)
                result[index++] = n;
        }

        return result;
    }

    public static void main(String[] args){

        int[] a={7,2,9,4,1,6,3,8};

        int[] result = rearrangeExtraArray(a);

        for(int n:result)
            System.out.print(n+" ");
    }
}
