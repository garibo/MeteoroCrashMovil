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
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.util.Random; 
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.midlet.MIDletStateChangeException;
public class Pantalla extends Canvas implements Runnable, CommandListener{
    
 static boolean permisoParaDisparar=false; 
 Image nave,roca,fondo,bala,marcador;
 protected String voz = "/sonidos/boom.wav"; 
 private boolean running = false; 
 private int sleepTime = 30; 
 private Player player;
 int chilakil=0;
 int indice =0;
 Command exit;
 Graphics g;
 Midlet p;
 
 
 

 
 /*******************************************
  * Variables para la nave
  *******************************************/
 int anchoOriginalNave=40;
 int altoOriginalNave=80;
 int anchoFinalNave=80;
 int altoFinalNave=80;
 int posicionNaveX=80;
 int posicionNaveY=235;
 
 
  /*******************************************
  * Variable para los meteoros
  *******************************************/
 
 int anchoOriginalMeteoro=40;
 int altoOriginalMeteoro=40;
 int anchoFinalMeteoro=80;
 int altoFinalMeteoro=80;
 int posicionMeteoroX=50;
 int posicionMeteoroY=-40;


 /****************************************************
  *  Variables para las balas
  ****************************************************/
 int anchoOriginalBala=0;
 int altoOriginaBala=0;
 int anchoFinalBala=11;
 int altoFinalBala=57;
 int posicionBalaX=posicionNaveX+40;
 int posicionBalaY=posicionNaveY+90;
 
 
  /****************************************************
  *  Variables para el marcador
  ****************************************************/

 int anchoOriginalMarcador=0;
 int altoOriginalMaracador=0;
 int anchoFinalMarcador=26;
 int altoFinalMarcador=142;
 int posicionMaracadorX=215;
 int posicionMaracadorY=0;
 

 
 /*Aqui comiwnza el metodo Constructor para nuestro programa :D */
 public Pantalla(Midlet p){
  this.p=p;
  try {
      
   nave=Image.createImage("/imagenes/NaveEspacial.png");
   anchoOriginalNave=nave.getWidth();
   altoOriginalNave=nave.getHeight();
   
   
   roca=Image.createImage("/imagenes/roka.png");
   anchoOriginalMeteoro=roca.getWidth();
   altoOriginalMeteoro=roca.getHeight();
   
   
   bala=Image.createImage("/imagenes/bala.png");
   anchoOriginalBala=bala.getWidth();
   altoOriginaBala=bala.getHeight();
   
   
   marcador=Image.createImage("/imagenesMarcador/"+indice+".png");
   anchoOriginalMarcador=marcador.getWidth();
   altoOriginalMaracador=marcador.getHeight();
   
   
   exit=new Command("Salir", Command.EXIT, 0);
   setCommandListener(this);
  } catch (IOException e) {
   e.printStackTrace();
  }
  
   running = true;
   Thread t = new Thread(this);
   t.start();
 }
 
 
 
 
 
 protected void paint(Graphics g) {
  this.g=g;
  
  Image thumb = Image.createImage(anchoFinalNave, altoFinalNave);
  Graphics g2 = thumb.getGraphics();
  
  Image thumb2= Image.createImage(anchoOriginalMeteoro-24,altoOriginalMeteoro-24);
  Graphics g3 = thumb2.getGraphics();
  
  Image thumb4= Image.createImage(anchoOriginalBala,altoOriginaBala);
  Graphics g5 = thumb4.getGraphics();
  
  Image thumb5= Image.createImage(anchoOriginalMarcador,altoOriginalMaracador);
  Graphics g6 = thumb5.getGraphics();
          

  
   for (int yM = 0; yM < altoFinalBala; yM++) {
   for (int xM = 0; xM < anchoFinalBala; xM++) {
   g5.setClip(xM, yM, 1, 1);
   int dx = xM * anchoOriginalBala /anchoFinalBala;
   int dy = yM* altoOriginaBala / altoFinalBala;
   g5.drawImage(bala, xM - dx, yM - dy, Graphics.LEFT | Graphics.TOP);
   }}
  
   
   for (int yB = 0; yB < altoFinalMarcador; yB++) {
   for (int xB = 0; xB < anchoFinalMarcador; xB++) {
   g6.setClip(xB, yB, 1, 1);
   int dx = xB * anchoOriginalMarcador /anchoFinalMarcador;
   int dy = yB* altoOriginalMaracador / altoFinalMarcador;
   g6.drawImage(marcador, xB - dx, yB - dy, Graphics.LEFT | Graphics.TOP);
   }}
   
   
   for (int y = 0; y < altoFinalNave; y++) {
   for (int x = 0; x < anchoFinalNave; x++) {
   g2.setClip(x, y, 1, 1);
   int dx = x * anchoOriginalNave / anchoFinalNave;
   int dy = y * altoOriginalNave / altoFinalNave;
   g2.drawImage(nave, x - dx, y - dy, Graphics.LEFT | Graphics.TOP);
   }}
  
 
  
  for (int y2 = 0; y2 < altoFinalMeteoro; y2++) {
  for (int x2 = 0; x2 < anchoFinalMeteoro; x2++) {
  g3.setClip(x2, y2, 1, 1);
  int dx = x2 * anchoOriginalMeteoro /anchoFinalMeteoro;
  int dy = y2* altoOriginalMeteoro / altoFinalMeteoro;
  g3.drawImage(roca, x2 - dx, y2 - dy, Graphics.LEFT | Graphics.TOP);
  }}
  
  
   
  
  Image immutableThumb4 = Image.createImage(thumb4);
  Image immutableThumb5 = Image.createImage(thumb5);
  Image immutableThumb = Image.createImage(thumb);
  Image immutableThumb2 = Image.createImage(thumb2);
  
  this.g.setColor(0, 0, 0);
  this.g.fillRect(0, 0, getWidth(), getHeight());
  
  this.g.drawImage(immutableThumb4, posicionBalaX,posicionBalaY, Graphics.LEFT | Graphics.TOP);
  this.g.drawImage(immutableThumb5, posicionMaracadorX,posicionMaracadorY, Graphics.LEFT | Graphics.TOP);
  this.g.drawImage(immutableThumb2, posicionMeteoroX,posicionMeteoroY, Graphics.LEFT | Graphics.TOP);
  this.g.drawImage(immutableThumb, posicionNaveX,posicionNaveY, Graphics.LEFT | Graphics.TOP);

 }
 
 
 
 
 public void commandAction(Command arg0, Displayable arg1) {
 Command c=(Command)arg0;
 if(c.equals(exit)){
 try {
 p.destroyApp(false);
 p.notifyDestroyed();
 } catch (MIDletStateChangeException e) {
 e.printStackTrace();
 }}}
 
 
 
