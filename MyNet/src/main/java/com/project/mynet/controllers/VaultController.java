package com.project.mynet.controllers;

import com.project.mynet.dto.VaultDTO;
import com.project.mynet.models.UpdateAmountRequest;
import com.project.mynet.models.Vault;
import com.project.mynet.services.VaultService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/vault")
public class VaultController {

    private VaultService vaultService;

    @GetMapping("/all/{client_id}")
    public ResponseEntity<Collection<VaultDTO>> getAllForOneClient(@PathVariable Long client_id) {
        Collection<Vault> allVaults = vaultService.getAllForOneClient(client_id);
        List<VaultDTO> allVaultsDTO = allVaults.stream().map(vaultService::mapToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(allVaultsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VaultDTO> getById(@PathVariable("id") Long id) {
        Vault vault = vaultService.getOne(id);
        if (vault == null) {
            return ResponseEntity.notFound().build();
        }
        VaultDTO vaultDTO = vaultService.mapToDTO(vault);
        return ResponseEntity.ok(vaultDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<VaultDTO> addVault(@RequestBody Vault vault) {
        Vault newVault = vaultService.addNew(vault);
        VaultDTO newVaultDTO = vaultService.mapToDTO(newVault);
        return ResponseEntity.ok(newVaultDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<VaultDTO> updateVault(@RequestBody Vault vault) {
        Vault updatedVault = vaultService.update(vault);
        VaultDTO updatedVaultDTO = vaultService.mapToDTO(updatedVault);
        return ResponseEntity.ok(updatedVaultDTO);
    }

    @PutMapping("/updateAmount/{id}")
    public ResponseEntity<Double> updateVaultAmount(@PathVariable("id") Long id, @RequestBody UpdateAmountRequest request) {
        double newAmount = vaultService.updateAmount(request.getAmount(), id);
        return ResponseEntity.ok(newAmount);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVault(@PathVariable("id") Long id) {
        String message = vaultService.delete(id);
        return ResponseEntity.ok(message);
    }
}

