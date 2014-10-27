import java.util.HashMap;

public class Button {
	String caption;
	Tuple position;
	Tuple dimensions;
	
	public Button(String caption, Tuple position, Tuple dimensions){
		this.caption = caption;
		this.position = position;
		this.dimensions = dimensions;
	}
	
	public Button(String caption, Tuple position){
		this.caption = caption;
		float width = 15*spaceOfWord(caption);
		this.position = new Tuple((int) (position.x - width/2), position.y);
		this.dimensions = new Tuple((int) width, 50);
	}
	
	public static float spaceOfWord(String word){
		HashMap<Character, Float> widths = letterWidths();
		float space = 0;
		for(int i = 0; i < word.length(); i++){
			space += widths.get(word.charAt(i));
		}
		return space;
	}
	
	public static HashMap<Character, Float> letterWidths(){
		HashMap<Character, Float> widths = new HashMap<Character, Float>();
		widths.put('a', 1.5f);
		widths.put('b', 1.5f);
		widths.put('c', 1.5f);
		widths.put('d', 1.5f);
		widths.put('e', 1.5f);
		widths.put('f', 1f);
		widths.put('g', 1.5f);
		widths.put('h', 1.5f);
		widths.put('i', 1f);
		widths.put('j', 1f);
		widths.put('k', 1.5f);
		widths.put('l', 1f);
		widths.put('m', 2f);
		widths.put('n', 1.5f);
		widths.put('o', 1.5f);
		widths.put('p', 1.5f);
		widths.put('q', 1.5f);
		widths.put('r', 1.5f);
		widths.put('s', 1.5f);
		widths.put('t', 1f);
		widths.put('u', 1.5f);
		widths.put('v', 1.5f);
		widths.put('w', 2f);
		widths.put('x', 1.5f);
		widths.put('y', 1.5f);
		widths.put('z', 1.5f);
		widths.put('A', 2.5f);
		widths.put('B', 2.5f);
		widths.put('C', 2.5f);
		widths.put('D', 2.5f);
		widths.put('E', 2.5f);
		widths.put('F', 2.5f);
		widths.put('G', 2.5f);
		widths.put('H', 2.5f);
		widths.put('I', 1.5f);
		widths.put('J', 2f);
		widths.put('K', 2.5f);
		widths.put('L', 2.5f);
		widths.put('M', 2.5f);
		widths.put('N', 2.5f);
		widths.put('O', 2.5f);
		widths.put('P', 2.5f);
		widths.put('Q', 2.5f);
		widths.put('R', 2.5f);
		widths.put('S', 2.5f);
		widths.put('T', 2.5f);
		widths.put('U', 2.5f);
		widths.put('V', 2.5f);
		widths.put('W', 2.5f);
		widths.put('X', 2.5f);
		widths.put('Y', 2.5f);
		widths.put('Z', 2.5f);
		widths.put('0', 1.5f);
		widths.put('1', 1f);
		widths.put('2', 1.5f);
		widths.put('3', 1.5f);
		widths.put('4', 1.5f);
		widths.put('5', 1.5f);
		widths.put('6', 1.5f);
		widths.put('7', 1.5f);
		widths.put('8', 1.5f);
		widths.put('9', 1.5f);
		widths.put(' ', 1f);
		return widths;
	}
}
