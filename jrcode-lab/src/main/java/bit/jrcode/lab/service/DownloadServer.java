package bit.jrcode.lab.service;

import bit.jrcode.lab.model.DownloadModel;
import bit.jrcode.lab.repository.DownloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DownloadServer {
	@Autowired
	DownloadRepository repository;




	public List<DownloadModel> findFiles() {
		return repository.findFiles();
	}

	public DownloadModel findFileById(Integer id) {
		return repository.findFileById(id);
	}
}
