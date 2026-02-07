package com.example.clients.controllers;

import com.example.clients.dto.ClientDTO;
import com.example.clients.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    @Autowired
    private ClientService service;

    @GetMapping(value= "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO clientDto = service.findById(id);
        return ResponseEntity.ok(clientDto);
    }

    @GetMapping()
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable page){
        Page<ClientDTO> results = service.findAll(page);
        return ResponseEntity.ok(results);
    }

}
