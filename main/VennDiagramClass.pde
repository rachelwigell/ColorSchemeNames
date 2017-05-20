public class VennDiagram {
  ArrayList colors;
  int numCircles;
  int canvasSize;
  int circleSize;
  int angle;
  
  public VennDiagram(choiceQueue, int canvasSize){
    this.colors = new ArrayList();
    for(int i = 0; i < choiceQueue.length; i++){
      String hex = choiceQueue[i].substring(1);
      this.colors.add(color(unhex(hex)));
    }
    this.numCircles = this.colors.size();
    this.canvasSize = canvasSize;
    this.circleSize = canvasSize/numCircles;
    this.angle = 0;
  }
  
  public void drawVennDiagram(){
    background(255);
    noStroke();
    for(int i = 0; i < numCircles; i++){
      double j = i * (2*Math.PI/numCircles) + this.angle;
      int x = (int) (this.circleSize/3 * Math.cos(j)) + canvasSize/2;
      int y = (int) (this.circleSize/3 * Math.sin(j)) + canvasSize/2;
      int color = colors.get(i);
      fill(red(color), green(color), blue(color), 120);
      ellipse(x,y,circleSize,circleSize);
    }
  }
}