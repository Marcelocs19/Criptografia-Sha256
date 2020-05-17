package br.com.pucrs;

import java.util.List;

import br.com.pucrs.sha256.CryptographySHA256;

public class App {

	public static void main(String[] args) {
		CryptographySHA256 c = new CryptographySHA256();

		try {
			List<byte[]> divideEGeraArquivoSha = c.divideEGeraArquivoSha("video1.mp4");
			c.gravaArquivoResultado(divideEGeraArquivoSha);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
