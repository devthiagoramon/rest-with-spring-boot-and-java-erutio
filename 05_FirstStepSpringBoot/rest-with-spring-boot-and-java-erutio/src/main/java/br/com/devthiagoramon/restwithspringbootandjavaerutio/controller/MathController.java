package br.com.devthiagoramon.restwithspringbootandjavaerutio.controller;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.exceptions.UnsopportedMathOperationException;
import br.com.devthiagoramon.restwithspringbootandjavaerutio.services.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin("*")
public class MathController {

    @Autowired
    public MathService mathService;

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws UnsopportedMathOperationException {
        return mathService.sum(numberOne, numberTwo);
    }

    @GetMapping("/diff/{numberOne}/{numberTwo}")
    public Double diff(@PathVariable("numberOne") String numberOne,
                       @PathVariable("numberTwo") String numberTwo) throws UnsopportedMathOperationException {
        return mathService.subtract(numberOne, numberTwo);
    }

    @GetMapping("/prod/{numberOne}/{numberTwo}")
    public Double prod(@PathVariable("numberOne") String numberOne,
                       @PathVariable("numberTwo") String numberTwo) throws UnsopportedMathOperationException {
        return mathService.product(numberOne, numberTwo);
    }

    @GetMapping("/div/{numberOne}/{numberTwo}")
    public Double div(@PathVariable("numberOne") String numberOne,
                      @PathVariable("numberTwo") String numberTwo) throws UnsopportedMathOperationException, IllegalArgumentException {
        return mathService.division(numberOne, numberTwo);
    }

    @GetMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(@PathVariable("numberOne") String numberOne,
                       @PathVariable("numberTwo") String numberTwo) throws UnsopportedMathOperationException {
        return mathService.mean(numberOne, numberTwo);
    }

    @GetMapping("/sqrt/{number}")
    public Double sqrt(@PathVariable("number") String number) throws UnsopportedMathOperationException, IllegalArgumentException {
        return mathService.raizQuadrada(number);
    }
}
