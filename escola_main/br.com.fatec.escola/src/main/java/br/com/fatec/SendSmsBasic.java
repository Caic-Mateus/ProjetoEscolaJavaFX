package br.com.fatec;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
 
/**
 * Send an SMS message directly by calling HTTP endpoint.
 *
 * THIS CODE EXAMPLE IS READY BY DEFAULT. HIT RUN TO SEND THE MESSAGE!
 *
 * Send SMS API reference: https://www.infobip.com/docs/api#channels/sms/send-sms-message
 * See Readme file for details.
 */
public class SendSmsBasic {
 
    private static final String BASE_URL = "https://j3q59n.api.infobip.com";
    private static final String API_KEY = "App 5e4c91cfbef865a5b53cfb5378315a19-aa816813-d6fc-4f2f-b27d-0af81d3eb51a";
    private static final String MEDIA_TYPE = "application/json";

    private static final String SENDER = "CINETEC";


    public void send(String numeroCliente, String nomeCliente, String poltronas, String nomeFilme, String hora) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        poltronas = poltronas.replace(",", "");
        String mensagem = String.format("Oii, %s. Voce reservou ingressos do Cinetec para ver o filme %s. \\nHorário da sessão: %s \\nPoltronas reservadas: %s", nomeCliente, nomeFilme, hora, poltronas);
        
 
        String bodyJson = String.format("{\"messages\":[{\"from\":\"%s\",\"destinations\":[{\"to\":\"%s\"}],\"text\":\"%s\"}]}",
                SENDER,
                numeroCliente,
                mensagem
        );
 
        MediaType mediaType = MediaType.parse(MEDIA_TYPE);
        RequestBody body = RequestBody.create(bodyJson, mediaType);
 
        Request request = prepareHttpRequest(body);
        Response response = client.newCall(request).execute();
 
        System.out.println("HTTP status code: " + response.code());
        System.out.println("Response body: " + response.body().string());
    }
 
    private static Request prepareHttpRequest(RequestBody body) {
        return new Request.Builder()
                .url(String.format("%s/sms/2/text/advanced", BASE_URL))
                .method("POST", body)
                .addHeader("Authorization", API_KEY)
                .addHeader("Content-Type", MEDIA_TYPE)
                .addHeader("Accept", MEDIA_TYPE)
                .build();
    }
}

