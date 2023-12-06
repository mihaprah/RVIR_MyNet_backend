package com.project.mynet.controllers;

import com.project.mynet.models.Vault;
import com.project.mynet.services.VaultService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/vault")
public class VaultController {

    private VaultService vaultService;


    //mogoce da se tu pridobijo vsi vaulti dolocenega uporabnika ? al zake bi nucala vse vaulte
    @GetMapping
    public Collection<Vault> getAll(){
        return vaultService.getAll();
    }

    @GetMapping("/{id}")
    public Vault getById(@PathVariable("id") Long id) {
        return vaultService.getOne(id);
    }

    //mogoce nebi rabo add dodat idk !! kar na glavnem koreni post ?
    @PostMapping("/add")
    public ResponseEntity<Vault> addVault(@RequestBody Vault vault){
        Vault newVault = vaultService.addNew(vault);
        return ResponseEntity.ok(newVault);
    }

    @PutMapping("/update")
    public ResponseEntity<Vault> updateVault(@RequestBody Vault vault){
        Vault updatedVault = vaultService.update(vault);
        return ResponseEntity.ok(updatedVault);
    }

    //nevem al bi blo bolse da se da id isto v request body?? nekaj ni ok!!
    @PutMapping("/updateAmount/{id}")
    public ResponseEntity<Double> updateVaultAmount(@PathVariable("id") Long id, @RequestBody double amount){
        double newAmount = vaultService.updateAmount(amount, id);
        return ResponseEntity.ok(newAmount);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVault(@PathVariable("id") Long id) {
        String message = vaultService.delete(id);
        return ResponseEntity.ok(message);
    }

}
