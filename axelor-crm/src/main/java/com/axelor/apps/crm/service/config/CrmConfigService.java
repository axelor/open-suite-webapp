/**
 * Axelor Business Solutions
 *
 * Copyright (C) 2016 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.crm.service.config;

import com.axelor.apps.base.db.Company;
import com.axelor.apps.crm.db.CrmConfig;
import com.axelor.apps.crm.exception.IExceptionMessage;
import com.axelor.exception.AxelorException;
import com.axelor.exception.db.IException;
import com.axelor.i18n.I18n;

public class CrmConfigService {
	public CrmConfig getCrmConfig(Company company) throws AxelorException  {
		CrmConfig crmConfig = company.getCrmConfig();

		if(crmConfig == null)  {
			throw new AxelorException(String.format(I18n.get(IExceptionMessage.CRM_CONFIG_1), company.getName()),IException.CONFIGURATION_ERROR);
		}
		return crmConfig;
	}

}
