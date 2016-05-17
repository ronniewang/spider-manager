package com.spider.manager.controller;

import com.spider.utils.FileOperateUtils;
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
        FileOperateUtils.download(response, downloadname);
    }
}
