package com.juan.tasky.api.service;

import com.juan.tasky.api.exceptions.InvalidTaskName;
import com.juan.tasky.api.exceptions.TaskAlreadyExists;
import com.juan.tasky.api.model.Task;
import com.juan.tasky.api.repository.TaskRepository;
import com.juan.tasky.api.exceptions.TaskNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task save(Task task) {
        // verifica se o nome é nulo ou com apenas espaços em branco
        if (task.getName() == null || task.getName().trim().isEmpty()) {
            throw new InvalidTaskName("Nome inválido");
        }
        // verifica se já existe
        if (taskRepository.existsByName(task.getName())) {
            throw new TaskAlreadyExists("Já existe uma tarefa com esse nome.");
        }
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll(); // retorna todas as entidades do banco
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
            // lança exceção se a entidade Task não existe no banco.
            .orElseThrow(() -> new TaskNotFound("A tarefa não existe."));
    }

    public void deleteById(Long id) {
        // similar a validação de existência por id mas com condicional, pois a função
        // retorna VOID
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFound("A tarefa não existe.");
        }
        taskRepository.deleteById(id);
    }

    public Task update(Long id, String name) {
        Task existingTask = findById(id);

        // Verifica se o nome está sendo alterado para um diferente
        if (!existingTask.getName().equals(name) && taskRepository.existsByName(name)) {
            throw new TaskAlreadyExists("Já existe uma tarefa com esse nome.");
        }
        existingTask.setName(name);
        return taskRepository.save(existingTask);
    }
}
