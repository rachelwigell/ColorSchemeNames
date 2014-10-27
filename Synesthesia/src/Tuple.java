
public class Tuple {
	int x;
	int y;
	
	public Tuple(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public float distance(Tuple from){
		return (float) Math.sqrt(Math.pow(from.x-this.x, 2) + Math.pow(from.y-this.y, 2));
	}
}
