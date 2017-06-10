/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Splash;

import Ventanas.Inicio;

/**
 *
 * @author JIGA_
 */
public class SplashScreen {
    public static void main(String[] args) {
        Splash splash = new Splash();
        splash.setVisible(true);
        Inicio inicio = new Inicio();
        
        
        try {
            for (int i = 0; i<=100; i++){
                Thread.sleep(40);
                splash.loadingnum.setText(Integer.toString(i)+"%");
                splash.loadingbar.setValue(i);
                if(i==100)
                {
                    splash.setVisible(false);
                    inicio.setVisible(true);
                }
                
            }
        }catch (Exception e){
            
        }
                
    }
}
