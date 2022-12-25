package PruebaTecnica.BackendRaquelNeira.application;

import PruebaTecnica.BackendRaquelNeira.domain.model.User;
import PruebaTecnica.BackendRaquelNeira.infrastructure.adapter.output.PostgresqlRepository;
import PruebaTecnica.BackendRaquelNeira.infrastructure.port.output.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCaseOut {


    @Autowired
    PostgresqlRepository postgresqlRepository;

    public List<User> selectAllUsers(){ return postgresqlRepository.listAll(); }

    public User createNewUser(User user){ return postgresqlRepository.newEntity(user);
    }

    public boolean existsById(Integer id){
        return postgresqlRepository.existsById(id);
    }

    public User updateUser(User user){ return postgresqlRepository.updateEntity(user); }

    public void deleteUser(Integer id) { postgresqlRepository.deleteIdEntity(id); }
}
