package mantenimiento.codecounter.validators;

import mantenimiento.codecounter.interfaces.FormatValidatorHandler;
import mantenimiento.codecounter.interfaces.LogicalValidatorHandler;
import mantenimiento.codecounter.validators.logicalValidators.MethodDeclarationValidator;
import mantenimiento.codecounter.validators.logicalValidators.TypeDeclarationValidator;

/** Brinda el acceso a los validadores de formato o de líneas lógicas */
public class ValidatorManager {
  private static FormatValidatorHandler formatValidator = null;
  private static LogicalValidatorHandler logicalValidator = null;

  /**
   * Genera la secuencia de validaciones de formato
   *
   * @return Encadenamiento de validadores de format
   */
  public static FormatValidatorHandler getFormatValidator() {

    if (formatValidator != null) {
      return formatValidator;
    }

    FormatValidatorHandler importValidator = new ImportValidator();
    FormatValidatorHandler styleKAndRValidator = new StyleKAndRValidator();
    FormatValidatorHandler singleAnnotationValidator = new SingleAnnotationValidator();
    FormatValidatorHandler singleDeclarationValidator = new SingleDeclarationValidator();

    importValidator.setNextValidator(styleKAndRValidator);
    styleKAndRValidator.setNextValidator(singleAnnotationValidator);
    singleAnnotationValidator.setNextValidator(singleDeclarationValidator);
    formatValidator = importValidator;

    return formatValidator;
  }

  /**
   * Genera la secuencia de validaciones de líneas lógicas
   *
   * @return Encadenamiento de validadores de líneas lógicas
   */
  public static LogicalValidatorHandler getLogicalValidator() {
    if (logicalValidator != null) {
      return logicalValidator;
    }

    LogicalValidatorHandler typeDeclarationValidator = new TypeDeclarationValidator();
    LogicalValidatorHandler methodDeclarationValidator = new MethodDeclarationValidator();

    typeDeclarationValidator.setNextValidator(methodDeclarationValidator);
    methodDeclarationValidator.setNextValidator(typeDeclarationValidator);
    logicalValidator = typeDeclarationValidator;

    return logicalValidator;
  }
}
