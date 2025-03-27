package mantenimiento.codecounter.models.counters;

/** Clase que representa un contador de lineas fisicas */
public class LineCounter {
  private int physicalLineCount;
  private String fileName;

  public LineCounter(String fileName) {
    this.fileName = fileName;
    this.physicalLineCount = 0;
  }

  public String getFileName() {
    return this.fileName;
  }

  public int getPhysicalLineCount() {
    return physicalLineCount;
  }

  public void setPhysicalLineCount(int physicalLineCount) {
    this.physicalLineCount = physicalLineCount;
  }
}
