package org.finalproject.facade;

public interface Facade<E,DTOResponse, DTORequest> {

    DTOResponse toDTOResponse(DTORequest dtoRequest);

    DTORequest toDTORequest(DTOResponse dtoResponse);

    DTOResponse fromEntity(E entity);

    E toEntity(DTORequest dtoRequest);

}
