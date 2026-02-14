package Business;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import dal.Transliteration;

class TransliterationTest {
    
    private Transliteration transliteration;
    
    @BeforeEach
    void setUp() {
        transliteration = new Transliteration();
    }
    
    @Test
    @DisplayName("Transliteration should process Arabic text correctly")
    void testTransliterateArabicText() {
        // Arrange
        String arabicText = "مرحبا";
        
        // Act
        String result = transliteration.transliterate(arabicText);
        
        // Assert
        assertNotNull(result, "Result should not be null");
        assertFalse(result.isEmpty(), "Result should not be empty");
    }
    
    @Test
    @DisplayName("Transliteration should process Urdu text correctly")
    void testTransliterateUrduText() {
        // Arrange
        String urduText = "السلام علیکم";
        
        // Act
        String result = transliteration.transliterate(urduText);
        
        // Assert
        assertNotNull(result, "Result should not be null for Urdu text");
    }
    
    @Test
    @DisplayName("Transliteration should handle English text")
    void testTransliterateEnglishText() {
        // Arrange
        String englishText = "Hello World";
        
        // Act
        String result = transliteration.transliterate(englishText);
        
        // Assert
        assertNotNull(result, "Should handle English text");
    }
    
    @Test
    @DisplayName("Transliteration should handle empty text gracefully")
    void testTransliterateEmptyText() {
        // Arrange
        String emptyText = "";
        
        // Assert: Should not crash
        assertDoesNotThrow(() -> {
            transliteration.transliterate(emptyText);
        }, "Should handle empty text without error");
    }
    
    @Test
    @DisplayName("Transliteration should handle null input gracefully")
    void testTransliterateNullInput() {
        // Assert: Should not crash
        assertDoesNotThrow(() -> {
            transliteration.transliterate(null);
        }, "Should handle null input without crashing");
    }
    
    @Test
    @DisplayName("Transliteration should handle special characters")
    void testTransliterateSpecialCharacters() {
        // Arrange
        String specialChars = "@#$%^&*()_+{}|:<>?";
        
        // Act
        String result = transliteration.transliterate(specialChars);
        
        // Assert
        assertNotNull(result, "Should handle special characters");
    }
    
    @Test
    @DisplayName("Transliteration should handle mixed language text")
    void testTransliterateMixedText() {
        // Arrange
        String mixedText = "Hello مرحبا World";
        
        // Act
        String result = transliteration.transliterate(mixedText);
        
        // Assert
        assertNotNull(result, "Should handle mixed language text");
        assertFalse(result.isEmpty(), "Result should not be empty");
    }
    
    @Test
    @DisplayName("Transliteration should handle numbers")
    void testTransliterateNumbers() {
        // Arrange
        String numbersText = "12345 67890";
        
        // Assert: Should process without error
        assertDoesNotThrow(() -> {
            String result = transliteration.transliterate(numbersText);
            assertNotNull(result, "Should handle numbers");
        }, "Should handle numeric text");
    }
    
    @Test
    @DisplayName("Transliteration should handle whitespace-only text")
    void testTransliterateWhitespaceOnly() {
        // Arrange
        String whitespace = "     \t\n   ";
        
        // Assert: Should handle gracefully
        assertDoesNotThrow(() -> {
            transliteration.transliterate(whitespace);
        }, "Should handle whitespace-only text");
    }
    
    @Test
    @DisplayName("Transliteration result should not be null for valid input")
    void testResultNotNullForValidInput() {
        // Arrange
        String validText = "Test text for transliteration";
        
        // Act
        String result = transliteration.transliterate(validText);
        
        // Assert
        assertNotNull(result, "Result should never be null for valid input");
    }
}