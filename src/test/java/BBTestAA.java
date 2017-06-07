import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dounine.wopi.Application;
import com.dounine.wopi.result.ActResult;
import com.google.common.io.Files;
import com.sun.javafx.fxml.builder.URLBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lake on 17-6-3.
 */
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class BBTestAA {

    @Autowired
    private TestRestTemplate testRestTemplate;

//    @Test
    public void postHeader() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","546456456");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake");
        HttpEntity formEntity = new HttpEntity(multiValueMap,headers);
        ActResult result = testRestTemplate.postForObject("/test/postHeader",formEntity,ActResult.class);
        System.out.println(JSON.toJSONString(result));
    }

//    @Test
    public void putHeader() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","546456456");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake");
        HttpEntity formEntity = new HttpEntity(multiValueMap,headers);
        ResponseEntity<ActResult> result = testRestTemplate.exchange("/test/putHeader", HttpMethod.PUT,formEntity,ActResult.class);
        System.out.println(JSON.toJSONString(result.getBody()));
    }

//    @Test
    public void delete() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","546456456");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake");
        HttpEntity formEntity = new HttpEntity(multiValueMap,headers);
        String[] urlVariables = new String[]{"admin"};
        ResponseEntity<ActResult> result = testRestTemplate.exchange("/test/delete?username={username}", HttpMethod.DELETE,formEntity,ActResult.class,urlVariables);
        System.out.println(JSON.toJSONString(result.getBody()));
    }

//    @Test
    public void getHeader() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","546456456");
        HttpEntity formEntity = new HttpEntity(headers);
        String[] urlVariables = new String[]{"admin"};
        ResponseEntity<ActResult> result = testRestTemplate.exchange("/test/getHeader?username={username}", HttpMethod.GET,formEntity,ActResult.class,urlVariables);
        System.out.println(JSON.toJSONString(result.getBody()));
    }

//    @Test
    public void get() throws Exception {
        Map<String,String> multiValueMap = new HashMap<>();
        multiValueMap.put("username","lake");
        ActResult result = testRestTemplate.getForObject("/test/get?username={username}",ActResult.class,multiValueMap);
        System.out.println(JSON.toJSONString(result));
    }

//    @Test
    public void post() throws Exception {
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake");
        ActResult result = testRestTemplate.postForObject("/test/post",multiValueMap,ActResult.class);
        System.out.println(JSON.toJSONString(result));
    }

//    @Test
    public void upload() throws Exception {
        Resource resource = new FileSystemResource("/home/lake/github/wopi/build.gradle");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake");
        multiValueMap.add("files",resource);
        ActResult result = testRestTemplate.postForObject("/test/upload",multiValueMap,ActResult.class);
        System.out.println(JSON.toJSONString(result));
    }

//    @Test
    public void download() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","546456456");
        HttpEntity formEntity = new HttpEntity(headers);
        String[] urlVariables = new String[]{"admin"};
        ResponseEntity<byte[]> response = testRestTemplate.exchange("/test/download?username={1}",HttpMethod.GET,formEntity,byte[].class,urlVariables);
        if (response.getStatusCode() == HttpStatus.OK) {
            Files.write(response.getBody(),new File("/home/lake/github/file/test.gradle"));
        }
    }

}
