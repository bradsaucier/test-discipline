// Component: Task
// Purpose: Enforce field integrity for an in-memory task record.
// Operating principle: fail fast at the boundary so invalid data does not propagate.

public final class Task {

    // Single source of truth for field constraints.
    private static final int TASK_ID_MAX_LENGTH = 10;
    private static final int NAME_MAX_LENGTH = 20;
    private static final int DESCRIPTION_MAX_LENGTH = 50;

    // Immutable after construction; used as the service lookup key.
    private final String taskId;

    // Mutable; see setters for validation rules.
    private String name;
    private String description;

    public Task(String taskId, String name, String description) {
        validate(taskId, "taskId", TASK_ID_MAX_LENGTH);
        validate(name, "name", NAME_MAX_LENGTH);
        validate(description, "description", DESCRIPTION_MAX_LENGTH);

        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validate(name, "name", NAME_MAX_LENGTH);
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        validate(description, "description", DESCRIPTION_MAX_LENGTH);
        this.description = description;
    }

    // Validates a required String field.
    // Gate check: not null, within max length.
    private static void validate(String value, String fieldName, int maxLength) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null");
        }

        if (value.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + " cannot exceed " + maxLength + " characters");
        }
    }
}
