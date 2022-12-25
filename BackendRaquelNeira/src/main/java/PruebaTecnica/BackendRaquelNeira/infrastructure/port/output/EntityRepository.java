package PruebaTecnica.BackendRaquelNeira.infrastructure.port.output;

import PruebaTecnica.BackendRaquelNeira.domain.model.User;
import org.springframework.data.repository.CrudRepository;


public interface EntityRepository extends  CrudRepository <User, Integer> { }
