
package websocket.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Thread1 extends Thread
{
    
    private static final Logger logger=Logger.getLogger(Thread1.class);
    static String path="C:\\Users\\Pranshu\\Documents\\NetBeansProjects\\09-07-19\\ServerFinal\\src\\java\\websocket\\server\\log4j.properties";
    static Properties p;
   
    
    
    
  Random rand = new Random(); 
  private static final DecimalFormat df2 = new DecimalFormat("#.##");
  static long time;
    
    @Override
    public void run()
    {
         
         PropertyConfigurator.configure(path);
        logger.info("in last5rows");
        
        p = new Properties();
         InputStream is = null;
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException ex) {
            logger.error(ex);
        }
        try {
            p.load(is);
        } catch (IOException ex) {
            logger.error(ex);
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
            
            
          
            logger.info("record");
            System.out.println();
            
           
            try {
                //Thread.sleep(time);
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println(e);
            }
             
        }
    }
}


    
    