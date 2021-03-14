package com.dscumbajin.demo.srv;

import com.dscumbajin.demo.entity.Erubro;
import com.dscumbajin.demo.entity.Rubro;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcesoSrv {

    private final RubroSrv rubroSrv;
    private final ErubroSrv erubroSrv;

    public ProcesoSrv(RubroSrv rubroSrv, ErubroSrv erubroSrv) {
        this.rubroSrv = rubroSrv;
        this.erubroSrv = erubroSrv;
    }

    public ResponseEntity<byte[]> proceso(String cod) throws IOException {
        List<Erubro> primer = erubroSrv.findByCodpro(cod);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(cod);
        if (primer.size() > 0) {
            String[] colnames = new String[]{" codsecrub",
                    "codrub",
                    "nombrerub",
                    "descrub",
                    "rendrubreal",
                    "rendrubref",
                    "costorubreal",
                    "costorubref",
                    "costorubstd",
                    "padrerub",
                    "nivelrub",
                    "cantrubreal",
                    "cantrubref",
                    "cantrubstd",
                    "codpro",
                    " statusrub",
                    "cdrubreal",
                    "cdrubref",
                    "cdrubstd",
                    "coderub",
                    "codcc",
                    "codidgasto",
                    "ordenrub",
                    "codnodo",
                    "codigotemp"};
            List<Integer> coderubs = primer.stream().map(Erubro::getCoderub).collect(Collectors.toList());
            List<Rubro> rubros = rubroSrv.findByCoderub(coderubs);
            if (rubros.size() > 0) {
                int rownum = 0;
                XSSFRow header = sheet.createRow(rownum++);
                int colnumH = 0;
                for (String h : colnames) {
                    Cell cell = header.createCell(colnumH++);
                    cell.setCellValue(h);
                }
                for (Rubro rubro : rubros) {
                    XSSFRow row = sheet.createRow(rownum++);
                    //mandar al excel si quieres aqui va otras variables de otras consultas que sean del rubro
                    excel(rubro, row);
                }
                recusrsiveProcees(rubros, sheet, rownum);
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            sheet.autoSizeColumn(13);
            sheet.autoSizeColumn(14);
            sheet.autoSizeColumn(15);
            sheet.autoSizeColumn(16);
            sheet.autoSizeColumn(17);
            sheet.autoSizeColumn(18);
            sheet.autoSizeColumn(19);
            sheet.autoSizeColumn(20);
            sheet.autoSizeColumn(21);
            sheet.autoSizeColumn(22);
            sheet.autoSizeColumn(23);
            sheet.autoSizeColumn(24);
        } else {
            Row header = sheet.createRow(0);
            Cell cell = header.createCell(0);
            cell.setCellValue("NO EXISTEN DATOS");
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);
        workbook.close();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + "Export-" + cod + ".xlsx" + "\"");
        return new ResponseEntity<>(stream.toByteArray(), headers, HttpStatus.OK);
    }

    public void recusrsiveProcees(List<Rubro> rubros, XSSFSheet sheet, int rownum) {
        List<String> codpros = rubros.stream().filter(rubro -> (rubro.getCodpro() != null && !rubro.getCodpro().equals("0"))).map(Rubro::getCodpro).collect(Collectors.toList());
        List<Erubro> erubros = erubroSrv.findByCodpro(codpros);
        List<Integer> coderubs = erubros.stream().map(Erubro::getCoderub).collect(Collectors.toList());
        rubros = rubroSrv.findByCoderub(coderubs);
        if (rubros.size() > 0) {
            for (Rubro rubro : rubros) {
                XSSFRow row = sheet.createRow(rownum++);
                //mandar al excel si quieres aqui va otras variables de otras consultas que sean del rubro
                excel(rubro, row);
                if (!rubro.getCodpro().equals("0")) {
                    List<Erubro> erubrosTmp = erubroSrv.findByCodpro(rubro.getCodpro());
                    List<Integer> coderubsTmp = erubrosTmp.stream().map(Erubro::getCoderub).collect(Collectors.toList());
                    List<Rubro> rubrosTmp = rubroSrv.findByCoderub(coderubsTmp);
                    recusrsiveProcees(rubrosTmp, sheet, rownum);
                }
            }
        }
    }

    public void excel(Rubro rubro, XSSFRow row) {
        if (rubro.getCodsecrub() != null) {
            XSSFCell cell = row.createCell(0);
            String value = rubro.getCodsecrub().toString();
            cell.setCellValue(value);
        }
        if (rubro.getCodrub() != null) {
            XSSFCell cell = row.createCell(1);
            String value = rubro.getCodrub();
            cell.setCellValue(value);
        }
        if (rubro.getNombrerub() != null) {
            XSSFCell cell = row.createCell(2);
            String value = rubro.getNombrerub();
            cell.setCellValue(value);
        }
        if (rubro.getDescrub() != null) {
            XSSFCell cell = row.createCell(3);
            String value = rubro.getDescrub();
            cell.setCellValue(value);
        }

        if (rubro.getRendrubreal() != null) {
            XSSFCell cell = row.createCell(4);
            String value = rubro.getRendrubreal().toString();
            cell.setCellValue(value);
        }
        if (rubro.getRendrubref() != null) {
            XSSFCell cell = row.createCell(5);
            String value = rubro.getRendrubref().toString();
            cell.setCellValue(value);
        }
        if (rubro.getCostorubreal() != null) {
            XSSFCell cell = row.createCell(6);
            String value = rubro.getCostorubreal().toString();
            cell.setCellValue(value);
        }
        if (rubro.getCostorubref() != null) {
            XSSFCell cell = row.createCell(7);
            String value = rubro.getCostorubref().toString();
            cell.setCellValue(value);
        }

        if (rubro.getCostorubstd() != null) {
            XSSFCell cell = row.createCell(8);
            String value = rubro.getCostorubstd().toString();
            cell.setCellValue(value);
        }
        if (rubro.getPadrerub() != null) {
            XSSFCell cell = row.createCell(9);
            String value = rubro.getPadrerub();
            cell.setCellValue(value);
        }
        if (rubro.getNivelrub() != null) {
            XSSFCell cell = row.createCell(10);
            String value = rubro.getNivelrub();
            cell.setCellValue(value);
        }
        if (rubro.getCantrubreal() != null) {
            XSSFCell cell = row.createCell(11);
            String value = rubro.getCantrubreal().toString();
            cell.setCellValue(value);
        }

        if (rubro.getCantrubref() != null) {
            XSSFCell cell = row.createCell(12);
            String value = rubro.getCantrubref().toString();
            cell.setCellValue(value);
        }
        if (rubro.getCantrubstd() != null) {
            XSSFCell cell = row.createCell(13);
            String value = rubro.getCantrubstd().toString();
            cell.setCellValue(value);
        }
        if (rubro.getCodpro() != null) {
            XSSFCell cell = row.createCell(14);
            String value = rubro.getCodpro().toString();
            cell.setCellValue(value);
        }
        if (rubro.getStatusrub() != null) {
            XSSFCell cell = row.createCell(15);
            String value = rubro.getStatusrub().toString();
            cell.setCellValue(value);
        }
        if (rubro.getCdrubreal() != null) {
            XSSFCell cell = row.createCell(16);
            String value = rubro.getCdrubreal().toString();
            cell.setCellValue(value);
        }
        if (rubro.getCdrubref() != null) {
            XSSFCell cell = row.createCell(17);
            String value = rubro.getCdrubref().toString();
            cell.setCellValue(value);
        }
        if (rubro.getCdrubstd() != null) {
            XSSFCell cell = row.createCell(18);
            String value = rubro.getCdrubstd().toString();
            cell.setCellValue(value);
        }
        if (rubro.getCoderub() != null) {
            XSSFCell cell = row.createCell(19);
            String value = rubro.getCoderub().toString();
            cell.setCellValue(value);
        }

        if (rubro.getCodcc() != null) {
            XSSFCell cell = row.createCell(20);
            String value = rubro.getCodcc();
            cell.setCellValue(value);
        }
        if (rubro.getCodidgasto() != null) {
            XSSFCell cell = row.createCell(21);
            String value = rubro.getCodidgasto().toString();
            cell.setCellValue(value);
        }
        if (rubro.getOrdenrub() != null) {
            XSSFCell cell = row.createCell(22);
            String value = rubro.getOrdenrub().toString();
            cell.setCellValue(value);
        }
        if (rubro.getCodnodo() != null) {
            XSSFCell cell = row.createCell(23);
            String value = rubro.getCodnodo().toString();
            cell.setCellValue(value);
        }
        if (rubro.getCodigotemp() != null) {
            XSSFCell cell = row.createCell(24);
            String value = rubro.getCodigotemp();
            cell.setCellValue(value);
        }
    }
}
