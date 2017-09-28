package utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exceptions.CSVReadingException;

public final class DataUtils {
    private static final Logger log = LoggerFactory.getLogger(DataUtils.class);
    private static final String PATH_TO_CSV_FOLDER_FOR_PARAMETRIZED_TESTS = System.getProperty(Props.PATH_TO_CSV_FOLDER_FOR_PARAMETRIZED_TESTS);

    public static List<String> getData(String fileName) {
        Reader in;
        List<String> resultList = new ArrayList<>();
        try {
            in = new FileReader(PATH_TO_CSV_FOLDER_FOR_PARAMETRIZED_TESTS + fileName);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            for (CSVRecord record : records) {
                String ex = record.get(0);
                resultList.add(ex);
                log.info(ex);
            }
        } catch (IOException e) {
            log.error("CSVReadingException", e.getMessage());
            throw new CSVReadingException(e.getMessage());
        }

        return resultList;
    }

}
