package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import categories.InvalidPartition;
import categories.ValidPartition;

public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController controller; 

    @Before
    public void setUp() {
        //should see all mocks and inject on controller
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    @Category(InvalidPartition.class)
    public void dontSaveTaskWithoutDescription () {
        Task todoTask = new Task(); 
        todoTask.setDueDate(LocalDate.now());

        try {
            controller.save(todoTask);
            Assert.fail("Don't should be here");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    @Category(InvalidPartition.class)
    public void dontSaveWithEmptySpaceInDescription () {
        Task todoTask = new Task();
        todoTask.setDueDate(LocalDate.now());
        todoTask.setTask("");
        
        try {
            controller.save(todoTask);
            Assert.fail("Don't should be here");
        } catch (ValidationException e) {
            Assert.assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    @Category(InvalidPartition.class)
    public void dontSaveTaskWithoutDate() {
        Task todoTask = new Task();
        todoTask.setTask("With description, without date");

        try {
            controller.save(todoTask);
            Assert.fail("Don't should be here");
        } catch (ValidationException e ) {
            Assert.assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    @Category(InvalidPartition.class)
    public void dontSaveTaskWithPastDate() {
        Task todoTask = new Task();
        todoTask.setDueDate(LocalDate.of(1999,02,01));
        todoTask.setTask("With past date and description");

        try {
            controller.save(todoTask);  
            Assert.fail("Don't should be here");  
        } catch (ValidationException e) {
            Assert.assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    @Category(ValidPartition.class)
    public void saveTaskWithValidValues () throws ValidationException {
        Task todoTask = new Task();
        todoTask.setDueDate(LocalDate.now());
        todoTask.setTask("Valid Task");

        controller.save(todoTask);

        //Verify if the mock was saved equal todoTask, verify if the controller.save was really invoke
        Mockito.verify(taskRepo).save(todoTask);
    }

}
