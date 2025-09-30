package br.com.projeto.watchthis.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import com.google.genai.errors.ServerException;

public class ConsultaGemini {

        private static final String CHAVE = System.getenv("GEMINI_APIKEY");
        public static String obterTraducao(String texto) {

            try {Client client = Client.builder().apiKey(CHAVE).build();;
                GenerateContentResponse response =
                        client.models.generateContent(
                                "gemini-2.0-flash",
                                "Apenas traduza, sem me dizer nada alem disso, o seguinte trecho: " + texto,
                                null);

                return response.text();
            }catch (ServerException e) {
                System.err.println("Erro 503 (modelo sobrecarregado): " + e.getMessage());
                return texto;
            } catch (Exception e) {

                System.err.println("Erro inesperado na tradução: " + e.getMessage());
                return texto;
            }
            // The client gets the API key from the environment variable `GOOGLE_API_KEY`.

        }
    }





