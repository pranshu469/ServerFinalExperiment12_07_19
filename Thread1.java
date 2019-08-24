
package websocket.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;


public class Thread1 extends Thread
{
    
    static String path="C:\\Users\\Pranshu\\Documents\\NetBeansProjects\\09-07-19\\ServerFinal\\src\\java\\websocket\\server\\log4j.properties";
    static Properties p;
    
  Random rand = new Random(); 
  private static final DecimalFormat df2 = new DecimalFormat("#.##");
  static long time;
    
    @Override
    public void run()
    {
         p = new Properties();
         InputStream is = null;
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
        try {
            p.load(is);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        String xyz=p.getProperty("threadtime");
        time=Long.parseLong(xyz);  
        
        while(true)
        {
            double rand_dub1 = rand.nextDouble()+11; 
           
            double sell=rand_dub1+0.1;
            String roundedSell=df2.format(sell);
            
            double buy=rand_dub1-0.1;
            String roundedBuy=df2.format(buy);
            
           
            //JSON.........................................
                StringBuffer input = new StringBuffer("{");
                input.append("\"SellingPrice\":");
                input.append(roundedSell);
                input.append(",");
                input.append("\"BuyingPrice\":");
                input.append(roundedBuy);
                input.append("}");
             
            String string=input.toString();
            CustomEndPoint.sendAll(string);

            System.out.println();
           
            try {
                Thread.sleep(time);
                //Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println(e);
            }
             
        }
    }
}


    
    
