/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainapp;

import controller.WorkerHandler;

/**
 *
 * @author Admin
 */
public class WorkerManager {
    
    public static void main(String[] args) {
        WorkerHandler workerHandler = new WorkerHandler();
        workerHandler.execute();
    }
    
}
