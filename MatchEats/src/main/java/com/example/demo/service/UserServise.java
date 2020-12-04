package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserInfoDto;
import com.example.demo.entity.BankTblEntity;
import com.example.demo.entity.BankUserId;
import com.example.demo.entity.TransferId;
import com.example.demo.entity.TransferTblEntity;
import com.example.demo.entity.UserTblEntity;
import com.example.demo.repository.BankRepository;
import com.example.demo.repository.TransferRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserServise {


	@Autowired
	UserRepository userRepository;

	@Autowired
	TransferRepository transferRepository;
	
	@Autowired
	BankRepository bankRepository;


	public void insert(UserInfoDto dto) {

		UserTblEntity userEntity = change(dto);
		userRepository.saveAndFlush(userEntity);
		BankTblEntity bankEntity = change2(dto);
		bankRepository.saveAndFlush(bankEntity);

	}

	public void update(UserInfoDto dto) {
		UserTblEntity userEntity = new UserTblEntity();

		userEntity.setUserId(dto.getUserId());
		userEntity.setUserMail(dto.getUserMail());
		userEntity.setUserPass(dto.getUserPass());
		userEntity.setNickName(dto.getNickName());
		userEntity.setUserName(dto.getUserName());
		userEntity.setUserTel(dto.getUserTel());
		userEntity.setPostalCode(dto.getPostalCode());
		userEntity.setUserAdress(dto.getUserAddres());

		//日付の方変換
		try {
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date userBirth = sdFormat.parse(dto.getUserBirth());
			userEntity.setUserBirth(userBirth);

		} catch (ParseException e) {
				e.printStackTrace();
		}

		userEntity.setCardName(dto.getCardName());
		userEntity.setUserCard(dto.getUserCard());
		userEntity.setLimitDate(dto.getLimitDate());
		userEntity.setSecureCode(dto.getSecureCode());




		userRepository.update(
				userEntity.getUserId(),
				userEntity.getUserMail(),
				userEntity.getUserPass(),
				userEntity.getNickName(),
				userEntity.getUserName(),
				userEntity.getUserTel(),
				userEntity.getPostalCode(),
				userEntity.getUserAdress(),
				userEntity.getUserBirth(),
				userEntity.getCardName(),
				userEntity.getUserCard(),
				userEntity.getLimitDate(),
				userEntity.getSecureCode()

				);
	}
	
	
	
	
	
	//削除
	public void delete(Integer userId) {
		userRepository.deleteUser(userId);
	}
	
	
	
	
	
	public UserInfoDto getUser(int id) {

		UserTblEntity userEntity = new UserTblEntity();
		userEntity = userRepository.getOne(id);

		UserInfoDto dto = new UserInfoDto();
		dto = change3(userEntity);

		return dto;

	}

	//売上申請を登録する
	public void insertTransfer(UserInfoDto dto) throws ParseException {


		TransferTblEntity transFerEntity =  new TransferTblEntity();
		UserTblEntity userEntity =  new UserTblEntity();
		userEntity.setUserId(dto.getUserId());

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String strDate = dateFormat.format(date);
		//String型の日付をDate型に変更している
		SimpleDateFormat d1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date now = d1.parse(strDate);

		TransferId id = new TransferId();
		id.setUserTbl(userEntity);
		transFerEntity.setTransferId(id);
		transFerEntity.setPrice(dto.getSales());
		transFerEntity.setTransferDate(now);
		transFerEntity.setAcceptFlag(false);

		//売上振り込み申請をする
		transferRepository.saveAndFlush(transFerEntity);
		//ユーザーの売上を0にする
		userRepository.reset(dto.getUserId());

	}


	// dtoの値をentityに入れるメソッド
	private UserTblEntity change(UserInfoDto dto) {

		UserTblEntity userEntity = new UserTblEntity();
		    userEntity.setUserName(dto.getUserName());
		    userEntity.setNickName(dto.getNickName());
		    userEntity.setUserMail(dto.getUserMail());
		    userEntity.setUserPass(dto.getUserPass());
		    userEntity.setPostalCode(dto.getPostalCode());
			userEntity.setUserAdress(dto.getUserAddres());
			userEntity.setUserTel(dto.getUserTel());

			//日付変換
			try {
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date userBirth = sdFormat.parse(dto.getUserBirth());
				userEntity.setUserBirth(userBirth);

			} catch (ParseException e) {
					e.printStackTrace();
			}

			userEntity.setUserCard(dto.getUserCard());
			userEntity.setCardName(dto.getCardName());
			userEntity.setLimitDate(dto.getLimitDate());
			userEntity.setSecureCode(dto.getSecureCode());
			userEntity.setAccountType("0");
			userEntity.setSales(0);
			userEntity.setAssessMent(0);





			return userEntity;


	}

	private BankTblEntity change2(UserInfoDto dto) {
		BankTblEntity bankEntity = new BankTblEntity();
		bankEntity.setBankName(dto.getBankName());
		bankEntity.setAccountNumber(dto.getAccountNumber());
		bankEntity.setBranchName(dto.getBranchName());
		bankEntity.setAccountName(dto.getAccountName());
		UserTblEntity entity = userRepository.getUserInfo(dto.getUserMail(),dto.getUserPass());
		BankUserId id = new BankUserId();
		id.setUserTbl(entity);
		bankEntity.setBankUserId(id);

		return bankEntity;

	}


	// entityの値をdtoに入れるメソッド
		private UserInfoDto change3(UserTblEntity userEntity) {

			UserInfoDto userDto = new UserInfoDto();
			userDto.setUserId(userEntity.getUserId());
			userDto.setUserName(userEntity.getUserName());
			userDto.setNickName(userEntity.getNickName());
			userDto.setUserMail(userEntity.getUserMail());
			userDto.setUserPass(userEntity.getUserPass());
			userDto.setPostalCode(userEntity.getPostalCode());
			userDto.setUserAddres(userEntity.getUserAdress());
			userDto.setUserTel(userEntity.getUserTel());
			userDto.setSales(userEntity.getSales());
			userDto.setAssessment(userEntity.getAssessMent());

			//変換
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(userEntity.getUserBirth());
			userDto.setUserBirth(strDate);

			userDto.setUserCard(userEntity.getUserCard());
			userDto.setCardName(userEntity.getCardName());
			userDto.setLimitDate(userEntity.getLimitDate());
			userDto.setSecureCode(userEntity.getSecureCode());



				return userDto;

		}


	//ニックネームを取得する(履歴用）
		public String getNickName(String userId) {

			UserTblEntity userEntity = userRepository.getOne(Integer.parseInt(userId));

			String nickName = userEntity.getNickName();

			return nickName;
		}

	//ユーザーの本名を取得する(履歴用）
		public String getTrueName(int userId) {

			UserTblEntity userEntity = userRepository.getOne(userId);

			String trueName  = userEntity.getUserName();

			return trueName;
		}



}
