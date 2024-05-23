package br.edu.unichristus.projaura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.edu.unichristus.projaura.data.dto.UserDTO;
import br.edu.unichristus.projaura.data.dto.UserLowDTO;
import br.edu.unichristus.projaura.data.model.User;
import br.edu.unichristus.projaura.dozer.DozerConverter;
import br.edu.unichristus.projaura.exception.CommonsException;
import br.edu.unichristus.projaura.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;
    
    public UserLowDTO save(UserDTO userDTO) {
        var userModel = DozerConverter.parseObject(userDTO, User.class);
        if (userModel.getName().length() > 150) {
            throw new CommonsException(
                    HttpStatus.BAD_REQUEST,
                    "unichristus.backend.service.user.badrequest.exception",
                    "Limite de caracteres excedido!"
            );
        }
        var userFind = repository.findByEmail(userModel.getEmail());
        if (!userFind.isEmpty()) {
            throw new CommonsException(
                    HttpStatus.CONFLICT,
                    "unichristus.backend.service.user.conflict.exception",
                    "O Login informado já existe!"
            );
        }
        
        var userSaved = repository.save(userModel);
        var userLowDTO = DozerConverter.parseObject(userSaved, UserLowDTO.class);
        
        return userLowDTO;
    }
    
    public UserLowDTO update(Long id, UserDTO userDTO) {
        var existingUser = repository.findById(id)
            .orElseThrow(() -> new CommonsException(
                HttpStatus.NOT_FOUND,
                "unichristus.backend.service.user.notfound.exception",
                "Usuário com a ID informada não foi encontrado."
            ));
        
        // Atualiza os campos necessários
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setBirthday(userDTO.getBirthday());
        existingUser.setCountry(userDTO.getCountry());
        existingUser.setCity(userDTO.getCity());
        existingUser.setPassword(userDTO.getPassword());
        
        var userUpdated = repository.save(existingUser);
        return DozerConverter.parseObject(userUpdated, UserLowDTO.class);
    }
    
    public List<UserLowDTO> listAll() {
        var listUserLow = repository.findAll();
        var listConverted = DozerConverter.parseListObjects(listUserLow, UserLowDTO.class);
        
        return listConverted;
    }
    
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
    
    public User findById(Long id) {
        var user = repository.findById(id);
        if (user.isEmpty()) {
            throw new CommonsException(
                    HttpStatus.NOT_FOUND,
                    "unichristus.backend.service.user.notfound.exception",
                    "Usuário com a ID informada não foi encontrado."
            );
        }
        return user.get();
    }
}