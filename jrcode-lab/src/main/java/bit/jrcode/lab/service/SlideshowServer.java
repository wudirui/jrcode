package bit.jrcode.lab.service;

import bit.jrcode.lab.model.SlideshowModel;
import bit.jrcode.lab.repository.SlideshowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideshowServer {
	@Autowired
	SlideshowRepository repository;

	public List<SlideshowModel> findSlideshowOfHome(Integer menuId) {

		return repository.findSlideshowOfHome(menuId);
	}
}
