package org.sattrack.sattracker_harvestdb.Services;

import org.sattrack.sattracker_harvestdb.Records.CelestrakSatelliteData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {

    private final WebClient webClient;
    private final String format = "json";

    public ApiService(@Value("${api.base-url}") String baseUrl, WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                            .baseUrl(baseUrl)
                            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                            .build();
    }

    public Mono<CelestrakSatelliteData[]> getSatelliteGroupData(String group) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/NORAD/elements/gp.php")
                        .queryParam("GROUP", group)
                        .queryParam("FORMAT", format)
                        .build())
                .retrieve()
                .bodyToMono(CelestrakSatelliteData[].class);
    }
}
