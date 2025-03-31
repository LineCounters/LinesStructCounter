package mantenimiento.codecounter.models;

public class JavaClass {
  int methodsAmount;
  String className;

  public JavaClass(String className) {
    this.className = className;
    this.methodsAmount = 0;
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
}
