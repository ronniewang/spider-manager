package com.spider.manager.controller;

import com.spider.utils.FileDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * spider-web的导出文件功能都使用这个接口
 *
 * @author ronnie
 */
@Controller
public class DownloadController {

    @Autowired
    private FileDownloader fileDownloader;

    /**
     * 下载文件
     *
     * @param downloadname
     * @param response
     * @throws Exception
     */
    @RequestMapping("download")
    public void download(@RequestParam String downloadname, HttpServletResponse response) throws Exception {

        downloadname = downloadname.replaceAll("\"", "");
        fileDownloader.download(response, downloadname);
    }
}
