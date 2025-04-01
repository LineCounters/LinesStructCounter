package mantenimiento.codecounter.models;

public class JavaClass {
  int methodsAmount;
  int linesOfCode;
  String className;

  public JavaClass(String className) {
    this.className = className;
    this.methodsAmount = 0;
    this.linesOfCode = 0;
  }

  /* Devolver el total de métodos */
  public int getMethodsAmount() {
    return methodsAmount;
  }

  /* Devolver el nombre de la clase */
  public String getClassName() {
    return className;
  }

  /* Incrementar el numero de metodos */
  public void incrementMethodsAmount() {
    this.methodsAmount++;
  }

  /* Incrementar el numero de lineas de código */
  public void incrementLinesOfCode() {
    this.linesOfCode++;
  }

  /* Devolver el total de lineas de código */
  public int getLinesOfCode() {
    return linesOfCode;
  }
}
