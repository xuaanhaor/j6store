package poly.store.impl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import poly.store.service.UploadService;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    final
    ServletContext app;
    @Value("${server.upload.dir}")
    private String uploadDir;

    public UploadServiceImpl(ServletContext app) {
        this.app = app;
    }

    @SneakyThrows
    @Override
    public File save(MultipartFile file, String folder) {
        String s = file.getOriginalFilename();
        assert s != null;
        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
//        Path dirPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static", uploadDir, folder);
        Path dirPath = Paths.get(System.getProperty("user.dir"), uploadDir, folder);
        log.info(">>Image folder path: {}", dirPath);
        if (Files.notExists(dirPath)) Files.createDirectories(dirPath);

//            File saveFile = new File(dir, file.getOriginalFilename());
        Path saveFile = dirPath.resolve(name);
        file.transferTo(saveFile);
        log.info(">> File path: {} in desktop.", saveFile.toFile().getAbsolutePath());
        return saveFile.toFile();
    }
}
