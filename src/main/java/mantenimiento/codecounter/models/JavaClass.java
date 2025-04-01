package mantenimiento.codecounter.models;

public class JavaClass {
  int methodsAmount;
  int linesOfCode;
  String className;

  public JavaClass(String className) {
    this.className = className;
    this.methodsAmount = 0;
    this.linesOfCode = 0; // Asegúrate de inicializar correctamente
  }

  public int getMethodsAmount() {
    return methodsAmount;
  }

  public String getClassName() {
    return className;
  }

  public void incrementMethodsAmount() {
    this.methodsAmount++;
  }

  public void incrementLinesOfCode() {
    this.linesOfCode++;
  }

  public int getLinesOfCode() {
    return linesOfCode;
  }
}
