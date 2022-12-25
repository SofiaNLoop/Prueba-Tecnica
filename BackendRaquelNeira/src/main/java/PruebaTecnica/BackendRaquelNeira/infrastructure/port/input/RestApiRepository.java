package PruebaTecnica.BackendRaquelNeira.infrastructure.port.input;

import PruebaTecnica.BackendRaquelNeira.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RestApiRepository {
    public List<User> getAllApi();
    public User postNewApi(User newObj);
    public User putUpdateApi(User updObj);
    public void deleteApi(Integer id);
}
