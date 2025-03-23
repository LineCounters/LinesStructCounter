package mantenimiento.codecounter.validators.logicalValidators;

import java.util.ArrayList;
import java.util.List;
import mantenimiento.codecounter.interfaces.LogicalValidatorHandler;
import mantenimiento.codecounter.utils.TypeVerifier;

/**
 * Proporciona una implementación base para la validación lógica de código. Implementa la interfaz
 * {@link LogicalValidatorHandler} y permite encadenar validadores.
 */
public abstract class LogicalValidator implements LogicalValidatorHandler {
  private LogicalValidatorHandler nextValidator;

  /**
   * Establece el siguiente validador en la cadena de validación lógica.
   *
   * @param nextLogicalValidator Siguiente validador lógico en la cadena.
   */
  @Override
  public void setNextValidator(LogicalValidatorHandler nextLogicalValidator) {
    this.nextValidator = nextLogicalValidator;
  }

  /**
   * Valida la siguiente regla en la cadena de responsabilidad.
   *
   * @param lineOfFile Líneas de código por validar.
   * @return {@code true} si la validación es exitosa, {@code false} en caso contrario.
   */
  protected boolean validateNext(String lineOfFile) {
    if (nextValidator != null) {
      return nextValidator.isValid(lineOfFile);
    }

    return false;
  }

  /**
   * Obtiene los tipos de validación que se deben aplicar a una línea de código.
   *
   * @param line Línea de código a validar.
   * @return Lista de tipos de validación que se deben aplicar a la línea.
   */
  @Override
  public List<TypeVerifier> getValidationTypes(String line) {
    List<TypeVerifier> validationTypes = new ArrayList<>();

    if (validate(line)) {
      validationTypes.add(getValidationType());
    }

    if (nextValidator != null) {
      validationTypes.addAll(nextValidator.getValidationTypes(line));
    }

    return validationTypes;
  }

  /**
   * Valida una línea de código.
   *
   * @param line Línea de código a validar.
   * @return {@code true} si la línea es válida, {@code false} en caso contrario.
   */
  protected abstract boolean validate(String line);

  /**
   * Obtiene el tipo de validación que se debe aplicar a una línea de código.
   *
   * @return Tipo de validación que se debe aplicar a la línea.
   */
  protected abstract TypeVerifier getValidationType();
}
