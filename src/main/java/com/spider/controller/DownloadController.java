package com.spider.controller;

import com.spider.utils.FileOperateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author wsy
 *
 */
@Controller
public class DownloadController {

    @RequestMapping("download")
    public void download(@RequestParam String downloadname, HttpServletResponse response) throws Exception {

        downloadname = downloadname.replaceAll("\"", "");
        FileOperateUtils.download(response, downloadname);
    }
}