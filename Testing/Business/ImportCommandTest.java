package business;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

// ⚠️ IMPORTANT: Yahan apne actual project ka import statement lagao
// Example: import com.texteditor.business.ImportCommand;
import yourproject.business.ImportCommand;

class ImportCommandTest {
    
    private ImportCommand importCommand;
    
    @BeforeEach
    void setUp() {
        importCommand = new ImportCommand();
    }
    
    @Test
    @DisplayName("Import should succeed with valid file path")
    void testExecuteWithValidFile() {
        // Arrange: Valid file path
        String validPath = "test-data/sample.txt";
        
        // Act: Execute command
        boolean result = importCommand.execute(validPath);
        
        // Assert: Should return true
        assertTrue(result, "Import should succeed with valid file");
    }
    
    @Test
    @DisplayName("Import should fail with invalid file path")
    void testExecuteWithInvalidFile() {
        // Arrange: Invalid path
        String invalidPath = "nonexistent_file.txt";
        
        // Act
        boolean result = importCommand.execute(invalidPath);
        
        // Assert: Should return false
        assertFalse(result, "Import should fail with invalid file");
    }
    
    @Test
    @DisplayName("Import should handle null path gracefully")
    void testExecuteWithNullPath() {
        // Assert: Should not throw exception
        assertDoesNotThrow(() -> {
            importCommand.execute(null);
        }, "Should handle null path without crashing");
    }
    
    @Test
    @DisplayName("Import should handle empty path")
    void testExecuteWithEmptyPath() {
        String emptyPath = "";
        
        boolean result = importCommand.execute(emptyPath);
        
        assertFalse(result, "Import should fail with empty path");
    }
}