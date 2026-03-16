package me.chromiumore.tigerbank.service.output;

import me.chromiumore.tigerbank.domain.BaseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public abstract class OutputStrategy {
    protected Map<Integer, BaseEntity> data;
    protected String fileName;
    protected String fileExtension;

    public OutputStrategy(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    protected abstract String dataToFormat();

    public void setData(Map<Integer, BaseEntity> data) {
        this.data = data;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeFile() throws IOException {
        String content = dataToFormat();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String actualFileName = (fileName != null) ? fileName : timestamp;

        Path path = Paths.get("./target/" + actualFileName + fileExtension);
        Files.write(path, content.getBytes());
        System.out.println("Файл успешно сохранен: " + path.toAbsolutePath());
    }
}