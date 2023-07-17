package com.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Sarvesh Kumar
 * Provide update and download server in the application.
 */

@RestController
@CrossOrigin("http://localhost:3000")
public class UploadDownloadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadDownloadController.class);
    
    public static StringBuilder data_quocgia = new StringBuilder();   
    public static StringBuilder data_sanpham = new StringBuilder();
    public static StringBuilder data_transaction = new StringBuilder();
    public static StringBuilder data_tongdoanhthu = new StringBuilder();
    public static StringBuilder data_top5customer = new StringBuilder();
    public static StringBuilder customers = new StringBuilder();
    
    public UploadDownloadController() {
    }

    @GetMapping({"/download/{fileName:.+}"})
    public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
        String content="";
        try {
            InputStream resource1 = new ClassPathResource("data/"+fileName).getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource1));
            content = reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return ((BodyBuilder)ResponseEntity.ok().header("Content-Disposition", new String[]{"attachment; filename=\"" + fileName + "\""})).body(content);
    }

    @PostMapping({"/upload_quocgia"})
    public void upload(@RequestParam MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer lines = new StringBuffer("");
            lines.append(bufferedReader.readLine());
            
            
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                lines.append("\n"+line);
                
            }
            bufferedReader.close();

            logger.info("lines: {}", lines.toString());
            data_quocgia.append(lines.toString());
            
            
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }
    
    @PostMapping({"/upload_sanpham"})
    public void upload1(@RequestParam MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer lines = new StringBuffer("");
            lines.append(bufferedReader.readLine());
            
            
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                lines.append("\n"+line);
                
            }
            bufferedReader.close();

            logger.info("lines: {}", lines.toString());
            data_sanpham.append(lines.toString());
            
            
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }
    
    @PostMapping({"/upload_transaction"})
    public void upload2(@RequestParam MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer lines = new StringBuffer("");
            lines.append(bufferedReader.readLine());
            
            
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                lines.append("\n"+line);
                
            }
            bufferedReader.close();

            logger.info("lines: {}", lines.toString());
            data_transaction.append(lines.toString());
            
            
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }
    
    @PostMapping({"/upload_tongdoanhthu"})
    public void upload3(@RequestParam MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer lines = new StringBuffer("");
            lines.append(bufferedReader.readLine());
            
            
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                lines.append("\n"+line);
                
            }
            bufferedReader.close();

            logger.info("lines: {}", lines.toString());
            data_tongdoanhthu.append(lines.toString());
            
            
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }
    
    @PostMapping({"/upload_customer"})
    public void upload4(@RequestParam MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer lines = new StringBuffer("");
            lines.append(bufferedReader.readLine());
            
            
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                lines.append("\n"+line);
                
            }
            bufferedReader.close();

            logger.info("lines: {}", lines.toString());
            data_top5customer.append(lines.toString());
            
            
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }
    
    @PostMapping({"/upload_customers"})
    public void upload5(@RequestParam MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer lines = new StringBuffer("");
            lines.append(bufferedReader.readLine());
            
            
            for(String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                lines.append("\n"+line);
                
            }
            bufferedReader.close();

            logger.info("lines: {}", lines.toString());
            customers.append(lines.toString());
            
            
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }

    
    @GetMapping({"/data_quocgia"})
    public List<DataModel> getData() {
    	//
    	List<DataModel> datamodels = new ArrayList<>();
    	
    	String temp = data_quocgia.toString();
    	
    	String[] dong = temp.split("\n");
    	
    	for (String w : dong) {
    		
    		DataModel datamodel = new DataModel();
    		
    		String[] tachdong = w.split("\t");
    		
    		datamodel.setCountry(tachdong[0]);
    		datamodel.setTotalprice(tachdong[1]);
    		
    		datamodels.add(datamodel);
    		
    	}
    	
    	
        //String result = data.toString();
        //data = new StringBuilder();
        
        //logger.info("haha: {}", result);
        return datamodels;
    }
    
    @GetMapping({"/data_sanpham"})
    public List<DataModel> getData1() {
    	//
    	List<DataModel> datamodels = new ArrayList<>();
    	
    	String temp = data_sanpham.toString();
    	
    	String[] dong = temp.split("\n");
    	
    	for (String w : dong) {
    		
    		DataModel datamodel = new DataModel();
    		
    		String[] tachdong = w.split("\t");
    		
    		datamodel.setProductname(tachdong[0]);
    		datamodel.setQuantity(tachdong[1]);
    		datamodel.setTotalprice(tachdong[2]);
    		
    		datamodels.add(datamodel);
    		
    	}
        return datamodels;
    }
    
    @GetMapping({"/data_transaction"})
    public List<DataModel> getData2() {
    	//
    	List<DataModel> datamodels = new ArrayList<>();
    	
    	String temp = data_transaction.toString();
    	
    	String[] dong = temp.split("\n");
    	
    	for (String w : dong) {
    		
    		DataModel datamodel = new DataModel();
    		
    		String[] tachdong = w.split("\t");
    		
    		datamodel.setId(tachdong[0]);
    		datamodel.setQuantity(tachdong[1]);
    		datamodel.setTotalprice(tachdong[2]);
    		
    		datamodels.add(datamodel);
    		
    	}
        return datamodels;
    }

    
    @GetMapping({"/data_tongdoanhthu"})
    public List<DataModel> getData3() {
    	//
    	List<DataModel> datamodels = new ArrayList<>();
    	
    	String temp = data_tongdoanhthu.toString();
    	
    	String[] dong = temp.split("\n");
    	
    	for (String w : dong) {
    		
    		DataModel datamodel = new DataModel();
    		
    		String[] tachdong = w.split("\t");
    		
    		datamodel.setDate(tachdong[0]);
    		datamodel.setTotalprice(tachdong[1]);
    		
    		datamodels.add(datamodel);
    		
    	}
        return datamodels;
    }
    
    @GetMapping({"/data_customer"})
    public List<DataModel> getData4() {
    	//
    	List<DataModel> datamodels = new ArrayList<>();
    	
    	String temp = data_top5customer.toString();
    	
    	String[] dong = temp.split("\n");
    	
    	for (String w : dong) {
    		
    		DataModel datamodel = new DataModel();
    		
    		String[] tachdong = w.split("\t");
    		
    		datamodel.setUser(tachdong[0]);
    		datamodel.setQuantity(tachdong[1]);
    		datamodel.setTotalprice(tachdong[2]);
    		
    		datamodels.add(datamodel);
    		
    	}
        return datamodels;
    }
    
    @GetMapping({"/data_customers"})
    public List<DataModel> getData5() {
    	//
    	List<DataModel> datamodels = new ArrayList<>();
    	
    	String temp = customers.toString();
    	
    	String[] dong = temp.split("\n");
    	
    	for (int i = 1; i < dong.length; i++) {
    		
    		DataModel datamodel = new DataModel();
    		
    		String[] tachdong = dong[i].split(",");
    		
    		datamodel.setUser(tachdong[0]);
    		datamodel.setQuantity(tachdong[1]);
    		datamodel.setTotalprice(tachdong[2]);
    		datamodel.setPrediction(tachdong[3]);
    		
    		datamodels.add(datamodel);
    		
    	}
        return datamodels;
    }
}
