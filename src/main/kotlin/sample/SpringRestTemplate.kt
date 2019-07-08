package sample

import org.springframework.http.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


class SpringRestTemplate {

    fun getForObject(): String? {
        val restTemplate = RestTemplate()
        // SampleとしてQiitaApiを使用する
        return restTemplate.getForObject("URL?locale=ja", String::class.java)
    }


    fun getForObjectWithParam(): String? {
        val uriVariables = mutableMapOf<String, String>()
        uriVariables.put("hoge", "hoge")
        uriVariables.put("huga", "huga")
        val restTemplate = RestTemplate()
        // SampleとしてQiitaApiを使用する
        return restTemplate.getForObject("URL/{hoge}?huga={huga}", String::class.java, uriVariables)
    }

    fun exchangeGet(): String {
        val headers = HttpHeaders()
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE)

        val builder = UriComponentsBuilder.fromHttpUrl("URL")
                .queryParam("locale", "ja")

        val entity = HttpEntity<String>(headers)

        val restTemplate = RestTemplate()
        val response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String::class.java
        )
        return response.body
    }

    fun postForObject(): String? {
        val uriVariables = mutableMapOf<String, String>()

        val restTemplate = RestTemplate()
        // SampleとしてQiitaApiを使用する 404が返るが実行できることを確認
        return restTemplate.postForObject("URL", "request", String::class.java)
    }

    fun exchangePost(): String? {
        val headers = HttpHeaders()
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE)

        val builder = UriComponentsBuilder.fromHttpUrl("URL")

        val entity = HttpEntity<String>(headers)
        val requestEntity = RequestEntity
                .post(builder.build().encode().toUri())
                .body<String>("")
        val restTemplate = RestTemplate()
        val response = restTemplate.exchange(requestEntity, String::class.java)

        return response.body
    }
}