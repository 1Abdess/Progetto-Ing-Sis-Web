package org.abdessamad.backend.controller;

import org.abdessamad.backend.model.Buono;
import org.abdessamad.backend.service.BuonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class BuonoController {

    @Autowired
    private BuonoService buonoService;

    @GetMapping("/disponibili")
    public ResponseEntity<List<Buono>> getAvailableCoupons() {
        List<Buono> availableCoupons = buonoService.getAvailableCoupons();
        return ResponseEntity.ok(availableCoupons);
    }

    @PostMapping("/crea")
    public ResponseEntity<Buono> createCoupon(@RequestBody Buono buono) {
        Buono newBuono = buonoService.createCoupon(buono);
        return ResponseEntity.ok(newBuono);
    }
}
