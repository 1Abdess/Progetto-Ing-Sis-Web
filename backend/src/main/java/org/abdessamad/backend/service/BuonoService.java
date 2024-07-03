package org.abdessamad.backend.service;

import org.abdessamad.backend.model.Buono;
import org.abdessamad.backend.repository.BuonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BuonoService {

    @Autowired
    private BuonoRepository buonoRepository;

    public List<Buono> getAvailableCoupons() {
        LocalDate today = LocalDate.now();
        List<Buono> availableCoupons = buonoRepository.findByAttivoTrueAndValidoAAfter(today);

        // Disattiva i coupon scaduti
        List<Buono> allCoupons = buonoRepository.findAll();
        for (Buono buono : allCoupons) {
            if (buono.getValidoA().isBefore(today) && buono.isAttivo()) {
                buono.setAttivo(false);
                buonoRepository.save(buono);
            }
        }

        return availableCoupons;
    }

    public Buono createCoupon(Buono buono) {
        return buonoRepository.save(buono);
    }
}
