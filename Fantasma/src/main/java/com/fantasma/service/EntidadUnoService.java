package com.fantasma.service;

import com.fantasma.domain.EntidadUno;
import com.fantasma.exceptions.FantasmaRequestException;
import com.fantasma.mapper.EntidadUnoMapper;
import com.fantasma.repository.EntidadUnoRepository;
import com.fantasma.repository.models.EntidadUnoModel;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

public class EntidadUnoService {

    private static final int PAGE_SIZE = 10;
    private final EntidadUnoRepository entidadUnoRepository;

    public EntidadUnoService(EntidadUnoRepository entidadUnoRepository){
        this.entidadUnoRepository = entidadUnoRepository;
    }

    @Transactional
    public EntidadUno createEntidadUno(EntidadUno entidadUno){
        EntidadUnoModel entidadUnoModel = EntidadUnoMapper.mapDomainToModel(entidadUno);
        entidadUnoRepository.save(entidadUnoModel);
        return EntidadUnoMapper.mapModelToDomain(entidadUnoModel);
    }

    @Transactional
    public EntidadUno updateEntidadUno(Long id, EntidadUno entidadUno) throws FantasmaRequestException {
        Optional<EntidadUnoModel> entidadUnoModel = entidadUnoRepository.findById(id);
        if(entidadUnoModel.isEmpty()){
            throw new FantasmaRequestException("Entidad not found", "not.found", HttpStatus.NOT_FOUND);
        }
        EntidadUnoModel entidadOld = entidadUnoModel.get();
        entidadOld.setName(entidadUno.getName());
        entidadOld.setFecha(entidadUno.getFecha());
        return EntidadUnoMapper.mapModelToDomain(entidadUnoRepository.save(entidadOld));
    }

    @Transactional
    public Page<EntidadUno> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        var paginatedMembers = entidadUnoRepository.findAll(pageable);
        var members = paginatedMembers.getContent().stream().map(EntidadUnoMapper::mapModelToDomain).collect(Collectors.toList());
        return new PageImpl<>(members, pageable, paginatedMembers.getTotalElements());
    }

    @Transactional
    public void deleteEntidad(Long id) throws FantasmaRequestException {
        Optional<EntidadUnoModel> entidadUnoModelOptional = entidadUnoRepository.findById(id);
        if(entidadUnoModelOptional.isEmpty()){
            throw new FantasmaRequestException("Entidad not found", "not.found", HttpStatus.NOT_FOUND);
        }else{
            EntidadUnoModel entidadUnoModel = entidadUnoModelOptional.get();
            entidadUnoRepository.delete(entidadUnoModel);
        }
    }
}
