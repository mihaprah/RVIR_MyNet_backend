package com.project.mynet.services;

import com.project.mynet.dao.VaultRepository;
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

    public Vault getOne(Long id) {
        return vaultDao.findById(id).orElseThrow(() -> new NotFoundCustomException("Vault with this id does not exist.", 404));
    }

    public Collection<Vault> getAll() {
        return vaultDao.findAll();
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

        oldVault.setIcon(vault.getIcon());
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
