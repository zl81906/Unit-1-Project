import java.util.Scanner;

public class Main {
    
        public static void main(String[] args) {
        
        Scanner reader = new Scanner(System.in);
        
        System.out.println("Enter the mass of the water, in grams");
        double mass = reader.nextDouble();
        System.out.println("Enter the initial temperature of the water, in Celsius");
        double initialTemp = reader.nextDouble();
        if(initialTemp < -273.14)
            initialTemp = -273.14;
        System.out.println("Enter the final temperature of the water, in Celsius");
        double finalTemp = reader.nextDouble();
        if(finalTemp < -273.14)
            finalTemp = -273.14;
            
        String initialPhase = "liquid";
        if(initialTemp < 0)
            initialPhase = "solid";
        if(initialTemp > 100)
            initialPhase = "vapor";
            
        String finalPhase = "liquid";
        if(finalTemp < 0)
            finalPhase = "solid";
        if(finalTemp > 100)
            finalPhase = "vapor";
            
        System.out.println("Mass: " + mass + "g");
        System.out.println("Initial temperature: " + initialTemp + "C " + initialPhase);
        System.out.println("Final temperature: " + finalTemp + "C " + finalPhase + "\n");
        
        boolean endothermic = false;
        if(finalTemp > initialTemp)
            endothermic = true;
        
        double heatEnergy = 0;
        
        //initial phase: solid
        if(initialPhase.equals("solid")) {
            heatEnergy += tempChangeSolid(mass, initialTemp, finalTemp, finalPhase, endothermic);
            
            
            if(!finalPhase.equals("solid")) {
                heatEnergy += fusion(mass, endothermic);
                heatEnergy += tempChangeLiquid(mass, 0, finalTemp, finalPhase, endothermic);
            }
            
            if(finalPhase.equals("vapor")) {
                heatEnergy += vaporization(mass, endothermic);
                heatEnergy += tempChangeVapor(mass, 100, finalTemp, finalPhase, endothermic);
            }
        }//end of section for initial phase: solid
    
        //initial phase: liquid
        if(initialPhase.equals("liquid")) {
            heatEnergy += tempChangeLiquid(mass, initialTemp, finalTemp, finalPhase, endothermic);
            
            
            if(finalPhase.equals("solid")) {
                heatEnergy += fusion(mass, endothermic);
                heatEnergy += tempChangeSolid(mass, 0, finalTemp, finalPhase, endothermic);
            }
            
             if(finalPhase.equals("vapor")) {
                heatEnergy += vaporization(mass, endothermic);
                heatEnergy += tempChangeVapor(mass, 100, finalTemp, finalPhase, endothermic);
            }
        }//end of section for initial phase: liquid
    
        //initial phase: vapor
        if(initialPhase.equals("vapor")) {
            heatEnergy += tempChangeVapor(mass, initialTemp, finalTemp, finalPhase, endothermic);
            
            
            if(!finalPhase.equals("solid")) {
                heatEnergy += vaporization(mass, endothermic);
                heatEnergy += tempChangeLiquid(mass, 100, finalTemp, finalPhase, endothermic);
            }
            
            if(finalPhase.equals("solid")) {
                heatEnergy += fusion(mass, endothermic);
                heatEnergy += tempChangeSolid(mass, 0, finalTemp, finalPhase, endothermic);
            }
        }//end of section for initial phase: vapor
        
        
        System.out.println("Total Heat Energy: " + round(heatEnergy) + "kj");
    
    }
        //main ends here
  
    public static double tempChangeSolid(double mass, double startTemp, double endTemp, String endPhase, boolean endothermic) {
        if(!endPhase.equals("solid"))
            endTemp = 0;
        double energyChange = round(mass*0.002108*(endTemp - startTemp));
            if(endothermic)
                System.out.println("Heating (solid): " + energyChange + "kj");
            else
                System.out.println("Cooling (solid): " + energyChange + "kj");
        return energyChange;
    }

    public static double tempChangeVapor(double mass, double startTemp, double endTemp, String endPhase, boolean endothermic) {
        if(!endPhase.equals("vapor"))
            endTemp = 100;
        double energyChange = round(mass*0.001996*(endTemp - startTemp));
            if(endothermic)
                System.out.println("Heating (vapor): " + energyChange + "kj");
            else
                System.out.println("Cooling (vapor): " + energyChange + "kj");
        return energyChange;
    }
    
     public static double tempChangeLiquid(double mass, double startTemp, double endTemp, String endPhase, boolean endothermic) {
        if(endPhase.equals("solid"))
            endTemp = 0;
        if(endPhase.equals("vapor"))
            endTemp = 100;
        double energyChange = round(mass*0.004184*(endTemp - startTemp));
            if(endothermic)
                System.out.println("Heating (liquid): " + energyChange + "kj");
            else
                System.out.println("Cooling (liquid): " + energyChange + "kj");
        return energyChange;
    }
    
    public static double fusion(double mass, boolean endothermic) {
        double energyChange;
        if(endothermic) {
            energyChange = round(0.333*mass);
            System.out.println("Melting: " + energyChange + "kj");
        }
        else {
            energyChange = round(-0.333*mass);
            System.out.println("Freezing: " + energyChange + "kj");
        }
        return energyChange;
    }
    
    public static double vaporization(double mass, boolean endothermic) {
        double energyChange;
        if(endothermic) {
            energyChange = round(2.257*mass);
            System.out.println("Evaporation: " + energyChange + "kj");
        }
        else {
            energyChange = round(-2.257*mass);
            System.out.println("Condensation: " + energyChange + "kj");
        }
        return energyChange;
    }
    
    public static double round(double x) {
        x *= 1000;
        if(x > 0)
            return (int)(x + 0.5)/1000.0;
        else
            return (int)(x - 0.5)/1000.0;
    }
    
    
    
    
    
    
    
    
    
}   











