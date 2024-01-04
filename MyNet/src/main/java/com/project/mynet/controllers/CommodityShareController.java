package com.project.mynet.controllers;


import com.project.mynet.models.CommodityShare;
import com.project.mynet.models.UpdateAmountRequest;
import com.project.mynet.services.CommodityShareService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/commodityshare")
public class CommodityShareController {

    private CommodityShareService commodityShareService;

    @GetMapping("/{id}")
    public ResponseEntity<CommodityShare> getByID(@PathVariable("id") Long id){
        CommodityShare commodityShare = commodityShareService.getByID(id);
        return ResponseEntity.ok(commodityShare);
    }

    @GetMapping("/all/{client_id}")
    public ResponseEntity<Collection<CommodityShare>> getAllForOneClient(@PathVariable Long client_id) {
        Collection<CommodityShare> allShares = commodityShareService.getAllForOneClient(client_id);
        return ResponseEntity.ok(allShares);
    }

    @PostMapping("/add")
    public ResponseEntity<CommodityShare> createCommodityShare(@RequestBody CommodityShare commodityShare){
        CommodityShare newCommodityShare = commodityShareService.addNew(commodityShare);
        return ResponseEntity.ok(newCommodityShare);

    }

    @PutMapping("/updateAmount/{id}")
    public ResponseEntity<Double> updateCommodityShare(@PathVariable("id") Long id, @RequestBody UpdateAmountRequest request){
        double newCommodityShareAmount = commodityShareService.updateCommodityAmount(id, request.getAmount());
        return ResponseEntity.ok(newCommodityShareAmount);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteShare(@PathVariable("id")Long id) {
        String message = commodityShareService.deleteShare(id);
        return ResponseEntity.ok(message);
    }

}
