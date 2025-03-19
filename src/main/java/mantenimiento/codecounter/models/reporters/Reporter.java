package mantenimiento.codecounter.models.reporters;

import java.nio.file.Path;
import java.util.List;
import mantenimiento.codecounter.models.LineCounter;

/** Clase abstracta que define el comportamiento de un generador de reportes */
public abstract class Reporter {
  protected String programName;
  protected List<LineCounter> lineCounters;

  public Reporter(Path filePath, List<LineCounter> lineCounters) {
    this.programName = filePath.getFileName().toString();
    this.lineCounters = lineCounters;
  }

  /** Genera un reporte con la información de los contadores de líneas */
  public abstract void generateReport();
}
