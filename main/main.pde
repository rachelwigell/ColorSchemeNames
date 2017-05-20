ColorPicker colorPicker;
VennDiagram vennDiagram;

public void setup(){
  colorPicker = new ColorPicker(150);
  size(colorPicker.radius*2+25, colorPicker.radius*2+25, P2D);
  frameRate(10);
  colorPicker.drawColorPicker();
}

public void draw(){
  if(vennDiagram != null){
    vennDiagram.drawVennDiagram();
    vennDiagram.angle++;
    renderThinkingText();
  }
}

public String getCurrentColor(){
  return intToHex(colorPicker.colorChosen);
}

public String intToHex(int colorval){
  return "#" + hex(colorval, 6);
}

public void mouseDragged(){
  updateColorSelection();
}

public void mouseClicked(){
  updateColorSelection();
}

public void updateColorSelection(){
  if(vennDiagram == null){
    colorPicker.moveUserChoice(mouseX, mouseY);
    colorPicker.drawColorPicker();
    update_button_color(getCurrentColor());
  }
}

public void createVennDiagram(colors){
  vennDiagram = new VennDiagram(colors, colorPicker.radius*2);
  //vennDiagram.drawVennDiagram();
}

public void renderThinkingText(){
  fill(0);
  text("Thinking...", colorPicker.radius-25, colorPicker.radius);
}