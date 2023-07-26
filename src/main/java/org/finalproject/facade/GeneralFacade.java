package org.finalproject.facade;


import lombok.Data;
import lombok.NoArgsConstructor;

import org.finalproject.entity.BaseEntity;
import org.finalproject.service.GeneralService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@Data
@Component
public abstract class GeneralFacade<E extends BaseEntity, I, O> {

    @Autowired
    private ModelMapper mm  ;

    private
    GeneralService<E> service;

    @Autowired
    public GeneralFacade(ModelMapper mm, GeneralService<E> service) {
        this.mm = mm;
        this.service = service;
    }

    public O convertToDto(E entity) {
        final O dto = mm.map(entity, getClassO());
        decorateDto(dto, entity);
        return dto;

    }

    public E convertToEntity(I rqDto) {
        final E entity = mm.map(rqDto, getClassE());
        decorateEntity(entity, rqDto);
        return entity;
    }

    protected void decorateEntity(final E entity, final I dto) {

    }

    protected void decorateDto(final O dto, final E entity) {

    }

    private Class<E> getClassE() {
        return (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Class<O> getClassO() {
        return (Class<O>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[2];
    }

    public O save(E entity) {
        return convertToDto(service.save(entity));
    }

    public void delete(E entity) {
        service.delete(entity);
    }

    public List<O> findAll() {
        List<E> entities = service.findAll();
        List<O> entitiesRs = entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return entitiesRs;
    }

    public void deleteById(Long id) {
        service.deleteById(id);
    }

    public O findById(Long id) {
        return convertToDto(service.findEntityById(id));
    }


}
