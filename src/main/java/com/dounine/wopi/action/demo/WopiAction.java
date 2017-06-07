package com.dounine.wopi.action.demo;

import com.dounine.wopi.entity.CheckFileInfo;
import com.dounine.wopi.entity.IFileInfo;
import com.dounine.wopi.result.ActResult;
import com.dounine.wopi.result.Result;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 测试功能
 * Created by lake on 17-4-19.
 */
@RestController
@RequestMapping("wopi")
public class WopiAction {

    @GetMapping("test")
    public Result test(){
        ActResult actResult = new ActResult();
        actResult.setData(new CheckFileInfo());
        actResult.setMsg("success");
        return actResult;
    }

    @PostMapping("upload")
    public Result upload(@RequestParam("files") MultipartFile[] files) throws FileNotFoundException {
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


    @GetMapping("files/{fileName}")
    public IFileInfo fileInfo(@PathVariable String fileName) {
        CheckFileInfo fileInfo = new CheckFileInfo();
        fileInfo.setBaseFileName("123.xlsx");
        File file = new File("C:/Users/Administrator/Desktop/WOPI/WopiHost/App_Data/test.xlsx");
        fileInfo.setSize(file.length() + "");
        fileInfo.setOwnerId("admin");
        fileInfo.setVersion(file.lastModified() + "");
        try {
            fileInfo.setSHA256(Hex.encodeHexString(DigestUtils.sha256(new FileInputStream(file))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInfo;
    }

    @GetMapping("files/{fileName}/contents")
    public void contents(@PathVariable String fileName, HttpServletResponse response) throws FileNotFoundException, UnsupportedEncodingException {
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
        File file = new File("C:/Users/Administrator/Desktop/WOPI/WopiHost/App_Data/test.xlsx");
        response.addHeader("Content-Length", "" + file.length());
        response.setContentType("application/octet-stream");
        InputStream fis = new FileInputStream(file);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = fis.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            os.flush();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
