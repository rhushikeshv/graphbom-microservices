package com.graphbom.app.rest;

import com.graphbom.app.model.Part;
import com.graphbom.app.output.Result;
import com.graphbom.app.service.PartBOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/services/partservice")
public class PartController {

    @Autowired
    private PartBOMService partBOMService;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @GetMapping(value = "/findparts")
    public Result<String, Iterable<Part>> findParts() {
        Iterable<Part> parts = partBOMService.findParts();
        Result<String,Iterable<Part>> result = new Result<>();
        result.setMessage("OK");
        result.setData(parts);
        return result;
    }

    @GetMapping(value="/loadbom")
    public Map<String, List<Part>> loadBom(@RequestParam(name ="type") String type,
                                           @RequestParam(name="name") String name,
                                           @RequestParam(name="rev") Long rev){
        return partBOMService.loadBOM(type,name,rev);
    }
    @PostMapping(value="/createpart")
    public Result createPart(@RequestBody Part part){
        return this.partBOMService.createPart(part);
    }
    @PutMapping(value="/updatepart")
    public Result updatePart(@RequestBody Part part){
        return this.partBOMService.updatePart(part);
    }
    @PostMapping(value="/uploadFile")
    public Result importBOM(@RequestParam("file") MultipartFile file){
        System.out.println(" uploaded file size is " + file.getSize());
        Result<String,String> result = new Result<>();
        try {
            result = this.partBOMService.importBOM(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            result.setMessage("FAIL");
        }
        return result;
    }


}
