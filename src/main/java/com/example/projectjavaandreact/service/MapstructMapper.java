package com.example.projectjavaandreact.service;

import com.example.projectjavaandreact.dto.ImageDTO;
import com.example.projectjavaandreact.dto.SuiteDTO;
import com.example.projectjavaandreact.model.Images;
import com.example.projectjavaandreact.model.Suite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MapstructMapper {

    MapstructMapper INSTANCE = Mappers.getMapper(MapstructMapper.class);

    // המרת Image ל-ImageDTO
    ImageDTO imageToImageDTO(Images image);

    // המרת ImageDTO ל-Image
    Images imageDTOToImage(ImageDTO imageDTO);

    // המרת Suite ל-SuiteDTO
    @Mapping(source = "imageList", target = "images")
    SuiteDTO suiteToSuiteDTO(Suite suite);

    // המרת SuiteDTO ל-Suite
    @Mapping(source = "images", target = "imageList")
    Suite suiteDTOToSuite(SuiteDTO suiteDTO);
}
