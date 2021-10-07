package netty.client;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpRequestTest {
    public static void main(String[] args) throws IOException {
        final List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("netty", "true"));
        final CloseableHttpClient client = HttpClientBuilder.create().setDefaultHeaders(headers).build();
        final CloseableHttpResponse response = client.execute(new HttpGet("http://127.0.0.1:8888/"));
        for (Header allHeader : response.getAllHeaders()) {
            System.out.println("Header name:"+allHeader.getName()+" value:"+allHeader.getValue());
        }
        final HttpEntity entity = response.getEntity();
        System.out.println("contentType : "+entity.getContentType());
        System.out.println("contentLength" + entity.getContentLength());
        System.out.println("content byte :" + entity.getContent().toString());
        final InputStreamReader stream = new InputStreamReader(entity.getContent());
        final BufferedReader reader = new BufferedReader(stream);
        final StringBuilder builder = new StringBuilder();
        String i;
        while ((i = reader.readLine()) != null) {
            builder.append(i);
        }
        client.close();
        reader.close();
        stream.close();
        System.out.println(builder.toString());
    }
}
