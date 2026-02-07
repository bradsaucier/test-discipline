// Unit tests for TaskService add, delete, and update behavior (JUnit 5).

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {

    private static final String TASK_ID = "T1";

    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService();
    }

    private static Task makeTask() {
        return new Task(TASK_ID, "Alpha Task", "Initial task description");
    }

    @Test
    @DisplayName("S1 - addTask stores a task by unique id")
    void addTaskStoresTask() {
        service.addTask(makeTask());

        assertEquals(1, service.getTaskCount());
        Task stored = service.getTask(TASK_ID);
        assertNotNull(stored);
        assertEquals(TASK_ID, stored.getTaskId());
    }

    @Test
    @DisplayName("S1 - addTask rejects null task")
    void addNullTaskThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                service.addTask(null)
        );
    }

    @Test
    @DisplayName("S1 - adding a duplicate id is rejected")
    void addTaskDuplicateIdThrows() {
        service.addTask(makeTask());

        assertThrows(IllegalArgumentException.class, () ->
                service.addTask(makeTask())
        );
    }

    @Test
    @DisplayName("S2 - deleteTask removes an existing task")
    void deleteTaskRemovesTask() {
        service.addTask(makeTask());
        service.deleteTask(TASK_ID);

        assertEquals(0, service.getTaskCount());
        assertNull(service.getTask(TASK_ID));
    }

    @Test
    @DisplayName("S2 - deleteTask on unknown id is rejected")
    void deleteUnknownIdThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                service.deleteTask("NOPE")
        );
    }

    @Test
    @DisplayName("S2 - deleteTask rejects null id")
    void deleteNullIdThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                service.deleteTask(null)
        );
    }

    @Test
    @DisplayName("S3 - updateTask changes updatable fields and preserves id")
    void updateTaskUpdatesFields() {
        service.addTask(makeTask());

        service.updateTask(TASK_ID, "Bravo Task", "Updated task description");

        Task updated = service.getTask(TASK_ID);
        assertNotNull(updated);

        assertAll(
                () -> assertEquals(TASK_ID, updated.getTaskId()),
                () -> assertEquals("Bravo Task", updated.getName()),
                () -> assertEquals("Updated task description", updated.getDescription())
        );
    }

    @Test
    @DisplayName("S3 - updateTask supports partial updates (null means no change)")
    void updateTaskPartialUpdateKeepsOtherFields() {
        service.addTask(makeTask());

        service.updateTask(TASK_ID, "Bravo Task", null);

        Task after = service.getTask(TASK_ID);
        assertNotNull(after);

        assertAll(
                () -> assertEquals(TASK_ID, after.getTaskId()),
                () -> assertEquals("Bravo Task", after.getName()),
                () -> assertEquals("Initial task description", after.getDescription())
        );
    }

    @Test
    @DisplayName("S3 - partial update with null name keeps name unchanged")
    void updateTaskPartialUpdateDescriptionOnly() {
        service.addTask(makeTask());

        service.updateTask(TASK_ID, null, "Updated description only");

        Task after = service.getTask(TASK_ID);
        assertNotNull(after);

        assertAll(
                () -> assertEquals(TASK_ID, after.getTaskId()),
                () -> assertEquals("Alpha Task", after.getName()),
                () -> assertEquals("Updated description only", after.getDescription())
        );
    }

    @Test
    @DisplayName("S3 - update with both fields null is a no-op")
    void updateTaskNoOpWhenBothNull() {
        service.addTask(makeTask());

        service.updateTask(TASK_ID, null, null);

        Task after = service.getTask(TASK_ID);
        assertNotNull(after);

        assertAll(
                () -> assertEquals(TASK_ID, after.getTaskId()),
                () -> assertEquals("Alpha Task", after.getName()),
                () -> assertEquals("Initial task description", after.getDescription())
        );
    }

    @Test
    @DisplayName("S3 - updateTask on unknown id is rejected")
    void updateUnknownIdThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                service.updateTask("NOPE", "Bravo Task", null)
        );
    }

    @Test
    @DisplayName("S3 - updateTask rejects null id")
    void updateNullIdThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                service.updateTask(null, "Bravo Task", "Updated")
        );
    }

    @Test
    @DisplayName("S3 - invalid field value is rejected")
    void invalidFieldValueThrows() {
        service.addTask(makeTask());

        assertThrows(IllegalArgumentException.class, () ->
                service.updateTask(TASK_ID, "123456789012345678901", null)
        );
    }

    @Test
    @DisplayName("Helper - getTask returns null for unknown id")
    void getTaskReturnsNullForUnknownId() {
        assertNull(service.getTask("UNKNOWN"));
    }
}
