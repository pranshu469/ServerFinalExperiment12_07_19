package websocket.server;




import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.websocket.*;
import javax.websocket.server.*;



@ServerEndpoint("/server")
public class CustomEndPoint {
 
    
    static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
    
 static
 {
     Thread1 rateThread=new Thread1();
     rateThread.start();
     
    
 }

@OnMessage
 public void onMessage(Session session, String msg) 
 {
  try {   
        System.out.println("received msg "+msg+" from "+session.getId());
  } catch (Exception e) {
    System.out.println(e);
  }
 }
@OnOpen
 public void open(Session session) {
  queue.add(session);
  System.out.println("New session opened: "+session.getId());
 }
  @OnError
 public void error(Session session, Throwable t) {
  queue.remove(session);
  System.out.println("Error on session "+session.getId());  
 }
 @OnClose
 public void closedConnection(Session session) { 
  queue.remove(session);
  System.out.println("session closed: "+session.getId());
 }
 public static void sendAll(String msg) {
  try {
   /* Send the new rate to all open WebSocket sessions */  
   ArrayList<Session > closedSessions= new ArrayList<>();
   for (Session session : queue) {
    if(!session.isOpen())
    {
     System.out.println("Closed session: "+session.getId());
     closedSessions.add(session);
    }
    else
    {
 session.getBasicRemote().sendText(msg);
    }    
   }
   queue.removeAll(closedSessions);
   System.out.println("Sending "+msg+" to "+queue.size()+" clients");
  } catch (Throwable ex) {
    System.out.println(ex);
  }
 }
}