package com.project.mynet.services;

import com.project.mynet.dao.ClientRepository;
import com.project.mynet.dao.VaultRepository;
import com.project.mynet.dto.VaultDTO;
import com.project.mynet.exceptions.NotFoundCustomException;
import com.project.mynet.models.Vault;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@Service
@AllArgsConstructor
public class VaultService {

    private VaultRepository vaultDao;
    private ClientRepository clientDao;

    public VaultDTO mapToDTO(Vault vault) {
        VaultDTO dto = new VaultDTO();
        dto.setId(vault.getId());
        dto.setName(vault.getName());
        dto.setGoal(vault.getGoal());
        dto.setAmount(vault.getAmount());
        dto.setDueDate(vault.getDueDate());
        return dto;
    }

    public Vault getOne(Long id) {
        return vaultDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Vault with this id does not exist.", 404));
    }

    public Collection<Vault> getAllForOneClient(Long client_id) {
        clientDao.findById(client_id).orElseThrow(() -> new NotFoundCustomException("Client does not exist.", 400));
        return vaultDao.findAllByClient_Id(client_id);
    }

    public Vault addNew(Vault vault) {
        String vaultName = vault.getName();
        Collection<Vault> existingVaults = vaultDao.findAll();
        for (Vault val : existingVaults) {
            if (Objects.equals(val.getName(), vaultName)) {
                throw new NotFoundCustomException("Vault with this name already exists. Please choose a different name.", 400);
            }
        }
        return vaultDao.save(vault);
    }

    public Vault update(Vault vault) {
        Vault oldVault = vaultDao.findById(vault.getId()).orElseThrow(() -> new NotFoundCustomException("Vault with this id does not exist.", 404));

        oldVault.setGoal(vault.getGoal());
        oldVault.setName(vault.getName());
        oldVault.setDueDate(vault.getDueDate());

        return vaultDao.save(oldVault);
    }

    public double updateAmount(double newAmount, Long id) {
        Vault vault = vaultDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Vault with this id does not exist.", 404));
        vault.setAmount(newAmount);
        vaultDao.save(vault);
        return vault.getAmount();
    }

    public String delete(Long id) {
        vaultDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Vault with this id does not exist.", 404));
        vaultDao.deleteById(id);
        return "Deleted successfully";
    }
}
