package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TransferInfoDto;
import com.example.demo.entity.TransferId;
import com.example.demo.entity.TransferTblEntity;
import com.example.demo.repository.TransferRepository;

@Service
public class TransferService {

	@Autowired
	private TransferRepository transferRepository;


	//振込申請確認
	public List<TransferInfoDto> getTransferList(){

		List<TransferInfoDto> list = new ArrayList<TransferInfoDto>();
		List<TransferTblEntity> entityList = transferRepository.getAll();

		for(TransferTblEntity entity:entityList) {

			TransferInfoDto dto = new TransferInfoDto();
			dto.setId(entity.getTransferId().getTransferId());
			dto.setPrice(entity.getPrice());
			dto.setTransferDate(entity.getTransferDate());
			dto.setUserName(entity.getTransferId().getUserTbl().getUserName());
			list.add(dto);
		}

		return list;

	}

	//承認確認用情報再取得
	public TransferInfoDto getInfo(int transferId) {

		TransferTblEntity entity = transferRepository.getTransfer(transferId);
		TransferInfoDto dto = new TransferInfoDto();

		dto.setUserId(entity.getTransferId().getUserTbl().getUserId());
		dto.setPrice(entity.getPrice());
		dto.setTransferDate(entity.getTransferDate());

		return dto;

	}

	public void insertApproval(Date now,int transferId) {

		transferRepository.insertApproval(now,transferId);

	}
}




