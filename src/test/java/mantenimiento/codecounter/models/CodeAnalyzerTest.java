package mantenimiento.codecounter.models;

import static org.junit.jupiter.api.Assertions.*;

import mantenimiento.codecounter.models.counters.StructCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CodeAnalyzerTest {

  private StructCounter counter;
  private CodeAnalyzer codeAnalyzer;

  @BeforeEach
  void setUp() {
    counter = new StructCounter("Test.java");
    codeAnalyzer = new CodeAnalyzer(counter);
  }

  @Test
  @DisplayName("Debe incrementar el contador de clases al procesar una declaración de clase")
  void testProcessLine_TypeDeclaration() {
    String line = "class MyClass {";

    codeAnalyzer.processLine(line);

    assertEquals(1, counter.getClassCount());
    assertEquals(1, counter.getPhysicalLineCount());
  }

  @Test
  @DisplayName("Debe incrementar el contador de métodos al procesar una declaración de método")
  void testProcessLine_MethodDeclaration() {
    String line = "public void myMethod() {";

    codeAnalyzer.processLine(line);

    assertEquals(1, counter.getMethodsCount());
    assertEquals(1, counter.getPhysicalLineCount());
  }

  @Test
  @DisplayName("No debe incrementar contadores al procesar una llave de cierre")
  void testProcessLine_CloseBrace() {
    String line = "}";

    codeAnalyzer.processLine(line);

    assertEquals(0, counter.getClassCount());
    assertEquals(0, counter.getMethodsCount());
    assertEquals(0, counter.getPhysicalLineCount());
  }

  @Test
  @DisplayName("Debe incrementar el contador de líneas físicas dentro de una clase")
  void testProcessLine_PhysicalLineInsideClass() {
    codeAnalyzer.processLine("class MyClass {");
    codeAnalyzer.processLine("int x = 0;");

    assertEquals(1, counter.getClassCount());
    assertEquals(2, counter.getPhysicalLineCount());
  }

  @Test
  @DisplayName("Debe manejar múltiples clases y métodos correctamente")
  void testProcessLine_MultipleClassesAndMethods() {
    codeAnalyzer.processLine("class MyClass {");
    codeAnalyzer.processLine("public void myMethod() {");
    codeAnalyzer.processLine("int x = 0;");
    codeAnalyzer.processLine("}");
    codeAnalyzer.processLine("}");

    assertEquals(1, counter.getClassCount());
    assertEquals(1, counter.getMethodsCount());
    assertEquals(5, counter.getPhysicalLineCount());
  }

  @Test
  @DisplayName("Debe manejar múltiples clases correctamente")
  void testProcessLine_MultipleClasses() {
    codeAnalyzer.processLine("class MyClass {");
    codeAnalyzer.processLine("}");
    codeAnalyzer.processLine("class AnotherClass {");
    codeAnalyzer.processLine("}");

    assertEquals(2, counter.getClassCount());
    assertEquals(0, counter.getMethodsCount());
    assertEquals(4, counter.getPhysicalLineCount());
  }
}
