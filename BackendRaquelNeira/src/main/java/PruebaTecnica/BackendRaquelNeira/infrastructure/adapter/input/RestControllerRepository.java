package PruebaTecnica.BackendRaquelNeira.infrastructure.adapter.input;


import PruebaTecnica.BackendRaquelNeira.domain.model.User;
import PruebaTecnica.BackendRaquelNeira.exception.CustomException;
import PruebaTecnica.BackendRaquelNeira.infrastructure.port.input.RestApiRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class RestControllerRepository {


    @Autowired
    RestApiRepository restApiRepository;

    public boolean nullFields(User user){
        return user.getIdentificacion() == null ||
                user.getNombre() == null ||
                user.getTelefono() == null;
    }
    public boolean emptyFields(User user){
        return user.getNombre().equals("") ||
                user.getTelefono().equals( "");
    }

    @GetMapping("/list-user")
    public List<User> listUsers(){
        return restApiRepository.getAllApi();
    }

    @PostMapping("/new-user")
    public ResponseEntity createUser(@RequestBody User user){
        boolean isNull = this.nullFields(user);
        boolean isEmpty = this.emptyFields(user);
        if(isNull || isEmpty){
            throw new CustomException(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST,
                    "Por favor ingrese todos los datos del usuario a crear."
            );
        } else {
            User userCreated = restApiRepository.postNewApi(user);
            JsonConverter message = new JsonConverter("¡Usuario creado!", "Correcto");
            return new ResponseEntity(message, HttpStatus.CREATED);
        }
    }

    @PutMapping("/update-user")
    public ResponseEntity updateUser(@RequestBody User user)   {
        boolean isNull = this.nullFields(user);
        boolean isEmpty = this.emptyFields(user);
        if(isNull || isEmpty){
            throw new CustomException(
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST,
                    "Por favor ingrese todos los datos del usuario a actualizar."
            );
        } else {
            User userUpdated = restApiRepository.putUpdateApi(user);
            JsonConverter message = new JsonConverter("¡Usuario actualizado!", "Correcto");
            return  new ResponseEntity(message, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        restApiRepository.deleteApi(id);
        JsonConverter message = new JsonConverter("¡Usuario eliminado!", "Correcto");
        return new ResponseEntity(message, HttpStatus.OK);
    }

}
