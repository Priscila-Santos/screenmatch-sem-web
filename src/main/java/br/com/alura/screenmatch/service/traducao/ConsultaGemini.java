package br.com.alura.screenmatch.service.traducao;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import io.github.cdimascio.dotenv.Dotenv;

public class ConsultaGemini{
    public static GenerateContentResponse obterTraducao (String texto) {
        String modeloGemini = "gemini-2.0-flash-lite";
        String prompt = "Traduz o seguinte texto para português brasileiro: " + texto;
//        Dotenv dotenv = Dotenv.load();
//        String apiKeyGemini = dotenv.get("API_KEY_GEMINI");
//        Client client = new Client.Builder().apiKey(apiKeyGemini).build();

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
