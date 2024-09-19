package SendingBalanceSheet;

public class Post {
//    private static String cash = "Nakit ve Nakit Benzerleri";
    private static String cash = "NAKİT DEĞERLER VE MERKEZ BANKASI";
    private static String bonds = "Devlet Borçlanma Senetleri";
    private static String loans = "KREDİLER VE ALACAKLAR";
    private static String loansF = "Krediler"; // use getLoanF for this
    private static String tangibleAssets = "MADDİ DURAN VARLIKLAR (Net)";
    private static String reip = "YATIRIM AMAÇLI GAYRİMENKULLER (Net)"; // real estate for invesment purposes
    private static String totalAssets = "AKTİF TOPLAMI";
    // the below are liabilities
    private static String banksBonds = "PARA PİYASALARINA BORÇLAR"; // liabilities
    private static String debtProducts = "SERMAYE BENZERİ BORÇLANMA ARAÇLARI"; // liabilities
    private static String totalL = "PASİF TOPLAMI";
//    private static String totalDip = "TOPLANAN FONLAR"; // Islamic banks
    private static String totalDip = "MEVDUAT";


    public static void main(String[] args) {
        String source = "ziraatB2008Q4.pdf";
        int pageAssets = 1;
        int pageLia = 2;
        int year = 2008;
        int bankId = 6;
        int param = 1000;
        String resC = Balance.getBalanceSheetData(source, pageAssets, cash);
        Long FResC = Balance.Clean(resC, param);
        String resB = Balance.getBalanceSheetData(source, pageAssets, bonds);
        Long FResB = Balance.Clean(resB, param);
        String resL = Balance.getBalanceSheetData(source, pageAssets, loans);
        Long FResL = Balance.Clean(resL, param);
        String resLF = Balance.getLoanF(source, pageAssets, loansF);
        Long FResLF = Balance.Clean(resLF, param);
        String resTan = Balance.getBalanceSheetData(source, pageAssets, tangibleAssets);
        Long FResTan = Balance.Clean(resTan, param);
        String resReip = Balance.getBalanceSheetData(source, pageAssets, reip);
        Long FResReip = Balance.Clean(resReip, param);
        String resTAS = Balance.getBalanceSheetData(source, pageAssets, totalAssets);
        Long FResTas = Balance.Clean(resTAS, param);
        // Liabilities now
        String resBBonds = Balance.getBalanceSheetData(source, pageLia, banksBonds);
        Long FResBBonds = Balance.Clean(resBBonds, 1000);
        String resDebt = Balance.getBalanceSheetData(source, pageLia, debtProducts);
        Long FResDebt = Balance.Clean(resDebt, param);
        String resTotalL = Balance.getBalanceSheetData(source, pageLia, totalL);
        Long FResTotalL = Balance.Clean(resTotalL, param);
        String resTotalDip = Balance.getBalanceSheetData(source, pageLia, totalDip);
        Long FReTotalDip = Balance.Clean(resTotalDip, param);

        Send.post(year, bankId, FResC, FResB, FResL, FResLF, FResTan, FResReip, FResTas, FResBBonds, FResDebt, 1, FResTotalL, FReTotalDip);

    }
}
