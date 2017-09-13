package com.axelor.apps.fnath.db.repo;

import javax.persistence.PersistenceException;

import com.axelor.apps.account.db.repo.PartnerAccountRepository;
import com.axelor.apps.base.db.IAdministration;
import com.axelor.apps.base.db.Partner;
import com.axelor.apps.base.exceptions.IExceptionMessage;
import com.axelor.apps.base.service.administration.SequenceService;
import com.axelor.i18n.I18n;
import com.axelor.inject.Beans;
import com.google.common.base.Strings;

public class PartnerFnathRepository extends PartnerAccountRepository {
	
	@Override
	public Partner save(Partner partner) {
		try {

			if (partner.getPartnerCategorySelect().contains("1") && Strings.isNullOrEmpty(partner.getNumberSeq()) ) {
				
				String seq = "";
				//Groupement
				if (partner.getMembreCompany() != null) { seq += partner.getMembreCompany().getCode(); }
				
				seq += Beans.get(SequenceService.class).getSequenceNumber("partnerNumber");
				
				if (partner.getMembreSection() != null) { seq += partner.getMembreSection().getSectionCode(); }
				
				partner.setNumberSeq(seq);
			}
			

			return super.save(partner);
		} catch (Exception e) {
			throw new PersistenceException(e.getLocalizedMessage());
		}
	}
	

}
