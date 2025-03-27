package mantenimiento.codecounter.models.counters;

/** Clase que representa un contador de estructuras : Clase y método */
public class StructCounter extends LineCounter {
  private int classAmount;
  private int methodsAmount;

  public StructCounter(String fileName) {
    super(fileName);
    this.classAmount = 0;
    this.methodsAmount = 0;
  }

  /** Incrementa el contador de clases en 1. */
  public void incrementClassesAmount() {
    this.classAmount++;
  }

  /** Incrementa el contador de métodos en 1. */
  public void incrementMethodsAmount() {
    this.methodsAmount++;
  }

  /**
   * Obtiene la cantidad de clases.
   *
   * @return Cantidad de clases.
   */
  public int getClassesAmount() {
    return this.classAmount;
  }

  /**
   * Obtiene la cantidad de métodos.
   *
   * @return Cantidad de métodos.
   */
  public int getMethodsAmount() {
    return this.methodsAmount;
  }
}
