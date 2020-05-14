package bit.jrcode.lab.repository;

import bit.jrcode.lab.model.SlideshowModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface SlideshowRepository extends Repository<SlideshowModel, Long> {

	@Query("select slideshow from SlideshowModel slideshow where slideshow.menuId=?1 order by slideshow.sort")
	List<SlideshowModel> findSlideshowOfHome(Integer menuId);

}
