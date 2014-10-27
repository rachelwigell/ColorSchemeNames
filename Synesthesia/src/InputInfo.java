import java.util.HashMap;


public class InputInfo {
	String toConvert;
	HashMap<String, String> values;
	
	public InputInfo(){
		this.values = new HashMap<String, String>();
	}
	
	public void addToData(String key, String value){
		this.values.put(key,value);
	}
}
