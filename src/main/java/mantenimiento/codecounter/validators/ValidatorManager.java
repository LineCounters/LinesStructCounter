package mantenimiento.codecounter.validators;

import java.util.List;
import mantenimiento.codecounter.templates.FormatValidator;
import mantenimiento.codecounter.templates.LogicalValidator;
import mantenimiento.codecounter.validators.formatValidators.ImportValidator;
import mantenimiento.codecounter.validators.formatValidators.SingleAnnotationValidator;
import mantenimiento.codecounter.validators.formatValidators.SingleDeclarationValidator;
import mantenimiento.codecounter.validators.formatValidators.StyleKAndRValidator;
import mantenimiento.codecounter.validators.logicalValidators.MethodDeclarationValidator;
import mantenimiento.codecounter.validators.logicalValidators.TypeDeclarationValidator;

/** Brinda el acceso a los validadores de formato o de líneas lógicas */
public class ValidatorManager {
  private static FormatValidator formatValidator = null;
  private static LogicalValidator logicalValidator = null;

  /**
   * Genera la secuencia de validaciones de formato
   *
   * @return Encadenamiento de validadores de format
   */
  public static FormatValidator getFormatValidator() {

    if (formatValidator != null) {
      return formatValidator;
    }

    FormatValidator importValidator = new ImportValidator();
    FormatValidator styleKAndRValidator = new StyleKAndRValidator();
    FormatValidator singleAnnotationValidator = new SingleAnnotationValidator();
    FormatValidator singleDeclarationValidator = new SingleDeclarationValidator();

    importValidator.setNextValidator(styleKAndRValidator);
    styleKAndRValidator.setNextValidator(singleAnnotationValidator);
    singleAnnotationValidator.setNextValidator(singleDeclarationValidator);
    formatValidator = importValidator;

    return formatValidator;
  }

  /**
   * Genera la secuencia de validaciones de líneas lógicas
   *
   * @return Lista de validadores lógicos
   */
  public static LogicalValidator getLogicalValidators(String lineOfCode) {

    final List<LogicalValidator> logicalValidators =
        List.of(new MethodDeclarationValidator(), new TypeDeclarationValidator());
    if (lineOfCode == null || lineOfCode.isBlank()) {
      return null;
    }
    return logicalValidators.stream()
        .filter(e -> e.validateType(lineOfCode))
        .findFirst()
        .orElse(null);
  }
}
