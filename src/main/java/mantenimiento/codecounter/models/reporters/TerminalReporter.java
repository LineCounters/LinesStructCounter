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
    printHeader();
    printBody();
  }

  /**
   * Imprime el encabezado del reporte incluyengo: el nombre del programa y los nombres de las
   * columnas
   */
  private void printHeader() {
    System.out.println(
        "----------------------------------------------------------------------------");
    System.out.println("Programa: " + this.programName);
    System.out.println(
        "----------------------------------------------------------------------------");
    System.out.printf(" %-40s  %-15s  %-15s \n", "Archivo", "Líneas Físicas", "Líneas Lógicas");
    System.out.println(
        "----------------------------------------------------------------------------");
  }

  /**
   * Imprime el cuerpo del reporte incluyendo la información de los contadores de líneas por archivo
   * analizado
   */
  private void printBody() {
    for (StructCounter lineCounter : this.lineCounters) {
      System.out.printf(" %-40s  %-15d  %-15d \n", lineCounter.getFileName());
    }
    System.out.println(
        "----------------------------------------------------------------------------");
  }
}
