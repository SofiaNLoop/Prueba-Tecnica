package PruebaTecnica.BackendRaquelNeira.infrastructure.adapter.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class JsonConverter {

    private String msg;
    private String status;

}
