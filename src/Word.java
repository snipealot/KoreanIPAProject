
public class Word {
	Syllable[] syllables;
	String original;
	String IPA;

	Word(String w) throws Exception {
		original = w;
		char[] word = w.toCharArray();
		syllables = new Syllable[word.length];
		for(int i = 0; i < word.length; i++) {
			try {
				syllables[i] = new Syllable(word[i]);
			} catch (Exception e) {
				throw new Exception();
			}
			if(syllables[i] == null) {
				return;
			}
		}
		IPA = "";
		preprocess();
		setIPA();
	}

	public void preprocess() {
		for(int i = 1; i < syllables.length; i++) { // Starting from the second syllable to preprocess ㅇ
			if(syllables[i].lead.character == 'ㅇ' && syllables[i-1].tail != null && syllables[i-1].tail.character != 'ㅇ') {
				if(syllables[i-1].tail instanceof ComplexTail) {
					ComplexTail lastTail = (ComplexTail) syllables[i-1].tail;
					syllables[i].lead.character = lastTail.partTwo;
					syllables[i-1].tail.character = lastTail.partOne;
				} else {
					syllables[i].lead.character = syllables[i-1].tail.character;
					syllables[i-1].tail = null;
				}
			}
			if(syllables[i].lead.character == 'ㅎ' && syllables[i-1].tail != null) {
				if(syllables[i-1].tail instanceof ComplexTail) {
					ComplexTail lastTail = (ComplexTail) syllables[i-1].tail;
					syllables[i].lead.character = lastTail.partTwo;
					syllables[i-1].tail.character = lastTail.partOne;
					syllables[i].lead.aspirate();
				} else if(syllables[i-1].tail.character == 'ㅅ') {
					syllables[i].lead.character = 'ㅌ';
				} else if(syllables[i-1].tail.character == 'ㄷ') {
					syllables[i].lead.character = 'ㅊ';
				} else {
					syllables[i].lead.character = syllables[i-1].tail.character;
					syllables[i-1].tail = null;
					syllables[i].lead.aspirate();
				}
			}
			if(syllables[i].lead.character == 'ㄹ' && syllables[i-1].tail != null && syllables[i-1].tail.character == 'ㄴ') {
				syllables[i-1].tail.character = 'ㄹ';
			}
			if(syllables[i].lead.character == 'ㄴ' && syllables[i-1].tail != null) {
				switch(syllables[i-1].tail.character) {
				case 'ㄹ': syllables[i].lead.character = 'ㄹ'; break;
				case 'ㄷ': syllables[i-1].tail.character = 'ㄴ'; break;
				case 'ㅅ': syllables[i-1].tail.character = 'ㄴ'; break;
				case 'ㅈ': syllables[i-1].tail.character = 'ㄴ'; break;
				case 'ㅊ': syllables[i-1].tail.character = 'ㄴ'; break;
				case 'ㅎ': syllables[i-1].tail.character = 'ㄴ'; break;
				case 'ㅌ': syllables[i-1].tail.character = 'ㄴ'; break;
				case 'ㄱ': syllables[i-1].tail.character = 'ㅇ'; break;
				case 'ㄲ': syllables[i-1].tail.character = 'ㅇ'; break;
				case 'ㄺ': syllables[i-1].tail.character = 'ㅇ'; break;
				case 'ㅂ': syllables[i-1].tail.character = 'ㅁ'; break;
				case 'ㅍ': syllables[i-1].tail.character = 'ㅁ'; break;
				}
			}
			if(syllables[i].lead.character == 'ㅁ' && syllables[i-1].tail != null) {
				switch(syllables[i-1].tail.character) {
				case 'ㅂ': syllables[i-1].tail.character = 'ㅁ'; break;
				case 'ㅅ': syllables[i-1].tail.character = 'ㄴ'; break;
				case 'ㄱ': syllables[i-1].tail.character = 'ㅇ'; break;
				case 'ㄲ': syllables[i-1].tail.character = 'ㅇ'; break;
				case 'ㄺ': syllables[i-1].tail.character = 'ㅇ'; break;
				case 'ㄷ': syllables[i-1].tail.character = 'ㄴ'; break;
				case 'ㅌ': syllables[i-1].tail.character = 'ㄴ'; break;
				case 'ㅈ': syllables[i-1].tail.character = 'ㄴ'; break;
				case 'ㅊ': syllables[i-1].tail.character = 'ㄴ'; break;
				}
			}
			if(syllables[i-1].tail != null && syllables[i-1].tail.character == 'ㅎ') {
				syllables[i].lead.aspirate();
			}
			if(syllables[i-1].tail != null && syllables[i-1].tail.character == syllables[i].lead.character) {
				syllables[i].lead.glottalize();
			}
			if(syllables[i-1].tail != null && syllables[i-1].tail.character == 'ㅅ' && syllables[i-1].original != '못') {
				syllables[i].lead.glottalize();
			}
		}
	}
	
