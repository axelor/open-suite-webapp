package com.axelor.apps.fnath.web;

import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;
import com.axelor.apps.fnath.db.NumberFile;
import com.axelor.apps.fnath.db.repo.NumberFileRepository;
import com.axelor.apps.fnath.db.repo.NumberFileTransferRepository;
import com.axelor.apps.fnath.service.NumberFileService;

public class NumberFileController {
	
	@Inject
	private NumberFileRepository numberFileRepo;
	
	@Inject
	private NumberFileService numberFileService;
	
	
	public void transferFile(ActionRequest request, ActionResponse response){
		
		NumberFile file = numberFileRepo.find( request.getContext().asType(NumberFile.class).getId() ) ;
		numberFileService.transfer( file, NumberFileTransferRepository.TYPE_TRANSFER );
		response.setReload(true);
		
	}
	
	public void returnFile(ActionRequest request, ActionResponse response){
		
		NumberFile file = numberFileRepo.find( request.getContext().asType(NumberFile.class).getId() ) ;
		numberFileService.transfer( file, NumberFileTransferRepository.TYPE_RETURN );
		response.setReload(true);
		
	}
	
	public void onGoing(ActionRequest request, ActionResponse response){
		
		NumberFile file = numberFileRepo.find( request.getContext().asType(NumberFile.class).getId() ) ;
		numberFileService.onGoing(file);
		response.setReload(true);
		
	}

	public void closing(ActionRequest request, ActionResponse response){
		
		NumberFile file = numberFileRepo.find( request.getContext().asType(NumberFile.class).getId() ) ;
		numberFileService.closing(file);
		response.setReload(true);
	}
}
