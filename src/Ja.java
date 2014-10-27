
public abstract class Ja extends Jamo {

	Ja(char c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public void aspirate() {
		switch(character) {
		case 'ㄱ': character = 'ㅋ'; break;
		case 'ㄷ': character = 'ㅌ'; break;
		case 'ㅈ': character = 'ㅊ'; break;
		case 'ㅂ': character = 'ㅍ'; break;
		}
	}
	
	public void glottalize() {
		switch(character) {
		case 'ㄱ': character = 'ㄲ'; break;
		case 'ㄷ': character = 'ㄸ'; break;
		case 'ㅈ': character = 'ㅉ'; break;
		case 'ㅂ': character = 'ㅃ'; break;
		case 'ㅅ': character = 'ㅆ'; break;
		case 'ㅋ': character = 'ㄲ'; break;
		case 'ㅌ': character = 'ㄸ'; break;
		case 'ㅊ': character = 'ㅉ'; break;
		case 'ㅍ': character = 'ㅃ'; break;
		}
	}
}
