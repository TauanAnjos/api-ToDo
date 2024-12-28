package com.ToDo_backend.Projeto.ToDo.services;

import com.ToDo_backend.Projeto.ToDo.exception.BusinessRuleException;
import com.ToDo_backend.Projeto.ToDo.models.TaskModel;
import com.ToDo_backend.Projeto.ToDo.models.UserModel;
import com.ToDo_backend.Projeto.ToDo.repositories.TaskRepository;
import com.ToDo_backend.Projeto.ToDo.repositories.UserRepository;
import com.ToDo_backend.Projeto.ToDo.rest.dtos.TaskDTORequest;
import com.ToDo_backend.Projeto.ToDo.rest.dtos.TaskDTOResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private  UserRepository userRepository;

    @Transactional
    public TaskDTOResponse createTask(UUID userId, TaskDTORequest taskDTORequest){
        UserModel userExists = userRepository.findById(userId).orElseThrow(() ->
                new BusinessRuleException("Usuário não encontrado."));
        TaskModel taskModel = new TaskModel(taskDTORequest.title(), taskDTORequest.description(), userExists);
        taskRepository.saveAndFlush(taskModel);
        return taskModel.toDTO();
    }
    @Transactional(readOnly = true)
    public List<TaskDTOResponse> getAllTasks(UUID userId){

        List<TaskModel> listTasks = taskRepository.findAllByUserId(userId).orElseThrow(()-> new BusinessRuleException("Tarefas não encontradas"));
        return listTasks.stream().map(task -> task.toDTO()).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TaskDTOResponse taskFindById(UUID taskId){
        TaskModel taskExist = taskRepository.findById(taskId).orElseThrow(()->
                new BusinessRuleException("ID de tarefa não encontrado."));
        return taskExist.toDTO();
    }

    @Transactional
    public TaskDTOResponse updateTask(UUID taskId, TaskModel taskModel){
        TaskModel taskExist = taskRepository.findById(taskId).orElseThrow(()->
                new BusinessRuleException("ID de tarefa não encontrado."));
        if (taskModel.getTitle() != null && !taskModel.getTitle().isEmpty()){
            taskExist.setTitle(taskModel.getTitle());
        }else {
            throw new BusinessRuleException("O titulo precisa ser preenchido.");
        }
        if(taskModel.getDescription() != null && !taskModel.getDescription().isEmpty()){
            taskExist.setDescription(taskModel.getDescription());
        }else {
            throw new BusinessRuleException("Preencha a descrição.");
        }
        taskRepository.save(taskExist);
        return taskExist.toDTO();
    }

    @Transactional
    public void deleteTask(UUID taskId){
        if(!taskRepository.existsById(taskId)){
            throw new BusinessRuleException("ID de tarefa não encontrado.");
        }
        taskRepository.deleteById(taskId);
    }
}