 protected void keyPressed(int keyCode) {
  switch(keyCode){
      
      
  case Canvas.KEY_NUM5:
       posicionBalaY=posicionNaveY-20;
       posicionBalaX=posicionNaveX+40;
       permisoParaDisparar=true;    
  break;     
      
         
  case Canvas.KEY_NUM2:
       if(posicionNaveY>0){ posicionNaveY-=20;}       
  break; 
      
      
  case Canvas.KEY_NUM4:
   if(posicionNaveX>0){posicionNaveX-=20;}
  break;
      
      
  case Canvas.KEY_NUM6:
   if(posicionNaveX<getWidth()-altoFinalNave){posicionNaveX+=20;}
  break;
      
      
  case Canvas.KEY_NUM8:
   if(posicionNaveY<getHeight()-altoFinalNave){posicionNaveY+=20;}
  break;
  }
  repaint();
 }

   			

 /***************************************************
  * Metodo Run() aqui correremos nuestro objetos
  ***************************************************/
 
 public void run() {
 while (running) {
            
     
avanzar();
if(permisoParaDisparar==true)
{ balaAvanza();}


if(posicionMeteoroY>posicionBalaY)
{
if(((posicionBalaX+11)>posicionMeteoroX)&&((posicionBalaX+11)<(posicionMeteoroX+80))&&(posicionBalaX>posicionMeteoroX)&&(posicionBalaX<(posicionMeteoroX+80)))
{
int lugar; 
Random Posicionar = new Random(); 
lugar=Posicionar.nextInt(184);
posicionMeteoroY=-40;
posicionMeteoroX=lugar;
         
posicionBalaY=posicionNaveY+90;
posicionBalaX=posicionNaveX+40; 
permisoParaDisparar=false;
indice++;

  try {
  marcador=Image.createImage("/imagenesMarcador/"+indice+".png");
  } catch (IOException ex) {ex.printStackTrace();}  
  explotar();
  }
  }
           
           
  try {
  Thread.sleep(100);
  } catch (InterruptedException ie) {
  System.out.println("Thread exception");}
  }}
 
 
 
 public void avanzar()
 {
 posicionMeteoroY+=10;
 if(posicionMeteoroY>350)
 {
       
 int lugar; 
 Random Posicionar = new Random(); 
 lugar=Posicionar.nextInt(184);
 posicionMeteoroY=-40;
 posicionMeteoroX=lugar;
 }
 repaint();
 }
 
 
 
 public void balaAvanza()
 {
 posicionBalaY-=10;
 if(posicionBalaY<-60)
 {permisoParaDisparar=false;}
 repaint();}
 
 
 
 public void explotar() {
 try {playMedia( voz);}
 catch (Exception e) 
 { System.out.println(e.getMessage());}}
 
 
 
  private void playMedia(String u ) throws Exception {  
  try {   
  InputStream in = getClass().getResourceAsStream(voz );   
  player = Manager.createPlayer(in, "audio/x-wav");  
  player.start();  
  } catch (Exception e) {  
  System.err.println(e);  
  }}


}