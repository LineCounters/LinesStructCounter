package mantenimiento.codecounter.models;

import mantenimiento.codecounter.models.counters.StructCounter;
import mantenimiento.codecounter.templates.LogicalValidator;
import mantenimiento.codecounter.validators.ValidatorManager;
import mantenimiento.codecounter.validators.logicalValidators.MethodDeclarationValidator;
import mantenimiento.codecounter.validators.logicalValidators.TypeDeclarationValidator;

/** Clase encargada de procesar las lineas, contando líneas de código físicas, métodos y clases. */
public class CodeAnalyzer {
  private final StructCounter counter;
  private int openBraces = 0;
  private boolean insideClass = false;

  public CodeAnalyzer(StructCounter counter) {
    this.counter = counter;
  }

  /**
   * Procesa una linea de código, contando líneas de código físicas, métodos y clases.
   *
   * @param line Linea de código a procesar.
   */
  public void processLine(String line) {
    String trimmedLine = line.trim();
    LogicalValidator validator = ValidatorManager.getLogicalValidators(trimmedLine);

    applyValidation(validator);
    countPhysicalLines();
    updateBraces(trimmedLine);
  }

  /**
   * Actualiza el contador de llaves abiertas y cerradas, y verifica si se encuentra dentro de una
   * clase o método.
   *
   * @param line Linea de código a procesar.
   */
  private void updateBraces(String line) {

    if (line.equals("}")) {
      openBraces--;
      if (openBraces == 0) {
        insideClass = false;
      }
    }
  }

  /**
   * Aplica la validación correspondiente a la linea de código.
   *
   * @param validator Validador lógico a aplicar.
   */
  private void applyValidation(LogicalValidator validator) {
    if (validator instanceof TypeDeclarationValidator) {
      counter.incrementClassCount();
      insideClass = true;
    } else if (validator instanceof MethodDeclarationValidator) {
      counter.incrementMethodCount();
      insideClass = true;
    }
  }

  /** Incrementa el contador de líneas físicas de clase y método. */
  private void countPhysicalLines() {
    if (insideClass) counter.incrementPhysicalLineCount();
  }
}
