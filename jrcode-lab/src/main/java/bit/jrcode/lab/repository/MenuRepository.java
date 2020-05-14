package bit.jrcode.lab.repository;

import bit.jrcode.lab.model.MenuParentModel;
import bit.jrcode.lab.model.MenuSubModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MenuRepository extends JpaRepository<MenuParentModel, Long> {

	@Query("select menus from MenuParentModel menus where menus.type=?1")
	MenuParentModel findMenuByType(String type);

	@Query("select subMenu from MenuSubModel subMenu where subMenu.type=?1")
	List<MenuSubModel> findSubMenuByType(String profileOverviewType);
}
