
package dk.sdu.mmmi.rd1.edgedetect;

import dk.sdu.mmmi.rd1.robotcomm.RobotClient;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
//ReadME
//Change the Image path in line 90 to be resized (check the extention)
// IN line 101 save the resized image in a path you choose (lets call it "Path1")
// Set "Path1" in line 115 to be procssed through Edgedetecor class ( I know it is lame and i will change it soon)
//in line 133 check The IP adress And the port number to be matched with you server.
//you are done!

public class ImportImage
{
    //We Start by making borders to the image and make a line saparator when the line ends
    public String drawDelimiter(int x, int y, int width, int height) 
    {   
        if (x != width - 1) 
        {
            return "";
        } 
        else
        {
            if (y != height - 1) 
            {
                String bothStrings ="T"+System.lineSeparator();
                return (bothStrings);    
            }
        }
        return "";
    }   
          //  else {
          //      return "End";
          //  }
       // }
       // return null;
        
  //  }
    
    //1 = Black , 0 = white.
  //  public static String valueOf(int a)
     public int binaryTrunc (int a)// have to be String 
             
        {
         if(a <= 127)
             return 1;
         else
             return 0;
        }   

     //In Picture Class We have BefferedImage that cane Resize the 
     
    private BufferedImage getScaledImage(BufferedImage src, int w, int h){
        int original_width = src.getWidth();
        int original_height = src.getHeight();
        int bound_width = w;
        int bound_height = h;
        int new_width = original_width;
        int new_height = original_height;

       
        if (original_width > bound_width) {
           
            new_width = bound_width;
         
            new_height = (new_width * original_height) / original_width;
        }

        if (new_height > bound_height) {
          
            new_height = bound_height;
          
            new_width = (new_height * original_width) / original_height;
        }

        BufferedImage resizedImg = new BufferedImage(new_width, new_height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.drawImage(src, 0, 0, new_width, new_height, null);
        g2.dispose();
        return resizedImg;
    }
    
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws InterruptedException, IOException
    {
        //Insert picture
        File input = new File("D:\\funny.png");
       
        ImportImage importImage = new ImportImage();
        
        
        try
        {
            BufferedImage image = ImageIO.read(input);
            BufferedImage resized = importImage.getScaledImage(image, 70, 70);
            
            //Here the output the the resized image 
            File output = new File("D:\\TegnerobotResized.jpg");
            ImageIO.write(resized, "jpg", output);
        }
        
        catch(IllegalArgumentException e)
        {
            System.out.println("Illegal Argument Exception - Input is null. Choose a different file");    
        } 
        catch(IOException ex)
        {
            System.out.println("Wrong input file or output destination");
        }         
              
       //Jere we call the resized imagge
        EdgeDetector edge = new EdgeDetector("D:\\TegnerobotResized.jpg");
        
        BufferedImage after = edge.getBufferedImage();          
        int[][] magnitudeImg =edge.getMagnitudeArray();  
        
        int height = after.getHeight();
        int width = after.getWidth();       
              
        
        String outputString = "";
        
        //RobotClient client = new RobotClient("127.0.0.1", 12345);
       // client.connect();
      //  String text = null;
        //client.read(text);
        
        for (int y = 0; y < height; y++) 
        {
             RobotClient client = new RobotClient("127.0.0.1", 22345);
            //  client.wait(1000);
           // client.connect();
           //Thread.sleep(1000);
            while (!client.connect())
            {
                //Thread.sleep(1000);
            }
             String string1="OK";
             while(client.read()== string1)
                {
                }
         //   while ("OK".equals(client.read()))
           // {
            
            //}
            for (int x = 0; x < width; x++) 
            {
                  //public static String valueOf(int a){}
                
                outputString += String.valueOf(importImage.binaryTrunc(magnitudeImg[x][y]) + importImage.drawDelimiter(x, y, width, height));
                
                
                //Thread.sleep(1000);
                        
            }
             // Thread.sleep(1000);
            //wait for the 'OK' to send the next line
           
                                
               
                
           
                System.out.print(outputString );
                client.write(outputString);
                outputString ="";
            
                client.disconnect();
                
                
                
        }
        
        
        
        
        
        
        
        
       // client.disconnect();
        
     
        /*
        for (int i =0 ; i< outputString.length() ; i++)
            {
                System.out.print(outputString.charAt(i)+ "");
                Thread.sleep(1000);
            }
        */
      //  System.out.println(outputString);
      ///int i ;
      
     //    char oneline = outputString./70;
       // System.out.println(oneline);                
        //Connecting to the PLC and sending the string.
     
       // RobotClient client = new RobotClient("127.0.0.1", 12345);
        
        //client.connect();
        
      // for(int i=0; i<outputString.length() ;i++ )
               
         //  { 
        //   char oneline = outputString.chars()/70;
          // char line = outputString.charAt(i);
            //for(int j =0; j <width ;j++){
        //client.write(oneline);
          //  Thread.sleep(1000);}
            //}
        
      // client.disconnect();
    }

}
