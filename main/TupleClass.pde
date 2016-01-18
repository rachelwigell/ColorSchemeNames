public class Tuple {
  int x;
  int y;
  
  public Tuple(int x, int y){
    this.x = x;
    this.y = y;
  }
  
  public float distance(Tuple from){
    return (float) sqrt(pow(from.x-this.x, 2) + pow(from.y-this.y, 2));
  }
}