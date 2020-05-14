package bit.jrcode.lab.service;

import bit.jrcode.lab.model.HtmlContentModel;
import bit.jrcode.lab.repository.HtmlContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HtmlContentServer {
	@Autowired
	HtmlContentRepository html;

	public List<HtmlContentModel> findHtmlContentByMenuId(Integer menuId) {
		return html.findHtmlContentByMenuId(menuId);
	}

	public HtmlContentModel findHtmlContentById(Integer id) {
		return html.findHtmlContentById(id);
	}

	public List<HtmlContentModel> findHtmlContent(String req) {
		return html.findHtmlContent(req);
	}
}
