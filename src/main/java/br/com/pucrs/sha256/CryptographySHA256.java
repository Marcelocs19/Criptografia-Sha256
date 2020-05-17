package br.com.pucrs.sha256;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class CryptographySHA256 {


	public List<byte[]> divideEGeraArquivoSha(String nomeArquivo) throws Exception {
		List<byte[]> arquivoDivido = new ArrayList<>();
		List<byte[]> arquivoSha = new ArrayList<>();

		try (InputStream leitor = new FileInputStream(nomeArquivo)) {
			byte[] buffer;

			while (leitor.available() > 0) {
				if (leitor.available() < 1024) {
					buffer = new byte[leitor.available()];
				} else {
					buffer = new byte[1024];
				}
				leitor.read(buffer);
				arquivoDivido.add(buffer);
			}

			MessageDigest mensagem = MessageDigest.getInstance("SHA-256");

			for (byte[] buffers : arquivoDivido) {
				arquivoSha.add(mensagem.digest(buffers));
			}
			return arquivoSha;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arquivoSha;
	}

	public void gravaArquivoResultado(List<byte[]> arquivo) {
		try {
			Files.deleteIfExists(Paths.get("resultado"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < arquivo.size(); i++) {
			gravaLinha(arquivo.get(i));
		}

	}

	private void gravaLinha(byte[] linhaDeBytes) {
		String linha = "";
		try (PrintWriter escrever = new PrintWriter(new BufferedWriter(new FileWriter("resultado", true)))) {
			for (int i = 0; i < linhaDeBytes.length; i++) {
				linha += Integer.toString((linhaDeBytes[i] & 0xff) + 0x100, 16).substring(1);
			}
			escrever.println(linha);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
