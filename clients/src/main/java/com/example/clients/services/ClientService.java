package com.example.clients.services;

import com.example.clients.dto.ClientDTO;
import com.example.clients.entities.Client;
import com.example.clients.repositories.ClientRepository;
import com.example.clients.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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


    public void copyDtoClient(ClientDTO clientDTO, Client entity){
        entity.setName(clientDTO.getName());
        entity.setCpf(clientDTO.getCpf());
        entity.setIncome(clientDTO.getIncome());
        entity.setBithDate(clientDTO.getBithDate());
        entity.setChildren(clientDTO.getChildren());

    }

}
