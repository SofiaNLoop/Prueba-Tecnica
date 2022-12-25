package PruebaTecnica.BackendRaquelNeira.domain.service;


import PruebaTecnica.BackendRaquelNeira.application.UserCaseOut;
import PruebaTecnica.BackendRaquelNeira.domain.model.User;
import PruebaTecnica.BackendRaquelNeira.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserCaseOut userCaseOut;

    public List<User> allUsers(){
        return userCaseOut.selectAllUsers();
    }

    public boolean existsById(int id){
        return userCaseOut.existsById(id);
    }


    public User createUser(User user){
        if (existsById(user.getIdentificacion())) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST,
                    "El usuario con esa identificacion ya está registrado."
            );
        } else {
            return userCaseOut.createNewUser(user);
        }
    }

    public User updateUser(User user){
        if (!existsById(user.getIdentificacion())) {
            throw new CustomException(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST,
                    "No se puede actualizar un usuario inexistente."
            );
        } else {
            return userCaseOut.updateUser(user);
        }
    }

    public void deteleUser(Integer id){
        if(!existsById(id)){
            throw new CustomException(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST,
                    "No se puede eliminar un usuario inexistente."
            );
        } else {
            userCaseOut.deleteUser(id);
        }
    }

}
