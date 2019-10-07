package org.e3.controller;

import org.e3.common.util.FastDFSClient;
import org.e3.common.util.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**图片上传的controller
 * @author xujin
 * @package-name org.e3.controller
 * @createtime 2019-10-06 17:36
 */
@Controller
@RequestMapping("/pic")
public class PictureController {
    @Value("${image_server_url}")
    private String image_server_url;

    @RequestMapping(value = "/upload",produces = MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    @ResponseBody
    public String upLoadFile(MultipartFile uploadFile) {
        FastDFSClient fastDFSClient= null;
        Map<String,Object> map=new HashMap<>();
        try {
            fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
            String originalFileName=uploadFile.getOriginalFilename();
            String extraName=originalFileName.substring(originalFileName.lastIndexOf(".")+1);
            String url=fastDFSClient.uploadFile(uploadFile.getBytes(),extraName);
            url=image_server_url+url;
            map.put("error",0);
            map.put("url",url);
            return JsonUtils.objectToJson(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error",1);
            map.put("message","图片上传失败");
            return JsonUtils.objectToJson(map);
        }

    }

}
