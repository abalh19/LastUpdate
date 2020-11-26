/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.rd1.robotcomm;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ancla
 */
public class RobotClient {

    private String hostname;
    private int port;
    private Socket connection;
    private PrintWriter out;
    private ServerSocket Server;
    private BufferedReader  inputok;
    private DataInputStream dataInputStream;
   // private BufferedReader  inputok;
   // private BufferedReader inReader ;

    /**
     *
     * @param hostname Hostname of the robot
     * @param port Port of the robot
     */
    public RobotClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        
    }

    /**
     * Method which connects to the robot, using the parameters provided to the constructor.
     * @return 
     */
    public boolean connect() 
        {
          try {
                 connection = new Socket(hostname, port);
            
                 out = new PrintWriter(connection.getOutputStream(), true);
            
                 inputok = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                  //   inReader = new BufferedReader( new InputStreamReader(connection.getInputStream()));
                   return true;
             } 
          catch (IOException ex) 
            {
            Logger.getLogger(RobotClient.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error connecting to robot: " + ex.getMessage());
          
            return false;
            
            }
        
        
       }
    
   /* public void readytorecieve()
    {
        try
            {
            Server = new ServerSocket(12345);
            Socket Server1 = Server.accept();
            inputok = BufferedReader(new DataInputStream(Server1.getInputStream()));
            // = inputok.readLine();
            
            
             }
        catch (IOException e)
            {
             Logger.getLogger(RobotClient.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Error connecting to robot: " + e.getMessage());
            
            }
    
    }
    
    
    BufferedReader BufferedReader(DataInputStream dataInputStream)
        {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

   
    public boolean readytosend() throws IOException
        {      
          
              String  readline;
        readline = inputok.readLine();
                      String string1="OK";
              if (readline==string1)
                  {
                  return true;
                  }
              else{return false;}
        }
        
    */

    /**
     * This method is used to determine if a connection has been established to
     * the robot.
     *
     * @return COnnection state to see if connection is established (true) or
     * not (false).
     */
    public boolean isConnected() {
        return connection.isConnected();
    }
    
    
  /*   public boolean linereader() throws IOException
        {  String inputline = input.readLine();
          if (isConnected() && ( inputline.equals("OK") ))
              {
                  
          return true;
              }
          else
              { return false;}
        
        } 
    */
    
  /*  public boolean linereader() throws IOException
        { 
            try
                {
                String inputline = inputok.readLine();
                        inputline = inputline.equals("OK");
                        
                return true;
                }
            catch (IOException em)
                {
                    Logger.getLogger(RobotClient.class.getName()).log(Level.SEVERE, null, em);
                   System.out.println("Error connecting to robot: " + em.getMessage());
          
            return false;
            
                
                }
 
        }
    
    */
    
    
    

    /**
     * This method writes a message to the robot iff a connection to the robot
     * is established.
     *
     * @param message The message to write to the robot.
     */
    public void write(String message) {
        if (isConnected()) {
            out.print(message);
            out.flush();
        }
    }
    
    
 public String read() throws IOException  
       {
       
        String readLine = inputok.readLine();
        return "";
       }
          
     
     
    
    

    /**
     * Method to close connection to the robot.
     */
   public void disconnect() 
        {
        if (isConnected()) {
            try {
                connection.close();
            } catch (IOException ex) {
                Logger.getLogger(RobotClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public
    void write(char line)
        {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
    
}
