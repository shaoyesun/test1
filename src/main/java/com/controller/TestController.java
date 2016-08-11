package com.controller;

import com.log.Log;
import com.remote.RemoteDemo;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by sunjf on 2016/6/26.
 */
@Controller
public class TestController {

    //@Log(desc = "log测试")
    @RequestMapping(value = "test1")
    public String test1(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "sun");
        jsonObject.put("password", "123");
        //String result = RemoteDemo.remoteJsonRequest("http://localhost:8080/test2?username=sun&password=123", 5000, jsonObject);
        //System.out.println(result);
        return "success";
    }

    @RequestMapping(value = "test2")
    public String test2(){
        System.out.println("test2");
        return "index";
    }

    public static void main(String[] args) {
        HttpServletResponse response = null;
        try {
            // path是指欲下载的文件的路径。
            File file = new File("http://e.vhall.com/export/fcode/828356316");
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream("http://e.vhall.com/export/fcode/828356316"));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            // read file content from file
            StringBuffer sb= new StringBuffer("");

            FileReader reader = new FileReader("/home/sunjf/webinar_fcode_323151277_201607040851.csv");
            BufferedReader br = new BufferedReader(reader);

            String str = null;

            while((str = br.readLine()) != null) {
                sb.append(str+"/n");

                System.out.println(str);
            }

            br.close();
            reader.close();

            // write string to file
            /*FileWriter writer = new FileWriter("c://test2.txt");
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(sb.toString());

            bw.close();
            writer.close();*/
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}

