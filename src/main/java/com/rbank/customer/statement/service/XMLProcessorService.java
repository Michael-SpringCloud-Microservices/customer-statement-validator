package com.rbank.customer.statement.service;


import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rbank.customer.statement.constants.CustomHttpStatusCodes;
import com.rbank.customer.statement.exception.FileParsingException;
import com.rbank.customer.statement.model.RecordDetail;
import com.rbank.customer.statement.model.Records;

/**
 * @author Michael Philomin Raj
 *
 */

@Service
public class XMLProcessorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(XMLProcessorService.class);

	private Unmarshaller unmarshaller;

	/**
	 * 
	 * @param file
	 * @return list of RecordDetail
	 */
	public List<RecordDetail> processXml(MultipartFile file){
		String fileName = file.getOriginalFilename(); 
		try {
			if(this.unmarshaller == null) { 
				buildJaxbUnmarshaller(); 
			}
			Records records = (Records) unmarshaller.unmarshal(file.getInputStream());
			return Arrays.asList(records.getRecordDetails());
		}catch(Exception e) {
			LOGGER.error("XMLProcessorService - Exception at processXml : ", e);
			throw new FileParsingException(CustomHttpStatusCodes.HTTP_STATUS_234,String.join(" ", "Exception at convertXmlToReportDetails - FileName =",fileName,"- Failed due to invalid data"));			
		}
	}

	private void buildJaxbUnmarshaller() throws JAXBException
	{
		JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
		this.unmarshaller = jaxbContext.createUnmarshaller();
	}
}
