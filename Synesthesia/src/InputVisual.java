import g4p_controls.GTextArea;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PFont;

public class InputVisual extends PApplet{
	InputInfo info = new InputInfo();
	Tuple userChoicePosition = new Tuple(440, 230);
	int colorChosen = color(0);
	String valueConsidering = "";
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<String> toAsk = new ArrayList<String>();
	Mode mode = Mode.TEXTTOCONVERT;
	GTextArea textInput;
	HashMap<Integer, PFont> fonts = new HashMap<Integer, PFont>();
	
	public void setup(){
		size(600, 450, P2D);
		changeMode(Mode.TEXTTOCONVERT);
		fonts.put(24, createFont("Arial", 24));
		fonts.put(120, createFont("Arial", 120));
		fonts.put(14, createFont("Arial", 14));
		fonts.put(32, createFont("Arial", 32));
	}
	
	public void draw(){
		background(255);
		drawNeccesary();
	}
	
	public void changeMode(Mode to){
		this.mode = to;
		switch(to){
		case TEXTTOCONVERT:
			textInput = new GTextArea(this, 10, 120, 580, 240, GTextArea.SCROLLBAR_VERTICAL);
			addButton(new Tuple(300, 375), "Done");
			break;
		case CHARACTERS:
			userChoicePosition = new Tuple(440, 230);
			colorChosen = color(0);
			for(int i = 65; i < 91; i++){
				String add = "" + (char) i;
				toAsk.add(add);
			}
			for(int i = 0; i < 10; i++){
				String add = "" + i;
				toAsk.add(add);
			}
			valueConsidering = toAsk.get(0);
			buttons = new ArrayList<Button>();
			addButton(new Tuple(120, 340), "Next");
			break;
		case ASKFORMORE:
			buttons = new ArrayList<Button>();
			if(info.values.get("Sunday") == null){
				addButton(new Tuple(300, 130), "Days of the week");
			}
			if(info.values.get("January") == null){
				addButton(new Tuple(300, 200), "Months");
			}
			addButton(new Tuple(300, 270), "Other");
			addButton(new Tuple(300, 340), "Done");
			break;
		case STRINGS:
			userChoicePosition = new Tuple(440, 230);
			colorChosen = color(0);
			valueConsidering = toAsk.get(0);
			buttons = new ArrayList<Button>();
			addButton(new Tuple(120, 340), "Next");
			break;
		case CUSTOM:
			userChoicePosition = new Tuple(440, 230);
			colorChosen = color(0);
			valueConsidering = "";
			buttons = new ArrayList<Button>();
			addButton(new Tuple(80, 340), "Next");
			addButton(new Tuple(200, 340), "Done");
			break;
		case ENDSCREEN:
			OutputWriter out = new OutputWriter(this.info);
			out.writeToHtml();
			break;
		}
	}
	
	public void drawNeccesary(){
		switch(this.mode){
		case TEXTTOCONVERT:
			drawInstructions(new Tuple(300, 50), "Welcome to Silly Synestheisa App!",
					new Tuple(10, 65), "This app takes in text of your choice and information about your"
							+ "synesthesia characteristics and outputs an HTML file with your "
							+ "text converted to the \"right\" colors. Enter your text below.");
			drawButtons();
			break;
		case CHARACTERS:
			drawColorCircle();
			drawUserChoice();
			drawExample();
			drawInstructions(new Tuple(-150, 50), "What color is...");
			drawButtons();
			break;
		case ASKFORMORE:
			drawButtons();
			drawInstructions(new Tuple(10, 50), "Do any of these have colors for you?");
			break;
		case STRINGS:
			drawColorCircle();
			drawUserChoice();
			drawExample();
			drawInstructions(new Tuple(-150, 50), "What color is...");
			drawButtons();
			break;
		case CUSTOM:
			drawColorCircle();
			drawUserChoice();
			drawExample();
			drawInstructions(new Tuple(10, 20), "Type the word or character for which you want to specify a color.");
			drawButtons();
			break;
		case ENDSCREEN:
			drawInstructions(new Tuple(10, 100), "Your text has been converted! Look in the directory you"
					+ " ran this program from and find\nYourCustomizedText.HTML");
			break;
		}
	}
	
	public void drawInstructions(Tuple position, String heading){
		fill(0);
		textFont(fonts.get(24));
		textAlign(CENTER);
		text(heading, position.x, position.y, 580, 450);
	}
	
	public void drawInstructions(Tuple position, String heading, Tuple subPosition, String subHeading){
		fill(0);
		textFont(fonts.get(24));
		textAlign(CENTER);
		text(heading, position.x, position.y);
		textFont(fonts.get(14));
		text(subHeading, subPosition.x, subPosition.y, 580, 450);
	}
	
	public void drawExample(){
		fill(colorChosen);
		int fontSize = 120;
		Tuple position = new Tuple(80, 150);
		if(valueConsidering.length() != 1){
			fontSize = 32;
			position.y = 200;
		}
		textFont(fonts.get(fontSize));
		textAlign(LEFT);
		text(valueConsidering, position.x, position.y, 200, 150);
	}
	
	public Button addButton(Tuple position, String caption){
		Button button = new Button(caption, position);
		buttons.add(button);
		return button;
	}
	
	public void removeButton(String withCaption){
		ArrayList<Button> toRemove = new ArrayList<Button>();
		for(Button b: buttons){
			if(b.caption.equals(withCaption)){
				toRemove.add(b);
			}
		}
		for(Button b: toRemove){
			buttons.remove(b);
		}
	}
	
	public void keyReleased(){
		if(mode == Mode.CUSTOM){
			if(keyCode == BACKSPACE && valueConsidering.length() > 0){
				valueConsidering = valueConsidering.substring(0, valueConsidering.length()-1);
			}
			else{
				valueConsidering += key;
			}
		}
	}
	
