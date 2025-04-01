package mantenimiento.codecounter.models;

import mantenimiento.codecounter.exceptions.InvalidFormatException;
import mantenimiento.codecounter.models.counters.StructCounter;
import mantenimiento.codecounter.templates.LogicalValidator;
import mantenimiento.codecounter.validators.ValidatorManager;
import mantenimiento.codecounter.validators.logical_validators.MethodDeclarationValidator;
import mantenimiento.codecounter.validators.logical_validators.TypeDeclarationValidator;

/** Clase encargada de procesar las lineas, contando líneas de código físicas, métodos y clases. */
public class CodeAnalyzer {
  private final StructCounter counter;
  private int openBraces = 0;
  private String currentClass = null;

  public CodeAnalyzer(StructCounter counter) {
    this.counter = counter;
  }

  /**
   * Procesa una linea de código, contando líneas de código físicas, métodos y clases.
   *
   * @param line Linea de código a procesar.
   * @throws InvalidFormatException
   */
  public void processLine(String line) throws InvalidFormatException {
    String trimmedLine = line.trim();
    LogicalValidator validator = ValidatorManager.getLogicalValidators(trimmedLine);

    applyValidation(validator, trimmedLine);
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
    if (line.contains("{")) {
      openBraces++;
    }
    if (line.contains("}")) {
      openBraces--;
      if (openBraces == 0) {
        currentClass = null;
      }
    }
  }

  /**
   * Aplica la validación correspondiente a la linea de código.
   *
   * @param validator Validador lógico a aplicar.
   * @throws InvalidFormatException
   */
  private void applyValidation(LogicalValidator validator, String line)
      throws InvalidFormatException {
    if (validator instanceof TypeDeclarationValidator) {
      validateTypeDeclaration(line);
    } else if (validator instanceof MethodDeclarationValidator && currentClass != null) {
      counter.addMethodToLastClass();
    }
  }

  /**
   * Valida la declaración de una clase, asegurándose de que no haya clases anidadas.
   *
   * @param line Linea de código a validar.
   * @throws InvalidFormatException
   */
  private void validateTypeDeclaration(String line) throws InvalidFormatException {
    TypeDeclarationValidator typeValidator =
        (TypeDeclarationValidator) ValidatorManager.getLogicalValidators(line);
    String className = typeValidator.getTypeName(line);

    if (className != null) {
      if (counter.getClassesCount() > 0) {
        throw new InvalidFormatException("No puede existir una clase anidada dentro de otra clase");
      } else {
        currentClass = className;
        counter.addClass(currentClass);
      }
    }
  }

  /** Incrementa el contador de líneas físicas de clase y método. */
  private void countPhysicalLines() {
    counter.addLineToLastClass();
  }
}
