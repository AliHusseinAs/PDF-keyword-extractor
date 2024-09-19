package SendingBalanceSheet;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;

/*
The quarter data will look like : 12022 where 1 mean first quarter, 2022 means the whole year data
 */
public class Balance {
    private int bankId;
    private int year; // 22022 means 2nd quarter 2022
    private int cashAndEq;
    private int bond;
    private int loans;
    private int loansInFCurrency;
    private int tangibleAssets;
    private int refi; // real estate for investing purposes
    private int totalAssets;
    private int banksOwnBonds;
    private int debtProducts;
    private int npl;
    private int totalLiabilities;
    private int totalDeposits;

    public Balance(){}

    // This function returns total Cash and Cash Equivalent for the period in TRY;
    public static String getBalanceSheetData(String source, Integer pageNum, String key){
        File file = new File(source);
        try(PDDocument document = PDDocument.load(file)){
            PDPage page = document.getPage(pageNum - 1);
            try(PDDocument newDoc = new PDDocument()){
                newDoc.addPage(page);
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(newDoc);
                String[] lines = text.split("\\r?\\n");
                for(String line : lines){
                    if(line.contains(key)){
                        String[] parts = line.split("\\s+");
                        String res = parts[parts.length - 4]; // when there are only 3 parts TP, YP, and Toplam use 2 instead of 4
                        return res;
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            return "Error found";
        }
        return "No data found";
    }

    public static String getLoanF(String source, Integer pageNum, String key){
        File file = new File(source);
        try(PDDocument document = PDDocument.load(file)){
            PDPage page = document.getPage(pageNum - 1);
            try(PDDocument newDoc = new PDDocument()){
                newDoc.addPage(page);
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(newDoc);
                String[] lines = text.split("\\r?\\n");
                for(String line : lines){
                    if(line.contains(key)){
                        String[] parts = line.split("\\s+");
                        String res = parts[parts.length - 4];
                        return res;
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            return "Error found";
        }
        return "No data found";
    }

    public static Long Clean(String data, Integer currencyType){
        // currencyType is the type of the data, some data could be written like xxx.xxx.xxx but it is in thousands,
        // so we multiply by 1000 to give the real data;
        try{
            String data2 = data.replace(".", "");
            Long dataFinal = Long.parseLong(data2) * currencyType;
            return dataFinal;
        } catch (NumberFormatException e){
            return 1L;
        }

    }
}
