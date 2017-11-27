package com.example.controller;

/**
 * Created by Talal on 27.11.17.
 */


import application.FeatureService;
import application.JSONPoint;
import application.Point;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeatureController {

    FeatureService service = new FeatureService();

    @RequestMapping(value = "/point", method = RequestMethod.POST)
    public void addPoint(@RequestBody Point inputPoint) {
        service.addPoint(inputPoint);
    }

    @RequestMapping(value = "/space", method = RequestMethod.GET)
    public List<JSONPoint> getAllSpacePoints() {
        return service.getSpacePoints();
    }

    @RequestMapping(value = "/lines/{n}", method = RequestMethod.GET)
    public List<List<JSONPoint>> getCollinearPoints(@PathVariable("n") int n){
        return service.getLineSegments(n);
    }


}