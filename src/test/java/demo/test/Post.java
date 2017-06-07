package demo.test;

import com.alibaba.fastjson.JSON;
import com.dounine.wopi.Application;
import com.dounine.wopi.result.ActResult;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
public class Post {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @After
    public void dataClear(){//测试数据清空

    }

    @Test
    public void normal() throws Exception {//对于新增的数据必需执行删除
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake");
        ActResult result = testRestTemplate.postForObject("/test/post",multiValueMap,ActResult.class);
        Assert.assertEquals("lake",result.getMsg());
    }



    @Test
    public void abnormal() throws Exception {
        MultiValueMap multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("username","lake1");
        ActResult result = testRestTemplate.postForObject("/test/post",multiValueMap,ActResult.class);
        Assert.assertEquals(1,result.getCode());
    }

}
