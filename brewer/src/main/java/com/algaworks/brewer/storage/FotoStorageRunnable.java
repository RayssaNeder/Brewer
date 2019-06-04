package com.algaworks.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.brewer.dto.FotoDTO;

public class FotoStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<FotoDTO> resultado;

	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<FotoDTO> resultado) {
		this.files = files;
		this.resultado = resultado;
	}

	@Override
	public void run() {
		System.out.println("File >>>> " + files[0].getName() + " " + files[0].getSize());
		//TODO: Salvar foto no sistema de arquivos
		String nome = files[0].getOriginalFilename();
		String tipo = files[0].getContentType();
		
		resultado.setResult(new FotoDTO(nome, tipo));
	}

}