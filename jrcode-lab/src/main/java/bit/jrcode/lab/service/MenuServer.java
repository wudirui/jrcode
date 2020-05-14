package bit.jrcode.lab.service;

import bit.jrcode.lab.model.MenuParentModel;
import bit.jrcode.lab.model.MenuSubModel;
import bit.jrcode.lab.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServer {
	@Autowired
	MenuRepository menu;


	public List<MenuParentModel> findAllMenu() {
		return menu.findAll();
	}

	public MenuParentModel findMenuByType(String type) {
		return menu.findMenuByType(type);
	}


	public List<MenuSubModel> findSubMenuByType(String profileOverviewType) {
		return menu.findSubMenuByType(profileOverviewType);
	}
}
