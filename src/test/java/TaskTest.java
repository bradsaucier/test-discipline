// Unit tests for Task validation and update controls (JUnit 5).

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskTest {

    private static Task buildValidTask() {
        return new Task("T123456789", "Alpha Task", "Initial task description");
    }

    @Test
    @DisplayName("R1 - taskId must not be null")
    void taskIdNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task(null, "Alpha Task", "Initial task description")
        );
    }

    @Test
    @DisplayName("R1 - taskId length must be <= 10")
    void taskIdTooLongThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task("12345678901", "Alpha Task", "Initial task description")
        );
    }

    @Test
    @DisplayName("R1 - taskId length 10 is accepted")
    void taskIdLengthTenAccepted() {
        assertDoesNotThrow(() ->
                new Task("1234567890", "Alpha Task", "Initial task description")
        );
    }

    @Test
    @DisplayName("R2 - name must not be null")
    void nameNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task("T1", null, "Initial task description")
        );
    }

    @Test
    @DisplayName("R2 - name length must be <= 20")
    void nameTooLongThrows() {
        String name21 = "123456789012345678901"; // 21 chars
        assertThrows(IllegalArgumentException.class, () ->
                new Task("T1", name21, "Initial task description")
        );
    }

    @Test
    @DisplayName("R2 - name length 20 is accepted")
    void nameLengthTwentyAccepted() {
        String name20 = "12345678901234567890"; // 20 chars
        assertDoesNotThrow(() ->
                new Task("T1", name20, "Initial task description")
        );
    }

    @Test
    @DisplayName("R3 - description must not be null")
    void descriptionNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task("T1", "Alpha Task", null)
        );
    }

    @Test
    @DisplayName("R3 - description length must be <= 50")
    void descriptionTooLongThrows() {
        String desc51 = "123456789012345678901234567890123456789012345678901"; // 51 chars
        assertThrows(IllegalArgumentException.class, () ->
                new Task("T1", "Alpha Task", desc51)
        );
    }

    @Test
    @DisplayName("R3 - description length 50 is accepted")
    void descriptionLengthFiftyAccepted() {
        String desc50 = "12345678901234567890123456789012345678901234567890"; // 50 chars
        assertDoesNotThrow(() ->
                new Task("T1", "Alpha Task", desc50)
        );
    }

    @Test
    @DisplayName("Setters accept valid updates and taskId stays stable")
    void settersUpdateFieldsAndIdStaysStable() {
        Task t = buildValidTask();
        String originalId = t.getTaskId();

        t.setName("Bravo Task");
        t.setDescription("Updated task description");

        assertAll(
                () -> assertEquals(originalId, t.getTaskId()),
                () -> assertEquals("Bravo Task", t.getName()),
                () -> assertEquals("Updated task description", t.getDescription())
        );
    }

    @Test
    @DisplayName("Setters reject invalid updates")
    void settersRejectInvalidValues() {
        Task t = buildValidTask();

        assertThrows(IllegalArgumentException.class, () -> t.setName(null));
        assertThrows(IllegalArgumentException.class, () -> t.setDescription(null));

        assertThrows(IllegalArgumentException.class, () ->
                t.setName("123456789012345678901")
        );

        assertThrows(IllegalArgumentException.class, () ->
                t.setDescription("123456789012345678901234567890123456789012345678901")
        );
    }
}

