package demo.test;

import com.alibaba.fastjson.JSON;
import com.dounine.wopi.Application;
import com.dounine.wopi.result.ActResult;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lake on 17-6-6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class Get {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void dataInit(){//前期数据初始化
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake");
        ActResult result = testRestTemplate.postForObject("/test/post",multiValueMap,ActResult.class);
        Assert.assertEquals(result.getCode(),0);
        System.out.println("init");
    }

    @After
    public void dataClear(){//测试数据清空
        HttpHeaders headers = new HttpHeaders();
        headers.set("token","546456456");
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake");
        HttpEntity formEntity = new HttpEntity(multiValueMap,headers);
        String[] urlVariables = new String[]{"admin"};
        ResponseEntity<ActResult> responseEntity = testRestTemplate.exchange("/test/delete?username={username}", HttpMethod.DELETE,formEntity,ActResult.class,urlVariables);
        Assert.assertEquals(responseEntity.getBody().getCode(),0);
        System.out.println("clear");
    }

    @Test
    public void normal() throws Exception {
        Map<String,String> multiValueMap = new HashMap<>();//传值
        multiValueMap.put("username","lake");
        ActResult result = testRestTemplate.getForObject("/test/get?username={username}",ActResult.class,multiValueMap);
        Assert.assertEquals("lake",result.getMsg());
    }

    @Test
    public void abnormal() throws Exception {
        Map<String,String> multiValueMap = new HashMap<>();//传值
        multiValueMap.put("username","lake");
        ActResult result = testRestTemplate.getForObject("/test/get",ActResult.class,multiValueMap);
        Assert.assertEquals(1,result.getCode());
    }

}
