package service;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Possibility {
	Integer[][] matrix;
	private boolean turnLeft, turnRight, turnUp, turnDown;
	private Direction actualDirection = Direction.UNDEF;
	ArrayList<SavePoint> savePosition = new ArrayList<SavePoint>();

	private enum Direction {
		LEFT, RIGHT, UP, DOWN, UNDEF
	}

	public Possibility(Integer[][] matrixRoad) {
		this.matrix = matrixRoad;
		init();
	}
	
	private void init() {
		turnDown = false;
		turnRight = false;
		turnUp = false;
		turnLeft = false;
	}

	public CopyOnWriteArrayList<Point> findWayToPoint(Point from, Point to)
			throws NullPointerException {
		CopyOnWriteArrayList<Point> route = new CopyOnWriteArrayList<Point>();

		Point next = new Point(from.x, from.y);
		route.add(next);
		do {
			next = findNext(next, to, false);
			if (next == null) {
				SavePoint lastSave = savePosition.get(savePosition.size()-1);
				int removeFrom = route.indexOf(lastSave.p);
				while (route.size()!=removeFrom+1){
					route.remove(removeFrom+1);
				}
				next = findNext(lastSave.p, to, true);
			}
			//	System.out.println(next.x+","+next.y);
				route.add(next);
			

		} while (next.x != to.x || next.y != to.y);

//		for (int i = 0; i < route.size(); i++) {
//			System.out.println(route.get(i).x + "," + route.get(i).y);
//		}
		return route;
	}

	private Point findNext(Point actualPosition, Point target, boolean isSavePoint) {
		int omitThePoint = 0;
		if (matrix[actualPosition.x][actualPosition.y] == omitThePoint)
			return null;
		if (!isSavePoint){
		init();
		possibleMovement(actualPosition);
		
		if (actualDirection == Direction.UP)
			turnDown = false;
		if (actualDirection == Direction.RIGHT)
			turnLeft = false;
		if (actualDirection == Direction.LEFT)
			turnRight = false;
		if (actualDirection == Direction.DOWN)
			turnUp = false;
		
		determineDirection(actualPosition, target);
		} else {
			ArrayList<Direction> tempBetterDirections = savePosition.get(savePosition.size() - 1).betterDirections;  
			ArrayList<Direction> tempBadDirections = savePosition.get(savePosition.size() - 1).badDirections;
			if(!tempBetterDirections.isEmpty()){
				actualDirection = tempBetterDirections.get(tempBetterDirections.size()-1);
				tempBetterDirections.remove(actualDirection);
				tempBadDirections.remove(actualDirection);
			} else {
				actualDirection = tempBadDirections.get(tempBadDirections.size()-1);
				tempBadDirections.remove(actualDirection);
			}
			if(tempBadDirections.isEmpty()&&tempBetterDirections.isEmpty()) {
				savePosition.remove(savePosition.size() - 1);
			}
		} 

		switch (actualDirection) {
		case RIGHT:
			return new Point(actualPosition.x, actualPosition.y + 1);
		case LEFT:
			return new Point(actualPosition.x, actualPosition.y - 1);
		case UP:
			return new Point(actualPosition.x - 1, actualPosition.y);
		case DOWN:
			return new Point(actualPosition.x + 1, actualPosition.y);
		default:
		}

		return null;
	}

	/*
	 * Checking movement possibilities in 4 directions LEFT RIGHT UP DOWN
	 * 
	 * @param valueOnMatrixWhoCanGo -> checking the visibility of the number in
	 * matrix
	 */
	private void possibleMovement(Point actualPosition) {
		/*
		 * We use nagation becouse we have a lot point in array
		 */
		int valueOnMatrixWhoCanGo = 0;
		if ((actualPosition.x > 0)
				&& (matrix[actualPosition.x - 1][actualPosition.y]) != valueOnMatrixWhoCanGo)
			turnUp = true;
		if ((actualPosition.x < matrix.length)
				&& (matrix[actualPosition.x + 1][actualPosition.y]) != valueOnMatrixWhoCanGo)
			turnDown = true;
		if ((actualPosition.y > 0)
				&& (matrix[actualPosition.x][actualPosition.y - 1]) != valueOnMatrixWhoCanGo)
			turnLeft = true;
		if ((actualPosition.y < matrix[0].length)
				&& (matrix[actualPosition.x][actualPosition.y + 1]) != valueOnMatrixWhoCanGo)
			turnRight = true;
	}

	private void determineDirection(Point from, Point to) {
		/*
		 * B®dziemy szalec
		 */

		ArrayList<Direction> tempBetterDirections = new ArrayList<Direction>();
		ArrayList<Direction> tempBadDirections = new ArrayList<Direction>();
		if (turnDown && (matrix[from.x+1][from.y]==1 || (matrix[from.x+1][from.y]!=1 && from.x+1==to.x && from.y==to.y))) {
			tempBadDirections.add(Direction.DOWN);
		}
		if (turnRight&& (matrix[from.x][from.y+1]==1 || (matrix[from.x][from.y+1]!=1 && from.x==to.x && from.y+1==to.y))) {
			tempBadDirections.add(Direction.RIGHT);
		}
		if (turnLeft&& (matrix[from.x][from.y-1]==1 || (matrix[from.x][from.y-1]!=1 && from.x==to.x && from.y-1==to.y))) {
			tempBadDirections.add(Direction.LEFT);
		}
		if (turnUp&& (matrix[from.x-1][from.y]==1 || (matrix[from.x-1][from.y]!=1 && from.x-1==to.x && from.y==to.y))) {
			tempBadDirections.add(Direction.UP);
		}
		
		
			if (from.x<to.x && turnDown && (matrix[from.x+1][from.y]==1 || (matrix[from.x+1][from.y]!=1 && from.x+1==to.x && from.y==to.y))) {
				tempBetterDirections.add(Direction.DOWN);
			}
			if (from.y<to.y && turnRight&& (matrix[from.x][from.y+1]==1 || (matrix[from.x][from.y+1]!=1 && from.x==to.x && from.y+1==to.y))) {
				tempBetterDirections.add(Direction.RIGHT);
			}
			if (from.y>to.y&& turnLeft&&(matrix[from.x][from.y-1]==1 || (matrix[from.x][from.y-1]!=1 && from.x==to.x && from.y-1==to.y))) {
				tempBetterDirections.add(Direction.LEFT);
			}
			if (from.x>to.x &&turnUp&& (matrix[from.x-1][from.y]==1 || (matrix[from.x-1][from.y]!=1 && from.x-1==to.x && from.y==to.y))) {
				tempBetterDirections.add(Direction.UP);
			}
		
		if(!tempBetterDirections.isEmpty()){
			if (tempBetterDirections.size() > 1) {
				actualDirection = tempBetterDirections.get(0);
				tempBetterDirections.remove(actualDirection);
				tempBadDirections.remove(actualDirection);
				savePosition.add(new SavePoint(tempBetterDirections,tempBadDirections, from));
			} else if(tempBetterDirections.size() == 1){
				actualDirection = tempBetterDirections.get(0);
				if (tempBadDirections.size()>1){
					tempBetterDirections.remove(actualDirection);
					tempBadDirections.remove(actualDirection);
					savePosition.add(new SavePoint(tempBetterDirections,tempBadDirections, from));
				}
			}
		} else {
			if (tempBadDirections.size() > 1) {
				actualDirection = tempBadDirections.get(0);
				tempBadDirections.remove(0);
				savePosition.add(new SavePoint(tempBetterDirections,tempBadDirections, from));
			} else if(tempBadDirections.size() == 1){
				actualDirection = tempBadDirections.get(0);
			}
		}

	}

	class SavePoint {
		ArrayList<Direction> betterDirections = new ArrayList<Direction>();
		ArrayList<Direction> badDirections = new ArrayList<Direction>();
		Point p;

		public SavePoint(ArrayList<Direction> oArrayList, ArrayList<Direction> wArrayList, Point point) {
			this.betterDirections = oArrayList;
			this.badDirections = wArrayList;
			this.p = point;
		}

	}

}
