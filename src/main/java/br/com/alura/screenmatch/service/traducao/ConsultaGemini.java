package br.com.alura.screenmatch.service.traducao;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class ConsultaGemini{
    public static GenerateContentResponse obterTraducao (String texto) {
        String modeloGemini = "gemini-2.0-flash-lite";
        String prompt = "Traduz o seguinte texto para português brasileiro: " + texto;

       Client client = new Client.Builder().apiKey(System.getenv("GEMINI_APIKEY")).build();

        GenerateContentResponse response;
        try{
            response = client.models.generateContent(
                    modeloGemini,
                    prompt,
                    null); // parâmetro de configurações adicionais
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
