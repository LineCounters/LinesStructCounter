package mantenimiento.codecounter.validators.logicalValidators;

import static mantenimiento.codecounter.constants.JavaRegextConstants.ACCESS_MODIFIERS_REGEX;
import static mantenimiento.codecounter.constants.JavaRegextConstants.TYPE_KEYS;

import mantenimiento.codecounter.utils.TypeVerifier;

/**
 * Clase que verifica si existe una declaración de tipo (class, interface, enum) para poder
 * contabilizarlo como línea lógica
 */
public class TypeDeclarationValidator extends LogicalValidator {

  private static final String TYPE_DECLARATION =
      "^(\\s*" + ACCESS_MODIFIERS_REGEX + ".*\\s*" + TYPE_KEYS + ".*)";

  /**
   * Determina si una línea de código corresponde a una declaración de tipo para considerarla como
   * línea lógica
   *
   * @param linesOfCode
   * @return {@code true} si la corresponde a una declaración de tipo, {@code false} en caso
   *     contrario
   */
  @Override
  public boolean isValid(String linesOfCode) {
    return isTypeDeclaration(linesOfCode) || this.validateNext(linesOfCode);
  }

  /**
   * Verifica si la sentencia es una declaración de tipo
   *
   * @param lineOfCode sentencia de codigo por analizar
   * @return {@code true} si coincide con la declaracion, {@code false} en caso contrario
   */
  private boolean isTypeDeclaration(String line) {
    return line.matches(TYPE_DECLARATION);
  }

  /**
   * Obtiene el tipo de validación que se debe aplicar a una línea de código
   *
   * @return tipo de validación
   */
  @Override
  protected TypeVerifier getValidationType() {
    return TypeVerifier.CLASS_DECLARATION;
  }

  /**
   * Validación de la línea, devuelve si la línea corresponde a una declaración de tipo.
   *
   * @param line La línea de código a validar.
   * @return {@code true} si la línea corresponde a una declaración de tipo, {@code false} en caso
   *     contrario.
   */
  @Override
  protected boolean validate(String line) {
    return isTypeDeclaration(line);
  }
}
