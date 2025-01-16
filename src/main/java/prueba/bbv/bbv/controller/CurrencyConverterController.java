package prueba.bbv.bbv.controller;


import prueba.bbv.bbv.dto.ConversionRequest;
import prueba.bbv.bbv.dto.ConversionResponse;
import prueba.bbv.bbv.service.CurrencyConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "/api/v1/currency")
public class CurrencyConverterController {

    private final CurrencyConverterService service;

    public CurrencyConverterController(CurrencyConverterService service) {
        this.service = service;
    }

    @PostMapping("/convert")
    public ResponseEntity<ConversionResponse> convertCurrency(@Validated @RequestBody ConversionRequest request) {
        return ResponseEntity.ok(service.convertCurrency(request));
    }
}