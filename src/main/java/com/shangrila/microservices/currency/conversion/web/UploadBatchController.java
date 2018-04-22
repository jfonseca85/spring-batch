package com.shangrila.microservices.currency.conversion.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shangrila.microservices.currency.conversion.service.batch.BatchJobManagerService;


@RestController
public class UploadBatchController {

	@Autowired
	@Resource(name = "valueSetJobManager")
	private BatchJobManagerService valueSetJobManagerService;

	@RequestMapping(value = "/valueSet/batchUpload", method = RequestMethod.GET)
	public void batchValueSetUpload() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject("http://www4.bcb.gov.br/Download/fechamento/20180420.csv", String.class);
		System.out.println(response);
//		valueSetJobManagerService.batchUpload("20180420.csv");
		valueSetJobManagerService.batchUpload(response);
	}
	

}
