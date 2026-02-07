# Module 4 - Task Service Requirements Trace

## Requirements - Task

| ID | Field | Constraints |
|---|---|---|
| R1 | taskId | not null, <= 10 chars, not updatable |
| R2 | name | not null, <= 20 chars |
| R3 | description | not null, <= 50 chars |

## Requirements - TaskService

| ID | Capability | Requirement |
|---|---|---|
| S1 | add | add tasks with unique taskId |
| S2 | delete | delete tasks by taskId |
| S3 | update | update name, description by taskId |

Test-support helpers (not rubric requirements)
- H1: getTask(taskId) returns stored Task or null if not present
- H2: getTaskCount() returns current in-memory count

## Trace to tests

| Requirement | Test coverage |
|---|---|
| R1 | TaskTest.taskIdNullThrows, TaskTest.taskIdTooLongThrows, TaskTest.taskIdLengthTenAccepted |
| R2 | TaskTest.nameNullThrows, TaskTest.nameTooLongThrows, TaskTest.nameLengthTwentyAccepted |
| R3 | TaskTest.descriptionNullThrows, TaskTest.descriptionTooLongThrows, TaskTest.descriptionLengthFiftyAccepted |
| S1 | TaskServiceTest.addTaskStoresTask, TaskServiceTest.addNullTaskThrows, TaskServiceTest.addTaskDuplicateIdThrows |
| S2 | TaskServiceTest.deleteTaskRemovesTask, TaskServiceTest.deleteUnknownIdThrows, TaskServiceTest.deleteNullIdThrows |
| S3 | TaskServiceTest.updateTaskUpdatesFields, TaskServiceTest.updateTaskPartialUpdateKeepsOtherFields, TaskServiceTest.updateTaskPartialUpdateDescriptionOnly, TaskServiceTest.updateTaskNoOpWhenBothNull, TaskServiceTest.updateUnknownIdThrows, TaskServiceTest.updateNullIdThrows, TaskServiceTest.invalidFieldValueThrows |
| H1 | TaskServiceTest.getTaskReturnsNullForUnknownId |
