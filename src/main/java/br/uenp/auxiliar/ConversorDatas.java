package br.uenp.auxiliar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorDatas {

    public static Date converterStringforDate(String dataEntrada) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return (Date) formatter.parse(dataEntrada);
    }

}