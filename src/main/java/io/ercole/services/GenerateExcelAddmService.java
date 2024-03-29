package io.ercole.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.ercole.repositories.CurrentHostRepository;

/**
 * The type Generate excel service.
 */
@Service
public class GenerateExcelAddmService {

    @Autowired
    private CurrentHostRepository currentRepo;

    /**
     * Init excel response entity.
     *
     * @param env    the env
     * @param search the search
     * @return the response entity
     * @throws IOException the io exception
     */
    public ResponseEntity<byte[]> initExcel(final String env, final String search) throws IOException {

        List<Map<String, Object>> addmList = currentRepo.getADDMs(env, search);

        try (Workbook workbook = new XSSFWorkbook(new ClassPathResource("template_addm.xlsm").getInputStream())) {

            XSSFSheet xssfSheet = ((XSSFWorkbook) workbook).getSheet("Addm");
            //number of row where we will write (row 0,1,2 contains the heading of the table)
            int rowNumber = 3;


            for (Map<String, Object> addmMap : addmList) {
                XSSFRow row = xssfSheet.createRow(rowNumber);

                String[] dataOfHost = new String[8];

                dataOfHost[0]  = String.valueOf(addmMap.get("action"));

                dataOfHost[1]  = String.valueOf(addmMap.get("benefit"));

                dataOfHost[2]  = String.valueOf(addmMap.get("dbname"));

                dataOfHost[3]  = String.valueOf(addmMap.get("environment"));

                dataOfHost[4]  = String.valueOf(addmMap.get("finding"));

                dataOfHost[5]  = String.valueOf(addmMap.get("hostname"));

                dataOfHost[6]  = String.valueOf(addmMap.get("recommendation"));

                //insert data of host into a new cell
                int cellid = 0;
                for (int i = 0; i < 8; i++) {
                    //don't delete. see templateVuoto ABCD?F

                    Cell cell = row.createCell(cellid++);
                    cell.setCellValue(dataOfHost[i]);

                }
                rowNumber++;
            }
            //writing changes in the open file (templateVuoto)

            try (ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream()) {

                HttpHeaders headers = new HttpHeaders();
                workbook.write(outputStream2);
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ADDM.xlsm");
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                        .body(outputStream2.toByteArray());
            }
        }
    }
}
