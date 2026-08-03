ข้อ 6 การค้นหาคู่จำนวนที่มีผลรวมเท่ากับ k
    // =========================================================================
    // อัลกอริทึมที่ 1: Brute Force
    // เมธอดหลักตามโจทย์: static boolean findPairBruteForce(int[] a, int k)
    // =========================================================================
public class PairBruteForce {

    static boolean findPairBruteForce(int[] a, int k) {

        for (int i = 0; i < a.length - 1; i++) {

            for (int j = i + 1; j < a.length; j++) {

                if (a[i] + a[j] == k) {
                    System.out.println("Pair found: " + a[i] + " and " + a[j]);
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int[] a = {2,4,7,11,15,20};

        if(!findPairBruteForce(a,18))
            System.out.println("Pair not found");
    }
}
    // =========================================================================
    // อัลกอริทึมที่ 2: Recursive Two-Pointer
    // เมธอดหลักตามโจทย์: static boolean findPairRecursive( int[] a, int k, int left, int right)
    // =========================================================================
public class PairRecursive {

    static boolean findPairRecursive(int[] a, int k, int left, int right){

        if(left >= right)
            return false;

        int sum = a[left] + a[right];

        if(sum == k){

            System.out.println("Pair found: "
                    + a[left] + " and " + a[right]);

            return true;
        }

        if(sum < k)
            return findPairRecursive(a,k,left+1,right);

        return findPairRecursive(a,k,left,right-1);
    }

    public static void main(String[] args){

        int[] a={2,4,7,11,15,20};

        if(!findPairRecursive(a,18,0,a.length-1))
            System.out.println("Pair not found");
    }
}
    // =========================================================================
    // อัลกอริทึมที่ 3: Binary Search
    // เมธอดหลักตามโจทย์: static boolean findPairBinarySearch(int[] a, int k)
    // =========================================================================
public class PairBinarySearch {

    static boolean binarySearch(int[] a,int left,int right,int target){

        while(left <= right){

            int mid = (left + right)/2;

            if(a[mid] == target)
                return true;

            if(a[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return false;
    }

    static boolean findPairBinarySearch(int[] a,int k){

        for(int i=0;i<a.length;i++){

            int target = k - a[i];

            if(binarySearch(a,i+1,a.length-1,target)){

                System.out.println("Pair found: "
                        + a[i] + " and " + target);

                return true;
            }
        }

        return false;
    }

    public static void main(String[] args){

        int[] a={2,4,7,11,15,20};

        if(!findPairBinarySearch(a,18))
            System.out.println("Pair not found");
    }
}
