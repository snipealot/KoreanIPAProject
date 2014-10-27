
public abstract class Jamo {
	char character, original;
	String IPA;
	
	Jamo(char c) {
		character = original = c;
		IPA = "";
	}
	
	public String toString() {
		String ret = ""+IPA;
		return ret;
	}
}
