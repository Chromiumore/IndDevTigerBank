package me.chromiumore.services.output;

import me.chromiumore.entities.BaseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public abstract class OutputStrategy {
    protected Map<Integer, ? extends BaseEntity> data;
    protected String fileName;

    protected abstract String dataToFormat();

    public void setData(Map<Integer, ? extends BaseEntity> data) {
        this.data = data;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeFile() throws IOException {
        String content = dataToFormat();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String actualFileName = (fileName != null) ? fileName : timestamp;

        Path file = Paths.get(actualFileName + getFileExtension());
        Files.write(file, content.getBytes());
        System.out.println("Файл успешно сохранен: " + file.toAbsolutePath());
    }

    protected abstract String getFileExtension();
}