import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class OutputWriter {
	InputInfo info;
	
	public OutputWriter(InputInfo info){
		this.info = info;
	}
	
	public void writeToHtml(){
		BufferedWriter writer = null;
		File html = new File("YourCustomizedText.html");		
		try {
			writer = new BufferedWriter(new FileWriter(html));
			writer.write(compileWritable());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String compileWritable(){
		String toWrite = "";
		String upper = info.toConvert.toUpperCase();
		for(int i = 0; i < info.toConvert.length(); i++){
			if(upper.charAt(i) == '\n'){
				toWrite += "<BR>";
			}
			else{
				String s = "" + upper.charAt(i);
				String color = info.values.get(s);
				if(color == null) color = "#000000";
				toWrite += generateHtmlString(info.toConvert.charAt(i), color);
			}
		}
		for(String s: info.values.keySet()){
			if(s.length() > 1){
				int i = toWrite.indexOf(matchWordWithHtml(s), 0);
				while(i != -1){
					toWrite = toWrite.substring(0, i) + generateHtmlString(s, info.values.get(s)) + toWrite.substring(i+matchWordWithHtml(s).length(), toWrite.length());
					i = toWrite.indexOf(matchWordWithHtml(s), i+1);
				}
			}
		}
		return toWrite;
	}
	
	public String generateHtmlString(char character, String color){
		return "<font color = " + color + ">" + character + "</font>";
	}
	
	public String generateHtmlString(String word, String color){
		return "<font color = " + color + ">" + word + "</font>";
	}
	
	public String matchWordWithHtml(String word){
		String wordWithHtml = "";
		String upper = word.toUpperCase();
		for(int i = 0; i < word.length(); i++){
			String s = "" + upper.charAt(i);
			wordWithHtml += generateHtmlString(word.charAt(i), info.values.get(s));
		}
		return wordWithHtml;
	}
}
