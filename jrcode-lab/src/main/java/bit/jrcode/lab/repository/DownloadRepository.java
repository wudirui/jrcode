package bit.jrcode.lab.repository;

import bit.jrcode.lab.model.DownloadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DownloadRepository extends JpaRepository<DownloadModel, Long> {
	@Query("select items from DownloadModel items")
	List<DownloadModel> findFiles();

	@Query("select item from DownloadModel item where item.id = ?1")
	DownloadModel findFileById(Integer id);
}
