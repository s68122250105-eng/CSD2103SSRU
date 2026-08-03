ข้อ 3 การเปรียบเทียบจำนวนสระและพยัญชนะ
    // =========================================================================
    // อัลกอริทึมที่ 1: Recursive Counting
    // เมธอดหลักตามโจทย์: static boolean hasMoreVowelsRecursive(String s)
    // =========================================================================
  public class VowelRecursive {

    static int vowels = 0;
    static int consonants = 0;

    static boolean hasMoreVowelsRecursive(String s) {

        vowels = 0;
        consonants = 0;

        s = s.toLowerCase();

        return count(s, 0);
    }

    static boolean count(String s, int index) {

        if (index == s.length()) {
            return vowels > consonants;
        }

        char ch = s.charAt(index);

        if (Character.isLetter(ch)) {

            if ("aeiou".indexOf(ch) != -1)
                vowels++;
            else
                consonants++;
        }

        return count(s, index + 1);
    }

    public static void main(String[] args) {

        String s = "education";

        boolean result = hasMoreVowelsRecursive(s);

        System.out.println("Vowels = " + vowels);
        System.out.println("Consonants = " + consonants);
        System.out.println("Result = " + result);
    }
}
    // =========================================================================
    // อัลกอริทึมที่ 2: Iterative Counting
    // เมธอดหลักตามโจทย์: static boolean hasMoreVowelsIterative(String s)
    // =========================================================================
public class VowelIterative {

    static boolean hasMoreVowelsIterative(String s) {

        int vowels = 0;
        int consonants = 0;

        s = s.toLowerCase();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (Character.isLetter(ch)) {

                if ("aeiou".indexOf(ch) != -1)
                    vowels++;
                else
                    consonants++;
            }
        }

        System.out.println("Vowels = " + vowels);
        System.out.println("Consonants = " + consonants);

        return vowels > consonants;
    }

    public static void main(String[] args) {

        System.out.println(
            hasMoreVowelsIterative("education")
        );
    }
}
