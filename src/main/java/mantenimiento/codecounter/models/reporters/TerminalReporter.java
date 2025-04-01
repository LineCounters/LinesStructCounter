package mantenimiento.codecounter.models.reporters;

import java.nio.file.Path;
import java.util.List;
import mantenimiento.codecounter.models.counters.StructCounter;

/** Clase que genera un reporte en la terminal */
public class TerminalReporter extends Reporter {

  public TerminalReporter(Path filePath, List<StructCounter> lineCounter) {
    super(filePath, lineCounter);
  }

  /** Genera un reporte con la información de los contadores de líneas */
  @Override
  public void generateReport() {
    printBody();
  }

  /**
   * Imprime el cuerpo del reporte incluyendo la información de los contadores de líneas por archivo
   * analizado
   */
  private void printBody() {
    for (StructCounter lineCounter : lineCounters) {
      lineCounter.getClasses();
    }
    int totalPhysicalLines = lineCounters.stream().mapToInt(StructCounter::getLinesOfCode).sum();
    System.out.println("Total de líneas físicas: " + totalPhysicalLines);
  }
}
