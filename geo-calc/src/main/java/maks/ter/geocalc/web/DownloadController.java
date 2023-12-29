package maks.ter.geocalc.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.*;

@Controller
@RequestMapping("/download")
public class DownloadController {

    private static final String FILE_PATH = "/tmp/table_database.xlsx";
    private static final String APPLICATION_XLSX = "application/xlsx";

    @RequestMapping(value = "/file", method = RequestMethod.GET, produces = APPLICATION_XLSX)
    public @ResponseBody HttpEntity<byte[]> download() throws IOException {
        File file = getFile();
        byte[] document = FileCopyUtils.copyToByteArray(file);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "xlsx"));
        header.set("Content-Disposition", "inline; filename=" + file.getName());
        header.setContentLength(document.length);

        return new HttpEntity<byte[]>(document, header);
    }

    private File getFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + FILE_PATH + " was not found.");
        }
        return file;
    }

}