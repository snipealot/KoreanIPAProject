import java.io.File;

public class KoreanIPAProject {
	public static void main(String[] args) {
		File file = new File("input.txt");
		Reader reader = new Reader(file);
		System.out.println(reader.wordlist);
	}
}
