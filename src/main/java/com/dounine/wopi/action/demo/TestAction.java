package com.dounine.wopi.action.demo;

import com.dounine.wopi.config.ActException;
import com.dounine.wopi.entity.CheckFileInfo;
import com.dounine.wopi.entity.IFileInfo;
import com.dounine.wopi.result.ActResult;
import com.dounine.wopi.result.Result;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 测试功能
 * Created by lake on 17-4-19.
 */
@RestController
@RequestMapping("test")
public class TestAction {

    @GetMapping("get")
    public Result get(String username) throws ActException {
        if(!"lake".equals(username)){
            throw new ActException("username 必需为lake");
        }
//        System.out.println(username);
        return new ActResult(username);
    }

    @PostMapping("postHeader")
    public Result postHeader(HttpServletRequest request,String username)throws ActException  {
//        System.out.println("token:"+request.getHeader("token"));
//        System.out.println(username);
        return new ActResult("success");
    }

    @PutMapping("putHeader")
    public Result putHeader(HttpServletRequest request,String username)throws ActException  {
//        System.out.println("token:"+request.getHeader("token"));
//        System.out.println(username);
        return new ActResult("success");
    }



    @GetMapping("getHeader")
    public Result getHeader(HttpServletRequest request,String username)throws ActException  {
//        System.out.println("token:"+request.getHeader("token"));
//        System.out.println(username);
        return new ActResult("success");
    }

    @PostMapping("post")
    public Result post(String username,HttpServletRequest request) throws ActException {
//        System.out.println(username);
        if(!"lake".equals(username)){
            throw new ActException("username 必需为lake");
        }
        return new ActResult(username);
    }
    @DeleteMapping("delete")
    public Result delete(String username,HttpServletRequest request) throws ActException {
//        System.out.println(username);
//        System.out.println("token:"+request.getHeader("token"));
        return new ActResult(username);
    }

    @PutMapping("put")
    public Result put(HttpServletRequest request,String username) throws ActException {
//        System.out.println("token:"+request.getHeader("token"));
//        System.out.println(username);
        return new ActResult(username);
    }

    @PostMapping("upload")
    public Result upload(HttpServletRequest request,@RequestParam("files") MultipartFile[] files) throws FileNotFoundException,ActException {
//        System.out.println("token:"+request.getHeader("token"));
        if (null != files) {
            try {
                for(MultipartFile multipartFile : files){
                    multipartFile.transferTo(new File("/home/lake/github/file/" + multipartFile.getOriginalFilename()));
                }
                return new ActResult("success");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return new ActResult("error");
    }

    @GetMapping("download")
    public void upload(String username,HttpServletRequest request,HttpServletResponse response)throws ActException   {
//        System.out.println("token:"+request.getHeader("token"));
//        System.out.println(username);
        File file = new File("/home/lake/github/file/build.gradle");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String("build.gradle"));
        response.addHeader("Content-Length", "" + file.length());
        try {
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            int len = -1;
            byte[] bb = new byte[1024];
            while((len=fis.read(bb))!=-1){
                os.write(bb,0,len);
            }
            os.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
