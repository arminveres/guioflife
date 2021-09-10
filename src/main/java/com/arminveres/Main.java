package com.arminveres;


// Model - Model represents an object or JAVA POJO carrying data.
// It can also have logic to update controller if its data changes.

// View - View represents the visualization of the data that model contains.

// Controller - Controller acts on both model and view.
// It controls the data flow into model object and updates the view whenever data changes.
// It keeps view and model separate.


// The Model will be a Class possessing


// uses the controller

public class Main {
    public static void main(String[] args) throws InterruptedException {

        GameOfLifeModel model = new GameOfLifeModel();

        GameOfLife view = new GameOfLife();

        GameOfLifeController controller = new GameOfLifeController(model, view);

        controller.updateView();

    }
}
