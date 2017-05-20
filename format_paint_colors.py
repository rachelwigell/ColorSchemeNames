with open('SherwinWilliamsPaintColorNames.txt', 'r') as f:
  formatted = open('formatted.txt', 'w')
  for line in f:
    elements = line.split(' ')
    elements.pop(0)
    blue = int(elements.pop().strip())
    green = int(elements.pop())
    red = int(elements.pop())
    name = ' '.join(elements)
    formatted.write("{'name': \"" + name + "\", 'red': " + str(red) + ", 'green': " + str(green) + ", 'blue': " + str(blue) + "},\n");
  formatted.close()
  f.close();
