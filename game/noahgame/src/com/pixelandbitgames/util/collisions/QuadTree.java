package com.pixelandbitgames.util.collisions;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.pixelandbitgames.game.Handler;
import com.pixelandbitgames.rooms.Room;

/**
 * A lot of this code was stolen from this article: <br>
 * http://gamedevelopment.tutsplus.com/tutorials/quick-tip-use-quadtrees-to-detect-likely-collisions-in-2d-space--gamedev-374
 * <br>
 * <br>
 * created by chrislo27
 *
 */
public class QuadTree {
	 
	  	private int MAX_OBJECTS = 10;
	  	private int MAX_LEVELS = 5;
	 
	  	private int level;
	  	private ArrayList<Rectangle> entityBounds;
	  	private Rectangle bounds;
	  	private QuadTree[] nodes;
	 
	  	/*
	  	* Constructor
	  	*/
	  	public QuadTree(int pLevel, Rectangle rectangle) {
		  level = pLevel;
		  entityBounds = new ArrayList();
		  bounds = rectangle;
		  nodes = new QuadTree[4];
	  	}	
	  	public void clear() {
		   entityBounds.clear();
		 
		   for (int i = 0; i < nodes.length; i++) {
			   if (nodes[i] != null) {
				   nodes[i].clear();
				   nodes[i] = null;
			   }
		   }
	  	}
	   private void split() {
		   int subWidth = (int)(bounds.getWidth() / 2);
		   int subHeight = (int)(bounds.getHeight() / 2);
		   int x = (int)bounds.getX();
		   int y = (int)bounds.getY();
	   
		   nodes[0] = new QuadTree(level+1, new Rectangle(x + subWidth, y, subWidth, subHeight));
		   nodes[1] = new QuadTree(level+1, new Rectangle(x, y, subWidth, subHeight));
		   nodes[2] = new QuadTree(level+1, new Rectangle(x, y + subHeight, subWidth, subHeight));
		   nodes[3] = new QuadTree(level+1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight));
	   }
	   private int getIndex(Rectangle pRect) {
		   	int index = -1;
		   	double verticalMidpoint = bounds.getX() + (bounds.getWidth() / 2);
		   	double horizontalMidpoint = bounds.getY() + (bounds.getHeight() / 2);
		 
		   	// Object can completely fit within the top quadrants
		   	boolean topQuadrant = (pRect.getY() < horizontalMidpoint && pRect.getY() + pRect.getHeight() < horizontalMidpoint);
		   	// Object can completely fit within the bottom quadrants
		   	boolean bottomQuadrant = (pRect.getY() > horizontalMidpoint);
		 
		   	// Object can completely fit within the left quadrants
		   	if (pRect.getX() < verticalMidpoint && pRect.getX() + pRect.getWidth() < verticalMidpoint) {
		   		if (topQuadrant) {
		   			index = 1;
		   		}
		   		else if (bottomQuadrant) {
		   			index = 2;
		   		}
		   	}
		   	// Object can completely fit within the right quadrants
		    else if (pRect.getX() > verticalMidpoint) {
		    	if (topQuadrant) {
		    		index = 0;
		    	}
		    	else if (bottomQuadrant) {
		    		index = 3;
		    	}
		    }
		 
		   	return index;
		 }
	   public void insert(Rectangle pRect) {
		   if (nodes[0] != null) {
		     int index = getIndex(pRect);
		 
		     if (index != -1) {
		       nodes[index].insert(pRect);
		 
		       return;
		     }
		   }
		 
		   entityBounds.add(pRect);
		 
		   if (entityBounds.size() > MAX_OBJECTS && level < MAX_LEVELS) {
		      if (nodes[0] == null) { 
		         split(); 
		      }
		 
		     int i = 0;
		     while (i < entityBounds.size()) {
		       int index = getIndex(entityBounds.get(i));
		       if (index != -1) {
		         nodes[index].insert(entityBounds.remove(i));
		       }
		       else {
		         i++;
		       }
		     }
		   }
		 }
	   	/*
	   	 * Return all objects that could collide with the given object
	   	 */
	   	 public List retrieve(List returnObjects, Rectangle pRect) {
	   	   int index = getIndex(pRect);
	   	   if (index != -1 && nodes[0] != null) {
	   	     nodes[index].retrieve(returnObjects, pRect);
	   	   }
	   	 
	   	   returnObjects.addAll(entityBounds);
	   	 
	   	   return returnObjects;
	   	 }
	   	
}