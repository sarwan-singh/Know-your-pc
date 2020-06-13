package com.smurf.knowyourpc.controller;

import com.itextpdf.text.DocumentException;
import com.smurf.knowyourpc.model.PC;
import com.smurf.knowyourpc.service.PcService;
import org.springframework.boot.system.SystemProperties;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class PcController {

    @GetMapping(value = "/", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> DownloadPdf() throws DocumentException {

        ByteArrayInputStream in = PcService.getPc();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Gamers.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(in));
    }
}
