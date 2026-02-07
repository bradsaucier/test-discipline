// Component: TaskService
// Purpose: Add, delete, and update tasks in memory.
// Strategy: Map keyed by taskId for deterministic lookups and enforced uniqueness.
// Operating principle: guard the collection, keep failures explicit and early.

import java.util.HashMap;
import java.util.Map;

public final class TaskService {

    private final Map<String, Task> tasks = new HashMap<>();

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("task must not be null");
        }

        String id = task.getTaskId();
        if (tasks.containsKey(id)) {
            throw new IllegalArgumentException("taskId already exists: " + id);
        }

        tasks.put(id, task);
    }

    public void deleteTask(String taskId) {
        requireNonNullId(taskId);

        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("taskId not found: " + taskId);
        }

        tasks.remove(taskId);
    }

    public void updateTask(String taskId, String name, String description) {
        requireNonNullId(taskId);

        Task target = tasks.get(taskId);
        if (target == null) {
            throw new IllegalArgumentException("taskId not found: " + taskId);
        }

        // Null values are skipped; non-null values are validated and applied.
        if (name != null) {
            target.setName(name);
        }
        if (description != null) {
            target.setDescription(description);
        }
    }

    // Lookup helper: returns the task for the given id, or null if not found.
    // Actions (delete, update) fail fast on unknown ids to keep the service contract explicit.
    public Task getTask(String taskId) {
        requireNonNullId(taskId);
        return tasks.get(taskId);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    private static void requireNonNullId(String taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("taskId must not be null");
        }
    }
}
