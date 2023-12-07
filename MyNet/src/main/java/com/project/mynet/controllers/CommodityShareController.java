package com.project.mynet.controllers;


import com.project.mynet.models.CommodityShare;
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
    public CommodityShare getByID(@PathVariable("id") Long id){
        return commodityShareService.getByID(id);
    }

    @GetMapping
    public Collection<CommodityShare> getAll() {
        return commodityShareService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<CommodityShare> createCommodityShare(@RequestBody CommodityShare commodityShare){
        CommodityShare newCommodityShare = commodityShareService.addNew(commodityShare);
        return ResponseEntity.ok(newCommodityShare);

    }

    @PutMapping("/updateAmount/{id}")
    public ResponseEntity<Integer> updateCommodityShare(@PathVariable("id") Long id, @RequestBody int amount){
        int newCommodityShareAmount = commodityShareService.updateCommodityAmount(id, amount);
        return ResponseEntity.ok(newCommodityShareAmount);
    }



}
