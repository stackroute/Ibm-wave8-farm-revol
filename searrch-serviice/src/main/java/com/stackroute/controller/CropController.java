package com.stackroute.controller;

import com.stackroute.domain.Crop;
import com.stackroute.exception.CropNotFoundException;
import com.stackroute.service.CropService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping(value="api/v1")
@CrossOrigin("*")
public class CropController {
    private CropService cropService;
    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    public CropController(CropService cropService){
        this.cropService = cropService;
    }

    @PostMapping("crop")
    public ResponseEntity<?> saveCrop(@RequestBody Crop crop){
        ResponseEntity responseEntity;
        try{
            cropService.saveCrop(crop);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("crop")
    public ResponseEntity<?> getAllCrops(){
        ResponseEntity responseEntity;
        try{
            cropService.getAllCrops();
            responseEntity= new ResponseEntity<List<Crop>>(cropService.getAllCrops(),HttpStatus.OK);
        } catch (Exception e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("crop/{id}")
    public ResponseEntity<?> getCropById(@PathVariable("id") int id){
        ResponseEntity responseEntity;
        Crop crop;
        try {
            crop = cropService.getCropById(id);
            responseEntity=new ResponseEntity<Crop>(crop, HttpStatus.OK);
        } catch (Exception e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
            return responseEntity;
    }
    @DeleteMapping("crop/{id}")
    public ResponseEntity<?> deleteCrop(@PathVariable("id") int id) throws CropNotFoundException
    {
    ResponseEntity responseEntity;
        cropService.deleteCropById(id);
        responseEntity = new ResponseEntity<String>("Deleted successfully", HttpStatus.OK);
        return responseEntity;
    }
    @PutMapping("crop/{id}")
    public ResponseEntity<Crop> updateCrop(@RequestBody Crop crop){
        ResponseEntity responseEntity;
        try {
            cropService.updateCrop(crop);
            responseEntity= new ResponseEntity<String>("updated", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("cropname/{name}")
    public ResponseEntity<?> getCropByName(@PathVariable String name) {
        ResponseEntity responseEntity;
        try {
            Crop crop = cropService.getCropByName(name);
            responseEntity = new ResponseEntity<Crop>(crop, HttpStatus.OK);
        } catch (CropNotFoundException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }


}
