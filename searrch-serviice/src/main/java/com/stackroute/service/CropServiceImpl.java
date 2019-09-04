package com.stackroute.service;
import com.stackroute.domain.Crop;
import com.stackroute.exception.CropAlreadyExistsException;
import com.stackroute.exception.CropNotFoundException;
import com.stackroute.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropServiceImpl implements CropService {
    CropRepository cropRepository;
    @Autowired
    public CropServiceImpl(CropRepository cropRepository){

        this.cropRepository = cropRepository;
    }
    @Override
    public Crop saveCrop(Crop crop) throws CropAlreadyExistsException {
        if(cropRepository.existsById(crop.getId())) {
            throw new CropAlreadyExistsException("Crop already exists");
        }
        Crop saveCrop = cropRepository.save(crop);
        if(saveCrop ==null)
            throw new CropAlreadyExistsException(" Crop already exists");
        return saveCrop;
    }
    @Override
    public boolean deleteCropById(int id) throws CropNotFoundException {
        if (getCropById(id)== null) {
            throw new CropNotFoundException("The crop not found");
        } else {
        cropRepository.delete(getCropById(id));
            return true;
        }
    }
    @Override
    public Crop updateCrop(Crop crop) throws CropNotFoundException {
        Crop updatedCrop = cropRepository.findById(crop.getId()).get();
        if(updatedCrop !=null){
            updatedCrop.setId(crop.getId());
            updatedCrop.setCropName(crop.getCropName());
          cropRepository.save(crop);
        return updatedCrop;
        } else {
          throw new CropNotFoundException("Crop not found");
        }
    }
    @Override
    public Crop getCropById(int id) {
        Optional<Crop> cropOptional= cropRepository.findById(id);
        if(cropOptional.isPresent()){
            return cropOptional.get();

        }
        else {
            return null;
        }
    }
    @Override
    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    @Override
    public List<Crop> getCropByName(String name) throws CropNotFoundException {
        List<Crop> crop = cropRepository.getCropByName(name);
        if(crop.isEmpty()) {
            throw new CropNotFoundException("Crop not found");
        }
        return crop;
    }
}
