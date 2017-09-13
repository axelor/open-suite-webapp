package com.axelor.apps.fnath.service;


import java.time.LocalDate;

import com.axelor.apps.base.service.app.AppBaseService;
import com.axelor.apps.fnath.db.FnathAttachment;
import com.axelor.apps.fnath.db.NumberFile;
import com.axelor.apps.fnath.db.NumberFileTransfer;
import com.axelor.apps.fnath.db.repo.NumberFileRepository;
import com.axelor.apps.fnath.db.repo.NumberFileTransferRepository;
import com.axelor.inject.Beans;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class NumberFileService {
	
	@Inject
	private NumberFileRepository numberFileRepo;
	
	@Inject
	private NumberFileTransferRepository numberFileTransferRepo;
	
	@Transactional
	public void transfer(NumberFile numberFile, int desiredStatus){
		
		numberFile.setStatusSelect(desiredStatus == NumberFileTransferRepository.TYPE_TRANSFER ? NumberFileRepository.STATUS_TRANFERED : NumberFileRepository.STATUS_RETURNED);
		
		NumberFileTransfer transfer = createNumberFileTransfer(numberFile, null, desiredStatus);
		
		numberFileRepo.save(numberFile);
		numberFileTransferRepo.save(transfer);
		
	}
	
	
	public NumberFileTransfer createNumberFileTransfer(NumberFile file, LocalDate date, int status){
		
		NumberFileTransfer transfer = new NumberFileTransfer();
		transfer.setNumberFile(file);
		transfer.setTransferDate(date == null ? Beans.get(AppBaseService.class).getTodayDate() : date);
		transfer.setComments( file.getComments() );
		transfer.setTypeSelect(status);
		transfer.setCompany(file.getCompany());
		if (file.getAttachments() != null && !file.getAttachments().isEmpty()){
			//MetaFileRepository repo = Beans.get(MetaFileRepository.class);
			for (FnathAttachment attachment : file.getAttachments()) {
				FnathAttachment copy = new FnathAttachment();
				copy.setMetaFile( /*repo.copy(*/attachment.getMetaFile()/*, false) */ );
				copy.setName(attachment.getName());
				transfer.addAttachment(copy);
			}
		}
		
		
		return transfer;
		
	}
	
	@Transactional
	public void onGoing(NumberFile numberFile){
		
		numberFile.setStatusSelect( NumberFileRepository.STATUS_ONGOING );
		numberFileRepo.save(numberFile);
	}
	
	@Transactional
	public void closing(NumberFile numberFile){
		
		numberFile.setStatusSelect( NumberFileRepository.STATUS_CLOSED );
		numberFileRepo.save(numberFile);
	}

}
