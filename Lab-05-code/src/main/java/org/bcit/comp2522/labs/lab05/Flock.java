package org.bcit.comp2522.labs.lab05;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PVector;


/**
 * The Flock manages a list of Boid objects.
 *
 * @author paul_bucci (based on Daniel Shiffman's Boids)
 * @version 1.0
 */
public class Flock extends AbstractObservable {
  ArrayList<IMoveable> boids; // An ArrayList for all the boids
  Color color;
  PVector position;
  Home home;
  float homeRadius;
  BoidRenderer boidRenderer;
  HomeRenderer homeRenderer;

  /**
   * Constructor for a Flock.
   *
   * @param window the processing window
   * @param generator a random number generator
   */
  public Flock(Window window, Random generator) {
    this.boids = new ArrayList<>(); // Initialize the ArrayList
    this.position = new PVector(
      generator.nextFloat() * window.width,
      generator.nextFloat() * window.height
    );
    this.color = new Color(
      64 + ((int) (generator.nextFloat() * 255 / 2f)),
      64 + ((int) (generator.nextFloat() * 255 / 2f)),
      64 + ((int) (generator.nextFloat() * 255 / 2f))
    );
    PVector velocity = new PVector(
        generator.nextFloat(),
        generator.nextFloat()
    );
    homeRadius = 10f;
    home = new Home(this.position, velocity, homeRadius, color);
    boidRenderer = new BoidRenderer();
    homeRenderer = new HomeRenderer();
  }

  /**
   * Check whether the Home is under the mouse when clicked.
   *
   * @param mouseX from window
   * @param mouseY from window
   */
  public void checkClick(float mouseX, float mouseY) {
    PVector mousePos = new PVector(mouseX, mouseY);
    PVector homePos = home.getPosition();
    if (mousePos.dist(homePos) <= home.getRadius()) {
      notifyObservers();
    }
  }


  /**
   * Render all IMoveables.
   *
   * @param window is processing window
   */
  public void run(Window window) {
    for (IMoveable b : boids) {
      b.run(window, boids);  // Passing the entire list of boids to each boid individually
      boidRenderer.render(window, b);
    }
    homeRenderer.render(window, home);
  }

  public ArrayList<IMoveable> getBoids() {
    return boids;
  }

  public void addBoid() {
    Boid b = new Boid(position.x, position.y, color);
    boids.add(b);
  }

  @Override
  public void registerObserver(AbstractObserver observer) {
    // TODO: add functionality to add a Boid from a different Flock
  }

  @Override
  public void unregisterObserver(AbstractObserver observer) {
    // TODO: add functionality to remove Boid from this Flock
  }

  @Override
  public void notifyObservers() {
    // TODO: add functionality to update Boid behaviour
    System.out.format("Currently in %s.\n", this.toString()); // stub
  }
}