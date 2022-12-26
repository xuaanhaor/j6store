package poly.store.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public interface UploadService {
    File save(MultipartFile file, String folder);
}
