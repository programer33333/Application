package com.crud.tasks.coverage;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DbServiceTestSuite {
    @Autowired
    DbService dbService;

    @Test
    void saveTaskTest() throws TaskNotFoundException {
        //Given
        Task taskToSave = new Task(null, "test", "test_content");
        //When
        dbService.saveTask(taskToSave);
        //Then
        Long taskId = taskToSave.getId();
        Assertions.assertNotNull(taskId);
        //CleanUp
        dbService.deleteTask(taskId);
    }

    @Test
    void getTaskTest() throws TaskNotFoundException {
        //Given
        Task task = new Task(null, "test", "test_content");
        dbService.saveTask(task);
        Long taskId = task.getId();
        //When
        Task receivedTask = dbService.findById(taskId);
        //Then
        Assertions.assertEquals(taskId, receivedTask.getId());
        Assertions.assertEquals("test", receivedTask.getTitle());
        Assertions.assertEquals("test_content", receivedTask.getContent());
        //CleanUp
        dbService.deleteTask(taskId);
    }
}
