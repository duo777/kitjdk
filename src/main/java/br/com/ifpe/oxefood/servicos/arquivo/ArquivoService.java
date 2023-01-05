package br.com.ifpe.oxefood.servicos.arquivo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.com.ifpe.oxefood.modelo.empresa.Empresa;
import br.com.ifpe.oxefood.modelo.empresa.EmpresaService;

/**
 * @author Roberto Alencar
 *
 */
@Service
public class ArquivoService {

    @Value("${s3.dir-uploads}")
    private String dirUploadsS3;

    @Value("${s3.bucket-name}")
    private String bucketName;

    @Value("${s3.service-endpoint}")
    private String amazonS3Endpoint;

    @Value("${s3.access-key}")
    private String amazonS3AccessKey;

    @Value("${s3.secret-key}")
    private String amazonS3SecretKey;

    @Autowired
    private EmpresaService empresaService;

    /**
     * Upload image to AWS S3.
     *
     * @param arquivo
     */
    public String uploadToS3(MultipartFile arquivo, String chaveEmpresa) {

	String filepath = null;

	if (arquivo != null && arquivo.getSize() > 0) {

	    String extension = FilenameUtils.getExtension(arquivo.getOriginalFilename());

	    AWSCredentialsProvider awscp = new AWSStaticCredentialsProvider(
		    new BasicAWSCredentials(amazonS3AccessKey, amazonS3SecretKey));

	    AmazonS3 space = AmazonS3ClientBuilder.standard().withCredentials(awscp)
		    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonS3Endpoint, "nyc3"))
		    .build();

	    try {

		byte[] byteimage = arquivo.getBytes();
		InputStream is = new ByteArrayInputStream(byteimage);
		ObjectMetadata om = new ObjectMetadata();
		om.setContentLength(byteimage.length);
		om.setContentType("image/" + extension);

		Empresa empresa = empresaService.findEmpresaByChave(chaveEmpresa);
		String pathEmpresa = empresa.getUsuario().getUsername().toLowerCase().split("@")[0] + "_" + empresa.getId();
		filepath = this.getNomeArquivo(arquivo).replace(" ", "_");
		filepath = dirUploadsS3 + pathEmpresa + "/" + filepath;

		space.putObject(bucketName, filepath, is, om);

		space.getUrl(bucketName, filepath).toString();
		
		filepath = filepath.replace("/", "|");

	    } catch (IOException e) {
		
		e.printStackTrace();
		System.out.println("Falha ao inserir o arquivo: " + arquivo.getOriginalFilename());
		System.out.println(e.getMessage());
		throw new RuntimeException("Falha ao inserir o arquivo.");
	    }
	}

	return filepath;
    }
    
    private String getNomeArquivo(MultipartFile arquivo) {

	String nomeArquivo = null;

	if (arquivo != null && arquivo.getSize() > 0) {
	    nomeArquivo = String.format("%d-%s", new Date().getTime(), tratarEspacosTexto(arquivo.getOriginalFilename()));
	}

	return nomeArquivo;
    }

    public static String tratarEspacosTexto(String texto) {

	String textoTratado = "";

	if (texto != null) {
	    textoTratado = StringUtils.normalizeSpace(texto.trim());
	}

	return textoTratado;
    }
    
}
