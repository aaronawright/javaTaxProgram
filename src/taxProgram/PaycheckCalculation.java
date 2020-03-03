package taxProgram;

public class PaycheckCalculation {
	// it seems enum syntax is the same in java vs c# 
    public enum maritalStatusOptions
    {
        Single,
        Married
    }
    public enum payPeriodOptions
    {
        Biweekly,
        Monthly
    }
    // input
    private String name;
    private double grossPay;
    private int numDependents;
    private maritalStatusOptions maritalStatus;
    private payPeriodOptions payPeriod;

    // output
    private double[][] taxBracket;
    private double federalTax;
    private double socialSecurityTax;
    private double medicareTax;
    private double netPay;

    // constants used for calculations
    // c# constants: private const
    // java: static final
    private static final int maxDependents = 10;
    private static final double withholdingMultiplierBiweekly = 155.80;
    private static final double withholdingMultiplierMonthly = 337.50;
    private static final double socialSecurityMultiplier = 0.062;
    private static final double medicareMultiplier = 0.0145;	
    
    // tax bracket definitions
    // there is a slight change in 2d array syntax form c# => java
    // c# : [,]
    // java : [][]
    private double[][] bracketBiweeklySingle = new double[][] {
	    { 88, 447, 1548, 3623, 7460, 16115, 16181, 0 },
	    { 0, 0, 35.90, 201.05, 719.80, 1794.16, 4650.31, 4673.41 },
	    { 0, 0.10, 0.15, 0.25, 0.28, 0.33, 0.35, 0.396 }
    };
    private double[][] bracketBiweeklyMarried = new double[][] {
        { 333, 1050, 3252, 6221, 9308, 16360, 18437, 0 },
        { 0, 0, 71.70, 402.00, 1144.25, 2008.61, 4335.77, 5062.72 },
        { 0, 0.10, 0.15, 0.25, 0.28, 0.33, 0.35, 0.396 }
    };
    private double[][] bracketMonthlySingle = new double[][] {
        { 192, 969, 3354, 7850, 16163, 34917, 35058, 0 },
        { 0, 0, 77.70, 435.45, 1559.45, 3887.09, 10075.91, 10125.26 },
        { 0, 0.10, 0.15, 0.25, 0.28, 0.33, 0.35, 0.396 }
    };
    private double[][] bracketMonthlyMarried = new double[][] {
        { 721, 2275, 7046, 13479, 20167, 35446, 39946, 0 },
        { 0, 0, 155.40, 871.05, 2479.30, 4351.94, 9394.01, 10969.01 },
        { 0, 0.10, 0.15, 0.25, 0.28, 0.33, 0.35, 0.396 }
    };    
	
    // constructor
    public PaycheckCalculation(String n, double gPay, int nDependents, maritalStatusOptions mStatus, payPeriodOptions pPeriod)
    {
        name = n;
        grossPay = gPay;
        numDependents = nDependents;
        maritalStatus = mStatus;
        payPeriod = pPeriod;
    }    
    
    // class functions
    
    public void calculate()
    {
        double withholdingMultiplier = payPeriod == payPeriodOptions.Biweekly ? withholdingMultiplierBiweekly : withholdingMultiplierMonthly; // get correct multiplier
        double withhouldingAmount = numDependents * withholdingMultiplier; // multiply by number of dependents
        double taxablePay = grossPay - withhouldingAmount; // figure taxable pay as gross minus the withheld amount
        
        taxBracket = getBracket();
        
        
        federalTax = calculateFederalTax(taxablePay);
       
    	
        socialSecurityTax = grossPay * socialSecurityMultiplier;
        medicareTax = grossPay * medicareMultiplier;
        netPay = grossPay - federalTax - socialSecurityTax - medicareTax;
    }    
    
    public double calculateFederalTax(double taxPay)
    {
        double fedTax = 0;
        int threshold = 0; // loop through the tax thresholds
        /*
        I've replaced the if/else tests with a more efficient for loop
        */
       
        for(threshold = 0; threshold < taxBracket.length; threshold++) // slight change in .length syntax form C# => java
        {
        	
            if(taxPay <= taxBracket[0][threshold]) // taxable pay is equal to or below this threshold
            {
                if(threshold == 0) // first index means zero tax
                {
                    fedTax = 0;
                    System.out.print("No tax");
                } 
                else
                {
                    // calculate based on rows below this index as well as previous column
                    fedTax = taxBracket[1][threshold] + ((taxPay - taxBracket[0][threshold - 1]) * taxBracket[2][threshold]);
                    System.out.print(fedTax);
                	
                }
                threshold = taxBracket.length; // break out of `for` loop
            }
        }
     
        return fedTax;
    }

    public double[][] getBracket() // get the right bracket depending on pay period and marital status
    {
    	
        if(payPeriod == payPeriodOptions.Biweekly)
        {
            if (maritalStatus == maritalStatusOptions.Single)
                return bracketBiweeklySingle;
            else
                return bracketBiweeklyMarried;
        } 
        else
        {
            if (maritalStatus == maritalStatusOptions.Single)
                return bracketMonthlySingle;
            else
                return bracketMonthlyMarried;
        }
    }

    public static int getMaxDependents() // return max dependents
    {
        return maxDependents;
    }

    public double[] getResults() // return an array of these doubles.. rather than five different functions
    {
        return new double[] { 
            grossPay, 
            federalTax, 
            socialSecurityTax, 
            medicareTax, 
            netPay
        };
    }    
    
}
