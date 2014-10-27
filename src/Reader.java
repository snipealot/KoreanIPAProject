import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Reader {
	Scanner scan;
	LinkedList<Word> wordlist = new LinkedList<Word>();

	Reader(File fil) {
		try {
			scan = new Scanner(fil);
		} catch (FileNotFoundException e) { System.out.println("File not found"); return;}
		process();
		scan.close();
	}
	
	public void process() {
		if(!readFile()) {
			System.out.println("Could not read file");
		}
	}

	boolean readFile() {
		if(scan == null) return false;
		while(scan.hasNext()) {
			String inn = scan.next();
			if(!inn.isEmpty()) {
				inn = inn.replaceAll("(?!\")\\p{Punct}", "");
				inn = inn.replaceAll("(“|”)", "");
				if(inn.matches(".*[0-9]+.*")) {
					inn = inn.replaceAll("[0-9]+", processNumbers(inn));
				}
				try {
					wordlist.add(new Word(inn));
				} catch (Exception e) {
					System.out.println("Could not process: " + inn);
				}
			}
		}

		return true;
	}

	private String processNumbers(String inn) {
		char[] cArr = inn.toCharArray();
		int counter = 0;
		String num = "";
		String ret = "";
		for(int i = 0; i < cArr.length; i++) {
			if(cArr[i] > 47 && cArr[i] < 58) {
				num += cArr[i];
				counter++;
			}
		}
		for(int j = 0; j < num.length(); j++) {
			switch(counter) {
			case 1: ret += findKorNum((num.charAt(j)-48)); break;
			case 2: ret += findKorNum((num.charAt(j)-48))+"십"; break;
			case 3: ret += findKorNum((num.charAt(j)-48))+"백"; break;
			case 4: ret += findKorNum((num.charAt(j)-48))+"천"; break;
			case 5: ret += findKorNum((num.charAt(j)-48))+"만"; break;
			case 6: ret += findKorNum((num.charAt(j)-48))+"십만"; break;
			case 7: ret += findKorNum((num.charAt(j)-48))+"백만"; break;
			case 8: ret += findKorNum((num.charAt(j)-48))+"천만"; break;
			case 9: ret += findKorNum((num.charAt(j)-48))+"억"; break;
			}
			counter--;
		}
		return ret;
	}

	private String findKorNum(int num) {
		
		switch(num) {
		case 1: return "일";
		case 2: return "이";
		case 3: return "삼";
		case 4: return "사";
		case 5: return "오";
		case 6: return "육";
		case 7: return "칠";
		case 8: return "팔";
		case 9: return "구";
		default: return "";
		}
	}

}