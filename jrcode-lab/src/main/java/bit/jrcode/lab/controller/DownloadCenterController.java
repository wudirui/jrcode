package bit.jrcode.lab.controller;

import bit.jrcode.lab.config.MenuConfig;
import bit.jrcode.lab.model.DownloadModel;
import bit.jrcode.lab.service.DownloadServer;
import bit.jrcode.lab.service.MenuServer;
import bit.jrcode.lab.service.SlideshowServer;
import bit.jrcode.lab.utils.CommonTool;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 下载中心
 */
@Controller
public class DownloadCenterController {
	private static final Logger logger = LoggerFactory.getLogger(DownloadCenterController.class);
	@Autowired
	MenuServer menuServer;

	@Autowired
	SlideshowServer slideshowServer;

	@Autowired
	DownloadServer downloadServer;

	@RequestMapping(MenuConfig.DOWNLOAD_CENTER_DATA_PATH)
	public String overview(Model model) {
		setMenuInfo(model, menuServer, MenuConfig.DOWNLOAD_CENTER_DATA, MenuConfig.DOWNLOAD_CENTER_PATH);
		CommonTool.setSlideshow(model, menuServer, slideshowServer, MenuConfig.DOWNLOAD_CENTER_TYPE);
		List<DownloadModel> fileList = downloadServer.findFiles();
		logger.info("the download info is :" + new Gson().toJson(fileList));
		model.addAttribute("fileList", fileList);
		return "download/data_download";
	}

	@RequestMapping(value = MenuConfig.DOWNLOAD_CENTER_FILE_DOWNLOAD_PATH, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<byte[]> downLoad(Integer id) throws IOException {
		logger.info("the download file id is : " + id);
		// 根据id查询文档
		DownloadModel target = downloadServer.findFileById(id);
		logger.info(new Gson().toJson(target));
		String fileName = target.getFilename();

		File file = new File(target.getPath());
		// 创建springframework的HttpHeaders对象
		HttpHeaders headers = new HttpHeaders();
		// 下载显示的文件名，解决中文名称乱码问题
		//String downloadFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
		String downloadFileName = URLEncoder.encode(fileName, "UTF-8");
		// 通知浏览器以attachment（下载方式）打开图片
		headers.setContentDispositionFormData("attachment", downloadFileName);
		// application/octet-stream ： 二进制流数据（最常见的文件下载）。
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 201 HttpStatus.CREATED
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.OK);
	}


	/**
	 * 设置菜单
	 *
	 * @param model      model
	 * @param menuServer 菜单服务
	 * @param curr       当前菜单名称
	 * @param currPath   当前菜单路径
	 */
	public static void setMenuInfo(Model model, MenuServer menuServer, String curr, String currPath) {
		CommonTool.setMenuInfo(model, menuServer, MenuConfig.DOWNLOAD_CENTER_TYPE, MenuConfig.DOWNLOAD_CENTER, MenuConfig.DOWNLOAD_CENTER_PATH, curr, currPath);
	}
}
