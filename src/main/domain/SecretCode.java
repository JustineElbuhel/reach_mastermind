package main.domain;

import java.io.FileInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.Random;

public class SecretCode {
    private int difficulty;
    private String secretCode;
    private static final String CONFIG_FILE = "config.properties";

    public SecretCode(int difficulty){
        this.difficulty = difficulty;
    }

    public String generateSecretCode() throws Exception{
        String secretCode = "";
        String API_TOKEN = getApiToken();
        for(int i=0; i < difficulty; i++){
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://www.random.org/integers/?num=1&min=0&max=7&col=1&base=10&format=plain&rnd" +
                            "=new"))
                    .header("Authorization", "Bearer " + API_TOKEN)
                    .GET()
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200){
                secretCode += response.body().trim();
            } else {
                System.out.println("Failed to generate code through API, generating code locally.");
                secretCode = "";
                secretCode = locallyGenerateSecretCode(secretCode);
                break;
            }
        }
        this.secretCode = secretCode;
        return secretCode;
    }

    public String locallyGenerateSecretCode(String secretCode){
        Random random = new Random();

        for(int i = 0; i < difficulty; i++){
            int randomN = random.nextInt(8);
            secretCode += randomN;
        }
        return secretCode;
    }

    public static String getApiToken(){
        Properties props = new Properties();
        try(FileInputStream inputStream = new FileInputStream(CONFIG_FILE)){
            props.load(inputStream);
            return props.getProperty("API_TOKEN");
        } catch (Exception e){
            System.out.println("Failed to load API token: " + e.getLocalizedMessage());
            return null;
        }
    }
}
