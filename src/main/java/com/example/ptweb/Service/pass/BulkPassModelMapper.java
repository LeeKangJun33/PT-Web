package com.example.ptweb.Service.pass;

import com.example.ptweb.controller.admin.BulkPassRequest;
import com.example.ptweb.repository.pass.BulkPassEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BulkPassModelMapper {
    BulkPassModelMapper INSTANCE = Mappers.getMapper(BulkPassModelMapper.class);

    List<BulkPass> map(List<BulkPassEntity> passEntities);

    BulkPassEntity map(BulkPassRequest bulkPassRequest);

}
