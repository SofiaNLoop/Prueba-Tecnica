package PruebaTecnica.BackendRaquelNeira.infrastructure.adapter.output;

import PruebaTecnica.BackendRaquelNeira.domain.model.User;
import PruebaTecnica.BackendRaquelNeira.exception.CustomException;
import PruebaTecnica.BackendRaquelNeira.infrastructure.port.output.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


@Component
public  class PostgresqlRepository  {

    @Autowired
    private EntityRepository entityRepository;

    public List<User> listAll(){
        return (List<User>) entityRepository.findAll();
    }



    public User newEntity(User objToSave){ return entityRepository.save(objToSave); }

    public boolean existsById(Integer id){
        return entityRepository.existsById(id);
    }

    public User updateEntity(User objToUpdate) {
        return entityRepository.save(objToUpdate);
    }

    public void deleteIdEntity(Integer id){ entityRepository.deleteById(id); }

}




