package org.sattrack.sattracker_harvestdb.Controllers;

import org.sattrack.sattracker_harvestdb.Records.CelestrakSatelliteData;
import org.sattrack.sattracker_harvestdb.Services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CelestrakController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/fetch-data")
    public Mono<ResponseEntity<CelestrakSatelliteData[]>> fetchData(
            @RequestParam String group) {
        return apiService.getSatelliteGroupData(group)
                .map(ResponseEntity::ok);
    }
}
