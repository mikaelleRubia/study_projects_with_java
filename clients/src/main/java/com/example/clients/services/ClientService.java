package com.example.clients.services;

import com.example.clients.dto.ClientDTO;
import com.example.clients.entities.Client;
import com.example.clients.repositories.ClientRepository;
import com.example.clients.services.exceptions.DatabaseException;
import com.example.clients.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable page){
        Page<Client> result = repository.findAll(page);
        return result.map(x-> new ClientDTO(x));
    }

    @Transactional()
    public ClientDTO insert(ClientDTO clientDTO){
        Client client = new Client();
        copyDtoClient(clientDTO, client);
        client = repository.save(client);
        return new ClientDTO((client));

    }

    @Transactional
    public ClientDTO update(ClientDTO clientDTO, Long id){
        try {
            Client client = repository.getReferenceById(id);
            copyDtoClient(clientDTO, client);
            client = repository.save(client);
            return new ClientDTO((client));
        }
        catch(EntityNotFoundException e){
                throw new ResourceNotFoundException(" Recurso não encontrado");
        }
    }

    public void copyDtoClient(ClientDTO clientDTO, Client entity){
        entity.setName(clientDTO.getName());
        entity.setCpf(clientDTO.getCpf());
        entity.setIncome(clientDTO.getIncome());
        entity.setBithDate(clientDTO.getBithDate());
        entity.setChildren(clientDTO.getChildren());

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException(" Recurso não encontrado");
        }
        try{
            repository.deleteById(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }



}