	public void setIPA() {
		boolean firstletter = true;
		for(int i = 0; i < syllables.length; i++) {
			for(Jamo j : syllables[i].jamolist) {
				switch(j.character) {
				case 'ㅂ': {
					if(firstletter) {
						j.IPA = "p";
					} else if(j instanceof Lead) {
						j.IPA = "b";
					} else {
						j.IPA = "p";
					}
					break;
				}
				case 'ㅍ': {
					if(firstletter) {
						j.IPA = "pʰ";
					} else if(j instanceof Lead) {
						j.IPA = "pʰ";
					} else {
						j.IPA = "p";
					}
					break;
				}
				case 'ㅃ': {
					if(firstletter) {
						j.IPA = "p'";
					} else if(j instanceof Lead) {
						j.IPA = "p'";
					} else {
						// ---
					}
					break;
				}
				case 'ㄷ': {
					if(firstletter) {
						j.IPA = "t";
					} else if(j instanceof Lead) {
						j.IPA = "d";
					} else {
						j.IPA = "t";
					}
					break;
				}
				case 'ㅌ': {
					if(firstletter) {
						j.IPA = "tʰ";
					} else if(j instanceof Lead) {
						j.IPA = "tʰ";
					} else {
						j.IPA = "t";
					}
					break;
				}
				case 'ㄸ': {
					if(firstletter) {
						j.IPA = "t'";
					} else if(j instanceof Lead) {
						j.IPA = "t''";
					} else {
						// ---
					}
					break;
				}
				case 'ㅅ': {
					if(firstletter) {
						if(syllables[i].vowel.character == 'ㅣ' || syllables[i].vowel.character == 'ㅑ' || syllables[i].vowel.character == 'ㅕ' || syllables[i].vowel.character == 'ㅛ' || syllables[i].vowel.character == 'ㅠ' || syllables[i].vowel.character == 'ㅒ' || syllables[i].vowel.character == 'ㅖ') {
							j.IPA = "ʃ";
						} else {
							j.IPA = "s";
						}
					} else if(j instanceof Lead) {
						if(syllables[i].vowel.character == 'ㅣ' || syllables[i].vowel.character == 'ㅑ' || syllables[i].vowel.character == 'ㅕ' || syllables[i].vowel.character == 'ㅛ' || syllables[i].vowel.character == 'ㅠ' || syllables[i].vowel.character == 'ㅒ' || syllables[i].vowel.character == 'ㅖ') {
							j.IPA = "ʃ";
						} else {
							j.IPA = "s";
						}
					} else {
						j.IPA = "t";
					}
					break;
				}
				case 'ㅆ': {
					if(firstletter) {
						if(syllables[i].vowel.character == 'ㅣ' || syllables[i].vowel.character == 'ㅑ' || syllables[i].vowel.character == 'ㅕ' || syllables[i].vowel.character == 'ㅛ' || syllables[i].vowel.character == 'ㅠ' || syllables[i].vowel.character == 'ㅒ' || syllables[i].vowel.character == 'ㅖ') {
							j.IPA = "ʃ'";
						} else {
							j.IPA = "s'";
						}
					} else if(j instanceof Lead) {
						if(syllables[i].vowel.character == 'ㅣ' || syllables[i].vowel.character == 'ㅑ' || syllables[i].vowel.character == 'ㅕ' || syllables[i].vowel.character == 'ㅛ' || syllables[i].vowel.character == 'ㅠ' || syllables[i].vowel.character == 'ㅒ' || syllables[i].vowel.character == 'ㅖ') {
							j.IPA = "ʃ'";
						} else {
							j.IPA = "s'";
						}
					} else {
						j.IPA = "t";
					}
					break;
				}
				case 'ㄱ': {
					if(firstletter) {
						j.IPA = "k";
					} else if(j instanceof Lead) {
						j.IPA = "g";
					} else {
						j.IPA = "k";
					}
					break;
				}
				case 'ㅋ': {
					if(firstletter) {
						j.IPA = "kʰ";
					} else if(j instanceof Lead) {
						j.IPA = "kʰ";
					} else {
						j.IPA = "k";
					}
					break;
				}
				case 'ㄲ': {
					if(firstletter) {
						j.IPA = "k'";
					} else if(j instanceof Lead) {
						j.IPA = "k'";
					} else {
						j.IPA = "k";
					}
					break;
				}
				case 'ㅈ': {
					if(firstletter) {
						j.IPA = "c";
					} else if(j instanceof Lead) {
						j.IPA = "ɟ";
					} else {
						j.IPA = "t";
					}
					break;
				}
				case 'ㅊ': {
					if(firstletter) {
						j.IPA = "cʰ";
					} else if(j instanceof Lead) {
						j.IPA = "cʰ";
					} else {
						j.IPA = "t";
					}
					break;
				}
				case 'ㅉ': {
					if(firstletter) {
						j.IPA = "c'";
					} else if(j instanceof Lead) {
						j.IPA = "c'";
					} else {
						// ---
					}
					break;
				}
				case 'ㅎ': {
					if(firstletter) {
						j.IPA = "h";
					} else if(j instanceof Lead) {
						j.IPA = "h";
					} else {
						j.IPA = "t";
					}
					break;
				}
				case 'ㅁ': {
					if(firstletter) {
						j.IPA = "m";
					} else if(j instanceof Lead) {
						j.IPA = "m";
					} else {
						j.IPA = "m";
					}
					break;
				}
				case 'ㄴ': {
					if(firstletter) {
						if(syllables[i].vowel.character == 'ㅑ' || syllables[i].vowel.character == 'ㅕ' || syllables[i].vowel.character == 'ㅛ' || syllables[i].vowel.character == 'ㅠ' || syllables[i].vowel.character == 'ㅒ' || syllables[i].vowel.character == 'ㅖ') {
							j.IPA = "ɲ";
						} else {
							j.IPA = "n";
						}
					} else if(j instanceof Lead) {
						if(syllables[i].vowel.character == 'ㅑ' || syllables[i].vowel.character == 'ㅕ' || syllables[i].vowel.character == 'ㅛ' || syllables[i].vowel.character == 'ㅠ' || syllables[i].vowel.character == 'ㅒ' || syllables[i].vowel.character == 'ㅖ') {
							j.IPA = "ɲ";
						} else {
							j.IPA = "n";
						}
					} else {
							j.IPA = "n";
					}
					break;
				}
				case 'ㅇ': {
					if(firstletter) {

					} else if(j instanceof Lead) {
						
					} else {
						j.IPA = "ɲ";
					}
					break;
				}
				case 'ㄹ': {
					if(firstletter) {
						j.IPA = "r";
					} else if(j instanceof Lead) {
						if(syllables[i-1].tail != null && syllables[i-1].tail.character == 'ㄹ') {
							j.IPA = "l";
						} else {
							j.IPA = "r";
						}
					} else {
						j.IPA = "l";
					}
					break;
				}
				case 'ㄳ': {
					j.IPA = "k";
					break;
				}
				case 'ㄵ': {
					j.IPA = "n";
					break;
				}
				case 'ㄶ': {
					j.IPA = "n";
					break;
				}
				case 'ㄺ': {
					j.IPA = "k";
					break;
				}
				case 'ㄻ': {
					j.IPA = "l";
					break;
				}
				case 'ㄼ': {
					j.IPA = "p";
					break;
				}
				case 'ㄽ': {
					j.IPA = "l";
					break;
				}
				case 'ㄾ': {
					j.IPA = "l";
					break;
				}
				case 'ㄿ': {
					j.IPA = "pʰ";
					break;
				}
				case 'ㅀ': {
					j.IPA = "l";
					break;
				}
				case 'ㅄ': {
					j.IPA = "p";
					break;
				}
				case 'ㅏ': {
					j.IPA = "a";
					break;
				}
				case 'ㅓ': {
					j.IPA = "ə";
					break;
				}
				case 'ㅗ': {
					j.IPA = "o";
					break;
				}
				case 'ㅜ': {
					j.IPA = "u";
					break;
				}
				case 'ㅡ': {
					j.IPA = "ɨ";
					break;
				}
				case 'ㅐ': {
					j.IPA = "ɛ";
					break;
				}
				case 'ㅒ': {
					j.IPA = "jɛ";
					break;
				}
				case 'ㅘ': {
					j.IPA = "wa";
					break;
				}
				case 'ㅚ': {
					j.IPA = "we";
					break;
				}
				case 'ㅙ': {
					j.IPA = "wɛ";
					break;
				}
				case 'ㅢ': {
					if(i == 0 && syllables[i].lead.character == 'ㅇ'){
						j.IPA = "ɨj";
					} else {
						j.IPA = "i";
					}
					break;
				}
				case 'ㅑ': {
					if(syllables[i].lead.character == 'ㄴ' || syllables[i].lead.character == 'ㅅ') {
						j.IPA = "a";
					} else {
						j.IPA = "ja";
					}
					break;
				}
				case 'ㅕ': {
					if(syllables[i].lead.character == 'ㄴ' || syllables[i].lead.character == 'ㅅ') {
						j.IPA = "ə";
					} else {
						j.IPA = "jə";
					}
					break;
				}
				case 'ㅛ': {
					if(syllables[i].lead.character == 'ㄴ' || syllables[i].lead.character == 'ㅅ') {
						j.IPA = "o";
					} else {
						j.IPA = "jo";
					}
					break;
				}
				case 'ㅠ': {
					if(syllables[i].lead.character == 'ㄴ' || syllables[i].lead.character == 'ㅅ') {
						j.IPA = "u";
					} else {
						j.IPA = "ju";
					}
					break;
				}
				case 'ㅣ': {
					j.IPA = "i";
					break;
				}
				case 'ㅔ': {
					j.IPA = "e";
					break;
				}
				case 'ㅖ': {
					if(syllables[i].lead.character == 'ㄴ' || syllables[i].lead.character == 'ㅅ') {
						j.IPA = "e";
					} else {
						j.IPA = "je";
					}
					break;
				}
				case 'ㅝ': {
					j.IPA = "wə";
					break;
				}
				case 'ㅟ': {
					j.IPA = "wi";
					break;
				}
				case 'ㅞ': {
					j.IPA = "we";
					break;
				}
				}
				firstletter = false;
			}
		}
	}

	public String toString() {
		String ret = "";
		for(Syllable s : syllables) {
			ret += s+".";
		}
		return ret.substring(0, ret.length()-1);
	}
}
