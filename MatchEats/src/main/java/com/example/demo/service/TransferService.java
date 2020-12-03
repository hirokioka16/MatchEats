package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TransferInfoDto;
import com.example.demo.entity.TransferTblEntity;
import com.example.demo.repository.TransferRepository;

@Service
public class TransferService {

	@Autowired
	private TransferRepository transferRepository;


	//振込申請確認
	public List<TransferInfoDto> getTransferList(){

		List<TransferInfoDto> list = new ArrayList<TransferInfoDto>();
		List<TransferTblEntity> entityList = transferRepository.findAll();

		for(TransferTblEntity entity:entityList) {

			TransferInfoDto dto = new TransferInfoDto();
			dto.setTransferId(entity.getTransferId());
			dto.setPrice(entity.getPrice());
			dto.setTransferDate(entity.getTransferDate());
			list.add(dto);
		}

		return list;

	}
}




