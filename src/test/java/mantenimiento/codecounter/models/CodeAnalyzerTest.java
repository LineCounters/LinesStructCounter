package mantenimiento.codecounter.models;

import static org.junit.jupiter.api.Assertions.*;

import mantenimiento.codecounter.exceptions.InvalidFormatException;
import mantenimiento.codecounter.models.counters.StructCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CodeAnalyzerTest {

  private StructCounter counter;
  private CodeAnalyzer codeAnalyzer;

  @BeforeEach
  void setUp() {
    counter = new StructCounter("filename.java");
    codeAnalyzer = new CodeAnalyzer(counter);
  }

  @Test
  @DisplayName("Debe procesar una declaración de clase válida")
  void testProcessLine_ValidTypeDeclaration() throws InvalidFormatException {
    String line = "class TestClass {";

    codeAnalyzer.processLine(line);

    assertEquals(1, counter.getClassesCount());
  }

  @Test
  @DisplayName("Debe lanzar excepción al procesar una clase anidada")
  void testProcessLine_NestedClassThrowsException() {
    String line1 = "class OuterClass {";
    String line2 = "class NestedClass {";

    assertDoesNotThrow(() -> codeAnalyzer.processLine(line1));
    InvalidFormatException exception =
        assertThrows(InvalidFormatException.class, () -> codeAnalyzer.processLine(line2));
    assertEquals("No puede existir una clase anidada dentro de otra clase", exception.getMessage());
  }

  @Test
  @DisplayName("Debe procesar una declaración de método válida dentro de una clase")
  void testProcessLine_ValidMethodDeclaration() throws InvalidFormatException {
    String classLine = "class TestClass {";
    String methodLine = "public void testMethod() {";

    codeAnalyzer.processLine(classLine);
    codeAnalyzer.processLine(methodLine);

    assertEquals(1, counter.getClassesCount());
  }

  @Test
  @DisplayName("Debe incrementar el contador de líneas físicas")
  void testProcessLine_PhysicalLineCount() throws InvalidFormatException {
    String line1 = "class TestClass {";
    String line2 = "public void testMethod() {";
    String line3 = "System.out.println(\"Hello World\");";

    codeAnalyzer.processLine(line1);
    codeAnalyzer.processLine(line2);
    codeAnalyzer.processLine(line3);

    assertEquals(3, counter.getLinesOfCode());
  }

  @Test
  @DisplayName("Debe manejar correctamente los métodos")
  void testProcessLine_MethodHandling() throws InvalidFormatException {
    String classLine = "class TestClass {";
    String methodLine = "public void testMethod() {";

    codeAnalyzer.processLine(classLine);
    codeAnalyzer.processLine(methodLine);

    assertEquals(1, counter.getClassesCount());
    assertEquals(1, counter.getMethodsCount());
  }
}
