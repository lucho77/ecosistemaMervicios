package com.examenes.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.examenes.constants.ExamenesConstants;
import com.examenes.dto.ImpresionGenericaDTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class ReportServiceImpl implements ReportService {
	Logger log = LoggerFactory.getLogger(ReportServiceImpl.class);
	
	@Value("${jasper.report.path}")
	private String jrRepoPath;
    @Autowired
    private ModelMapper modelMapper; 

	@Override
	public boolean geterateReport( ImpresionGenericaDTO genericaDTO) {

		try 
		{
			Class c = Class.forName("com.examenes.dto."+genericaDTO.getClassDTO());
			List<Object> list = new ArrayList<>();
			for(Object object:genericaDTO.getList() ) {
				Object pojo = modelMapper.map(object, c);
				list.add(pojo);
				log.info("entro al dto");
			}

			
			File file = ResourceUtils.getFile("classpath:jasper/ejemploA4.jrxml");
			
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
			
			Map<String, Object>map = new HashMap<>();
			map.put("archivoMaqueta",genericaDTO.getArchivoMaqueta() );
			map.put("archivoFinal",genericaDTO.getArchivoFinal() );
			map.put("tipo",genericaDTO.getTipo() );
			URL urlLogo =  new URL("classpath:imagenes/logoRUS.jpg");
			map.put("logo",urlLogo );

			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
			
			switch (genericaDTO.getTipo())
			{
				case ExamenesConstants.TIPO_PDF:
					JasperExportManager.exportReportToPdfFile(jasperPrint, jrRepoPath + "/ejemplo.pdf");
					break;
				case ExamenesConstants.TIPO_XLS :
					break;				
			}			
		} 
		catch (FileNotFoundException e)
		{
			log.error("FileNotFoundException : ", e.getMessage(), e.getCause());
		} 
		catch (JRException e) 
		{
			log.error("JRException : ", e.getMessage(), e.getCause());
		}		
		catch (ClassNotFoundException e) 
		{
			log.error("JRException : ", e.getMessage(), e.getCause());
		}		
		catch (MalformedURLException e) 
		{
			log.error("URL exception : ", e.getMessage(), e.getCause());
		}		
		return true;
	
		
	}

	
}
