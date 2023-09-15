import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) {
		new CircuitTracer(args);
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private static void printUsage() {
		//TODO: print out clear usage instructions when there are problems with
		// any command line args
		// See https://en.wikipedia.org/wiki/Usage_message for format and content guidance
		System.out.println("Use -s for stack, -q for queue, -c for console with the file name. GUI not supported.");
	}
	
	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 * @throws FileNotFoundException 
	 * 
	 */
	public CircuitTracer(String[] args) {
		if (args.length != 3) {
			printUsage();
			return;
		}
		if (!args[0].equals("-s") && !args[0].equals("-q")) {
			printUsage();
			return;
		}
		if (args[1].equals("-g")) {
			System.out.println("GUI is not supported");
			return;
		}
		else if (!args[1].equals("-c")) {
			printUsage();
			return; 
		}
		ArrayList<TraceState> bestPaths = new ArrayList<TraceState>();
				
		CircuitBoard board;
		try {
			board = new CircuitBoard(args[2]);
			Storage<TraceState> stateStore;
			if (args[0].equals("-s")) {
				stateStore = new Storage<TraceState>(Storage.DataStructure.stack);
			}
			else if (args[0].equals("-q")){
				stateStore =  new Storage<TraceState>(Storage.DataStructure.queue);
			}else {
				printUsage();
				return;
			}
			Point start = board.getStartingPoint();
			if (board.isOpen((int)start.getX(), (int)start.getY() + 1)) {
				stateStore.store(new TraceState(board, (int)start.getX(), (int)start.getY() + 1));
			}
			if (board.isOpen((int)start.getX() + 1, (int)start.getY())) {
				stateStore.store(new TraceState(board, (int)start.getX() + 1, (int)start.getY()));
			}
			if (board.isOpen((int)start.getX(), (int)start.getY() - 1)) {
				stateStore.store(new TraceState(board, (int)start.getX(), (int)start.getY() - 1));
			}
			if (board.isOpen((int)start.getX() - 1, (int)start.getY())) {
				stateStore.store(new TraceState(board, (int)start.getX() - 1, (int)start.getY()));
			}
			while (!stateStore.isEmpty()) {
				TraceState trace = stateStore.retrieve();
				if (trace.isComplete()) {
						if (bestPaths.isEmpty() || trace.pathLength() == bestPaths.get(0).pathLength()) {
							bestPaths.add(trace);
						}else if (trace.pathLength() < bestPaths.get(0).pathLength()) {
							bestPaths.clear();
							bestPaths.add(trace);
						}
				}else {
					if (trace.isOpen(trace.getRow(), trace.getCol() + 1)) {
						stateStore.store(new TraceState(trace, trace.getRow(), trace.getCol() + 1));
					}
					if (trace.isOpen(trace.getRow() + 1, trace.getCol())) {
						stateStore.store(new TraceState(trace, trace.getRow() + 1, trace.getCol()));
					}
					if (trace.isOpen(trace.getRow(), trace.getCol() - 1)) {
						stateStore.store(new TraceState(trace, trace.getRow(), trace.getCol() - 1));
					}
					if (trace.isOpen(trace.getRow() - 1, trace.getCol())) {
						stateStore.store(new TraceState(trace, trace.getRow() - 1, trace.getCol()));
					}
				}
			}
			for (TraceState t : bestPaths) {
				System.out.println(t.getBoard());
			}
		}
		catch (InvalidFileFormatException e) {
			System.out.println("InvalidFileFormatException");
		}
		catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		}
	}
} // class CircuitTracer
