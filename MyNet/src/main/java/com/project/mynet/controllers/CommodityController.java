package com.project.mynet.controllers;


import com.project.mynet.models.Commodity;
import com.project.mynet.services.CommodityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/commodity")
public class CommodityController {

    private CommodityService commodityService;

    @GetMapping
    public Collection<Commodity> getALl(){
        return commodityService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commodity> getById(@PathVariable("id") Long id) {
        Commodity commodity = commodityService.getOne(id);
        return ResponseEntity.ok(commodity);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Commodity> getByCode(@PathVariable("code") String code) {
        Commodity commodity = commodityService.getByCode(code);
        return ResponseEntity.ok(commodity);
    }

    @PostMapping("/add")
    public ResponseEntity<Commodity> addCommodity(@RequestBody Commodity commodity){
        Commodity newCommodity = commodityService.addNew(commodity);
        return ResponseEntity.ok(newCommodity);
    }

    @PutMapping("/update")
    public ResponseEntity<Commodity> updateCommodity(@RequestBody Commodity commodity){
        Commodity updatedCommodity = commodityService.update(commodity);
        return ResponseEntity.ok(updatedCommodity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCommodity(@PathVariable("id") Long id) {
        String message = commodityService.delete(id);
        return ResponseEntity.ok(message);
    }

}
