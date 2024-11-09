package org.sattrack.sattracker_harvestdb.Controllers;

import org.sattrack.sattracker_harvestdb.Entity.SatGroup;
import org.sattrack.sattracker_harvestdb.Repository.SatGroupRepository;
import org.sattrack.sattracker_harvestdb.Services.RabbitPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController()
public class UpdateGroupsController {

    @Autowired
    SatGroupRepository satGroupRepository;

    @Autowired
    RabbitPublisherService rabbitPublisherService;

    @PostMapping("/updategroup")
    public ResponseEntity<String> startUpdateGroups(@RequestParam String group){

        Optional<SatGroup> satGroup = satGroupRepository.findBySatGroupQuery(group);
        if(satGroup.isPresent()){
            rabbitPublisherService.sendGroupUpdateMessage(group);
            return ResponseEntity.accepted().body("Group being processed.");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
