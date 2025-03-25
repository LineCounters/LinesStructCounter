package mantenimiento.codecounter.validators;

import mantenimiento.codecounter.interfaces.FormatValidatorChain;
import mantenimiento.codecounter.interfaces.LogicalValidatorFactory;
import mantenimiento.codecounter.interfaces.LogicalValidatorFactory;
import mantenimiento.codecounter.validators.formatValidators.ImportValidator;
import mantenimiento.codecounter.validators.formatValidators.SingleAnnotationValidator;
import mantenimiento.codecounter.validators.formatValidators.SingleDeclarationValidator;
import mantenimiento.codecounter.validators.formatValidators.StyleKAndRValidator;
import mantenimiento.codecounter.validators.logicalValidators.MethodDeclarationValidator;
import mantenimiento.codecounter.validators.logicalValidators.TypeDeclarationValidator;

/** Brinda el acceso a los validadores de formato o de líneas lógicas */
public class ValidatorManager {
  private static FormatValidatorChain formatValidator = null;
  private static LogicalValidatorFactory logicalValidator = null;

  /**
   * Genera la secuencia de validaciones de formato
   *
   * @return Encadenamiento de validadores de format
   */
  public static FormatValidatorChain getFormatValidator() {

    if (formatValidator != null) {
      return formatValidator;
    }

    FormatValidatorChain importValidator = new ImportValidator();
    FormatValidatorChain styleKAndRValidator = new StyleKAndRValidator();
    FormatValidatorChain singleAnnotationValidator = new SingleAnnotationValidator();
    FormatValidatorChain singleDeclarationValidator = new SingleDeclarationValidator();

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
  public static LogicalValidatorFactory getLogicalValidator() {
    if (logicalValidator != null) {
      return logicalValidator;
    }


    LogicalValidatorFactory typeDeclarationValidator = new TypeDeclarationValidator();
    LogicalValidatorFactory methodDeclarationValidator = new MethodDeclarationValidator();
   

    typeDeclarationValidator.setNextValidator(methodDeclarationValidator);
   

    return logicalValidator;
  }
}
