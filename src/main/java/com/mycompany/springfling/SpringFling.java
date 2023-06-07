/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.springfling;

import java.util.Scanner;

/**
 *
 * @author jackvanmilligen
 */
public class SpringFling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //Get Inputs For Distance and Height
        System.out.println("Enter Distance (m):");
        double dist = sc.nextDouble();
        System.out.println("Enter Height (m)(posotive)");
        double height = sc.nextDouble();
        
        //set height to be negative
        height = height * -1;
        
        //Get input angle
        System.out.println("Enter Theta (Degree) ");
        double theta = sc.nextDouble();
        
        //Set our knowns
        double efficeny = 0.983;
        double mass = 0.0468;
        double kVal = 94.2;
                //31.3;
        
        //Estimate a velocity assuming accelertion of g
        double estimateVelo = findVelo(dist, -9.81, theta, height);
        
        //calculate the acceleration including air resistance using our estimated velocity
        double acceleration = acceleration(estimateVelo, mass);
       
        //Calculate the new velocity with the more accurate acceleration
        double velo = findVelo(dist, acceleration, theta, height);
        
        //go through steps of the calculation
        double kin = kineticEnergy(velo, mass);
        double elast = elasticEnergy(kin, efficeny);
        double x = xTensionFind(elast, kVal);
        
        //TEST PRINTS
//        System.out.println("Velo: " + velo);
//        System.out.println("KinEnergy" + kin);
//        System.out.println("ElastEnergy" + elast);
        
        //PRINT out our extension
        System.out.println("Extension: " + x + "m");
        
    }
    
    /**
     * Finds the extension needed for the spring
     * @param elast the elastic energy which we wamt to acheive
     * @param k The spring's spring constant
     * @return The required extension
     */
    public static double xTensionFind(double elast, double k){
        double x = elast * 2;
        x = x / k;
        x = Math.sqrt(x);
        return x;
    }
    
    /**
     * Calculates the elsatic energy produced
     * @param kin The amount of kinetic energy input
     * @param efficency the springs efficency
     * @return 
     */
    public static double elasticEnergy(double kin, double efficency){
        double elst = kin / efficency;
        return elst;
    }
    
    /**
     * Calculates the amount of kinetic energy 
     * @param velo the velocity being input
     * @param mass the spring's mass
     * @return  the amount of kinetic energy
     */
    public static double kineticEnergy(double velo, double mass){
        double kin = 0.5 * mass * velo * velo;
        return kin;
    }
    
    /**
     * Finds the velocity
     * @param distance The distance you want the spring to travel
     * @param acceleration the vertical acceleration
     * @param theta the angle of launch
     * @param height the height it is being launced from
     * @return the velocity needed to acheive this
     */
    public static double findVelo(double distance, double acceleration, double theta, double height){
        double num = distance * distance * acceleration;
        double denom = 2 * Math.cos(degToRad(theta)) * Math.cos(degToRad(theta)) * (height - distance * Math.tan(degToRad(theta)));
        num = num / denom;
        num = Math.sqrt(num);
        return num;
    }
    
    /**
     * Converts degrees to radians
     * @param degree number of degrees
     * @return number of radians
     */
    public static double degToRad(double degree){
        double out = degree * 2 * Math.PI;
        out = out / 360;
        return out;
    }
    
    /**
     * calculates acceleration with air resistance
     * @param mockSpeed the estimated speed of the spring
     * @param mass the mass of the spring
     * @return the verical acceleration
     */
    public static double acceleration(double mockSpeed, double mass){
        //Find air resistance force
        double force = 0.5 * 0.85 * 1.23 * getCircArea(0.015875) * mockSpeed * mockSpeed;
        
        //Find acceleration from F = ma
        double acceleration = force / mass;
        
        acceleration -= 9.81;
        return acceleration;
    }
    
    /**
     * gives the area of a circle
     * @param r radius of circle
     * @return  the area
     */
    public static double getCircArea(double r){
        double out = Math.PI * r * r;
        return out;
    }
}
 
