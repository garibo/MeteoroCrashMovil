/* 
  
* ::::::::::Video Juego:::::::::::::: 
* Materia: Topicos Avanzados de programacion 
* Nombre del juego: Meteoro Crash 
* Profesora: Araceli Velasquez Gutierrez 
* Autores: 
*  
* Marco Antonio Villanueva Guzman 
* Carlos Hector cruz Lopez 
* Kevin Esteban Garibo Bracamontes 
*  
* copy@Left @ITLAC 
 */


import java.io.InputStream;
import javax.microedition.lcdui.Display;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.midlet.MIDlet; 
import javax.microedition.midlet.MIDletStateChangeException;
public class Midlet extends MIDlet {
    
 Display display;
 Pantalla pantalla;
 protected String voz = "/sonidos/marcha.wav"; 
 private Player player;
 
 
 public Midlet() {
 
 display=Display.getDisplay(this);
 pantalla=new Pantalla(this);
 }
 
 protected void destroyApp(boolean arg0) throws MIDletStateChangeException { }
 protected void pauseApp() { }
 protected void startApp() throws MIDletStateChangeException {    
 display.setCurrent(pantalla);
 hablar();
 }
 
 
 public void hablar() {
 try {playMedia( voz);}
 catch (Exception e) 
 { System.out.println(e.getMessage());}}
 
 
 
  private void playMedia(String u ) throws Exception {  
  try {   
  InputStream in = getClass().getResourceAsStream(voz );   
  player = Manager.createPlayer(in, "audio/x-wav");  
  player.start();  
  } catch (Exception e) {  
  System.err.println(e);}}
  
  
  
}