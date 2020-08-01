package com.casestudy.restservice.commons;

import com.casestudy.restservice.resources.ProductResourceImpl;
import com.casestudy.serviceContracts.redSky.RedSkyProducts;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.net.ssl.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


@Component
public class RedSkyProductInfoRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResourceImpl.class);

    public static String targetUrlPre = "https://redsky.target.com/v2/pdp/tcin/";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ApplicationContext context;

    /**
     * this method fetches the redsky response from json file and deserializes it
     *
     * @param productId - the product id to fetch
     * @return RedSkyProducts Response object
     */
    public RedSkyProducts queryRedSky(String productId) throws IOException {

        Resource resource = context.getResource("classpath:redSkyProduct.json");
        RedSkyProducts redSkyProducts = objectMapper.readValue(resource.getInputStream(), RedSkyProducts.class);

        return redSkyProducts;

    }


    /**
     * could not work on this in detail as the service is no longer available
     *
     * @param productId - the product id to fetch
     * @return RedSkyProducts Response object
     */
    public RedSkyProducts queryRedSky1(String productId) throws KeyManagementException, NoSuchAlgorithmException {

        final SSLContext context = SSLContext.getInstance("TLSv1");
        final TrustManager[] trustManagerArray = {new NullX509TrustManager()};

        Client client = ClientBuilder.newBuilder()
                .hostnameVerifier(new NullHostnameVerifier())
                .sslContext(context)
                .build();//.newClient();

        context.init(null, trustManagerArray, null);
        WebTarget resource = client.target(targetUrlPre + productId);
        Response response = resource.queryParam("excludes", "taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics").request(MediaType.APPLICATION_JSON)
                .get();
        if (response != null && response.getStatus() == Response.Status.OK.getStatusCode()) {
            LOGGER.info("red Sky call passed with status " + response.getStatus());
            return response.readEntity(RedSkyProducts.class);

        }
        return null;

    }

    private static class NullHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    /**
     * Trust manager that does not perform nay checks.
     */
    private static class NullX509TrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }


}
