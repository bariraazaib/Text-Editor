package Business;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import dal.TFIDFCalculator;

class TFIDFTest {
    
    private TFIDFCalculator tfidfCalculator;
    
    @BeforeEach
    void setUp() {
        tfidfCalculator = new TFIDFCalculator();
    }
    
    // ========== POSITIVE PATH TESTS ==========
    
    @Test
    @DisplayName("TF-IDF should calculate correct score for known document")
    void testCalculateWithKnownDocument() {
        // Arrange: Sample document with known word frequencies
        String document = "apple orange apple banana orange apple";
        String word = "apple";
        
        // Act: Calculate TF-IDF score
        double actualScore = tfidfCalculator.calculateTFIDF(word, document);
        
        // Assert: Score should be positive and reasonable
        assertTrue(actualScore > 0, 
                   "TF-IDF score should be positive for existing word");
        assertTrue(actualScore <= 1.0, 
                   "TF-IDF score should not exceed 1.0");
    }
    
    @Test
    @DisplayName("TF-IDF should return higher score for more frequent words")
    void testHighFrequencyWord() {
        // Arrange
        String document = "test test test test other words here";
        String frequentWord = "test";
        String rareWord = "other";
        
        // Act
        double frequentScore = tfidfCalculator.calculateTFIDF(frequentWord, document);
        double rareScore = tfidfCalculator.calculateTFIDF(rareWord, document);
        
        // Assert: Frequent word should have higher score
        assertTrue(frequentScore > rareScore, 
                   "More frequent word should have higher TF-IDF score");
    }
    
    @Test
    @DisplayName("TF-IDF calculation should be consistent")
    void testCalculationConsistency() {
        // Arrange
        String document = "consistency test document for tfidf calculation";
        String word = "test";
        
        // Act: Calculate twice
        double score1 = tfidfCalculator.calculateTFIDF(word, document);
        double score2 = tfidfCalculator.calculateTFIDF(word, document);
        
        // Assert: Same input should give same output
        assertEquals(score1, score2, 0.0001, 
                     "TF-IDF calculation should be consistent");
    }
    
    // ========== NEGATIVE PATH TESTS ==========
    
    @Test
    @DisplayName("TF-IDF should handle empty document gracefully")
    void testCalculateWithEmptyDocument() {
        // Arrange
        String emptyDoc = "";
        String word = "test";
        
        // Assert: Should not throw exception
        assertDoesNotThrow(() -> {
            tfidfCalculator.calculateTFIDF(word, emptyDoc);
        }, "Should handle empty document without crashing");
    }
    
    @Test
    @DisplayName("TF-IDF should handle null document")
    void testCalculateWithNullDocument() {
        // Arrange
        String word = "test";
        
        // Assert: Should handle null gracefully
        assertDoesNotThrow(() -> {
            tfidfCalculator.calculateTFIDF(word, null);
        }, "Should handle null document without crashing");
    }
    
    @Test
    @DisplayName("TF-IDF should handle special characters in document")
    void testCalculateWithSpecialCharacters() {
        // Arrange
        String specialDoc = "@#$%^&*() !@# Special chars only";
        String word = "test";
        
        // Act
        double score = tfidfCalculator.calculateTFIDF(word, specialDoc);
        
        // Assert: Score should be 0 or very low
        assertTrue(score >= 0, "Score should not be negative");
    }
    
    @Test
    @DisplayName("TF-IDF should handle document with only whitespace")
    void testCalculateWithWhitespaceOnly() {
        // Arrange
        String whitespaceDoc = "     \t\n   ";
        String word = "test";
        
        // Assert: Should handle gracefully
        assertDoesNotThrow(() -> {
            tfidfCalculator.calculateTFIDF(word, whitespaceDoc);
        }, "Should handle whitespace-only document");
    }
    
    @Test
    @DisplayName("TF-IDF should return zero for word not in document")
    void testCalculateWordNotFound() {
        // Arrange
        String document = "apple orange banana grape";
        String missingWord = "mango";
        
        // Act
        double score = tfidfCalculator.calculateTFIDF(missingWord, document);
        
        // Assert: Score should be zero
        assertEquals(0.0, score, 0.01, 
                     "Score should be zero for word not present in document");
    }
    
    @Test
    @DisplayName("TF-IDF should handle empty word parameter")
    void testCalculateWithEmptyWord() {
        // Arrange
        String document = "test document content";
        String emptyWord = "";
        
        // Assert: Should handle gracefully
        assertDoesNotThrow(() -> {
            tfidfCalculator.calculateTFIDF(emptyWord, document);
        }, "Should handle empty word parameter");
    }
    
    @Test
    @DisplayName("TF-IDF should handle null word parameter")
    void testCalculateWithNullWord() {
        // Arrange
        String document = "test document content";
        
        // Assert: Should handle gracefully
        assertDoesNotThrow(() -> {
            tfidfCalculator.calculateTFIDF(null, document);
        }, "Should handle null word parameter");
    }
    
    @Test
    @DisplayName("TF-IDF should handle Arabic/Urdu text")
    void testCalculateWithArabicText() {
        // Arrange
        String arabicDoc = "مرحبا العالم مرحبا";
        String arabicWord = "مرحبا";
        
        // Assert: Should process multilingual text
        assertDoesNotThrow(() -> {
            double score = tfidfCalculator.calculateTFIDF(arabicWord, arabicDoc);
            assertTrue(score >= 0, "Should return valid score for Arabic text");
        }, "Should handle Arabic/Urdu text");
    }
}
