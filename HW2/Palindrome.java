ข้อ 2 การตรวจสอบ Palindrome
    // =========================================================================
    // อัลกอริทึมที่ 1: Recursive Algorithm
    // เมธอดหลักตามโจทย์: static boolean isPalindromeByReverse(String s)
    // =========================================================================
public class Palindrome {

    static String cleanString(String s) {
        return s.toLowerCase().replaceAll("[^a-z]", "");
    }

    static boolean isPalindromeByReverse(String s) {

        s = cleanString(s);

        String reverse = "";

        for (int i = s.length() - 1; i >= 0; i--) {
            reverse += s.charAt(i);
        }

        return s.equals(reverse);
    }

    public static void main(String[] args) {

        System.out.println(isPalindromeByReverse("racecar"));
        System.out.println(isPalindromeByReverse("algorithm"));
        System.out.println(isPalindromeByReverse("A man, a plan, a canal: Panama"));
    }
}

    // =========================================================================
    // อัลกอริทึมที่ 2: Recursive Two-Pointer
    // เมธอดหลักตามโจทย์: static boolean isPalindromeRecursive(String s, int left, int right)
    // =========================================================================
public class Palindrome {

    static String cleanString(String s) {
        return s.toLowerCase().replaceAll("[^a-z]", "");
    }

    static boolean isPalindromeRecursive(String s, int left, int right) {

        if (left >= right)
            return true;

        if (s.charAt(left) != s.charAt(right))
            return false;

        return isPalindromeRecursive(s, left + 1, right - 1);
    }

    public static void main(String[] args) {

        String s = cleanString("A man, a plan, a canal: Panama");

        System.out.println(isPalindromeRecursive(s,0,s.length()-1));
    }
}
