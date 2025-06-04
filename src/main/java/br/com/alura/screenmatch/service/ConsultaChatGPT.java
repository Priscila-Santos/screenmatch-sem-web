package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService("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");

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}