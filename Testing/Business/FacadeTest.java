package Business;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import bll.FacadeBO;

class FacadeTest {
    
    private FacadeBO facade;
    
    @BeforeEach
    void setUp() {
        facade = new FacadeBO();
    }
    
    // ========== IMPORT COMMAND TESTS ==========
    
    @Test
    @DisplayName("Import should succeed with valid file path")
    void testImportWithValidFile() {
        // Arrange
        String validPath = "test-data/sample.txt";
        
        // Act
        boolean result = facade.importFile(validPath);
        
        // Assert
        assertTrue(result, "Import should succeed with valid file path");
    }
    
    @Test
    @DisplayName("Import should fail with invalid file path")
    void testImportWithInvalidFile() {
        // Arrange
        String invalidPath = "nonexistent_file.txt";
        
        // Act
        boolean result = facade.importFile(invalidPath);
        
        // Assert
        assertFalse(result, "Import should fail with invalid file path");
    }
    
    @Test
    @DisplayName("Import should handle null path gracefully")
    void testImportWithNullPath() {
        // Assert: Should not crash
        assertDoesNotThrow(() -> {
            facade.importFile(null);
        }, "Should handle null path without crashing");
    }
    
    @Test
    @DisplayName("Import should handle empty path")
    void testImportWithEmptyPath() {
        // Arrange
        String emptyPath = "";
        
        // Act
        boolean result = facade.importFile(emptyPath);
        
        // Assert
        assertFalse(result, "Import should fail with empty path");
    }
    
    // ========== EXPORT COMMAND TESTS ==========
    
    @Test
    @DisplayName("Export should succeed with valid path and content")
    void testExportWithValidData() {
        // Arrange
        String validPath = "output/test.txt";
        String content = "Sample content for export";
        
        // Act
        boolean result = facade.exportFile(validPath, content);
        
        // Assert
        assertTrue(result, "Export should succeed with valid data");
    }
    
    @Test
    @DisplayName("Export should fail with invalid path")
    void testExportWithInvalidPath() {
        // Arrange
        String invalidPath = "/invalid/restricted/path.txt";
        String content = "Test content";
        
        // Act
        boolean result = facade.exportFile(invalidPath, content);
        
        // Assert
        assertFalse(result, "Export should fail with invalid path");
    }
    
    @Test
    @DisplayName("Export should handle null content gracefully")
    void testExportWithNullContent() {
        // Arrange
        String validPath = "output/test.txt";
        
        // Assert: Should not crash
        assertDoesNotThrow(() -> {
            facade.exportFile(validPath, null);
        }, "Should handle null content gracefully");
    }
    
    @Test
    @DisplayName("Export should handle empty content")
    void testExportWithEmptyContent() {
        // Arrange
        String validPath = "output/test.txt";
        String emptyContent = "";
        
        // Act
        boolean result = facade.exportFile(validPath, emptyContent);
        
        // Assert
        assertTrue(result, "Export should succeed even with empty content");
    }
    
    // ========== SEARCH AND REPLACE TESTS ==========
    
    @Test
    @DisplayName("Search should find existing word in document")
    void testSearchExistingWord() {
        // Arrange
        String document = "This is a test document with test word";
        String searchWord = "test";
        
        // Act
        boolean found = facade.searchWord(searchWord, document);
        
        // Assert
        assertTrue(found, "Should find existing word in document");
    }
    
    @Test
    @DisplayName("Search should not find non-existing word")
    void testSearchNonExistingWord() {
        // Arrange
        String document = "This is a sample document";
        String searchWord = "missing";
        
        // Act
        boolean found = facade.searchWord(searchWord, document);
        
        // Assert
        assertFalse(found, "Should not find word that doesn't exist");
    }
    
    @Test
    @DisplayName("Replace should successfully replace word")
    void testReplaceWord() {
        // Arrange
        String originalText = "Hello world, hello universe";
        String oldWord = "hello";
        String newWord = "hi";
        
        // Act
        String result = facade.replaceWord(originalText, oldWord, newWord);
        
        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.contains(newWord), "Result should contain new word");
    }
    
    @Test
    @DisplayName("Replace should handle word not found")
    void testReplaceWordNotFound() {
        // Arrange
        String originalText = "Sample text here";
        String oldWord = "missing";
        String newWord = "replacement";
        
        // Act
        String result = facade.replaceWord(originalText, oldWord, newWord);
        
        // Assert
        assertEquals(originalText, result, 
                     "Text should remain unchanged if word not found");
    }
    
    @Test
    @DisplayName("Facade should handle null document in search")
    void testSearchWithNullDocument() {
        // Assert: Should handle gracefully
        assertDoesNotThrow(() -> {
            facade.searchWord("test", null);
        }, "Should handle null document in search");
    }
    
    @Test
    @DisplayName("Facade should handle empty search word")
    void testSearchWithEmptyWord() {
        // Arrange
        String document = "Sample document";
        String emptyWord = "";
        
        // Assert: Should handle gracefully
        assertDoesNotThrow(() -> {
            facade.searchWord(emptyWord, document);
        }, "Should handle empty search word");
    }
}
