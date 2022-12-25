package PruebaTecnica.BackendRaquelNeira.application;

import PruebaTecnica.BackendRaquelNeira.domain.model.User;
import PruebaTecnica.BackendRaquelNeira.domain.service.UserService;
import PruebaTecnica.BackendRaquelNeira.infrastructure.port.input.RestApiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserCaseIn implements RestApiRepository {



    @Autowired
    UserService userService;

    @Override
    public List<User> getAllApi() {
        return userService.allUsers();
    }

    @Override
    public User postNewApi(User newObj) {
        return userService.createUser(newObj);
    }


    @Override
    public User putUpdateApi(User updObj) {
        return userService.updateUser(updObj);
    }

    @Override
    public void deleteApi(Integer id) {
        userService.deteleUser(id);
    }


}
