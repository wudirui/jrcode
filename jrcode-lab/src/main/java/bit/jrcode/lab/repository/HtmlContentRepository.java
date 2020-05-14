package bit.jrcode.lab.repository;

import bit.jrcode.lab.model.HtmlContentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HtmlContentRepository extends JpaRepository<HtmlContentModel, Long> {
	@Query("select htmls from HtmlContentModel htmls where htmls.menuId=?1")
	List<HtmlContentModel> findHtmlContentByMenuId(Integer menuId);

	@Query("select html from HtmlContentModel  html where html.id = ?1")
	HtmlContentModel findHtmlContentById(Integer id);

	@Query("select content from HtmlContentModel content where content.title like %?1% or content.content like %?1%")
	List<HtmlContentModel> findHtmlContent(String req);
}
