package com.crud.tasks.coverage;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbServiceMockTest {
    @InjectMocks
    private DbService dbService;
    @Mock
    private TaskRepository taskRepository;

    @Test
    void shouldGetTaskTest() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1l,"test 1","testing"));
        taskList.add(new Task(2L,"test2", "testing"));
        when(taskRepository.findAll()).thenReturn(taskList);
        //When
        List<Task> receivedTasks = dbService.getAllTasks();
        //Then
        Assertions.assertEquals(2, receivedTasks.size());
    }
}
