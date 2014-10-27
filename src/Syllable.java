import java.util.LinkedList;


public class Syllable {
	Lead lead;
	Tail tail;
	Vowel vowel;
	char original;
	LinkedList<Jamo> jamolist = new LinkedList<Jamo>();
	
	Syllable(char syl) throws Exception {
		original = syl;
		int l = getLead(syl);
		int v = getVowel(syl);
		int t = getTail(syl);
		if(!assignLead(l)) throw new Exception();
		jamolist.add(lead);
		if(!assignVowel(v)) throw new Exception();
		jamolist.add(vowel);
		if(t != 0) {
			assignTail(t);
			jamolist.add(tail);
		}
	}
	
	private int getLead(char c) {
		int lead = 1+((c-44032)/588);
		return lead;
	}
	
	public boolean assignLead(int l) {
		switch(l) {
		case 1: {
			lead = new Lead('ㄱ');
			break;
		}
		case 2: {
			lead = new Lead('ㄲ');
			break;
		}
		case 3: {
			lead = new Lead('ㄴ');
			break;
		}
		case 4: {
			lead = new Lead('ㄷ');
			break;
		}
		case 5: {
			lead = new Lead('ㄸ');
			break;
		}
		case 6: {
			lead = new Lead('ㄹ');
			break;
		}
		case 7: {
			lead = new Lead('ㅁ');
			break;
		}
		case 8: {
			lead = new Lead('ㅂ');
			break;
		}
		case 9: {
			lead = new Lead('ㅃ');
			break;
		}
		case 10: {
			lead = new Lead('ㅅ');
			break;
		}
		case 11: {
			lead = new Lead('ㅆ');
			break;
		}
		case 12: {
			lead = new Lead('ㅇ');
			break;
		}
		case 13: {
			lead = new Lead('ㅈ');
			break;
		}
		case 14: {
			lead = new Lead('ㅉ');
			break;
		}
		case 15: {
			lead = new Lead('ㅊ');
			break;
		}
		case 16: {
			lead = new Lead('ㅋ');
			break;
		}
		case 17: {
			lead = new Lead('ㅌ');
			break;
		}
		case 18: {
			lead = new Lead('ㅍ');
			break;
		}
		case 19: {
			lead = new Lead('ㅎ');
			break;
		}
		default: {
			System.out.println("Error in unicode processing");
			return false;
		}
		}
		return true;
	}
	
	public boolean assignVowel(int v) {
		switch(v) {
		case 1: {
			vowel = new Vowel('ㅏ');
			break;
		}
		case 2: {
			vowel = new Vowel('ㅐ');
			break;
		}
		case 3: {
			vowel = new Vowel('ㅑ');
			break;
		}
		case 4: {
			vowel = new Vowel('ㅒ');
			break;
		}
		case 5: {
			vowel = new Vowel('ㅓ');
			break;
		}
		case 6: {
			vowel = new Vowel('ㅔ');
			break;
		}
		case 7: {
			vowel = new Vowel('ㅕ');
			break;
		}
		case 8: {
			vowel = new Vowel('ㅖ');
			break;
		}
		case 9: {
			vowel = new Vowel('ㅗ');
			break;
		}
		case 10: {
			vowel = new Vowel('ㅘ');
			break;
		}
		case 11: {
			vowel = new Vowel('ㅙ');
			break;
		}
		case 12: {
			vowel = new Vowel('ㅚ');
			break;
		}
		case 13: {
			vowel = new Vowel('ㅛ');
			break;
		}
		case 14: {
			vowel = new Vowel('ㅜ');
			break;
		}
		case 15: {
			vowel = new Vowel('ㅝ');
			break;
		}
		case 16: {
			vowel = new Vowel('ㅞ');
			break;
		}
		case 17: {
			vowel = new Vowel('ㅟ');
			break;
		}
		case 18: {
			vowel = new Vowel('ㅠ');
			break;
		}
		case 19: {
			vowel = new Vowel('ㅡ');
			break;
		}
		case 20: {
			vowel = new Vowel('ㅢ');
			break;
		}
		case 21: {
			vowel = new Vowel('ㅣ');
			break;
		}
		default: {
			System.out.println("Error in unicode processing");
			return false;
		}
		}
		return true;
	}
	
	public boolean assignTail(int t) {
		switch(t) {
		case 0: {
			break;
		}
		case 1: {
			tail = new Tail('ㄱ');
			break;
		}
		case 2: {
			tail = new Tail('ㄲ');
			break;
		}
		case 3: {
			tail = new ComplexTail('ㄳ', 'ㄱ', 'ㅅ');
			break;
		}
		case 4: {
			tail = new Tail('ㄴ');
			break;
		}
		case 5: {
			tail = new ComplexTail('ㄵ', 'ㄴ', 'ㅈ');
			break;
		}
		case 6: {
			tail = new ComplexTail('ㄶ', 'ㄴ', 'ㅎ');
			break;
		}
		case 7: {
			tail = new Tail('ㄷ');
			break;
		}
		case 8: {
			tail = new Tail('ㄹ');
			break;
		}
		case 9: {
			tail = new ComplexTail('ㄺ', 'ㄹ', 'ㄱ');
			break;
		}
		case 10: {
			tail = new ComplexTail('ㄻ', 'ㄹ', 'ㅁ');
			break;
		}
		case 11: {
			tail = new ComplexTail('ㄼ', 'ㄹ', 'ㅂ');
			break;
		}
		case 12: {
			tail = new ComplexTail('ㄽ', 'ㄹ', 'ㅅ');
			break;
		}
		case 13: {
			tail = new ComplexTail('ㄾ', 'ㄹ', 'ㅌ');
			break;
		}
		case 14: {
			tail = new ComplexTail('ㄿ', 'ㄹ', 'ㅍ');
			break;
		}
		case 15: {
			tail = new ComplexTail('ㅀ', 'ㄹ', 'ㅎ');
			break;
		}
		case 16: {
			tail = new Tail('ㅁ');
			break;
		}
		case 17: {
			tail = new Tail('ㅂ');
			break;
		}
		case 18: {
			tail = new ComplexTail('ㅄ', 'ㅂ', 'ㅅ');
			break;
		}
		case 19: {
			tail = new Tail('ㅅ');
			break;
		}
		case 20: {
			tail = new Tail('ㅆ');
			break;
		}
		case 21: {
			tail = new Tail('ㅇ');
			break;
		}
		case 22: {
			tail = new Tail('ㅈ');
			break;
		}
		case 23: {
			tail = new Tail('ㅊ');
			break;
		}
		case 24: {
			tail = new Tail('ㅋ');
			break;
		}
		case 25: {
			tail = new Tail('ㅌ');
			break;
		}
		case 26: {
			tail = new Tail('ㅍ');
			break;
		}
		case 27: {
			tail = new Tail('ㅎ');
			break;
		}
		default: {
			System.out.println("Error in unicode processing");
			return false;
		}
		}
		return true;
	}
	
	private int getTail(char c) {
		int tail = (c-44032)%28;
		return tail;
	}
	
	private int getVowel(char c) {
		int vowel = 1+(((c-44032-getTail(c))%588)/28);
		return vowel;
	}
	
	public String toString() {
		String ret;
		if(tail != null) {
			ret = ""+lead+vowel+tail;
		} else {
			ret = ""+lead+vowel;
		}
		return ret;
	}
}
