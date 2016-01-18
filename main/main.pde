Tuple userChoicePosition = new Tuple(175, 175);
int colorChosen = color(0);

public void setup(){
  size(350, 350, P2D);
  frameRate(10);
  drawColorPicker();
}

public void draw(){
}

public String intToHex(int colorval){
  return "#" + hex(colorval, 6);
}

public void drawUserChoice(){
  stroke(255);
  noFill();
  ellipse(userChoicePosition.x, userChoicePosition.y, 10, 10);
}

public void moveUserChoice(int x, int y){
  Tuple clicked = new Tuple(x, y);
  if(148 > clicked.distance(new Tuple(200, 200))){
    Tuple newPos = new Tuple(x, y);
    userChoicePosition = newPos;
    loadPixels();
    colorChosen = pixels[newPos.y * width + newPos.x];
    update_grapheme_color(intToHex(colorChosen));
  }
  drawColorPicker();
}

public void mouseDragged(){
  moveUserChoice(mouseX, mouseY);
  drawColorPicker();
}

public void mouseClicked(){
  moveUserChoice(mouseX, mouseY);
  drawColorPicker();
}

public void drawColorPicker(){
  background(255);
  int r = 255;
  int g = 0;
  int b = 0; 
  for(float i = 0; i < 2*Math.PI; i += .003){
    for(int j = 0; j < 150; j++){
      if(j > 100){
        float rProp = r/50.0f;
        float gProp = g/50.0f;
        float bProp = b/50.0f;
        set(175 + (int) (j * Math.cos(i)), 175 + (int) (j * Math.sin(i)), color((150-j)*rProp, (150-j)*gProp, (150-j)*bProp));
      }
      else if(j > 50){
        float rProp = (256-r)/50.0f;
        float gProp = (256-g)/50.0f;
        float bProp = (256-b)/50.0f;
        set(175 + (int) (j * Math.cos(i)), 175 + (int) (j * Math.sin(i)), color(r+(100-j)*rProp, g+(100-j)*gProp, b+(100-j)*bProp));
      }
      else{
        float prop = 256.0f/50.0f;
        set(175 + (int) (j * Math.cos(i)), 175 + (int) (j * Math.sin(i)), color(j*prop, j*prop, j*prop));
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