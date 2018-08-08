package com.lacosdaalegria.transparencia.service.controle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lacosdaalegria.transparencia.model.controle.Carga;
import com.lacosdaalegria.transparencia.model.financeiro.factory.MovimentoFactory;
import com.lacosdaalegria.transparencia.repository.controle.CargaRepository;
import com.lacosdaalegria.transparencia.service.access.UserService;
import com.lacosdaalegria.transparencia.service.financeiro.MovimentoService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CargaService {

	private @NonNull CargaRepository cargaRepository;
	private @NonNull UserService userService;
	private @NonNull MovimentoService movimentoService;
	
	public boolean valida(String mes, String ano) {
		return mes != null && ano != null && 
				mes.length() == 2 && ano.length() == 4
				&& cargaRepository.existsByMesAno(mes+ano);
	}
	
	public void carga(MultipartFile file, String mes, String ano) {
		
		Carga carga = create(mes, ano);
		
		cargaRepository.save(carga);
		
		if(processFile(file, carga)){
			
		} else {
			cargaRepository.delete(carga);
		}
		
	}
	
	private boolean processFile(MultipartFile file, Carga carga) {
		try {
			iterator(convert(file), carga);
			return true;
		} catch (IOException e) {
			return false;
		}
		   
	}
	
	private void iterator(File file, Carga carga) throws IOException {
		
		LineIterator it = FileUtils.lineIterator(file, "UTF-8");
		
		boolean first = true; 
		
		try {
		    
			while (it.hasNext()) {
		    	
		    	if(!first) {
		    		String linha = it.nextLine();
		    		
		    		if(verificaValor(linha)) {
		    			movimentoService.saveCarga(MovimentoFactory.create(linha, carga));
		    		}
		    		
		    	} else {
		    		first = false;
		    		it.nextLine();
		    	}
		    
		    }
			
		} finally {
		    it.close();
		}
		
	}
	
	private boolean verificaValor(String linha) {
		
		String[] infos = linha.split(";");
		
		return !infos[4].equals("\"0.00\"");
		
	}
	
	
	private File convert(MultipartFile file)	{    
		
	    File convFile = new File(file.getOriginalFilename());
	    
	    try {
			convFile.createNewFile();
		    FileOutputStream fos = new FileOutputStream(convFile); 
		    fos.write(file.getBytes());
		    fos.close(); 
	    } catch (IOException e) {
			e.printStackTrace();
		} 
	    return convFile;
	}
	
	private Carga create(String mes, String ano) {
			Carga carga = new Carga();
			carga.setMesAno(mes+ano);
			carga.setUser(userService.getLoggedUser());
			return carga;
	}
	
}
