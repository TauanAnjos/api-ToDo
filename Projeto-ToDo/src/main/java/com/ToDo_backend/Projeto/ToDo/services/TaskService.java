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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<TaskDTOResponse> getAllTasks(UUID userId, Pageable pageable){
        Page<TaskModel> taskPageable = taskRepository.findAllByUserId(userId, pageable);
        return taskPageable.map(TaskModel::toDTO);
    }

    @Transactional(readOnly = true)
    public TaskDTOResponse taskFindById(UUID taskId, UUID idUser){
        TaskModel taskExist = taskRepository.findById(taskId).orElseThrow(()->
                new BusinessRuleException("ID de tarefa não encontrado."));
        if (!taskExist.getUser().getUser_id().equals(idUser)){
            throw new BusinessRuleException("Essa tarefa não pertence ao usuário.");
        }
        return taskExist.toDTO();
    }

    @Transactional
    public TaskDTOResponse updateTask(UUID userId ,UUID taskId, TaskDTORequest taskDTORequest){
        TaskModel taskExist = taskRepository.findById(taskId).orElseThrow(()->
                new BusinessRuleException("ID de tarefa não encontrado."));
        if (!taskExist.getUser().getUser_id().equals(userId)){
            throw new BusinessRuleException("Essa tarefa não pertence ao usuário.");
        }
        if (taskDTORequest.title() != null && !taskDTORequest.title().isEmpty()){
            taskExist.setTitle(taskDTORequest.title());
        }else {
            throw new BusinessRuleException("O titulo precisa ser preenchido.");
        }
        if(taskDTORequest.description() != null && !taskDTORequest.description().isEmpty()){
            taskExist.setDescription(taskDTORequest.description());
        }else {
            throw new BusinessRuleException("Preencha a descrição.");
        }
        taskRepository.save(taskExist);
        return taskExist.toDTO();
    }

    @Transactional
    public void deleteTask(UUID taskId, UUID userId){
        if(!taskRepository.existsById(taskId)){
            throw new BusinessRuleException("ID de tarefa não encontrado.");
        }
        TaskModel taskExist = taskRepository.findById(taskId).orElseThrow(()-> new BusinessRuleException("ID de tarefa não encontrado."));
        if (!taskExist.getUser().getUser_id().equals(userId)){
            throw new BusinessRuleException("Essa tarefa não pertence ao usuário.");
        }
        taskRepository.deleteById(taskId);
    }
}
