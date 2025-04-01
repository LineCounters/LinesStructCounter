package mantenimiento.codecounter.models.counters;

import java.util.ArrayList;
import java.util.List;
import mantenimiento.codecounter.models.JavaClass;

/** Clase que representa un contador de estructuras: Clase y método */
public class StructCounter {
  private final List<JavaClass> javaClasses;

  public StructCounter(String fileName) {
    this.javaClasses = new ArrayList<>();
  }

  /** Añadir una nueva clase */
  public void addClass(String className) {
    javaClasses.add(new JavaClass(className));
  }

  /** Añadir un método a la última clase registrada */
  public void addMethodToLastClass() {
    if (!javaClasses.isEmpty()) {
      javaClasses.get(javaClasses.size() - 1).incrementMethodsAmount();
    }
  }

  /** Añadir una línea física a la última clase registrada */
  public void addLineToLastClass() {
    if (!javaClasses.isEmpty()) {
      javaClasses.get(javaClasses.size() - 1).incrementLinesOfCode();
    }
  }

  /** Añadir una línea lógica a la última clase registrada */
  public int getLinesOfCode() {
    int totalLines = 0;
    for (JavaClass javaClass : javaClasses) {
      totalLines += javaClass.getLinesOfCode();
    }
    return totalLines;
  }

  /** Devolver el total de clases */
  public int getClassesCount() {
    return javaClasses.size();
  }

  /** Devolver el total de métodos */
  public int getMethodsCount() {
    int totalMethods = 0;
    for (JavaClass javaClass : javaClasses) {
      totalMethods += javaClass.getMethodsAmount();
    }
    return totalMethods;
  }

  public void getClasses() {
    for (JavaClass javaClass : javaClasses) {
      System.out.println("Class: " + javaClass.getClassName());
      System.out.println("Methods: " + javaClass.getMethodsAmount());
      System.out.println("Lines of Code: " + javaClass.getLinesOfCode());
      System.out.println("-------------------------");
    }
  }
}
