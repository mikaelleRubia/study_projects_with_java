package com.example.clients.controllers;

import com.example.clients.dto.ClientDTO;
import com.example.clients.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id,@Valid @RequestBody ClientDTO dto){
        dto = service.update(dto, id);
        return  ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }


}
