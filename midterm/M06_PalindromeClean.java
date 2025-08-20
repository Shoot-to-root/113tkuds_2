import java.util.Scanner;

public class M06_PalindromeClean {

    private static String cleanString(String s) {
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        return cleaned.toString();
    }

    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false; 
            }
            left++;
            right--;
        }
        return true; 
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String input = scanner.nextLine();

        String cleanedString = cleanString(input);

        if (isPalindrome(cleanedString)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        
        scanner.close();
    }
}