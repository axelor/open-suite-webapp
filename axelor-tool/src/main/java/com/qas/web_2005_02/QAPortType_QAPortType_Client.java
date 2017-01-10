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
package com.qas.web_2005_02;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2012-09-12T15:14:19.614+02:00
 * Generated source version: 2.6.2
 * 
 */
public final class QAPortType_QAPortType_Client {

    private static final QName SERVICE_NAME = new QName("http://www.qas.com/web-2005-02", "ProWeb");

    private QAPortType_QAPortType_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = ProWeb.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        ProWeb ss = new ProWeb(wsdlURL, SERVICE_NAME);
        QAPortType port = ss.getQAPortType();  
        
        {
        System.out.println("Invoking doGetLicenseInfo...");
        try {
            com.qas.web_2005_02.QALicenceInfo _doGetLicenseInfo__return = port.doGetLicenseInfo();
            System.out.println("doGetLicenseInfo.result=" + _doGetLicenseInfo__return);

        } catch (Fault e) { 
            System.out.println("Expected exception: Fault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking doGetData...");
        try {
            com.qas.web_2005_02.QAData _doGetData__return = port.doGetData();
            System.out.println("doGetData.result=" + _doGetData__return);

        } catch (Fault e) { 
            System.out.println("Expected exception: Fault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking doGetExampleAddresses...");
        com.qas.web_2005_02.QAGetExampleAddresses _doGetExampleAddresses_body = null;
        try {
            com.qas.web_2005_02.QAExampleAddresses _doGetExampleAddresses__return = port.doGetExampleAddresses(_doGetExampleAddresses_body);
            System.out.println("doGetExampleAddresses.result=" + _doGetExampleAddresses__return);

        } catch (Fault e) { 
            System.out.println("Expected exception: Fault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking doRefine...");
        com.qas.web_2005_02.QARefine _doRefine_body = null;
        try {
            com.qas.web_2005_02.Picklist _doRefine__return = port.doRefine(_doRefine_body);
            System.out.println("doRefine.result=" + _doRefine__return);

        } catch (Fault e) { 
            System.out.println("Expected exception: Fault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking doGetAddress...");
        com.qas.web_2005_02.QAGetAddress _doGetAddress_body = null;
        try {
            com.qas.web_2005_02.Address _doGetAddress__return = port.doGetAddress(_doGetAddress_body);
            System.out.println("doGetAddress.result=" + _doGetAddress__return);

        } catch (Fault e) { 
            System.out.println("Expected exception: Fault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking doSearch...");
        com.qas.web_2005_02.QASearch _doSearch_body = null;
        try {
            com.qas.web_2005_02.QASearchResult _doSearch__return = port.doSearch(_doSearch_body);
            System.out.println("doSearch.result=" + _doSearch__return);

        } catch (Fault e) { 
            System.out.println("Expected exception: Fault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking doCanSearch...");
        com.qas.web_2005_02.QACanSearch _doCanSearch_body = null;
        try {
            com.qas.web_2005_02.QASearchOk _doCanSearch__return = port.doCanSearch(_doCanSearch_body);
            System.out.println("doCanSearch.result=" + _doCanSearch__return);

        } catch (Fault e) { 
            System.out.println("Expected exception: Fault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking doGetLayouts...");
        com.qas.web_2005_02.QAGetLayouts _doGetLayouts_body = null;
        try {
            com.qas.web_2005_02.QALayouts _doGetLayouts__return = port.doGetLayouts(_doGetLayouts_body);
            System.out.println("doGetLayouts.result=" + _doGetLayouts__return);

        } catch (Fault e) { 
            System.out.println("Expected exception: Fault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking doGetPromptSet...");
        com.qas.web_2005_02.QAGetPromptSet _doGetPromptSet_body = null;
        try {
            com.qas.web_2005_02.QAPromptSet _doGetPromptSet__return = port.doGetPromptSet(_doGetPromptSet_body);
            System.out.println("doGetPromptSet.result=" + _doGetPromptSet__return);

        } catch (Fault e) { 
            System.out.println("Expected exception: Fault has occurred.");
            System.out.println(e.toString());
        }
            }
        {
        System.out.println("Invoking doGetSystemInfo...");
        try {
            com.qas.web_2005_02.QASystemInfo _doGetSystemInfo__return = port.doGetSystemInfo();
            System.out.println("doGetSystemInfo.result=" + _doGetSystemInfo__return);

        } catch (Fault e) { 
            System.out.println("Expected exception: Fault has occurred.");
            System.out.println(e.toString());
        }
            }

        System.exit(0);
    }

}
