package leetCode;

/* https://leetcode.com/problems/ransom-note/
 */
public class RansomNote {
	
	public boolean canConstruct(String ransomNote, String magazine) {
        
		// Special cases
		if (ransomNote.length() > magazine.length())
			return false;
		else if (ransomNote.length() == 0)
			return true;
		else if (magazine.length() == 0)
			return false;
		
		// Counting characters
		int[] charCount = new int[26];
		
		for (int i = 0; i < magazine.length(); i++) {
			charCount[magazine.charAt(i) - 'a']++;
		}
		for (int i = 0; i < ransomNote.length(); i++) {
			charCount[ransomNote.charAt(i) - 'a']--;
		}
		
		return isAllPositive(charCount);
		
    }
	
	private boolean isAllPositive(int[] charCount) {
		for (int i = 0; i < charCount.length; i++) {
			if (charCount[i] < 0)
				return false;
		}
		return true;
	}

}