	public Button detectClick(){
		Button clicked = null;
		for(Button b: buttons){
			if(mouseX > b.position.x && mouseX < b.position.x+b.dimensions.x &&
					mouseY > b.position.y && mouseY < b.position.y+b.dimensions.y){
				clicked = b;
			}
		}
		return clicked;
	}
	
	public void handleClick(){
		Button clicked = detectClick();
		if(clicked == null) return;
		switch(mode){
		case STRINGS:
		case CHARACTERS:
			if(toAsk.size() > 0){
				info.addToData(valueConsidering, intToHex(colorChosen));
				valueConsidering = toAsk.get(0);
				toAsk.remove(0);
			}
			else{
				info.addToData(valueConsidering, intToHex(colorChosen));
				changeMode(Mode.ASKFORMORE);
			}
			break;
		case TEXTTOCONVERT:
			info.toConvert = textInput.getText();
			textInput.dispose();
			changeMode(Mode.CHARACTERS);
			break;
		case ASKFORMORE:
			if(clicked.caption.equals("Days of the week")){
				toAsk.add("Sunday");
				toAsk.add("Monday");
				toAsk.add("Tuesday");
				toAsk.add("Wednesday");
				toAsk.add("Thursday");
				toAsk.add("Friday");
				toAsk.add("Saturday");
				changeMode(Mode.STRINGS);
			}
			else if(clicked.caption.equals("Months")){
				toAsk.add("January");
				toAsk.add("February");
				toAsk.add("March");
				toAsk.add("April");
				toAsk.add("May");
				toAsk.add("June");
				toAsk.add("July");
				toAsk.add("August");
				toAsk.add("September");
				toAsk.add("October");
				toAsk.add("November");
				toAsk.add("December");
				changeMode(Mode.STRINGS);
			}
			else if(clicked.caption.equals("Other")){
				changeMode(Mode.CUSTOM);
			}
			else if(clicked.caption.equals("Done")){
				changeMode(Mode.ENDSCREEN);
			}
			break;
		case CUSTOM:
			if(clicked.caption.equals("Next")){
				info.addToData(valueConsidering, intToHex(colorChosen));
				valueConsidering = "";
			}
			else if(clicked.caption.equals("Done")){
				info.addToData(valueConsidering, intToHex(colorChosen));
				changeMode(Mode.ASKFORMORE);
			}
		}
	}
	
	public String intToHex(int color){
		int r = (int) red(color);
		int g = (int) green(color);
		int b = (int) blue(color);
		String hex = "#";
		String add = Integer.toHexString(r);
		if(add.length() < 2){
			add = "0" + add;
		}
		hex += add;
		add = Integer.toHexString(g);
		if(add.length() < 2){
			add = "0" + add;
		}
		hex += add;
		add = Integer.toHexString(b);
		if(add.length() < 2){
			add = "0" + add;
		}
		hex += add;
		return hex;
	}
	
	public void drawButtons(){
		noStroke();
		for(Button b: buttons){
			fill(78, 81, 216);
			rect(b.position.x, b.position.y, b.dimensions.x, b.dimensions.y, 10, 10, 10, 10);
			fill(255);
			textFont(fonts.get(32));
			textAlign(CENTER);
			text(b.caption, b.position.x+b.dimensions.x/2, (int) (b.position.y+36));
		}
	}
	
	public void drawUserChoice(){
		stroke(255);
		noFill();
		ellipse(userChoicePosition.x,userChoicePosition.y, 10, 10);
	}
	
	public void moveUserChoice(){
		Tuple clicked = new Tuple(mouseX, mouseY);
		if(148 > clicked.distance(new Tuple(440, 230))){
			Tuple newPos = new Tuple(mouseX, mouseY);
			userChoicePosition = newPos;
			loadPixels();
			colorChosen = pixels[newPos.y * width + newPos.x];
		}
	}
	
	public void mouseDragged(){
		moveUserChoice();
	}
	
	public void mouseClicked(){
		moveUserChoice();
		handleClick();
	}
	
	public void drawColorCircle(){
		int r = 255;
		int g = 0;
		int b = 0; 
		for(float i = 0; i < 2*Math.PI; i += .003){
			for(int j = 0; j < 150; j++){
				if(j > 100){
					float rProp = r/50.0f;
					float gProp = g/50.0f;
					float bProp = b/50.0f;
					set(440 + (int) (j * Math.cos(i)), 230 + (int) (j * Math.sin(i)), color((150-j)*rProp, (150-j)*gProp, (150-j)*bProp));
				}
				else if(j > 50){
					float rProp = (256-r)/50.0f;
					float gProp = (256-g)/50.0f;
					float bProp = (256-b)/50.0f;
					set(440 + (int) (j * Math.cos(i)), 230 + (int) (j * Math.sin(i)), color(r+(100-j)*rProp, g+(100-j)*gProp, b+(100-j)*bProp));
				}
				else{
					float prop = 256.0f/50.0f;
					set(440 + (int) (j * Math.cos(i)), 230 + (int) (j * Math.sin(i)), color(j*prop, j*prop, j*prop));
				}
			}
			if(i < Math.PI/3){
				g++;
			}
			else if(i < 2*Math.PI/3){
				r--;
			}
			else if(i < Math.PI){
				b++;
			}
			else if(i < 4*Math.PI/3){
				g--;
			}
			else if(i < 5*Math.PI/3){
				r++;
			}
			else if(i < 2*Math.PI){
				b--;
			}
		}
	}

	public static void main(String args[]){
		PApplet.main(new String[] {"InputVisual"});
	} 

}