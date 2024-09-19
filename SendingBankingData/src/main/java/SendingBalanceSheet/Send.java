package SendingBalanceSheet;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Send {
    public Send() {}

    public static void post(int year, int bankId, long cash, long bonds, long loans, long loansF, long tangibleAssets,
                            long reip, long totalAssets, long banksBonds, long debtProducts, long npl,
                            long totalL, long totalDip){
        String urls = "http://localhost:1000/api/public/addBalanceSheet";
        String toJson = String.format("{\"year\" : %d, \"bankId\" : %d, " +
                "\"cashAndCashEq\" : %d, \"bonds\" : %d, \"loans\" : %d, \"loansInForeignCurrency\" : %d, " +
                "\"tangibleAssets\" : %d, \"realEstateForInvestmentPurposes\" : %d, \"totalAssets\" : %d, " +
                "\"banksOwnBonds\" : %d, \"debtProducts\" : %d, \"npl\" : %d, \"totalLiabilities\" : %d ," +
                "\"totalDeposits\" : %d}", year, bankId, cash, bonds, loans, loansF, tangibleAssets, reip, totalAssets,
                banksBonds, debtProducts, npl, totalL, totalDip);

        try{
            URL url = new URL(urls);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try(OutputStream os = connection.getOutputStream()){
                byte[] input = toJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = connection.getResponseCode();
            System.out.println(code);
            connection.disconnect();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
