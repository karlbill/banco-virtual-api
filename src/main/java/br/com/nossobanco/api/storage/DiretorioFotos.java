package br.com.nossobanco.api.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.nossobanco.domain.exception.NegocioException;


/**Nome: DiretorioFotos.java
 * Propósito: Classe responsável por armazenar a foto no diretório local configurado no sistema.
 * @author Carlos Guilherme M. B. Abdalla   
 */
@Component
public class DiretorioFotos {

	@Value("${contato.disco.raiz}")
	private String raiz;
	
	@Value("${contato.disco.diretorio-fotos}")
	private String diretorioFotos;
	
	
	/**
	 * Propósito: Recebe o arquivo MultipartFile e carrega o caminho onde a foto deverá ser salva no sistema
	 * @param: foto(MultipartFile, arquivo JPG obrigatório)
	 * @return: retorna o caminho onde a foto foi salva no sistema    
	 */
	public Path salvarFoto(MultipartFile foto) {
		Path caminho = this.salvar(this.diretorioFotos, foto);
		
		return caminho;
	}
	
	/**
	 * Propósito: Salva a foto recebida no caminho especificado no sistema.
	 * @param: diretorio(String, caminho informado no arquivo application.properties)
	 * @param: arquivo(MultipartFile, arquivo JPG obrigatório)
	 * @return: retorna o caminho onde a foto foi salva no sistema    
	 */
	public Path salvar(String diretorio, MultipartFile arquivo) {
		Path diretorioPath = Paths.get(this.raiz, diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());
		
		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());		
			
			return arquivoPath;
		
		} catch (IOException e) {
			throw new NegocioException("Erro ao tentar salvar o arquivo na pasta: "+arquivoPath);
		
		}		
	}
}