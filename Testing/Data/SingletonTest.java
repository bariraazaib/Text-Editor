package Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import dal.DatabaseConnection;

class SingletonTest {
    
    @Test
    @DisplayName("getInstance should always return the same instance")
    void testSingletonReturnsSameInstance() {
        // Arrange & Act: Get two instances
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();
        
        // Assert: Both should be exactly the same object
        assertSame(instance1, instance2, 
                   "getInstance() should return the same instance every time");
    }
    
    @Test
    @DisplayName("Singleton instance should not be null")
    void testSingletonInstanceNotNull() {
        // Act
        DatabaseConnection instance = DatabaseConnection.getInstance();
        
        // Assert
        assertNotNull(instance, 
                      "Singleton instance should never be null");
    }
    
    @Test
    @DisplayName("Multiple calls should maintain singleton property")
    void testMultipleCallsMaintainSingleton() {
        // Act: Call getInstance multiple times
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        DatabaseConnection db3 = DatabaseConnection.getInstance();
        
        // Assert: All should be same
        assertSame(db1, db2, "First and second instance should be same");
        assertSame(db2, db3, "Second and third instance should be same");
        assertSame(db1, db3, "First and third instance should be same");
    }
    
    @Test
    @DisplayName("Singleton should have only one instance in memory")
    void testOnlyOneInstanceExists() {
        DatabaseConnection instance = DatabaseConnection.getInstance();
        
        // Get hash codes (unique memory addresses)
        int hashCode1 = System.identityHashCode(instance);
        int hashCode2 = System.identityHashCode(DatabaseConnection.getInstance());
        
        assertEquals(hashCode1, hashCode2, 
                     "Both instances should have same memory address");
    }
}
