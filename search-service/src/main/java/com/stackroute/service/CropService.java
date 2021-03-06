package com.stackroute.service;

import com.stackroute.domain.Crop;
import com.stackroute.domain.Land;
import com.stackroute.exception.CropAlreadyExistsException;
import com.stackroute.exception.CropNotFoundException;

import java.util.List;

public interface CropService {

    public Crop saveCrop(Crop crop) throws CropAlreadyExistsException;
    public boolean deleteCropById(int id) throws CropNotFoundException;
    public Crop updateCrop(Crop crop) throws CropNotFoundException;
    public Crop getCropById(int id);
    public List<Crop> getAllCrops();
    public Crop getCropByName(String name) throws CropNotFoundException;
    public Crop saveLandByCropName(String name, Land land) throws CropNotFoundException;
}
