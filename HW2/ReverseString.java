ข้อ 1 การกลับลำดับสตริง
    // =========================================================================
    // อัลกอริทึมที่ 1: Recursive Algorithm
    // เมธอดหลักตามโจทย์: static String reverseRecursive(String s)
    // =========================================================================
public class ReverseString {

    public static String reverseRecursive(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        return s.charAt(s.length() - 1)
                + reverseRecursive(s.substring(0, s.length() - 1));
    }

    public static void main(String[] args) {

        String s = "pots&pans";

        System.out.println("Original : " + s);
        System.out.println("Reverse  : " + reverseRecursive(s));
    }
}
    // =========================================================================
    // อัลกอริทึมที่ 2: Iterative Algorithm
    // เมธอดหลักตามโจทย์: static String reverseIterative(String s)
    // =========================================================================
public class ReverseString {

    public static String reverseIterative(String s) {

        if (s == null) {
            return null;
        }

        String result = "";

        for (int i = s.length() - 1; i >= 0; i--) {
            result += s.charAt(i);
        }

        return result;
    }

    public static void main(String[] args) {

        String s = "pots&pans";

        System.out.println("Original : " + s);
        System.out.println("Reverse  : " + reverseIterative(s));
    }
}

  
