package br.com.devthiagoramon.restwithspringbootandjavaerutio.services;

import br.com.devthiagoramon.restwithspringbootandjavaerutio.exceptions.UnsopportedMathOperationException;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    public Double sum(String n1S, String n2S) throws UnsopportedMathOperationException {
        try {
            Double n1 = convertToNumber(n1S);
            Double n2 = convertToNumber(n2S);
            return n1 + n2;
        } catch (NumberFormatException e) {
            throw new UnsopportedMathOperationException("Isn't a number");
        }
    }

    public Double subtract(String n1S, String n2S) throws UnsopportedMathOperationException {
        try {
            Double n1 = convertToNumber(n1S);
            Double n2 = convertToNumber(n2S);
            return n1 - n2;
        } catch (NumberFormatException e) {
            throw new UnsopportedMathOperationException("Isn't a number");
        }
    }

    public Double product(String n1S, String n2S) throws UnsopportedMathOperationException {
        try {
            Double n1 = convertToNumber(n1S);
            Double n2 = convertToNumber(n2S);
            return n1 * n2;
        } catch (NumberFormatException e) {
            throw new UnsopportedMathOperationException("Isn't a number");
        }
    }

    public Double division(String n1S, String n2S) throws UnsopportedMathOperationException, IllegalArgumentException {
        try {
            double n1 = convertToNumber(n1S);
            double n2 = convertToNumber(n2S);
            if (n2 != 0) {
                throw new IllegalArgumentException("Can't divide by zero");
            }
            return n1 / n2;
        } catch (NumberFormatException e) {
            throw new UnsopportedMathOperationException("Isn't a number");
        }
    }

    public Double mean(String n1S, String n2S) throws UnsopportedMathOperationException{
        try {
            double n1 = convertToNumber(n1S);
            double n2 = convertToNumber(n2S);
            double sum = n1 + n2;
            return sum / 2;
        } catch (NumberFormatException e){
            throw new UnsopportedMathOperationException("Isn't a number");
        }
    }

    public Double raizQuadrada(String n1S) throws UnsopportedMathOperationException, IllegalArgumentException{
        try {
            double n1 = convertToNumber(n1S);
            if (n1 < 0) {
               throw new UnsopportedMathOperationException("Can't operate negative number");
            }
            return Math.sqrt(n1);
        } catch (NumberFormatException e){
          throw new UnsopportedMathOperationException("Isn't a number");
        }
    }

    private Double convertToNumber(String number) throws NumberFormatException {
        if (number == null) {
            return 0D;
        }
        String formattedNumber = number.replace(",", ".");
        return Double.parseDouble(formattedNumber);
    }

}
