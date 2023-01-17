package com.example.ContractManager.controller;
import com.example.ContractManager.model.ImageContract;
import com.example.ContractManager.repository.ImageRepository;
import com.example.ContractManager.service.FileLocationService;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class Controller {
    @Autowired
    CrudController crudController;
    @Autowired
    FileLocationService fileLocationService;



//    @GetMapping(value = "/image/imageId", produces = MediaType.IMAGE_JPEG_VALUE)
//    FileSystemResource downloadImage(@RequestParam("id") Long imageId) throws Exception {
//
//                return fileLocationService.find(imageId);
//    }

    @GetMapping("/viewPdf")
    public ResponseEntity<Resource> viewPdf(@RequestParam("pdfid") Long imageId)throws Exception
    {
        File file =
//                new File("C:\\Users\\Prashant\\Desktop\\Dummy.pdf");
        fileLocationService.find(imageId).getFile();
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename="+ file.getName());

        Path path = Paths.get(file.getAbsolutePath());

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }
//    @GetMapping("/downloadPdf")
//    public ResponseEntity<Resource> downloadPdf(@RequestParam("downloadid") Long imageId)throws Exception
//    {
//        File file =
////                new File("C:\\Users\\Prashant\\Desktop\\Dummy.pdf");
//                fileLocationService.find(imageId).getFile();
//        HttpHeaders header = new HttpHeaders();
//        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+ file.getName());
//
//        Path path = Paths.get(file.getAbsolutePath());
//
//        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
//        return ResponseEntity.ok()
//                .headers(header)
//                .contentLength(file.length())
//                .contentType(MediaType.parseMediaType("application/pdf"))
//                .body(resource);
//    }
//    @GetMapping("pdfmerge")
//    public void pdfMerge() throws IOException {
//
////        List<String> sources = new ArrayList<>();
////        sources.add("C:/Users/Grebnev_A/1.pdf");
////        sources.add("C:/Users/Grebnev_A/2.pdf");
////        sources.add("C:/Users/Grebnev_A/3.pdf");
////        PDFMergerUtility ut = new PDFMergerUtility();
//
//
//
////        for (MultipartFile s: files) {
//////File f = (File) s;
////            System.out.println(new String(files.getBytes(), Charset.defaultCharset()));
////            ut.addSource(s);
////        }
////        ut.setDestinationFileName("C:/Users/Grebnev_A/test.pdf");
////        ut.mergeDocuments();
////        BufferedImage bf = ImageIO.read(files.getInputStream());
//
//
//        File path = new File("C:/Users/Grebnev_A/images/"); // base path of the images
//
//// load source images
//
//        List<BufferedImage> bufferedImageList = new ArrayList<>();
//        for (String s: listFilesUsingJavaIO("C:/Users/Grebnev_A/images")) {
//            bufferedImageList.add(ImageIO.read(new File("C:/Users/Grebnev_A/images/"+s)));
//        }
////        BufferedImage image = ImageIO.read(new File(path, "1.png"));
////        BufferedImage overlay = ImageIO.read(new File(path, "2.png"));
////
//
//// create the new image, canvas size is the max. of both image sizes
////        int w = Math.max(image.getWidth(), overlay.getWidth());
////        int h = image.getHeight()+overlay.getHeight()+10;
//        int w = bufferedImageList
//                .stream()
//                .map(e -> e.getWidth())
//                .findFirst().get();
//       int h = 0;
//        for (int i=0; i< bufferedImageList.size(); i++) {
//            h+=bufferedImageList.get(i).getHeight()+10;
//        }
//
//        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
//
//// paint both images, preserving the alpha channels
//        Graphics g = combined.getGraphics();
//        for (int i=0; i< bufferedImageList.size(); i++) {
//            g.drawImage(bufferedImageList.get(i), 0, i*bufferedImageList.get(i).getHeight()+10, null);
////            System.out.println(i+" "+bufferedImageList.get(i).getHeight() );
//        }
////        g.drawImage(image, 0, 0, null);
////        g.drawImage(overlay, 0, image.getHeight()+10, null);
//
//        g.dispose();
//
//// Save as new image
//        ImageIO.write(combined, "PNG", new File(path, "combined.png"));
//    }
//
//    public Set<String> listFilesUsingJavaIO(String dir) {
//        return Stream.of(new File(dir).listFiles())
//                .filter(file -> !file.isDirectory())
//                .map(File::getName)
//                .collect(Collectors.toSet());
//    }


    public FileSystemResource getResource(String path) {
        return new FileSystemResource(path);
    }

}
