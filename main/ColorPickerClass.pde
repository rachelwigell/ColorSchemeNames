public class ColorPicker {
  Tuple userChoicePosition;
  int colorChosen;
  int radius;
  
  public ColorPicker(int radius){
    this.radius = radius;
    this.colorChosen = color(0);
    this.userChoicePosition = new Tuple(radius, radius);
    
  }
  
  public void drawUserChoice(){
    stroke(255);
    noFill();
    ellipse(userChoicePosition.x, userChoicePosition.y, 10, 10);
  }
  
  public void moveUserChoice(int x, int y){
    Tuple clicked = new Tuple(x, y);
    if(this.radius > clicked.distance(new Tuple(this.radius, this.radius))){
      Tuple newPos = new Tuple(x, y);
      userChoicePosition = newPos;
      loadPixels();
      this.colorChosen = pixels[newPos.y * width + newPos.x];
    }
    drawColorPicker();
  }
  
  public void drawColorPicker(){
    background(255);
    int r = 255;
    int g = 0;
    int b = 0; 
    for(float i = 0; i < 2*Math.PI; i += .003){
      for(int j = 0; j < this.radius; j++){
        if(j > this.radius*.67){
          float rProp = r/(this.radius*.33);
          float gProp = g/(this.radius*.33);
          float bProp = b/(this.radius*.33);
          set(this.radius + (int) (j * Math.cos(i)), this.radius + (int) (j * Math.sin(i)), color((this.radius-j)*rProp, (this.radius-j)*gProp, (this.radius-j)*bProp));
        }
        else if(j > this.radius*.33){
          float rProp = (256-r)/(this.radius*.33);
          float gProp = (256-g)/(this.radius*.33);
          float bProp = (256-b)/(this.radius*.33);
          set(this.radius + (int) (j * Math.cos(i)), this.radius + (int) (j * Math.sin(i)), color(r+(this.radius*.67-j)*rProp, g+(this.radius*.67-j)*gProp, b+(this.radius*.67-j)*bProp));
        }
        else{
          float prop = 256.0f/(this.radius*.33);
          set(this.radius + (int) (j * Math.cos(i)), this.radius + (int) (j * Math.sin(i)), color(j*prop, j*prop, j*prop));
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
    drawUserChoice();
  }
}